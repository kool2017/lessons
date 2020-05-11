package com.kool.lessons.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

/**
 * @author luyu
 */
public class FastFileUtil {
    public static void copyByChannelMapped(String from, String to) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        FileChannel fileInChannel = null;
        FileChannel fileOutChannel = null;
        try {
            fileInputStream = new FileInputStream(new File(from));
            fileOutputStream = new FileOutputStream(new File(to));

            fileInChannel = fileInputStream.getChannel();
            fileOutChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
            while (fileInChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();

                fileOutChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
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

    public static void copyByChannel(String from, String to) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        FileChannel fileInChannel = null;
        FileChannel fileOutChannel = null;
        try {
            fileInputStream = new FileInputStream(new File(from));
            fileOutputStream = new FileOutputStream(new File(to));

            fileInChannel = fileInputStream.getChannel();
            fileOutChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            while (fileInChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();

                fileOutChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
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

    public static void copyByStream(String from, String to) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(from));
            fileOutputStream = new FileOutputStream(new File(to));

            byte[] buff = new byte[1024 * 1024];
            int len = 0;
            while ((len = fileInputStream.read(buff)) != -1) {
                fileOutputStream.write(buff, 0, len);
            }
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
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
        String from = "F:\\ly\\装机包\\VisualStudio2015.iso";
        String to1 = "F:\\ly\\temp\\copy\\1.iso";
        String to2 = "F:\\ly\\temp\\copy\\2.iso";
        String to3 = "F:\\ly\\temp\\copy\\3.iso";
//        String from = "F:\\ly\\装机包\\mail.exe";
//        String to1 = "F:\\ly\\temp\\copy\\1.exe";
//        String to2 = "F:\\ly\\temp\\copy\\2.exe";
//        String to3 = "F:\\ly\\temp\\copy\\3.exe";
        long time1 = System.currentTimeMillis();
        System.out.println(new Date());
        copyByStream(from, to1);
        long time2 = System.currentTimeMillis();
        System.out.println(new Date());
        copyByChannel(from, to2);
        long time3 = System.currentTimeMillis();
        System.out.println(new Date());
        copyByChannelMapped(from, to3);
        long time4 = System.currentTimeMillis();
        System.out.println(String.format("copyByStream cost %ss, copyByChannel cost %ss, copyByChannelMapped cost %ss,", (time2 - time1) / 1000, (time3 - time2) / 1000, (time4 - time3) / 1000));
    }
}
