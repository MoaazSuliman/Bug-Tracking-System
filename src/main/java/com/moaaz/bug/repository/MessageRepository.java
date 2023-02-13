package com.moaaz.bug.repository;

import jakarta.mail.MessageAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moaaz.bug.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findBySenderId(int senderId);

}
