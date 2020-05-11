package com.kool.lessons.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author luyu
 */
public class FileSender {

    public void send(String filePath) {
        BufferedOutputStream bufferedOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            Socket socket = new Socket("192.168.110.68", 9999);
            OutputStream outputStream = socket.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            fileInputStream = new FileInputStream(new File(filePath));
            byte[] buff = new byte[1024 * 1024];
            int hasRead = 0;
            while ((hasRead = fileInputStream.read(buff)) != -1) {
                bufferedOutputStream.write(buff, 0, hasRead);
                bufferedOutputStream.flush();
            }
            fileInputStream.close();
            bufferedOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FileSender fileSender = new FileSender();
        fileSender.send("F:\\ly\\doc\\ab压测脚本.txt");
    }
}
