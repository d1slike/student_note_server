package ru.disdev.network.components;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.ServerPacket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * Created by Dislike on 18.07.2016.
 */
public class Client {

    private final Lock lock = new ReentrantLock();

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
        lock.lock();
        try {
            channel.close();
            channel = null;
            id = null;
        } finally {
            lock.unlock();
        }
    }

    public void consumeClientPacket(ClientPacket packet) {
        lock.lock();
        try {
            packet.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
