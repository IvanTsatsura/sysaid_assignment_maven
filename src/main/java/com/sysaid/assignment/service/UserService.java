package com.sysaid.assignment.service;

import com.sysaid.assignment.data.FakeDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private FakeDB dB;
    public UserService(){
        this.dB = FakeDB.getDB();
    }
    public ResponseEntity<String> addUser(String newUser){
        String responseMessage;
        try{
            dB.addUser(newUser);
            responseMessage = "New user added";
        }catch (Exception e){
            responseMessage = String.format("Username %s is already taken", newUser);
        }

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
