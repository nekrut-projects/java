package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import shared.Marker;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class InHandler extends ChannelInboundHandlerAdapter {
    private static final String userDir = "serverDir";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        ByteBuf msg = (ByteBuf) o;
        byte marker = msg.readByte();
        if (marker == Marker.DOWNLOAD_FILE.getValue()){
            int lengthName = msg.readInt();
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < lengthName; i++) {
                name.append(msg.readByte());
            }
            byte[] contentFile = Files.readAllBytes(Paths.get(userDir,name.toString()));
            ByteBuffer message = ByteBuffer.allocate(1 + 4 +
                    name.toString().getBytes(StandardCharsets.UTF_8).length + contentFile.length);
            message.put(Marker.DOWNLOAD_FILE.getValue())
                    .putInt(lengthName)
                    .put(name.toString().getBytes())
                    .put(contentFile)
                    .flip();
            ctx.fireChannelRead(message);
        } else if (marker == Marker.UPLOAD_FILE.getValue()) {
            int lengthName = msg.readInt();
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < lengthName; i++) {
                name.append((char) msg.readByte());
            }
            ByteBuffer contentFile = msg.nioBuffer();

            Path path = Paths.get(userDir, name.toString());
            RandomAccessFile file = new RandomAccessFile(path.toFile(), "rw");
            FileChannel channel = file.getChannel();
            channel.write(contentFile);
            channel.close();
        } else if (marker == Marker.SEND_LIST_FILES.getValue()) {
            ctx.fireChannelRead(getServerFiles());
        }
    }

    public ByteBuffer getServerFiles() {
        String listFiles = null;
        try {
            listFiles = Files.list(Paths.get(userDir))
                    .map(n -> n.getFileName().toString())
                    .collect(Collectors.joining(","));

        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteBuffer message = ByteBuffer.allocate(1 + listFiles.getBytes(StandardCharsets.UTF_8).length);
        message.put(Marker.SEND_LIST_FILES.getValue())
                .put(listFiles.getBytes(StandardCharsets.UTF_8));
        return message;
    }


}
