package ru.disdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.disdev.model.Group;
import ru.disdev.model.GroupPrivileges;
import ru.disdev.model.User;
import ru.disdev.repository.GroupRepository;

import java.util.List;

/**
 * Created by DisDev on 24.07.2016.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;


    @Override
    public Group getById(long id) {
        return groupRepository.getOne(id);
    }

    @Override
    public List<Group> getAllFromUniversity(String universityName) {
        return groupRepository.findAllByUniversityNameIgnoreCase(universityName);
    }

    @Override
    public void update(Group group) {
        groupRepository.save(group);
    }

    @Override
    public Group getByName(String name) {
        return groupRepository.findOneByName(name);
    }

    @Override
    public void deleteGroupById(long id) {
        groupRepository.delete(id);
        //todo delete from users
    }

    @Override
    @Transactional
    public Group createNewGroup(String userId, String universityName, String groupName) {
        Group group = null;
        User user = userService.getById(userId);
        if (user != null) {
            group = new Group(groupName, universityName, userId);
            groupRepository.save(group);
            user.setGroupId(group.getId());
            user.setPrivilege(GroupPrivileges.ELDER);
            userService.updateUser(user);
        }
        return group;
    }
}
