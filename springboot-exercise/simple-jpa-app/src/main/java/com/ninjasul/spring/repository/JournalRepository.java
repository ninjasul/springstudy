package com.ninjasul.spring.repository;

import com.ninjasul.spring.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    List<Journal> findByCreatedAfter(Date date);

    @Query("select j from Journal j where j.title like %?1%")
    List<Journal> findByCustomQuery(String word);
}
