package ru.disdev.network.components;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import ru.disdev.network.packets.ServerPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Dislike on 18.07.2016.
 */
public class ActiveClientsPool {
    private static ActiveClientsPool ourInstance = new ActiveClientsPool();

    public static ActiveClientsPool getInstance() {
        return ourInstance;
    }

    private final Map<ChannelId, Client> clientMap;

    private ActiveClientsPool() {
        clientMap = new ConcurrentHashMap<>();
    }

    public Client putClient(Channel channel) {
        Client client = clientMap.get(channel.id());
        if (client != null)
            return client;
        client = new Client(channel);
        clientMap.put(client.getId(), client);
        return client;
    }

    public Client getClientByChannelId(ChannelId id) {
        return clientMap.get(id);
    }

    public void removeClientIfActive(ChannelId id) {
        Client client = clientMap.remove(id);
        if (client == null)
            return;
        client.closeConnectivity();
    }
}
