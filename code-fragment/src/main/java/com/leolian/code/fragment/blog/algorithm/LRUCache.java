package com.leolian.code.fragment.blog.algorithm;

import java.util.Hashtable;

/**
 * @description:
 * @author lianliang
 * @date 2019/2/12 14:27
 */
public class LRUCache {
    private Hashtable<Object, LRUNode> nodes; //缓存容器

    private int capacity; //指定容量
    private int size; //当前双向链表的大小
    private LRUNode first;  //链表头
    private LRUNode last;   //链表尾

    public LRUCache(int i) {
        size = 0;
        capacity = i;
        nodes = new Hashtable<Object, LRUNode>(i);//缓存容器
    }

    /**
     * 设置缓存
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        LRUNode lruNode = nodes.get(key);
        if (null != lruNode) {
            moveToFirst(lruNode);
            lruNode.value = value; //缓存值覆盖
        } else { //不存在这个key
            if (size >= capacity) { // 容量满了，删除链表末尾的节点
                nodes.remove(last.key);
                removeLast();
            } else {
                size++;
            }
            LRUNode node = new LRUNode();
            node.key = key;
            node.value = value;
            moveToFirst(node); //新增节点放到头部
            nodes.put(key, node);
        }
    }

    /**
     * 取缓存
     * @param key
     * @return
     */
    public Object get(Object key) {
        LRUNode lruNode = nodes.get(key);
        if (lruNode != null) {
            moveToFirst(lruNode);
            return lruNode.value;
        } else {
            return null;
        }
    }

    /**
     * 从缓存删除某个key
     * @param key
     */
    public void remove(Object key) {
        LRUNode node = nodes.get(key);
        //在链表中删除
        if (node != null) {
            if (last == node) { // 如果删除的节点为最后一个节点
                last = node.prev;
            } else if (first == node) { // 如果删除的节点为第一个节点
                first = node.next;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
        }
        //在Hashtable中删除
        nodes.remove(key);
    }

    /**
     * 移动到链表头部
     * @param node
     */
    private void moveToFirst(LRUNode node) {
        if (node == first)
            return;
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        if (last == node)
            last = node.prev;
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;
        node.prev = null;
        if (last == null)
            last = first;
    }

    /**
     * 从链表尾部移除
     */
    private void removeLast() {
        if (last != null) {
            if (last.prev != null)
                last.prev.next = null;
            else
                first = null;
            last = last.prev;
        }
    }

    /**
     * 清空缓存
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public Hashtable<Object, LRUNode> getNodes() {
        return nodes;
    }

}

/**
 * 节点
 */
class LRUNode {
    Object key;
    Object value;
    LRUNode prev;
    LRUNode next;
}