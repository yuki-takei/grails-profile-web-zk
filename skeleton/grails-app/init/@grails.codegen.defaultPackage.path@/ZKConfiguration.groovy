package @grails.codegen.defaultPackage@

import groovy.util.logging.Commons
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zkoss.zk.au.http.DHtmlUpdateServlet
import org.zkoss.zk.ui.http.DHtmlLayoutServlet

import javax.annotation.PostConstruct

/**
 * @see http://www.marcelustrojahn.com/2015/12/integrating-zk-framework-on-a-spring-boot-application/
 */
@Configuration
@Commons
class ZKConfiguration {

  /**
   * zk.xml substitute
   */
  @PostConstruct
  void initZkConfig() {
    // see: https://github.com/zkoss/atlantic
    // see: https://www.zkoss.org/wiki/ZK%20Configuration%20Reference/zk.xml/The%20library-property%20Element
    org.zkoss.lang.Library.addProperty('org.zkoss.theme.preferred', 'atlantic');
  }

  @Bean
  ServletRegistrationBean registerDHtmlLayoutServlet() {
    log.info('initialize DHtmlLayoutServlet')

    def params = [ 'update-uri': '/zkau' ]
    ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlLayoutServlet(), "*.zul", "*.zhtml")
    reg.setLoadOnStartup(1)
    reg.setInitParameters(params)
    return reg
  }

  @Bean
  ServletRegistrationBean registerDHtmlUpdateServlet() {
    log.info('initialize DHtmlUpdateServlet')

    def params = [ 'update-uri': '/zkau/*' ]
    ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*")
    reg.setLoadOnStartup(2)
    reg.setInitParameters(params)
    return reg
  }

}
