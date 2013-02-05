package com.smv.test.s3;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jets3t.service.security.AWSCredentials;

import com.smv.util.vendor.s3.ConfigBean;

/**
 * Utilities used by all Sample code, collected in one place for convenience.
 * 
 * @author James Murty
 */
public class SamplesUtils {
    
    public static final String SAMPLES_PROPERTIES_NAME = "samples.properties";
    public static final String AWS_ACCESS_KEY_PROPERTY_NAME = "awsAccessKey";
    public static final String AWS_SECRET_KEY_PROPERTY_NAME = "awsSecretKey";
    
    /**
     * Loads AWS Credentials from the file <tt>samples.properties</tt>
     * ({@link #SAMPLES_PROPERTIES_NAME}) that must be available in the  
     * classpath, and must contain settings <tt>awsAccessKey</tt> and 
     * <tt>awsSecretKey</tt>.
     * 
     * @return
     * the AWS credentials loaded from the samples properties file.
     */
    public static AWSCredentials loadAWSCredentials() throws IOException {

        
        AWSCredentials awsCredentials = new AWSCredentials(
            ConfigBean.S3_ACCESS_KEY_ID,
            ConfigBean.S3_SECRET_ACCESS_KEY);
        
        return awsCredentials;        
    }

}
