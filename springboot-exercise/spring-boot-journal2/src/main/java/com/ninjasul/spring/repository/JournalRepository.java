package com.ninjasul.spring.repository;

import com.ninjasul.spring.domain.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

// @Transactional: REST 호출에 트랜잭션을 걸어 API 호출 시 데이터 정합성에 문제가 없도록 보호.
@Transactional
@RepositoryRestResource(collectionResourceRel = "entry", path = "journal")
public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findByCreatedAfter(@Param("after") @DateTimeFormat(iso =ISO.DATE) Date date);
    List<JournalEntry> findByCreatedBetween(@Param("after") @DateTimeFormat(iso = ISO.DATE) Date after,
                                            @Param("before") @DateTimeFormat (iso = ISO.DATE ) Date before) ;
    List<JournalEntry> findByTitleContaining(@Param("word") String word );
    List<JournalEntry> findBySummaryContaining(@Param ("word") String word );
}
