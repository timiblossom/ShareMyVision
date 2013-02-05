package com.smv.util.spring;



import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class SpringBeanReader {
        private final static Logger log = Logger.getLogger(SpringBeanReader.class);

        private static SpringBeanReader instance;
        public static ClassPathXmlApplicationContext context;
        private WebApplicationContext ctx;


        public static SpringBeanReader getInstance()  {
                synchronized (SpringBeanReader.class) {
                        if (instance == null)  {
                                instance = new SpringBeanReader();
                        }
                        return instance;
                }
        }


        public void setServletContext(ServletContext ctx) {
                this.ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
        }


        public <BeanClass> BeanClass getBean(Class<BeanClass> beanClass)  {
                BeanClass bean = null;
                for (BeanClass firstBean : getBeans(beanClass).values())  {
                        bean = firstBean;
                        break;
                }
                return bean;
        }


        public <BeanClass> Map<String, BeanClass> getBeans(Class<BeanClass> beanClass)  {
                Map<String, BeanClass> beansOfType = ctx.getBeansOfType(beanClass);
                return beansOfType;
        }


        public Object getBeanById(String beanId)  {
        		if (ctx == null) {
        			if (context == null) {
        				loadProxiesConfiguration();
        			}
        			return context.containsBean(beanId)? context.getBean(beanId) : null;
        		}
                return this.ctx.containsBean(beanId)? this.ctx.getBean(beanId): null;
        }

        public WebApplicationContext getCtx() {
                return this.ctx;
        }

        public void setCtx(WebApplicationContext ctx) {
            this.ctx = ctx;
        }
        
        private void loadProxiesConfiguration() {
        	context  = new ClassPathXmlApplicationContext(new String[] {"client-proxy.xml"});
        }
}
