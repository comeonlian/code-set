package com.leolian.code.fragment.blog.algorithm;

import java.util.Hashtable;

/**
 * @description: 
 * @author lianliang
 * @date 2019/2/12 14:14
 */
public class LRUTest {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i, i);
        }
        Hashtable<Object, LRUNode> nodes = lruCache.getNodes();
        System.out.println(nodes.size());
        
        lruCache.put("hehe", "xixi");
        
        lruCache.put("he", "li");
        
    }
    
}

