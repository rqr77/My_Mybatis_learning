package com.utils;

import com.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDEA
 * User:MKL7
 * Date:2019/4/19
 * Time:15:28
 */

public class Executor {
    public <E>List<E> selectList(Mapper mapper, Connection conn){
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try {
            //1. 取出mapper中的数据
            String SQL=mapper.getSql (); //select *from user
            String resultType=mapper.getResultType ();//com.mlk.domain.User
            Class domainClass=Class.forName (resultType);
            //2. 获取PreparedStatement对象
            pstm= conn.prepareStatement (SQL);
            //3. 执行SQL语句 获取结果集
            rs= pstm.executeQuery ();
            //4. 封装结果集
            List<E> list=new ArrayList<E> ();//定义返回值
            while (rs.next ()){
                //实例化要封装的实体类对象
                E obj= (E)domainClass.newInstance ();

                //取出结果集的元信息
                ResultSetMetaData resultSetMetaData= rs.getMetaData ();
                //取出总列数
                int columnCount= resultSetMetaData.getColumnCount ();
                //遍历
                for (int i=1;i<=columnCount;i++) {
                    //获取每列的名称, 列名的序号是从1开始的
                    String columnName=resultSetMetaData.getColumnName (i);
                    //根据得到的列名获取每列的值
                    Object columnValue = rs.getObject (columnName);
                    //给obj赋值: 使用java内省机制(借助PropertyDescriptor实现属性的封装)
                    PropertyDescriptor pd=new PropertyDescriptor (columnName,domainClass);//要求: 实体类的属性和数据库表的列名保持一致
                    //获取他的写入方法
                    Method writeMethod = pd.getWriteMethod ();
                    //把获取的列的的 给对象赋值
                    writeMethod.invoke (obj,columnValue);
                }
                //把赋好值的对象加到集合中
                list.add (obj);
            }
            return  list;
        }catch (Exception e){
            throw  new RuntimeException (e);
        }finally {
            release (pstm,rs);
        }
    }

    private void release(PreparedStatement pstm, ResultSet rs){
        if (rs!=null){
            try {
                rs.close ();
            }catch (Exception e){
                e.printStackTrace ();
            }
        }
    }
}
