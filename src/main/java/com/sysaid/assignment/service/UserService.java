package com.sysaid.assignment.service;

import com.sysaid.assignment.data.FakeDB;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final FakeDB dB;
    public UserService(){
        this.dB = FakeDB.getDB();
    }
    public void addUser(String newUser){
        dB.addUser(newUser);
    }
}
