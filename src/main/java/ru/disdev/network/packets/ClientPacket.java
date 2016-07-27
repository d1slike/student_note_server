package ru.disdev.network.packets;

import com.sun.xml.internal.ws.api.message.Packet;
import io.netty.buffer.ByteBufUtil;
import ru.disdev.network.components.Client;

import java.util.stream.Stream;

/**
 * Created by Dislike on 18.07.2016.
 */
public abstract class ClientPacket extends AbstractPacket {

    private Client client;

    public abstract void execute();
    public abstract void decode();

    protected final int readInt() {
        return buffer.readInt();
    }

    protected final String readString() {
        StringBuilder builder = new StringBuilder();
        char c;
        while ((c = buffer.readChar()) != '\0')
            builder.append(c);
        return builder.toString();
    }

    protected final float readFloat() {
        return buffer.readFloat();
    }

    protected final double readDouble() {
        return buffer.readDouble();
    }

    protected final long readLong() {
        return buffer.readLong();
    }

    protected final boolean readBoolean() {
        return buffer.readBoolean();
    }

    public final void setClient(Client client) {
        this.client = client;
    }

    protected final Client getClient() {
        return client;
    }

    public final String tracePacket() {
        StringBuilder builder = new StringBuilder("ClientPacket: ").append(getClass().getSimpleName()).append("\n");
        Stream.of(getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                builder.append(field.getName()).append(" - ").append(field.get(this)).append("\n");
            } catch (IllegalAccessException e) {

            }
        });
        builder.append("----------------------------------\n");
        return builder.toString();
    }
}
