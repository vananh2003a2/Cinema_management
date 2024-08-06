package com.laptrinhjavaweb.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.multipart.MultipartResolver;

public class mvcConfig implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(mvcConfig.class);
    private MultipartResolver multipartResolver;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		initMultipartResolver(context);
	}
    
	private void initMultipartResolver(ApplicationContext context) {
        try {
            this.multipartResolver = context.getBean("multipartResolver", MultipartResolver.class);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Using MultipartResolver [" + this.multipartResolver + "]");
            }
        } catch (NoSuchBeanDefinitionException ex) {
            this.multipartResolver = null;
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Unable to locate MultipartResolver with name 'multipartResolver': no multipart request handling provided");
            }
        }
    }
}
