package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Account;
import com.example.entity.Message;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    //Create new services to use repository methods.
    @Autowired
    MessageService messageService = new MessageService();
    @Autowired
    AccountService accountService = new AccountService();

    /**
     * Implement mappings similar to other Spring applications.
     */
    //GET localhost:9000/cats?amount=50

    @PostMapping(value = "/register")
    public ResponseEntity postAccount(@RequestBody Account account){
        Optional<Account> newaccount;
        try {
            newaccount = accountService.postAccount(account);
            if (newaccount.isEmpty()) {
                return ResponseEntity.status(400).body(null);
                
            }
            return ResponseEntity.status(200).body(newaccount); 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(409).body(null);
        }
    }
    @PostMapping(value = "/login")
    public ResponseEntity loginAccount(@RequestBody Account account){
        Optional<Account> newaccount= accountService.loginAccount(account);
        if (newaccount.isEmpty()) {
            return ResponseEntity.status(401).body(null);
            
        }
        return ResponseEntity.status(200).body(newaccount); 
    }


    @PostMapping(value = "/messages")
    public ResponseEntity postMessage(@RequestBody Message message){
        if (accountService.findById(message.getPostedBy()).isPresent()){
            Optional<Message> newmessage= messageService.postMessage(message);
            if(newmessage.isPresent()){
                return ResponseEntity.status(200).body(newmessage);
            }
        }
            return ResponseEntity.status(400).body(null);
    }
    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity getAllMessagesById(@PathVariable int account_id){
        return ResponseEntity.status(200).body(messageService.getAllMessages(account_id));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity getMessageById(@PathVariable Integer id){
        Optional<Message> message= messageService.getMessageById(id);
        if (message.isEmpty()) {
            return ResponseEntity.status (200).body("");
            
        }
        return ResponseEntity.status(200).body(message.get());
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity patchMessage(@RequestBody Message message, @PathVariable Integer message_id ){
        Optional<Message> newmessage= messageService.patchMessage(message, message_id);
        if (newmessage.isEmpty()) {
            return ResponseEntity.status (400).body("");
        }
        return ResponseEntity.status(200).body(1);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity deleteMessageById(@PathVariable int id){
        Optional<Integer> response = messageService.deleteMessageById(id);
        if (response.isEmpty()) {
            return ResponseEntity.status(200).body("");
        }
        return ResponseEntity.status(200).body(response.get());
    }
        
}
