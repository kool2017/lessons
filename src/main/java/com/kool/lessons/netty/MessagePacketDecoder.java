package com.kool.lessons.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author luyu
 */
public class MessagePacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception
    {
        try {
            if (buffer.readableBytes() > 0) {
                // 待处理的消息包
                byte[] bytesReady = new byte[buffer.readableBytes()];
                buffer.readBytes(bytesReady);
                //这之间可以进行报文的解析处理
                out.add(bytesReady );
            }
        }finally {

        }
    }
}
