package com.mybatis.io;

import java.io.InputStream;

/*使用类加载器读取配置文件*/
public class Resources {

    public static InputStream getResourceAsStream(String s) {
        /*拿到当前字节码问价Resources.class
        * 获取字节码文件的类加载器
        * 根据类加载器来读取配置文件
        * */
        return Resources.class.getClassLoader().getResourceAsStream(s);
    }
}
