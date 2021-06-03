package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import shared.Marker;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleHandler extends ChannelInboundHandlerAdapter {
    private static final String serverRootDir = "serverDir";
    private Path userDir;

    public SimpleHandler() {
        this.userDir = Paths.get(serverRootDir);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        ByteBuf message = (ByteBuf) o;
        byte marker;
        while (message.isReadable()){
            marker = message.readByte();
            if (marker == (byte)Marker.DOWNLOAD_FILE.ordinal()){
                int sizeName = message.readInt();
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < sizeName; i++) {
                    name.append(message.readChar());
                }
                ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
                RandomAccessFile file = new RandomAccessFile(name.toString(), "rw");
                FileChannel in = file.getChannel();

                ctx.writeAndFlush((byte)Marker.DOWNLOAD_FILE.ordinal());
                int c;
                while ((c = in.read(byteBuffer)) > 0){
                    ctx.writeAndFlush(byteBuffer);
                    byteBuffer.clear();
                }
            } else if (marker == (byte)Marker.UPLOAD_FILE.ordinal()) {
                int sizeName = message.readInt();
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < sizeName; i++) {
                    name.append(message.readChar());
                }

                Path path = Paths.get(userDir.getFileName().toString(), name.toString());
                Files.createFile(path);



            } else if (marker == Marker.SEND_LIST_FILES.ordinal()) {
                //
            }
        }
    }


}
