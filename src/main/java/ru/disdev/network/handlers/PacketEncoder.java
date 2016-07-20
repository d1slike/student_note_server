package ru.disdev.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import ru.disdev.network.packets.ServerPacket;

/**
 * Created by Dislike on 18.07.2016.
 */
public class PacketEncoder extends MessageToByteEncoder<ServerPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ServerPacket msg, ByteBuf out) throws Exception {
        msg.setBuffer(out);
        out.writeByte(msg.key());
        msg.encode();
        msg.setBuffer(null);
    }
}
