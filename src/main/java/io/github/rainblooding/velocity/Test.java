package io.github.rainblooding.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Test {


    public static void main(String[] args) throws IOException {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //创建Velocity容器
        VelocityContext context = new VelocityContext();
        context.put("name", "io.github.rainblood.controller.ktc");
        //加载模板
        Template tpl = Velocity.getTemplate("vms/demo1.vm", "UTF-8");

        FileWriter fw  = new FileWriter("E:\\item\\java\\Project_Exercises\\src\\main\\resources\\vms\\demo1.html");
        //合并数据到模板
        tpl.merge(context, fw);

        //释放资源
        fw.close();
    }
}
