package ru.disdev.network.packets.in;

import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.components.InboundPacketsKeys;
import ru.disdev.services.ServiceHolder;

/**
 * Created by Dislike on 19.07.2016.
 */
public class RegistrationDeviceRequest extends ClientPacket {

    private String registrationId;

    @Override
    public void execute() {
        ServiceHolder.gcmService.registerNewDevice(registrationId, 1);
        ServiceHolder.gcmService.sendNotificationToUser(1, "blabla");
    }

    @Override
    public void decode() {
        registrationId = readString();
    }

    @Override
    public byte key() {
        return InboundPacketsKeys.RegistrationDeviceRequest;
    }
}
