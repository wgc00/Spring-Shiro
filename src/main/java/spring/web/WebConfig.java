package spring.web;

import org.springframework.web.filter.DelegatingFilterProxy;
import spring.SpringMVCConfig;
import spring.root.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*父容器*/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }


    /*子容器   */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        //这个类的好处是可以通过spring容器来管理filter的生命周期，还有就是，可以通过spring注入的形式，来代理一个filter执行，如shiro
        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy("shiroFilter");
        return new Filter[]{shiroFilter};
    }
}
