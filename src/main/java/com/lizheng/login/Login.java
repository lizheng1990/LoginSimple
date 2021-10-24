package com.lizheng.login;

import com.lizheng.mysql.ConnectMysql;
import com.lizheng.mysql.UseMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
//        response.getWriter().append("post Method is using");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        String regEx = "[^0-9a-zA-Z_-]";
        Pattern p = Pattern.compile(regEx);
        Matcher u = p.matcher(username);
        Matcher pw = p.matcher(password);
        String msg = "{";
        if ((username != null) && (password != null) && (!"".equals(username)) && (!"".equals(password))
                && (!username.trim().isEmpty()) && (!password.trim().isEmpty())) {
            if ((username.length() > 2) && (username.length() < 17) && (password.length() > 2)
                    && (password.length() < 17)) {
                if ((!u.find()) && (!pw.find())) {
                    if (request.getSession().getAttribute("username") == null) {
                        ConnectMysql connSql = new ConnectMysql();
                        UseMysql mySql = new UseMysql(connSql.conn);
                        boolean result = mySql.PLogin(username, password);
                        System.out.println(result);
                        if (result) {
                            msg = msg + "\"status\":200,\"message\":\"恭喜你： " + username + "，登录成功\"}";
                            HttpSession session = request.getSession();
                            session.setAttribute("username", username);
                            session.setMaxInactiveInterval(1800);
                            //获取session的Id
//                            String sessionId = session.getId();
//                            System.out.println("sessionId=" + sessionId);
//                            System.out.println("seesionName"+session.getAttributeNames());
                        } else {
                            msg = msg + "\"status\":4002,\"message\":\"登录失败，用户名或密码错误\"}";
                        }
                    } else if (request.getSession().getAttribute("username").equals(username)) {
                        msg = msg + "\"status\":4004,\"message\":\"登录失败，该用户已登录\"}";
                    } else {
                        msg = msg + "\"status\":4005,\"message\":\"登录失败，已有其他用户登录\"}";
                    }
                } else
                    msg = msg + "\"status\":4003,\"message\":\"登录失败，用户名或密码不能包含特殊字符\"}";
            } else
                msg = msg + "\"status\":4001,\"message\":\"登录失败，用户名或密码格式错误\"}";
        } else {
            msg = msg + "\"status\":4000,\"message\":\"登录失败，用户名或密码不能为空\"}";
        }
        response.getWriter().append(msg);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
//        response.getWriter().append("get Method is using");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        String msg = "{";
        if (username != null && password != null) {
            if (username.length() > 2 && username.length() < 17 && password.length() > 2 && password.length() < 17) {
                if (username.equals("lizheng") && password.equals("123456")) {
                    msg += "\"status\":200,\"message\":\"" + username + ",恭喜你，登录成功！\"}";
                } else {
                    msg += "\"status\":400,\"message\":\"用户名或密码错误！\"}";
                }
            } else {
                msg += "\"status\":400,\"message\":\"用户名或密码长度错误！\"}";
            }
        } else {
            msg += "\"status\":400,\"message\":\"用户名或密码为空！\"}";
        }
        response.getWriter().append(msg);
    }
}
