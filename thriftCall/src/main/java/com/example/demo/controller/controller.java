package com.example.demo.controller;

import com.example.demo.thriftImpl.UserServiceHandler;
import com.example.demo.thriftGenerated.User;
import com.example.demo.thriftGenerated.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class controller {

    @Autowired
    private UserServiceHandler userServiceThriftClient;

    @GetMapping("/thrift/users")
    public void getUsers() throws TException {
        TTransport transport = new TSocket("localhost", 8080);
        transport.open();
        TProtocol protocol = new TSimpleJSONProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);
        List<User> users = client.getUsers();
        
    }
}
