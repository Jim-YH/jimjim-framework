package cn.jimjim.framework.datasource.config.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据源上下文
 * 用户记录当前线程使用的数据源的key是什么，以及记录所有注册成功的数据源的key的集合。
 * 对于线程级别的私有变量，我们首先ThreadLocal来实现。
 * @author jim
 * @date 2019-07-31
 */
@Slf4j
public class DynamicDataSourceContextHolder {

    /**
     * 存储已经注册的数据源的key
     */
    public static List<String> dataSources = new ArrayList<>();

    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static String getDataSourceRouterKey () {
        return HOLDER.get();
    }

    public static void setDataSourceRouterKey (String dataSourceRouterKey) {
        log.info("切换至{}数据源", dataSourceRouterKey);
        HOLDER.set(dataSourceRouterKey);
    }

    /**
     * 设置数据源之前一定要先移除
     */
    public static void removeDataSourceRouterKey () {
        HOLDER.remove();
    }

    /**
     * 判断指定DataSource当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSources.contains(dataSourceId);
    }

}
