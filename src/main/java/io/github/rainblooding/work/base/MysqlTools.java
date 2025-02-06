package io.github.rainblooding.work.base;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class MysqlTools {

    enum Db {
        EF,
        ENR,
        EDC,
        EXB
    }

    // MySQL数据库连接信息
//        String url = "jdbc:mysql://192.168.0.64:3306"; // 请根据实际情况修改URL
    static List<String> urls = new ArrayList<>(
            Arrays.asList(
                    "jdbc:mysql://192.168.0.64:3306",
                    "jdbc:mysql://192.168.0.46:3306"
            )
    ); // 请根据实际情况修改URL
    static String user = "root"; // MySQL用户名
    static String password = "hfwy_3185558"; // MySQL密码

    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    static {
        // 加载JDBC驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init(String url, String user, String password) throws SQLException {
        // 建立数据库连接 查询数据库
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    public static void close() {
        // 关闭资源
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isDb(Db db, String dbName) {
        List<String> dbNameList;
        if (db == Db.EF) {
            dbNameList = new ArrayList<>(Arrays.asList("finance"));
        } else if (db == Db.ENR) {
            dbNameList = new ArrayList<>(Arrays.asList("wlbx", "enr", "bbyxy_2024"));
        } else if(db == Db.EDC){
            dbNameList = new ArrayList<>(Arrays.asList("data_center", "edc"));
        } else if (db == Db.EXB) {
            dbNameList = new ArrayList<>(Arrays.asList("exb"));
        } else {
            return false;
        }

        for (String dbName2 : dbNameList) {
            if (dbName.contains(dbName2)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
//        query(Db.EF);
        getMenuByTable("hsxy_pt_20240108", "ENR151546");
    }

    public static String getParamByTable(String table, String code) throws SQLException {
        return getMenu(urls.get(0) + "/" + table + "?useUnicode=true&characterEncoding=utf8&useSSL=false&autoReconnect=true&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull", code);
    }

    public static String getParam(String url, String code) throws SQLException {
        String result = "";
        // 建立数据库连接 查询数据库
        init(url, user, password);

        return result;
    }

    public static String getMenuByTable(String table, String mkid) throws SQLException {
        return getMenu(urls.get(0) + "/" + table + "?useUnicode=true&characterEncoding=utf8&useSSL=false&autoReconnect=true&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull", mkid);
    }

    public static String getMenu(String url, String mkid) throws SQLException {
        String result = "";
        // 建立数据库连接 查询数据库
        init(url, user, password);
        String sql = "select * from pt_mk where V_MKID='" + mkid + "'";
        List<Map<String, Object>> mkList = query(sql);
        if (mkList == null || mkList.size() == 0) return "";
        List<Map<String, Object>> gnList = new ArrayList<>();
        List<Map<String, Object>> czList = new ArrayList<>();
        for (Map<String, Object> mk : mkList) {
            System.out.println(mk.get("V_GNID"));
            sql = "select * from pt_gnbm where V_GNID='" + mk.get("V_GNID") + "'";
            List<Map<String, Object>> dataList = query(sql);
            if (dataList != null && dataList.size() > 0) {
                gnList.addAll(dataList);
            }
        }
        for (Map<String, Object> gn : gnList) {
            sql = "select * from pt_czqx where v_gnid='" + gn.get("V_GNID") + "'";
            List<Map<String, Object>> dataList = query(sql);
            if (dataList != null && dataList.size() > 0) {
                czList.addAll(dataList);
            }
        }

//        System.out.println("--------------------pt_mk---------------------------");
        String cloumns = "V_MKID,V_XTFLID,V_MKMC,I_XH,C_YZ,C_XS,V_KZ1,V_KZ2,C_YX,V_SJMKID,V_BZ,I_JS,V_GNID,I_CALLTYPE,V_JGID,V_REDIRECT,V_ROUTE,V_COMPONENT,V_COMPONENT_NAME,I_CACHE";
        sql = "INSERT INTO `pt_mk` (`V_MKID`, `V_XTFLID`, `V_MKMC`, `I_XH`, `C_YZ`, `C_XS`, `V_KZ1`, `V_KZ2`, `C_YX`, `V_SJMKID`, `V_BZ`, `I_JS`, `V_GNID`, `I_CALLTYPE`, `V_JGID`, `V_REDIRECT`, `V_ROUTE`, `V_COMPONENT`, `V_COMPONENT_NAME`, `I_CACHE`) VALUES " +
                "(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);\n";
        for (Map<String, Object> mk : mkList) {
            String[] clArray = cloumns.split(",");
            String[] values = new String[clArray.length];
            for (int i = 0; i < clArray.length; i++) {
                if (clArray[i].equals("V_JGID")) {
                    values[i] = ":as_jgid";
                } else
                {
                    Object value = mk.get(clArray[i]);
                    if (value == null) {
                        values[i] = "NULL";
                    }else if(value instanceof Integer) {
                        values[i] = "" + value + "";
                    } else {
                        values[i] = "'" + value + "'";
                    }
                }
            }
            result += String.format(sql, values);
//            System.out.printf(sql, values);
        }

//        System.out.println("\n\n--------------------pt_gnbm---------------------------");
        cloumns = "V_GNID,V_XTFLID,V_GNMC,V_YMLJ,V_CK,V_LC,I_MH,V_JGID,V_REDIRECT,V_ROUTE,V_COMPONENT,V_COMPONENT_NAME,I_CACHE";
        sql = "INSERT INTO `pt_gnbm` (`V_GNID`, `V_XTFLID`, `V_GNMC`, `V_YMLJ`, `V_CK`, `V_LC`, `I_MH`, `V_JGID`, `V_REDIRECT`, `V_ROUTE`, `V_COMPONENT`, `V_COMPONENT_NAME`, `I_CACHE`) VALUES " +
                "(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);\n";
        for (Map<String, Object> gn : gnList) {
            String[] clArray = cloumns.split(",");
            String[] values = new String[clArray.length];
            for (int i = 0; i < clArray.length; i++) {
                if (clArray[i].equals("V_JGID")) {
                    values[i] = ":as_jgid";
                } else
                {
                    Object value = gn.get(clArray[i]);
                    if (value == null) {
                        values[i] = "NULL";
                    }else if(value instanceof Integer) {
                        values[i] = "" + value + "";
                    } else {
                        values[i] = "'" + value + "'";
                    }
                }

            }
            result += String.format(sql, values);
//            System.out.printf(sql, values);
        }


//        System.out.println("\n\n--------------------pt_czqx---------------------------");
        cloumns = "V_CZQXID,V_XTFLID,V_GNID,V_QXMC,V_QXWM,V_WEBICO,V_BZ,C_YX,V_EVENT,V_TEXT,V_TIP,I_CALLTYPE,V_JGID,I_XH";
        sql = "INSERT INTO `pt_czqx` (`V_CZQXID`, `V_XTFLID`, `V_GNID`, `V_QXMC`, `V_QXWM`, `V_WEBICO`, `V_BZ`, `C_YX`, `V_EVENT`, `V_TEXT`, `V_TIP`, `I_CALLTYPE`, `V_JGID`, `I_XH`) VALUES " +
                "(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);\n";
        for (Map<String, Object> cz : czList) {
            String[] clArray = cloumns.split(",");
            String[] values = new String[clArray.length];
            for (int i = 0; i < clArray.length; i++) {
                if (clArray[i].equals("V_JGID")) {
                    values[i] = ":as_jgid";
                } else
                {
                    Object value = cz.get(clArray[i]);
                    if (value == null) {
                        values[i] = "NULL";
                    }else if(value instanceof Integer) {
                        values[i] = "" + value + "";
                    } else {
                        values[i] = "'" + value + "'";
                    }
                }
            }
//            System.out.printf(sql, values);
            result += String.format(sql, values);
        }

        close();
        return result;
    }

    public static void query(Db db) throws Exception {
        for (String url : urls) {
            query(url, user, password, db, "select count(0) from cw_make_detail_tmpl");
        }
    }

    public static void query(String url, String user, String password, Db db, String sql)throws Exception  {

        // 建立数据库连接 查询数据库
        init(url, user, password);
        String show = "SHOW DATABASES";
        resultSet = statement.executeQuery(show);

        List<String> databaseList = new ArrayList<>();
        while (resultSet.next()) {
            databaseList.add(resultSet.getString(1));
        }

        for (String database : databaseList) {
            if (!isDb(db, database)) {
                continue;
            }
            // 使用当前数据库
            statement.execute("USE `" + database + "`");
            try {
                Integer count = queryObj(sql);
                if (count > 0) {
                    System.out.println("Tables in database: " + database);
                    System.out.println("------------------------");
                    System.out.println("count: " + count);
                }
            } catch (SQLException e) {
                continue;
            }
        }
    }

    public static <T> T queryObj(String sql) throws SQLException {
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        if (metaData.getColumnCount() > 1) {
            throw new RuntimeException("sql语句错误，返回结果为多字段");
        }

        int i = 0;
        T ret = null;
        while (resultSet.next()) {
            ret = (T) resultSet.getObject(1);
            i++;
        }
        if (i > 1) {
            throw new RuntimeException("sql语句错误，返回结果为多行");
        }
        return ret;
    }


    public static List<Map<String, Object>> query(String sql) throws SQLException {
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> data;
        while (resultSet.next()) {
            data = new HashMap<>();

            for (int i = 0; i < metaData.getColumnCount(); i++) {
                data.put(metaData.getColumnLabel(i + 1), resultSet.getObject(metaData.getColumnLabel(i + 1)));
            }
            dataList.add(data);
        }
        return dataList;
    }


    public static void createTable(JTextArea textArea, String sql) throws Exception {
        long start = System.currentTimeMillis();

        executeSql(textArea, Db.EF, sql);
        textArea.append(String.format("------------执行结束，耗时：%d秒-----------", (System.currentTimeMillis() - start) / 1000));
        textArea.append("\n");
    }


    private static List<String> tableList = new ArrayList<>();

    public static void executeSql(JTextArea textArea, Db db, String... sqlArrays) throws SQLException {
        for (String url : urls) {
            textArea.append("url: " + url);
            textArea.append("\n");
            textArea.append("------------------------");
            textArea.append("\n");
            executeSql(textArea, url, user, password, db, sqlArrays);
        }
    }

    public static void executeSql( JTextArea textArea, String url, String user, String password, Db db, String... sqlArrays) throws SQLException {

        try {
            // 建立数据库连接 查询数据库
            init(url, user, password);
            String sql = "SHOW DATABASES";
            resultSet = statement.executeQuery(sql);

            List<String> databaseList = new ArrayList<>();
            while (resultSet.next()) {
                databaseList.add(resultSet.getString(1));
            }
            for (String database : databaseList) {
                if (!isDb(db, database)) {
                    continue;
                }
                textArea.append("Tables in database: " + database);
                textArea.append("\n");
                textArea.append("------------------------" );
                textArea.append("\n");
                // 使用当前数据库
                statement.execute("USE `" + database + "`");
                connection.setAutoCommit(false);
                try {
                    for (String sqlArray : sqlArrays) {
                            statement.execute(sqlArray);
                    }
                    tableList.add(database);
                } catch (Exception e) {
                    e.printStackTrace();
                    connection.rollback();
                } finally {
                    connection.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
