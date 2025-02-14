package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.Message;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    // List<Message> findAll();
@Query(nativeQuery =  true, value = "SELECT * FROM message")
List<Message> getAllMessages();
}
