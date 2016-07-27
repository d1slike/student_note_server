package ru.disdev.network.packets.out;

import ru.disdev.model.GroupPrivileges;
import ru.disdev.model.User;
import ru.disdev.network.packets.ServerPacket;
import ru.disdev.network.packets.components.OutboundPacketsKeys;

/**
 * Created by DisDev on 22.07.2016.
 */
public class AuthResponse extends ServerPacket {

    private final boolean success;
    private String userId = "";
    private long groupId = -1;
    private boolean canEditGroup;
    private boolean canMakeNewPost;

    public AuthResponse(User user) {
        this.success = user != null;
        if (success) {
            this.userId = user.getId();
            groupId = user.getGroupId();
            canEditGroup = user.getPrivilege() == GroupPrivileges.ELDER;
            canMakeNewPost = user.getPrivilege() == GroupPrivileges.ELDER || user.getPrivilege() == GroupPrivileges.CAN_POST;
        }

    }

    @Override
    public void encode() {
        writeBoolean(success);
        writeString(userId);
        writeLong(groupId);
        writeBoolean(canEditGroup);
        writeBoolean(canMakeNewPost);
    }

    @Override
    public byte key() {
        return OutboundPacketsKeys.AUTH_RESPONSE;
    }
}
