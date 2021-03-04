package com.leolian.javaweb.smart.framework;

import com.leolian.javaweb.smart.framework.classloader.ClassUtil;
import com.leolian.javaweb.smart.framework.helper.*;

/**
 * 加载相应的 Helper 类
 *
 */
public final class HelperLoader {

    public static void init() {
        // AopHelper要在IocHelper之前加载
        // 因为首先需要通过AopHelper获取代理对象，然后才能通过IocHelper进行依赖注入
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName(), true);
        }
    }

    public static void main(String[] args) {
        init();
    }
}
