package com.giousa.netty4protobufcustom.code;

import com.giousa.netty4protobufcustom.model.PBPlayer;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * Description:
 * Author:Giousa
 * Date:2017/2/16
 * Email:65489469@qq.com
 */
public class ProtobufDecoder extends MessageToMessageDecoder<ByteBuf>{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("decode");
        final byte[] array;
        final int length=byteBuf.readableBytes();
        array=new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array,0,length);
        list.add(PBPlayer.ADAPTER.decode(array));
    }
}
