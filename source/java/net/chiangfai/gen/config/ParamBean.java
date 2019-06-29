package net.chiangfai.gen.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chiangfai
 **/
@Data
public class ParamBean {

    //代码生成注释 @author
    private String author = "chiangfai";
    //模块名
    private String moduleName = "repository";
    //代码生成输出目录
    private String outputDir = System.getProperty("user.dir") + "/projects/" + getModuleName() + "/source/java";
    //是否生成接口
    private Boolean genInterface = false;

    //数据源属性配置
    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3308/repository?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=CTT";
    private String username = "root";
    private String password = "password";

    //填充字段
    private List<TableFill> tableFillList = new ArrayList<TableFill>() {{
        add(new TableFill("CREATE_TIME", FieldFill.INSERT));
        add(new TableFill("UPDATE_TIME", FieldFill.UPDATE));
        add(new TableFill("CREATE_USER", FieldFill.INSERT));
        add(new TableFill("UPDATE_USER", FieldFill.UPDATE));
    }};

    //代码生成的类的父包名称
    private String parentPkg = "net.chiangfai.repo.module";
}
