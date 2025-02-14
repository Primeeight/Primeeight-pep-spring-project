package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(nativeQuery =  true, value = "SELECT * FROM account WHERE username=:username_string")
    Optional<Account> findByUsername(@Param ("username_string") String username_string);
}
