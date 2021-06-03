package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


//public class Server {
//    static LinkedList<Handler> listUser = new LinkedList<>();
//
//    public static void main(String[] args) {
//        try {
//            ServerSocket server = new ServerSocket(8189);
//            System.out.println("Server started");
//
//            while (true) {
//                Socket socket = server.accept();
//                System.out.println("Client accepted");
//
//                Handler handler = new Handler(socket);
//                listUser.add(handler);
//                new Thread(handler).start();
//            }
//        } catch (IOException e) {
//            System.out.println("Connection was broken");
//        }
//
//    }
//}

public class Server {

    public Server() {
        EventLoopGroup auth = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(auth, worker)
                    .channel(NioServerSocketChannel.class )
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(
                                new SimpleHandler()
                            );
                        }
                    });
            ChannelFuture channelFuture = bootStrap.bind(8189).sync();
            System.out.println("Server start");
            System.out.println("Add logger");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}