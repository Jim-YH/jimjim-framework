package cn.jimjim.framework.datasource.generator;

import lombok.Data;

/**
 * @author jimijm
 */
@Data
public class MybatisGeneratorInfo {

    private String outputDir;
    private String dataSourceUserName;
    private String dataSourcePassword;
    private String dataSourceUrl;
    private String packageName;

    private String[] tablePrefix;
    private String[] includeTables;
    private String[] excludeTables;


}
