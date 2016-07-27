package ru.disdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.disdev.model.Group;

import java.util.List;

/**
 * Created by DisDev on 24.07.2016.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByUniversityNameIgnoreCase(String universityName);
    Group findOneByName(String name);
}
