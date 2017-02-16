package com.giousa.netty4protobufcustom.client;

import com.giousa.netty4protobufcustom.code.ProtobufDecoder;
import com.giousa.netty4protobufcustom.code.ProtobufEncoder;
import com.giousa.netty4protobufcustom.model.PBPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Description:
 * Author:Giousa
 * Date:2017/2/16
 * Email:65489469@qq.com
 */
public class Client {

    public static String host = "127.0.0.1";
    public static int port = 7878;
    public static int age = 0;
    /**
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ProtobufDecoder());
                            pipeline.addLast(new ProtobufEncoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    });

            // 连接服务端
            Channel ch = b.connect(host, port).sync().channel();

            // 控制台输入
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                System.out.println("请输入：");
                String line = in.readLine();
                if (line == null) {
                    continue;
                }
                System.out.println("client msg:"+getPlayer());
                ch.writeAndFlush(getPlayer());
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    private static PBPlayer getPlayer(){
        return new PBPlayer.Builder().age(age++).name("giousa").playerId(333l).build();
    }
}
