package cn.jimjim.framework.datasource;

import cn.jimjim.framework.datasource.aspect.DynamicDataSourceAnnotationAdvisor;
import cn.jimjim.framework.datasource.aspect.DynamicDataSourceAnnotationInterceptor;
import cn.jimjim.framework.datasource.config.datasource.DynamicDataSourceRegister;
import cn.jimjim.framework.datasource.config.mybatis.MybatisPlusConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


/**
 * @author jim
 * @date 2019-07-31
 */
@Import({MybatisPlusConfig.class, DynamicDataSourceRegister.class})
public class DataSourceApplication {

    @Bean
    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
    }

}
