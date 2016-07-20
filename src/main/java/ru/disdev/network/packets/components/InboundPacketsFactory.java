package ru.disdev.network.packets.components;

import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.in.RegistrationDeviceRequest;

/**
 * Created by Dislike on 18.07.2016.
 */
public class InboundPacketsFactory {
    public static ClientPacket create(final byte key) {
        switch (key) {
            case InboundPacketsKeys.RegistrationDeviceRequest:
                return new RegistrationDeviceRequest();
        }
        return null;
    }
}
