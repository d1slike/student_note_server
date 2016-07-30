package ru.disdev.network.packets.out;

import ru.disdev.network.packets.ServerPacket;
import ru.disdev.network.packets.components.OutboundPacketsKeys;

/**
 * Created by DisDev on 25.07.2016.
 */
public class CreateGroupAnswer extends ServerPacket {

    private final long newGroupId;

    public CreateGroupAnswer(long newGroupId) {
        this.newGroupId = newGroupId;
    }

    @Override
    public void encode() {
        writeLong(newGroupId);
    }

    @Override
    public byte key() {
        return OutboundPacketsKeys.CREATE_GROUP_ANSWER;
    }
}
