package cn.jimjim.framework.datasource.config.mybatis;

import cn.jimjim.framework.datasource.config.mybatis.method.BatchInsertAllColumn;
import cn.jimjim.framework.datasource.config.mybatis.method.SoftDeleteById;
import cn.jimjim.framework.datasource.config.mybatis.method.SoftDeleteByIds;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 查找已删除的记录
 * @author jim
 */
public class MybatisInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.addAll(Stream.of(
                new BatchInsertAllColumn(),
                new SoftDeleteById(),
                new SoftDeleteByIds()
        ).collect(Collectors.toList()));
        return methodList;
    }


}
