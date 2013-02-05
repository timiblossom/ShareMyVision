package com.smv.util.spring;


import org.apache.log4j.Logger;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class SpringXmlConfigReader {

        private static final Logger log = Logger.getLogger(SpringXmlConfigReader.class);
        private static ConfigurationManager instance;


        private ApplicationContext context;

        private SpringXmlConfigReader() {};

        public SpringXmlConfigReader(String springFile) {
                context = new ClassPathXmlApplicationContext(springFile);
        }


        public Object getBeanById(String beanId)
        {
                Object config = null;

                if(context != null) {
                        config = context.getBean(beanId);
                }

                return config;
        }


}
