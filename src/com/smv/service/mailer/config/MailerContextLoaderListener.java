package com.smv.service.mailer.config;


import javax.servlet.ServletContext;

import com.smv.util.webcontainer.DefaultContextLoaderListener;


/**
 * @author Minh Do
 * 07/22/2010
 */
public class MailerContextLoaderListener extends DefaultContextLoaderListener {


        protected void initializeComponents(ServletContext sc) {
        	    super.initializeComponents(sc);
                WebAppVelocityResourceLoader.setServletContext(sc);
                ResourceManager.initVelocity();
        }

}
