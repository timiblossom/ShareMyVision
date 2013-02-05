package com.smv.util.webcontainer;



import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.smv.util.spring.SpringBeanReader;


/**
 * @author Minh Do
 * 07/01/2010
 */
public class DefaultContextLoaderListener implements ServletContextListener {

        private static final String COMPONENT_NAME_PARAM = "componentName"; 
        public static SmvContext context;
        public static ServletContext servletContext;

        @Override
        public void contextDestroyed(ServletContextEvent arg0) {
                // TODO Auto-generated method stub

        }

        @Override
        public void contextInitialized(ServletContextEvent sce) {
                initializeComponents(sce.getServletContext());

        }

        protected void initializeComponents(ServletContext sc) {
        	    String componentName = sc.getInitParameter(COMPONENT_NAME_PARAM);

                sc.log("Initializing SMV[" + componentName + "] .........");
                servletContext = sc;
                context = new SmvContext();

                context.hostname = "sharemyvision.com";  //TODO: fix this later
                context.sc = sc;
                
                InetAddress addr;
				try {
					addr = InetAddress.getLocalHost();
			        context.serverIp = addr.getAddress();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					context.serverIp = new byte[] {1, 1, 1, 1};
				}	    

                SpringBeanReader springBeanReader = SpringBeanReader.getInstance();
                springBeanReader.setServletContext(sc);
        }

        public static class SmvContext {
                public String hostname;
                public byte[] serverIp;
                public ServletContext sc;
        }
}
