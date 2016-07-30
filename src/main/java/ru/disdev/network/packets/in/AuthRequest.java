package ru.disdev.network.packets.in;

import ru.disdev.model.User;
import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.components.ClientPacketIdentifier;
import ru.disdev.network.packets.components.InboundPacketsKeys;
import ru.disdev.network.packets.out.AuthResponse;
import ru.disdev.services.ServiceHolder;

/**
 * Created by DisDev on 22.07.2016.
 */
@ClientPacketIdentifier(InboundPacketsKeys.AUTH_REQUEST)
public class AuthRequest extends ClientPacket {
    private String token;

    @Override
    public void execute() {
        User user = ServiceHolder.getAuthenticationService().authenticateUserWithToken(token);
        getClient().sendPacket(new AuthResponse(user));
    }

    @Override
    public void decode() {
        token = readString();
    }

}
