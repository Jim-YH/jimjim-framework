package cn.jimjim.framework.datasource.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源路由配置
 * @author jim
 * @date 2019-07-31
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        log.info("当前数据源是：{}", dataSourceName != null ? dataSourceName : "default");
        return DynamicDataSourceContextHolder.getDataSourceRouterKey();
    }

}
