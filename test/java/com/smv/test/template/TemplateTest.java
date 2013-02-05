package com.smv.test.template;



import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class TemplateTest {
    public static void main1( String args[] ) throws Exception
    {
        /* first, we init the runtime engine.  Defaults are fine. */
    	
    	Properties p = new Properties();
        p.setProperty("file.resource.loader.path", "C:\\Dev\\temp");
        Velocity.init( p );



        /* lets make a Context and put data into it */

        VelocityContext context = new VelocityContext();

        context.put("name", "Velocity");
        context.put("project", "Jakarta");

        /* lets render a template */

        StringWriter w = new StringWriter();

        Velocity.mergeTemplate("testtemplate.vm", context, w );
        System.out.println(" template : " + w );

        /* lets make our own string to render */

        String s = "We are using $project $name to render this.";
        w = new StringWriter();
        Velocity.evaluate( context, w, "mystring", s );
        System.out.println(" string : " + w );
    }
    
    
    public static void main2( String[] args )
    throws Exception {
    	/*  first, get and initialize an engine  */
    	VelocityEngine ve = new VelocityEngine();
    	ve.init();
    	/*  next, get the Template  */
    	Template t = ve.getTemplate( "testtemplate.vm" );
    	/*  create a context and add data */
    	VelocityContext context = new VelocityContext();
    	context.put("name", "Velocity");
        context.put("project", "Jakarta");
        
    	/* now render the template into a StringWriter */
    	StringWriter writer = new StringWriter();
    	t.merge( context, writer );
    	/* show the World */
    	System.out.println( writer.toString() );     
    }
    
    
    public static void main( String args[] ) throws Exception
    {
        /* first, we init the runtime engine.  Defaults are fine. */
    	
    	Properties p = new Properties();
        p.setProperty("file.resource.loader.path", "C:\\Dev\\temp");
        Velocity.init( p );

        

        /* lets make a Context and put data into it */

        VelocityContext context = new VelocityContext();

        context.put("name", "Velocity");
        context.put("project", "Jakarta");

        /* lets render a template */

        StringWriter w = new StringWriter();

        Template template = Velocity.getTemplate("testtemplate.vm");
        template.merge(context, w);
        String content = w.toString();
        
        //Velocity.mergeTemplate("testtemplate.vm", context, w );
        System.out.println(" template : " + content );

        /* lets make our own string to render */

        //String s = "We are using $project $name to render this.";
        //w = new StringWriter();
        //Velocity.evaluate( context, w, "mystring", s );
        //System.out.println(" string : " + w );
    }

}
