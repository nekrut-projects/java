package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Network {

    private SocketChannel socketChannel;

    public Network() {
        new Thread(() -> {
            EventLoopGroup worker = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(worker)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                socketChannel = ch;
                                ch.pipeline().addLast(
//                                        new ObjectEncoder(),
//                                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                        new ClientMessageHandler()
                                );
                            }
                        });
                ChannelFuture channelFuture = bootstrap.connect("localhost", 8189).sync();
                channelFuture.channel().closeFuture().sync(); // block
            } catch (Exception e) {
                e.printStackTrace();
//                log.error("e = ", e);
            } finally {
                worker.shutdownGracefully();
            }
        }).start();
    }

    public void sendMessage(ByteBuf message) {
        socketChannel.writeAndFlush(message);
    }

}
