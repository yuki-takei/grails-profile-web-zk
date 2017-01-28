package @grails.codegen.defaultPackage@

import groovy.util.logging.Commons
import jp.co.weseek.spring.web.OpenSessionInViewFilter
import jp.co.weseek.spring.web.PrefixedViewResolver
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * Custom WebMvcConfigurerAdapter for ZK Application
 * @author Yuki Takei <yuki@weseek.co.jp>
 */
@Configuration
@ComponentScan("jp.co.weseek.spring")
@Commons
class ZKWebMvcConfigurer extends WebMvcConfigurerAdapter {

    /**
     * OpenSessionInViewFilter Bean Factory
     * @return
     */
    @Bean
    OpenSessionInViewFilter openSessionInViewFilter() {
        return new OpenSessionInViewFilter()
    }

    /**
     * regist OpenSessionInViewFilter
     * @return
     */
    @Bean
    FilterRegistrationBean registerOsivFilter() {
        FilterRegistrationBean reg = new FilterRegistrationBean(this.openSessionInViewFilter())
        reg.urlPatterns = ["/zkapp/*", "/zkau/*"]
        return reg
    }

    /**
     * PrefixedViewResolver Bean Factory
     * @return
     */
    @Bean
    InternalResourceViewResolver prefixedViewResolver() {
        log.info("initialize PrefixedViewResolver")

        PrefixedViewResolver viewResolver = new PrefixedViewResolver("mng:")
        viewResolver.setSuffix(".zhtml")
        return viewResolver
    }

    @Override
    void addViewControllers(ViewControllerRegistry registry) {
        log.info("add ViewController for '/zkapp/**'")

        registry.addViewController("/zkapp/**").setViewName("zkapp:index");
    }

}
