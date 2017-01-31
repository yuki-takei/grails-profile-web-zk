package jp.co.weseek.spring.web

import org.springframework.web.servlet.view.AbstractUrlBasedView
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * A ViewResolver that processes only views having specified prefix
 * @author Yuki Takei <yuki@weseek.co.jp>
 */
class PrefixedViewResolver extends InternalResourceViewResolver {

    /**
     * The prefix this ViewResolver processes
     */
    protected String canHandlePrefix = ""

    PrefixedViewResolver() {
    }

    PrefixedViewResolver(String canHandlePrefix) {
        this.setCanHandlePrefix(canHandlePrefix)
    }

    /**
     * setter for canHandlePrefix field
     * @param canHandlePrefix
     */
    void setCanHandlePrefix(String canHandlePrefix) {
        this.canHandlePrefix = canHandlePrefix
    }

    @Override
    protected boolean canHandle(String viewName, Locale locale) {
        return (this.hasPrefix(viewName) && super.canHandle(viewName, locale))
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        assert viewName != null
        assert this.hasPrefix(viewName)

        String fixedViewName = viewName.substring(canHandlePrefix.length())  // trim prefix
        return super.buildView(fixedViewName)
    }

    /**
     * whether the viewName has canHandlePrefix or not
     * @param viewName
     * @return T or F
     */
    private boolean hasPrefix(viewName) {
        return viewName =~ /^${canHandlePrefix}.+$/
    }
}
