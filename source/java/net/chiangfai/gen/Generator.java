package net.chiangfai.gen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;
import net.chiangfai.gen.config.ParamBean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chiangfai
 * <p>
 *     https://baomidou.oschina.io/mybatis-plus-doc/#/generate-code
 * </p>
 **/
@Slf4j
public class Generator {

    public static void start(ParamBean paramBean, String moduleName, String ...tables) {
        long startTime = System.currentTimeMillis();
        new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(paramBean.getOutputDir())
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(false)// 不需要ActiveRecord特性的请改为false
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        //.setKotlin(true) 是否生成 kotlin 代码
                        .setAuthor(paramBean.getAuthor())
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        //.setMapperName("%sMapper")
                        //.setXmlName("%sMapper")
                        //.setServiceName("%sService")
                        //.setServiceImplName("%sServiceImpl")
                        //.setControllerName("%sController")
                        //.setEntityName("%s")
                        .setServiceName("%sService")
                        .setServiceImplName(paramBean.getGenInterface() ? "%sServiceImp" : "%sService")
                        .setOpen(false)// 生成后打开文件夹

        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL)
                        /*.setTypeConvert(new MySqlTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                                return super.processTypeConvert(globalConfig, fieldType);
                            }
                        })*/
                        .setDriverName(paramBean.getDriverName())
                        .setUrl(paramBean.getUrl())
                        .setUsername(paramBean.getUsername())
                        .setPassword(paramBean.getPassword())
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        .setCapitalMode(false)// 全局大写命名 ORACLE 注意
                        .setTablePrefix("sys_")// 移除表前缀
                        // 表名生成策略
                        .setNaming(NamingStrategy.underline_to_camel)
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setInclude(tables)// 需要生成的表, null表示所有表需要生成
                        //.setExclude(tables)// 排除生成的表
                        .setTableFillList(paramBean.getTableFillList())// 公共字段填充
                        // 自定义 mapper 父类
                        // .setSuperMapperClass("com.baomidou.demo.TestMapper")
                        // 自定义 service 父类
                        // .setSuperServiceClass("com.baomidou.demo.TestService")
                        // 自定义 service 实现类父类
                        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                        // 自定义 controller 父类
                        .setSuperControllerClass("net.chiangfai.core.base.BaseController")
                        .setEntityLombokModel(true)
        ).setPackageInfo(
                new PackageConfig()
                        .setParent(paramBean.getParentPkg())// 包路径
                        .setModuleName(moduleName) // 模块名，包路径下创建sys包
                        .setController("web")// 控制器包名，默认 controller
                        //.setEntity("model")// 默认entity
                        //.setMapper("mapper")
                        .setService("service")
                        //.setServiceImpl("service.impl")
                        .setServiceImpl(paramBean.getGenInterface() ? "service.impl" : "service")
                        .setXml("mapper.mapping")
        ).setCfg(
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }
                /*.setFileOutConfigList(Collections.singletonList(new FileOutConfig() {
                    // 调整 xml 生成目录演示
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        log.info(getTemplatePath() + tableInfo.getEntityName());
                        return null;
                    }
                }))*/
        ).setTemplate(
                new TemplateConfig()
                        //.setController(null)
                        .setService(paramBean.getGenInterface() ? ConstVal.TEMPLATE_SERVICE : null)
                        .setServiceImpl(paramBean.getGenInterface() ? ConstVal.TEMPLATE_SERVICE_IMPL : net.chiangfai.gen.config.ConstVal.TEMPLATE_SERVICE_IMPL)
        ).execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        //System.err.println(mpg.getCfg().getMap().get("abc"));
        log.info("Keep time：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
        Generator.start(new ParamBean(), "sample", "test");
    }
}
