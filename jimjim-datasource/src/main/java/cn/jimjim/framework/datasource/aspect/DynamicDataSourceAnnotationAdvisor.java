package cn.jimjim.framework.datasource.aspect;

import cn.jimjim.framework.datasource.annotation.DataSource;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author jim
 * @date 2019-07-31
 */
public class DynamicDataSourceAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private Advice advice;

    private Pointcut pointcut;

    public DynamicDataSourceAnnotationAdvisor(DynamicDataSourceAnnotationInterceptor dynamicDataSourceAnnotationInterceptor) {
        this.advice = dynamicDataSourceAnnotationInterceptor;
        this.pointcut = buildPointcut();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

    private Pointcut buildPointcut() {
        Pointcut pointcut = (Pointcut) new AnnotationMatchingPointcut(DataSource.class, true);
        // 类注解
        Pointcut classPointCut = (Pointcut) AnnotationMatchingPointcut.forClassAnnotation(DataSource.class);
        // 方法注解
        Pointcut methodPointCut = (Pointcut) AnnotationMatchingPointcut.forMethodAnnotation(DataSource.class);
        return new ComposablePointcut(pointcut).union(classPointCut).union(methodPointCut);
    }
}
