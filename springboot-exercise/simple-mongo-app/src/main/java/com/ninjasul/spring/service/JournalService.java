package com.ninjasul.spring.service;

import com.ninjasul.spring.domain.Journal;
import com.ninjasul.spring.repository.JournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    private static final Logger logger = LoggerFactory.getLogger(JournalService.class);

    @Autowired
    JournalRepository journalRepository;

    public List<Journal> findAll() {
        return journalRepository.findAll();
    }
}