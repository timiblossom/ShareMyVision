package com.smv.test.webservice;

import javax.xml.ws.Endpoint;

import com.smv.service.file.FileServiceImpl;

public class Server {

    protected Server() throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        FileServiceImpl implementor = new FileServiceImpl();
        String address = "http://localhost:9000/FileService";
        Endpoint.publish(address, implementor);
        // END SNIPPET: publish
    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
