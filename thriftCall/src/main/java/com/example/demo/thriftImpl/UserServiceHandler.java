package com.example.demo.thriftImpl;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.thriftGenerated.User;
import com.example.demo.thriftGenerated.UserService;
import org.apache.thrift.TException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceHandler implements UserService.Iface {
    private List<UserEntity> userRepository = new ArrayList<>();
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<User> getUsers() throws TException {
        List<UserEntity> allUsers = userRepository;
        return allUsers.stream().map(current -> modelMapper.map(current, User.class)).collect(Collectors.toList());
    }

    @Override
    public User saveUser(User user) throws TException {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        String email = userEntity.getEmail();
        System.out.println("saved email is " + email);
        userRepository.add(userEntity);
        return modelMapper.map(userEntity, User.class);
    }
}
