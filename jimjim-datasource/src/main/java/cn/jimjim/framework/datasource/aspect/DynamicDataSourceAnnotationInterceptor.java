package cn.jimjim.framework.datasource.aspect;

import cn.jimjim.framework.datasource.annotation.DataSource;
import cn.jimjim.framework.datasource.config.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jim
 * @date 2019-07-31
 */
@Slf4j
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

    /**
     * 缓存方法注解值
     */
    private static final Map<Method, String> METHOD_CACHE = new HashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            String datasource = determineDatasource(invocation);
            if (! DynamicDataSourceContextHolder.containsDataSource(datasource)) {
                log.info("数据源[{}]不存在，使用默认数据源 >", datasource);
            }
            log.info("Use DataSource :{}", datasource);
            DynamicDataSourceContextHolder.setDataSourceRouterKey(datasource);
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeDataSourceRouterKey();
        }
    }

    private String determineDatasource(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        if (METHOD_CACHE.containsKey(method)) {
            return METHOD_CACHE.get(method);
        } else {
            DataSource ds = method.isAnnotationPresent(DataSource.class) ? method.getAnnotation(DataSource.class)
                    : AnnotationUtils.findAnnotation(method.getDeclaringClass(), DataSource.class);
            METHOD_CACHE.put(method, ds.value());
            return ds.value();
        }
    }
}
