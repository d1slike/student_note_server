package ru.disdev.network.packets.in;

import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.components.InboundPacketsKeys;
import ru.disdev.services.ServiceHolder;

/**
 * Created by Dislike on 19.07.2016.
 */
public class GCMRegistartionRequest extends ClientPacket {

    private String registrationId;
    private String userId;

    @Override
    public void execute() {
        ServiceHolder.getGcmService().registerNewDevice(registrationId, userId);
    }

    @Override
    public void decode() {
        registrationId = readString();
        userId = readString();
    }

    @Override
    public byte key() {
        return InboundPacketsKeys.GCM_REGISTRATION_REQUEST;
    }
}
