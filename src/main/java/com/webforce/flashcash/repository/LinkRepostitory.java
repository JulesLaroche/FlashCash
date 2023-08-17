package com.webforce.flashcash.repository;

import com.webforce.flashcash.model.Link;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepostitory extends CrudRepository<Link, Integer> {
    @Query(value = "SELECT c FROM Link c WHERE c.user1.email= :email")
    List<Link> findLinkByUser1Email(String email);

}
