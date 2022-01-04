package com.example.demo;

import com.example.demo.thriftGenerated.UserService;
import com.example.demo.thriftImpl.UserServiceHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            UserServiceHandler userServiceHandler = new UserServiceHandler();
            UserService.Processor<UserServiceHandler> processor = new UserService.Processor<>(userServiceHandler);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("starting the server......");
            server.serve();
        } catch (Exception e) {
            System.out.println("unable to start the server due to " + e.getMessage());
        }
    }

}
