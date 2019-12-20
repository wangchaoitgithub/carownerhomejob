package com.zhiguan.carownerhomecrm.servlet;

import java.util.ResourceBundle;

public class ResourceBundleImp {
	
    private static ResourceBundle resourceFileDirBundle = null;

    /**
     * 获得关键字的值
     * @param key String 关键字
     * @return String 值
     */
    public static String getFileDirValue(String key) {
        if(resourceFileDirBundle==null) {
            instantiation();
        }
        return resourceFileDirBundle.getString(key);
    }

    /**
     * 实例化配置文件
     */
    public static void instantiation() {
    	resourceFileDirBundle = ResourceBundle.getBundle("fileDir");
    }

}
