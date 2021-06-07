package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import shared.Marker;

public class ClientMessageHandler extends ChannelInboundHandlerAdapter {
    private Callback callback;
    public ClientMessageHandler(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        callback.call((ByteBuf) msg);
//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte marker = byteBuf.readByte();
//        System.out.println(marker);
//        System.out.println("........................");
//        if (marker == Marker.UPLOAD_FILE.getValue()) {
//            System.out.println("Client upload file bloc");
//            //
//        } else if(marker == Marker.DOWNLOAD_FILE.getValue()) {
//            System.out.println("Client download file bloc");
//            //
//        } else if (marker == Marker.SEND_LIST_FILES.getValue()) {
//            System.out.println("Client input list block");
//            ByteBuf b = (ByteBuf) msg;
//            StringBuilder listFiles = new StringBuilder();
////            System.out.println(b.readByte());
//            while (b.isReadable()){
//                listFiles.append((char) b.readByte());
//            }
//            System.out.println(listFiles.toString());
//            ctx
//            System.out.println("++++++++++++++++++++");
////            b.writeBytes(ctx.channel());
//            //
        }
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf byteBuf = ctx.alloc().directBuffer();
//        byte marker = byteBuf.readByte();
//        System.out.println(marker);
//        System.out.println("........................");
//        if (marker == Marker.UPLOAD_FILE.getValue()) {
//            System.out.println("Client upload file bloc");
//            //
//        } else if(marker == Marker.DOWNLOAD_FILE.getValue()) {
//            System.out.println("Client download file bloc");
//            //
//        } else if (marker == Marker.SEND_LIST_FILES.getValue()) {
//            System.out.println("Client input list block");
//            ByteBuf b = ctx.alloc().directBuffer();
////            b.writeBytes(ctx.channel());
//            //
//        }
//    }
//}
