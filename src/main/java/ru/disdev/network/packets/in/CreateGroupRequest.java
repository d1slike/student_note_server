package ru.disdev.network.packets.in;

import ru.disdev.model.Group;
import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.components.InboundPacketsKeys;
import ru.disdev.network.packets.out.CreateGroupAnswer;
import ru.disdev.services.ServiceHolder;

/**
 * Created by DisDev on 25.07.2016.
 */
public class CreateGroupRequest extends ClientPacket {
    private String userId;
    private String universityName;
    private String groupName;

    @Override
    public void execute() {
        Group newGroup = ServiceHolder.getGroupService().createNewGroup(userId, universityName, groupName);
        if (newGroup != null)
            getClient().sendPacket(new CreateGroupAnswer(newGroup.getId()));
    }

    @Override
    public void decode() {
        userId = readString();
        universityName = readString();
        groupName = readString();
    }

    @Override
    public byte key() {
        return InboundPacketsKeys.CREATE_GROUP_REQUEST;
    }
}
