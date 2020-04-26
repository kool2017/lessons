package com.kool.lessons;

/**
 * @author luyu
 */
public class MyHashMap<K,V> {
    //hashmap的最大容量，即所有节点数量，2的30次方
    static final int MAXIMUM_CAPACITY = 1 << 30;
    //默认扩容阈值
    static final float INIT_THRETHOLD = 0.75f;
    //hashmap的数据是存储在Node对象中的
    static class Node<K,V> {
        K key;
        V value;
        int hash;
        Node next;
    }
    //table是hashmap数据结构。它是一个数组。hashmap new实例的时候不需要初始化，第一次赋值的时候初始化。
    Node[] table;
    //当前容量
    int size;
    //扩容阈值，当容量size大于阈值时，触发扩容resize()，构造方法执行时初始化。
    int threshold;
    //负载因子，默认0.7f
    float loadFactor;


    //无参构造，初始化负载因子
    MyHashMap(){
        loadFactor = INIT_THRETHOLD;
    }
    //构造方法含容量参数，初始化扩容阈值=大于等于cap的最小的2的指数
    MyHashMap(int cap) {
        loadFactor = INIT_THRETHOLD;
        threshold = tableSizeFor(cap);
    }

    //添加元素，并返回原值
    public V put(K key, V value) {
        return null;
    }

    //获取元素
    public V get(K key) {
        return null;
    }


    //取大于等于cap的最小的2的指数
    public int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(n);
        //无符号右移异或,左边2位变为1
        n |= n >>> 1;
        System.out.println(n);
        //左边4位变为1
        n |= n >>> 2;
        System.out.println(n);
        //左边8位变为1
        n |= n >>> 4;
        System.out.println(n);
        //左边16位变为1
        n |= n >>> 8;
        System.out.println(n);
        //左边32位变为1，int最大值
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
    }
}
