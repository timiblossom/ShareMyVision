
package com.smv.test.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.SessionDTO;
import com.smv.common.bean.UserDTO;
import com.smv.service.file.IFileService;
import com.smv.service.user.IUserService;



public final class TestUserClient {

    private static final QName SERVICE_NAME
        = new QName("http://server.sharemyvision/", "HelloWorld");
    private static final QName PORT_NAME
        = new QName("http://server.sharemyvision/", "HelloWorldPort");


    private TestUserClient() {
    }

    public static void main(String args[]) throws Exception {
		ClassPathXmlApplicationContext context
		            = new ClassPathXmlApplicationContext(new String[] {"client-proxy.xml"});

		IUserService client = (IUserService)context.getBean("userProxy");
		List<Header> headers = new ArrayList<Header>();
		Header dummyHeader = new Header(new QName("uri:org.apache.cxf", "dummy"), "decapitated",
		                                new JAXBDataBinding(String.class));
		headers.add(dummyHeader);
		((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

		
		System.out.println("client class : " + client.getClass());

		Map<String, String> httpEnv = new HashMap<String, String>();
		//httpEnv.put("minh", "1111");
		UserDTO user = new UserDTO();
		user.setUserEmail("p@p.com");
		user.setUserPassword("p");
		
		System.out.println("User = " + user);
		
		SessionDTO session = client.login(user);
		
		
		System.out.println("Session = " + session);
		
		
		//client.initRequest(new EventDTO());
		//httpEnv = client.initRequest(new EventDTO());
		
		/*
		List<ItemDTO> items = client.getFilesTest();
		for(ItemDTO item : items) {
			System.out.println("Item : " + item.getFileSize());
			
		}
		*/
		
        System.exit(0);

        /*
        Service service = Service.create(SERVICE_NAME);
        // Endpoint Address
        //String endpointAddress = "http://localhost:9000/helloWorld";
        String endpointAddress = "http://fme2.com/ws/HelloWorld";

        // Add a port to the Service
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        HelloWorld hw = service.getPort(HelloWorld.class);
        System.out.println(hw.sayHi("World"));

        User user = new UserImpl("World");
        System.out.println(hw.sayHiToUser(user));

        //say hi to some more users to fill up the map a bit
        user = new UserImpl("Galaxy");
        System.out.println(hw.sayHiToUser(user));

        user = new UserImpl("Universe");
        System.out.println(hw.sayHiToUser(user));

        System.out.println();
        System.out.println("Users: ");
        Map<Integer, User> users = hw.getUsers();
        for (Map.Entry<Integer, User> e : users.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue().getName());
        }

        */
    }

}
