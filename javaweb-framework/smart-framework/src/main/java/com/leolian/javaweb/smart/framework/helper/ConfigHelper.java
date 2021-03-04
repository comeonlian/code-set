package com.leolian.javaweb.smart.framework.helper;

import com.leolian.javaweb.smart.framework.constant.ConfigConstant;
import com.leolian.javaweb.smart.framework.util.PropUtil;

import java.util.Properties;

/**
 * @description: 
 * @author lianliang
 * @date 2018/11/12 16:08
 */
public class ConfigHelper {
    
    private static final Properties CONFIG_PROPS = PropUtil.loadProps(ConfigConstant.CONFIG_FILE);
    
    public static String getJdbcDriver() {
        return PropUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }
    
    public static String getJdbcUrl() {
        return PropUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }
    
    public static String getJdbcUsername() {
        return PropUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }
    
    public static String getJdbcPassowrd() {
        return PropUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }
    
    public static String getAppBasePackage() {
        return PropUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }
    
    public static String getAppJspPath() {
        return PropUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }
    
    public static String getAppAssetPath() {
        return PropUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
    
}
