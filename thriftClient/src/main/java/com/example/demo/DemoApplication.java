package com.example.demo;

import com.example.demo.thrift.User;
import com.example.demo.thrift.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;


public class DemoApplication {

    public static void main(String[] args) throws TException {
        TTransport transport = new TSocket("localhost", 9090);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);
        perform(client);
        transport.close();
    }

    public static void perform(UserService.Client client) throws TException {
        client.saveUser(new User("firstName", "lastName", "exampleEmail"));
        client.saveUser(new User("sampleName", "secondLast", "sampleEmail"));
        List<User> users = client.getUsers();
        System.out.println("the size of users is " + users.size());
        users.forEach(current -> {
            System.out.println("firstName name is " + current.getFirstName() + " last name is " + current.getLastName() + " email is " + current.getEmail());
        });
    }

}
