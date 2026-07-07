package com.aditi.cognify_journal.journal.repository;

import com.aditi.cognify_journal.journal.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}