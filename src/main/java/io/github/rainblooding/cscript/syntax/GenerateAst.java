package io.github.rainblooding.cscript.syntax;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GenerateAst {

    public static void main(String[] args) throws IOException {
        defineStmtAst();
        defineExprAst();
    }

    public static void defineExprAst() throws IOException {
        String baseName = "Expr";
        String packageName = "io.github.rainblooding.cscript.syntax";
        String targetPath = "E:\\item\\java\\Project_Exercises\\src\\main\\java\\io\\github\\rainblooding\\cscript\\syntax\\Expr.java";
        defineAst(packageName, baseName, Arrays.asList(
                "Binary   : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal  : Object value",
                "Unary    : Token operator, Expr right",
                "Variable : Token name"
        ), targetPath);
    }

    public static void defineStmtAst() throws IOException {
        String baseName = "Stmt";
        String packageName = "io.github.rainblooding.cscript.syntax";
        String targetPath = "E:\\item\\java\\Project_Exercises\\src\\main\\java\\io\\github\\rainblooding\\cscript\\syntax\\Stmt.java";
        defineAst(packageName, baseName, Arrays.asList(
                "Expression : Expr expression",
                "Print      : Expr expression",
                "Var        : Token name, Expr initializer"
        ), targetPath);
    }

    public static void defineAst(String packageName,String baseName, List<String> list, String targetPath) throws IOException {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //创建Velocity容器
        VelocityContext context = new VelocityContext();

        context.put("package_name", packageName);
        context.put("base_name", baseName);

        Map<String, Object> type;
        List<Map<String, Object>> types = new ArrayList<>();
        for (String data : list) {
            type = new HashMap<>();
            String[] classNameAndField = data.split(":");
            type.put("name", classNameAndField[0].trim());

            String[] fieldArray = classNameAndField[1].split(",");

            Map<String, String> field;
            List<Map<String, String>> fields = new ArrayList<>();
            for (String fieldStr : fieldArray) {
                field = new HashMap<>();
                String[] fieldNameAndType = fieldStr.trim().split(" ");
                field.put("type", fieldNameAndType[0]);
                field.put("name", fieldNameAndType[1]);
                fields.add(field);
            }
            type.put("fields", fields);
            types.add(type);
        }

        context.put("types", types);



        //加载模板
        Template tpl = Velocity.getTemplate("cscript/Ast.vm", "UTF-8");

        FileWriter fw  = new FileWriter(targetPath);
        //合并数据到模板
        tpl.merge(context, fw);



        //释放资源
        fw.close();
    }
}
