package cn.jimjim.framework.datasource.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author jim
 */
public class MybatisGeneratorUtil {

    public static void main(String[] args) {
        MybatisGeneratorInfo mybatisGeneratorInfo = new MybatisGeneratorInfo();
        mybatisGeneratorInfo.setOutputDir("./target/generator".replace("/", File.separator));
        mybatisGeneratorInfo.setDataSourceUserName("root");
        mybatisGeneratorInfo.setDataSourcePassword("123456");
        mybatisGeneratorInfo.setDataSourceUrl("jdbc:mysql://127.0.0.1:3306/jimjim-demo");
//        mybatisGeneratorInfo.setTablePrefix(new String[]{"opt_"});
        mybatisGeneratorInfo.setPackageName("cn.jimjim");
        MybatisGeneratorUtil.generate(mybatisGeneratorInfo);
    }

    public static void generate(MybatisGeneratorInfo mybatisGeneratorObj) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl(mybatisGeneratorObj.getDataSourceUrl())
                .setUsername(mybatisGeneratorObj.getDataSourceUserName())
                .setPassword(mybatisGeneratorObj.getDataSourcePassword())
                .setDriverName("com.mysql.cj.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig()
                .setRestControllerStyle(true)
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(mybatisGeneratorObj.getTablePrefix())
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(mybatisGeneratorObj.getIncludeTables())
                .setExclude(mybatisGeneratorObj.getExcludeTables())
                .setVersionFieldName("version")
                .setTableFillList(Arrays.asList(
                        new TableFill("version", FieldFill.INSERT),
                        new TableFill("status", FieldFill.INSERT),
                        new TableFill("created", FieldFill.INSERT),
                        new TableFill("create_time", FieldFill.INSERT),
                        new TableFill("updated", FieldFill.INSERT_UPDATE),
                        new TableFill("update_time", FieldFill.INSERT_UPDATE)));

        GlobalConfig config = new GlobalConfig()
                .setActiveRecord(false)
                .setEnableCache(false)
                .setAuthor("jimjim")
                .setServiceName("%sService")
                .setEntityName("%sDO")
                .setOutputDir(mybatisGeneratorObj.getOutputDir())
                .setFileOverride(true)
                .setBaseResultMap(true);

        // 自定义新模板
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                this.setMap(new HashMap<String, Object>(16) {{
                    put("criteriaPackageName".substring(0, 1).toUpperCase(), mybatisGeneratorObj.getPackageName() + ".api");
                }});
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/criteria.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = mybatisGeneratorObj.getOutputDir() +
                        "/" + mybatisGeneratorObj.getPackageName().replaceAll("\\.", "/") +
                        "/api/criteria/";
                new File(path).mkdirs();
                return path + tableInfo.getEntityName().substring(0, tableInfo.getEntityName().indexOf("DO")) + "Criteria.java";
            }
        });
        cfg.setFileOutConfigList(focList);

        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(mybatisGeneratorObj.getPackageName())
                                .setController("provider.controller")
                                .setMapper("provider.dao")
                                .setXml("provider.dao.mapper")
                                .setEntity("api.model")
                                .setService("provider.service")
                                .setServiceImpl("provider.service.impl")
                )
                .setTemplate(
                        new TemplateConfig()
                                .setEntity("/templates/entity.java")
                                .setMapper("/templates/mapper.java")
                                .setXml("/templates/mapper.xml")
                                .setService("/templates/service.java")
                                .setServiceImpl("/templates/serviceImpl.java")
                                .setController("/templates/controller.java")
                )
                .setCfg(cfg)
                .execute();
    }
}
