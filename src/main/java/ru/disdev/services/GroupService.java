package ru.disdev.services;

import ru.disdev.model.Group;

import java.util.List;

/**
 * Created by DisDev on 24.07.2016.
 */
public interface GroupService {
    Group getById(long id);
    List<Group> getAllFromUniversity(String universityName);
    void update(Group group);
    Group getByName(String name);
    void deleteGroupById(long id);
    Group createNewGroup(String userId, String universityName, String groupName);
}
