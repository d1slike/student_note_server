package ru.disdev.network.packets.components;

import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.in.AuthRequest;
import ru.disdev.network.packets.in.CreateGroupRequest;
import ru.disdev.network.packets.in.GCMRegistartionRequest;

/**
 * Created by Dislike on 18.07.2016.
 */
public class InboundPacketsFactory {
    public static ClientPacket create(final byte key) {
        switch (key) {
            case InboundPacketsKeys.AUTH_REQUEST:
                return new AuthRequest();
            case InboundPacketsKeys.GCM_REGISTRATION_REQUEST:
                return new GCMRegistartionRequest();
            case InboundPacketsKeys.CREATE_GROUP_REQUEST:
                return new CreateGroupRequest();
        }
        return null;
    }
}
