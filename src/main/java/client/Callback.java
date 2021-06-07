package client;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

@FunctionalInterface
public interface Callback {
    void call(ByteBuf msg) throws IOException;
}
