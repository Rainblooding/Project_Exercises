package io.github.rainblooding.find;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 搜索指定文件
 * 在java中使用File代表文件或目录
 * 1.用listRoots方法获取根目录
 * 2.如果一个file是文件的话就直接与我们要匹配的字符校验下
 * 3.如果是目录先匹配，在用listFiles获取目录下的文件继续进行遍历
 *
 * @author rainblooding
 */
public class FindFile {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            args = new String[]{"FileVerify.java"};
        }
        List<String> targetList = new ArrayList<>();
        File[] roots = File.listRoots();
        final String filename = args[0];
        for (File root : roots) {
            targetList.addAll(dfs(root, (file -> filename.equals(file.getName()))));
        }
        System.out.println("匹配的文件或目录：");
        targetList.forEach(System.out::println);
    }

    public static List<String> dfs(File file, FileVerify verify) {
        List<String> targetList = new ArrayList<>();
        if (file.isFile()) {
            return targetList;
        }
        Stack<File> stack = new Stack<>();
        stack.push(file);
        while(!stack.isEmpty()) {
            File cur = stack.pop();
            loading(cur.getAbsolutePath());
            if (cur.isFile() && verify.verify(cur)) {
                targetList.add(cur.getAbsolutePath());
            } else if(cur.isDirectory()) {
                if (verify.verify(cur)) {
                    targetList.add(cur.getAbsolutePath());
                }
                File[] files = cur.listFiles();
                if (files != null && files.length > 0) {
                    stack.addAll(Arrays.asList(cur.listFiles()));
                }
            }
        }
        return targetList;
    }

    public static int loadInt = 0;
    public static void loading(String filePath) {
        String[] load = new String[]{"\\", "|", "/", "-"};
        loadInt++;
        loadInt = loadInt % 4;
        System.out.print(load[loadInt] + " 正在执行：" + filePath + "\r");
    }
}
