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
        }
    }

