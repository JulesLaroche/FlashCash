package com.webforce.flashcash.repository;

import com.webforce.flashcash.model.Transfer;
import com.webforce.flashcash.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {

    List<Transfer> findAllByFromId(Integer id);
}
