package ru.disdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.disdev.model.User;

/**
 * Created by Dislike on 20.07.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
