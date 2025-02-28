/*
 * Copyright (c) 2020 Kevin KDA. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevin.exercise201910253;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Kevin KDA on 2019/10/25 11:36
 * @version 1.0
 * @project Java_Course_Assignments
 * @package com.kevin.exercise201910252
 * @classname Server_Test
 * @description TODO
 * @interface/enum
 */
public class Server_Test extends Thread{
    ServerSocket server = null;
    Socket socket = null;

    public Server_Test(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        super.run();
        try {
            System.out.println("wait client connect...");
            socket = server.accept();
            new sendMessThread().start();//连接并返回socket后，再启用发送消息线程
            System.out.println(socket.getInetAddress().getHostAddress() + "SUCCESS TO CONNECT...");
            InputStream in = socket.getInputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                System.out.println("client saying: " + new String(buf, 0, len));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class sendMessThread extends Thread {
        @Override
        public void run() {
            super.run();
            Scanner scanner = null;
            OutputStream out = null;
            try {
                if (socket != null) {
                    scanner = new Scanner(System.in);
                    out = socket.getOutputStream();
                    String in = "";
                    do {
                        in = scanner.next();
                        out.write(("server saying: " + in).getBytes());
                        out.flush();//清空缓存区的内容
                    } while (!in.equals("q"));
                    scanner.close();
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //函数入口
    public static void main(String[] args) {
        Server_Test server = new Server_Test(1234);
        server.start();
    }
}