package com.ninjasul.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ninjasul.spring.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}
