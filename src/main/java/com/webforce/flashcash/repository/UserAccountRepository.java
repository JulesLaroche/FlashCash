package com.webforce.flashcash.repository;

import com.webforce.flashcash.model.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    @Query("SELECT a FROM UserAccount a WHERE a.id= :id")
    UserAccount findAccountByUserId(Integer id);

}
