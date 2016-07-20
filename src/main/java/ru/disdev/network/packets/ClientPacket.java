package ru.disdev.network.packets;

import ru.disdev.network.components.Client;

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
        while (buffer.isReadable() && (c = buffer.readChar()) != '\0')
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
}
