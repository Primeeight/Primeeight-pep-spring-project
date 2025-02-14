package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MessageService {
    //Create the respective repository.
    MessageRepository messageRepository;
    //Constructor
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    public MessageService() {
    }
    //Get all messages
    public List<Message> getAllMessages(){
        List<Message> messages = messageRepository.findAll();
        return messages;
    }
    public List<Message> getAllMessages(Integer id){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(id);
        List<Message> messages = messageRepository.findAllById(ids);
        return messages;
    }
    public Optional<Message> getMessageById(int id){
        Optional<Message> message = messageRepository.findById(id);
        return message;
    }
    //Delete a message by an id
    public Optional<Integer> deleteMessageById(int id){
        Optional<Message> message = getMessageById(id);  
        if (message.isEmpty()) {
            return Optional.empty();
        }
            messageRepository.deleteById(id);
            return Optional.of(1);    
    }
    //gives null pointer exception.
    public Optional<Message> postMessage(Message message) {
        if (message.getMessageText()!= "" && message.getMessageText().length() < 255) {
            
                Message newMessage = messageRepository.save(message);
                return Optional.of(newMessage);
        }
        return Optional.empty();
    }
    public Optional<Message> patchMessage(Message message, int message_id) {
        if (messageRepository.findById(message_id).isPresent() && message.getMessageText().length() < 255 && message.getMessageText() != "") {
            messageRepository.save(message);
            return Optional.of(message);
        }
        return Optional.empty();
    }
}
