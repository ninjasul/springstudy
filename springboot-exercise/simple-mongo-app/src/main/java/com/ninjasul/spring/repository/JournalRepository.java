package com.ninjasul.spring.repository;

import com.ninjasul.spring.domain.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalRepository extends MongoRepository<Journal, String> {
    List<Journal> findByTitleLike(String word);
}
