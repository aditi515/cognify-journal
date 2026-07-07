package com.aditi.cognify_journal.user.service;

import com.aditi.cognify_journal.user.entity.User;

public interface UserService {

    User registerUser(User user);

    User getCurrentUser();

    User updateProfile(User user);

    void deleteAccount();
}