package ru.disdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.disdev.model.User;
import ru.disdev.repository.UserRepository;

/**
 * Created by Dislike on 20.07.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getById(String id) {
        return repository.findOne(id);
    }

    @Override
    public boolean checkExist(String id) {
        return getById(id) != null;
    }

    @Override
    public void updateUser(User user) {
        repository.save(user);
    }

}
