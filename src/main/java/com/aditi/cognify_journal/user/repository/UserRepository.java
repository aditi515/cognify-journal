package com.aditi.cognify_journal.user.repository;

import com.aditi.cognify_journal.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}