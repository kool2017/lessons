package com.kool.lessons.threadsafe;

/**
 * @author luyu
 */
public class LoggingWidget extends Widget {

    public synchronized void doSomething(){
        System.out.println("son do something");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.doSomething();
    }
    public static void main(String[] args) {
//        new Thread(()->{new LoggingWidget().doSomething();}).start();
//        new Thread(()->{new LoggingWidget().doSomething();}).start();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    new LoggingWidget().doSomething();
                }
            }).start();

        }

    }
}
