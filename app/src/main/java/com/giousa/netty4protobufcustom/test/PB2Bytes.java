package com.giousa.netty4protobufcustom.test;

import com.giousa.netty4protobufcustom.model.PBPlayer;

import java.io.IOException;
import java.util.Arrays;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/2/8
 * Time:下午9:40
 */

public class PB2Bytes {


    public static void main(String[] args) {

        byte[] bytes = toBytes();

        toPlayer(bytes);
    }

    /**
     * 序列化
     * @return
     * 结果:
     * [8, -51, 2, 16, 23, 26, 6, 103, 105, 111, 117, 115, 97]
     */
    private static byte[] toBytes() {

        PBPlayer.Builder builder = new PBPlayer.Builder();

        builder.age(23).name("giousa").playerId(333l);

        PBPlayer player = builder.build();

        byte[] bytes = player.encode();

        System.out.println("数组:"+Arrays.toString(bytes));

//        System.out.println(player);


        return bytes;
    }

    /**
     * 反序列化
     * @param bs
     */
    private static void toPlayer(byte[] bs){
        try {
            PBPlayer player = PBPlayer.ADAPTER.decode(bs);
            System.out.println("Player:"+player);
            System.out.println("Player name:"+player.name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
