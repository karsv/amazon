package com.task.amazon.repository;

import com.task.amazon.entities.WordCountEntity;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordCountRepository extends JpaRepository<WordCountEntity, Long> {

    @Query("SELECT w FROM WordCountEntity w ORDER BY w.number DESC")
    List<WordCountEntity> getMostPopularWords(PageRequest pageRequest);
}
