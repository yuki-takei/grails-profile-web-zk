package @grails.codegen.defaultPackage.path@

import grails.transaction.Transactional
import groovy.util.logging.Commons
import org.zkoss.zk.ui.Component
import org.zkoss.zk.ui.select.annotation.VariableResolver
import org.zkoss.zkplus.spring.DelegatingVariableResolver

@Component
@Transactional
@VariableResolver(DelegatingVariableResolver.class)
@Commons
class AboutVM {

    String description = 'An example application with ZK'

}
