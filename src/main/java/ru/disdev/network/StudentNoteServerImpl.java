package ru.disdev.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.disdev.network.handlers.InboundTrafficHandler;
import ru.disdev.network.handlers.PacketDecoder;
import ru.disdev.network.handlers.PacketEncoder;
import ru.disdev.services.google.GCMService;

/**
 * Created by Dislike on 18.07.2016.
 */
@Component
public class StudentNoteServerImpl implements StudentNoteServer {

    private static final Logger LOGGER = LogManager.getLogger(StudentNoteServerImpl.class);

    private final EventLoopGroup headExecutor;
    private final EventLoopGroup childExecutor;

    public StudentNoteServerImpl() {
        childExecutor = new NioEventLoopGroup(2);
        headExecutor = new NioEventLoopGroup(2);
    }

    @Autowired
    GCMService gcmService;

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(headExecutor, childExecutor)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new PacketEncoder(), new PacketDecoder(), new InboundTrafficHandler());
                    }
                });
        bootstrap.bind(9191).addListener(future -> {
           if (future.cause() != null)
               LOGGER.error("Error while starting server.", future.cause());
            else
               LOGGER.info("Server was successfully up.");
        });
    }

    public void shutdown() {
        headExecutor.shutdownGracefully();
        childExecutor.shutdownGracefully();
    }
}
