package spring.root;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {
    //也可以不用写一个JdbcRealm对象直接在
    @Bean
    JdbcRealm jdbcRealm() {
        JdbcRealm realm = new JdbcRealm();
        //自定义设置sql语句，用户验证username和password
        //realm.setAuthenticationQuery();
        //查询用户角色，权限的控制
        //realm.setPermissionsQuery();
        return realm;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(jdbcRealm());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilter()  {
        ShiroFilterFactoryBean shiroFilter =  new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());

        shiroFilter.setLoginUrl("/login"); //
        shiroFilter.setSuccessUrl("/home"); //登陆成功后的页面
        shiroFilter.setUnauthorizedUrl("/500"); //错误页面

        // 规定设定
        // 基于 url 的规则
        HashMap<String, String> rules = new HashMap<>();
        /*设置内置过滤*/
        rules.put("/admin/**", "authc");
        rules.put("/policy/**", "authc");
        rules.put("/policy/view", "anon");
        rules.put("/logout", "logout");
        shiroFilter.setFilterChainDefinitionMap(rules);
        return shiroFilter;
    }


}
