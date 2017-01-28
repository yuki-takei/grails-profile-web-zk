package jp.co.weseek.spring.web

import grails.persistence.support.PersistenceContextInterceptorExecutor
import groovy.util.logging.Commons
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Open Session In View Filter
 * @author Yuki Takei <yuki@weseek.co.jp>
 */
@Commons
class OpenSessionInViewFilter extends OncePerRequestFilter {

    @Autowired
    ApplicationContext applicationContext

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        this.log.trace("init PersistenceContext")
        PersistenceContextInterceptorExecutor.initPersistenceContext(applicationContext)

        try {
            filterChain.doFilter(request, response)
        } finally {
            this.log.trace("destroy PersistenceContext")
            PersistenceContextInterceptorExecutor.destroyPersistenceContext(applicationContext)
        }
    }

}
