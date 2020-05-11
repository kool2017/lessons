package com.kool.lessons.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * @author luyu
 */
public class FileReceiver {
    public static final String FILE_PATH = "F:\\tmp\\";

    public void startServer() {
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            ServerSocket serverSocket = new ServerSocket(9999);

            while (true) {
                System.out.println("等待");
                Socket socket = serverSocket.accept();
                System.out.println("收到连接");
                InputStream inputStream = socket.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);

                fileOutputStream = new FileOutputStream(new File(FILE_PATH + UUID.randomUUID().toString() + ".upload"));
                byte[] buff = new byte[1024 * 1024];
                int hasRead = 0;
                while ((hasRead = bufferedInputStream.read(buff)) != -1) {
                    fileOutputStream.write(buff, 0, hasRead);
                    fileOutputStream.flush();
                }

                System.out.println("完成文件传输");
                fileOutputStream.close();
                bufferedInputStream.close();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FileReceiver fileReceiver = new FileReceiver();
        fileReceiver.startServer();
    }
}
