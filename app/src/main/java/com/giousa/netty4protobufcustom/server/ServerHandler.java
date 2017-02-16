package com.giousa.netty4protobufcustom.server;

import com.giousa.netty4protobufcustom.model.PBPlayer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Description:
 * Author:Giousa
 * Date:2017/2/16
 * Email:65489469@qq.com
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerHandler msg:"+msg);
        PBPlayer pbPlayer = (PBPlayer) msg;
        System.out.println("name="+pbPlayer.name+"  age="+pbPlayer.age);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
