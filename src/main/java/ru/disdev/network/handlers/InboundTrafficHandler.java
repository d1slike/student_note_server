package ru.disdev.network.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.disdev.network.components.ActiveClientsPool;
import ru.disdev.network.components.Client;
import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.components.InboundPacketsKeys;

/**
 * Created by Dislike on 18.07.2016.
 */
public class InboundTrafficHandler extends SimpleChannelInboundHandler<ClientPacket> {

    private static final Logger LOGGER = LogManager.getLogger(InboundTrafficHandler.class);
    private static final boolean DEBUG = true;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ClientPacket msg) throws Exception {
        Client client = ActiveClientsPool.getInstance().getClientByChannelId(ctx.channel().id());
        if (client == null)
            return;
        if (DEBUG)
            LOGGER.info(msg.tracePacket());
        msg.setClient(client);
        client.consumeClientPacket(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ActiveClientsPool.getInstance().removeClientIfActive(ctx.channel().id());
        LOGGER.error(cause);
        cause.printStackTrace();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ActiveClientsPool.getInstance().putClient(ctx.channel());
        LOGGER.info("Successfully connected " + ctx.channel().localAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ActiveClientsPool.getInstance().removeClientIfActive(ctx.channel().id());
        LOGGER.info("Disconnected " + ctx.channel().localAddress());
    }
}
