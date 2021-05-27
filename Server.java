package lesson_2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Server {

    private ByteBuffer buffer;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private String currentDir = "./";

    public Server() throws Exception {
        buffer = ByteBuffer.allocate(100);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8189));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (serverSocketChannel.isOpen()) {

            selector.select(); // block

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    handleAccept(key);
                }
                if (key.isReadable()) {
                    handleRead(key);
                }
                keyIterator.remove();
            }
        }
    }

    private void handleRead(SelectionKey key) throws IOException, URISyntaxException {
        SocketChannel channel = (SocketChannel) key.channel();
        StringBuilder s = new StringBuilder();
        int r;
        while (true) {
            r = channel.read(buffer);
            if (r == -1) {
                channel.close();
                return;
            }
            if (r == 0) {
                break;
            }
            buffer.flip();
            while (buffer.hasRemaining()) {
                s.append((char) buffer.get());
            }
            buffer.clear();
         }

        String message = s.toString();
        if (message.startsWith("ls")) {
            Path dir = Paths.get(currentDir);
            List<Path> listFiles = new LinkedList<>();

            Files.walkFileTree(dir,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            listFiles.add(file);
                            return super.visitFile(file, attrs);
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            listFiles.add(dir);
                            return super.preVisitDirectory(dir, attrs);
                        }
                    });
            for (Path f : listFiles) {
                channel.write(ByteBuffer.wrap((f.getFileName() + "\t").getBytes()));
            }
            channel.write(ByteBuffer.wrap("\n ~$ ".getBytes()));
        } else if (message.startsWith("cat")){
            String addressFile = message.split(" ")[1];
            Path path = Paths.get(addressFile);
            if (Files.exists(path)) {
                channel.write(ByteBuffer.wrap(Files.readAllBytes(path)));
            }
            channel.write(ByteBuffer.wrap("\n ~$ ".getBytes()));
        } else if (message.startsWith("mkdir")) {
            String file = message.split(" ")[1];
            Path path = Paths.get(currentDir, file);
            Files.createDirectory(path);
            channel.write(ByteBuffer.wrap("\n ~$ ".getBytes()));
        } else if (message.startsWith("touch")){
            String file = message.split(" ")[1];
            Path path = Paths.get(currentDir, file);
            Files.createFile(path);
            channel.write(ByteBuffer.wrap("\n ~$ ".getBytes()));
        } else if (message.startsWith("read")){
            String[] inputFromUser = message.split(" ");
            String file = inputFromUser[3];
            String text = inputFromUser[1];
            Path path = Paths.get(file);
            if (Files.exists(path)) {
                Files.write(path, text.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            }
            channel.write(ByteBuffer.wrap("\n ~$ ".getBytes()));
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        SocketChannel channel = serverSocketChannel.accept();
        channel.write(ByteBuffer.wrap("Welcome to server!\n ~$ ".getBytes(StandardCharsets.UTF_8)));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ, "Hello world");
    }
}
