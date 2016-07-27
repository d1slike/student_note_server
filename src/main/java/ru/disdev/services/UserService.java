package ru.disdev.services;

import ru.disdev.model.User;

/**
 * Created by Dislike on 20.07.2016.
 */
public interface UserService {
    User getById(String id);
    boolean checkExist(String id);
    void updateUser(User user);
}
