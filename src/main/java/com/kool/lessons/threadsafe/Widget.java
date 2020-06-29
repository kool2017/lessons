package com.kool.lessons.threadsafe;

/**
 * @author luyu
 */
public class Widget {
    public synchronized void doSomething() {
        System.out.println("father do something");
    }

}
