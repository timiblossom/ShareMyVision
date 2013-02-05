package com.smv.util.thread.generator;




import javax.xml.transform.TransformerFactory;
import com.smv.util.thread.AbstractBaseGenerator;

/**
 * @author Minh Do
 * 03/2010
 */

public class TransformerGenerator extends AbstractBaseGenerator {

	public TransformerGenerator() {
		super("javax.xml.transform.Transformer");
	}

	@Override
	public Object generateInstance() throws Exception {		
		Object result = null;
		synchronized (tf) {
			result = tf.newTransformer();
		}
		return result;
	}

	private static TransformerFactory tf = TransformerFactory.newInstance();
}
