package com.mybatis.sqlsession;

import com.cfg.Mapper;
import com.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String, Mapper> mappers,Connection connection) {
        this.mappers = mappers;
        this.connection= connection;
    }
    //注意注解不能少！！！相当于方法的父接口重写，不写只是子类方法与父类无关，获取不到method
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodname =method.getName();
        /*System.out.println(method);*/
/*        String methodname = "findAll";*/
        String classname = method.getDeclaringClass().getName();
/*        String classname = "com.dao.UserDao";*/

        System.out.println(classname);
        String key= classname+"."+methodname;//全限定类名+方法名
        System.out.println(key);
        System.out.println(mappers.containsKey(key));
        Mapper mapper =mappers.get(key);//获取操作mapper
        System.out.println(mappers);
        System.out.println(mapper);
        if (mapper==null){
            throw new IllegalArgumentException("params???");
        }
        return new Executor().selectList(mapper,connection);
    }
}
