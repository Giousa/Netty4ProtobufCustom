package com.giousa.netty4protobufcustom.code;

import com.giousa.netty4protobufcustom.model.PBPlayer;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Description:
 * Author:Giousa
 * Date:2017/2/16
 * Email:65489469@qq.com
 */
public class ProtobufEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        PBPlayer pbPlayer = (PBPlayer) o;
        byte[] bytes = pbPlayer.encode();
        System.out.println("bytes = "+ Arrays.toString(bytes));
        byteBuf.writeBytes(bytes);
    }
}
