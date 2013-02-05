package com.smv.service.mailer.config;


import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;


/**
 * @author Minh Do
 * 07/22/2010
 */
public class WebAppVelocityResourceLoader extends ResourceLoader {

    private static ServletContext context = null;

    public void init(ExtendedProperties extendedProperties) {

    }

    private static ServletContext getServletContext() {
    	if (context == null) {
    		context = MailerContextLoaderListener.servletContext;
    	}
        return context;
    }

    public static void setServletContext(ServletContext context) {
        WebAppVelocityResourceLoader.context = context;
    }

    /**
     * @see org.apache.velocity.runtime.resource.loader.ResourceLoader#getResourceStream(java.lang.String)
     */
    public InputStream getResourceStream(String name)
            throws ResourceNotFoundException {
        InputStream result = null;

        if (name == null || name.length() == 0) {
            throw new ResourceNotFoundException("No template name provided");
        }

        try {
            //if (!name.startsWith("/"))
            name = "/WEB-INF/templates/" + name;
            result = getServletContext().getResourceAsStream(name);
        } catch (NullPointerException npe) {
            throw new ResourceNotFoundException("ServletContext not found");
        } catch (Exception fnfe) {
            throw new ResourceNotFoundException(fnfe.getMessage());
        }

        return result;
    }

    public boolean isSourceModified(Resource arg0) {
        return false;
    }

    public long getLastModified(Resource arg0) {
        return 0;
    }

}
