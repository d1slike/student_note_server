package ru.disdev.network.components;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import ru.disdev.network.packets.ServerPacket;

import java.util.stream.Stream;

/**
 * Created by Dislike on 18.07.2016.
 */
public class Client {
    private Channel channel;
    private ChannelId id;

    public Client(Channel channel) {
        this.channel = channel;
        id = channel.id();
    }

    public void sendPackets(ServerPacket... packets) {
        Stream.of(packets).forEach(channel::write);
        channel.flush();
    }

    public void sendPacket(ServerPacket packet) {
        channel.writeAndFlush(packet);
    }

    public ChannelId getId() {
        return id;
    }

    public void closeConnectivity() {
        channel.close();
        channel = null;
        id = null;
    }
}
