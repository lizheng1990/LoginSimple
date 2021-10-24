package com.lizheng.mysql;

import java.sql.*;
import java.util.*;

public class testMysql {
    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://8.142.92.43:3306/loginProject?useUnicode=true&autoReconnect=true&characterEncoding=utf-8","root","Liz48225248");
//        System.out.println(conn);
//        Statement smt = conn.createStatement();
//        String sql = "select * from userinfo;";
//        ResultSet res = smt.executeQuery(sql);
//        List<Map<String,String>> users = new ArrayList<Map<String, String>>();
//        while (res!=null&&res.next()){
//            ResultSetMetaData rsm = res.getMetaData();
//            Map<String,String> user = new HashMap<>();
//            for(int i=1;i<rsm.getColumnCount();i++){
//                user.put(rsm.getColumnName(i),res.getString(i));
//            }
//            users.add(user);
//        }
//        Iterator<Map<String,String>> it = users.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
//        smt.close();
//        conn.close();

//        ConnectMysql cm =new ConnectMysql();
        testMysql tm =new testMysql();
        String path = System.getProperty("user.dir");
        System.out.println("path" + path);
        System.out.println(tm.getClass().getResource("").getPath() + "/inter.properties");
        System.out.println(path);
        tm.testResource();

    }


    public void testResource() {
        String path1 = this.getClass().getResource("").getPath();
        System.out.println("path1" + path1);

        String path2 = this.getClass().getResource("/").getPath();
        System.out.println("path2" + path2);

        String path3 = this.getClass().getClassLoader().getResource("user.xml").getPath();
        System.out.println("path3" + path3);

        String path4 = this.getClass().getClassLoader().getResource("").getPath();
        System.out.println("path4" + path4);
    }
}
