package ua.com.vovacoffee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ua.com.vovacoffee.controller", "ua.com.vovacoffee.config"})

public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    private static final String PREFIX = "/WEB-INF/views/";

    private static final String SUFFIX = ".jsp";

    private static final String RESOURCES_URL = "/resources/";

    private static final String LOGIN_URL = "/login";

    private static final String LOGIN_VIEW_NAME = "client/login";

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType(CONTENT_TYPE);
        viewResolver.setPrefix(PREFIX);
        viewResolver.setSuffix(SUFFIX);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry resource) {
        resource.addResourceHandler(RESOURCES_URL + "**").addResourceLocations(RESOURCES_URL);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewController) {
        viewController.addViewController(LOGIN_URL).setViewName(LOGIN_VIEW_NAME);
        viewController.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
