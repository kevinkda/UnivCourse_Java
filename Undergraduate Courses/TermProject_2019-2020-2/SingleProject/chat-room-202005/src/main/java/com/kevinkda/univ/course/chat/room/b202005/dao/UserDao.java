package com.kevinkda.univ.course.chat.room.b202005.dao;

import com.kevinkda.univ.course.chat.room.b202005.model.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin KDA on 2020/5/27 17:09
 * @version 1.0.0
 * @project chat-room-202005
 * @package com.kevinkda.univ.course.chat.room.b202005.dao
 * @classname UserDao
 * @apiNote <p></p>
 * @since 1.0.0
 */
public class UserDao {
    //判断用户名是否进行了登录
    public static int getIsLogin(String nickName, ServletContext appliaction1) {
        int number = -1;
        //你是第一个注册登录的人
        if (appliaction1.getAttribute("userLoginList") == null) {
        } else {
            List<User> list = (List<User>) appliaction1.getAttribute("userLoginList");
            for (int i = 0; i < list.size(); i++) {
                User u = list.get(i);
                if (u.getNickName().equals(nickName)) {
                    number = 0;
                    break;
                }
            }
        }
        return number;
    }

    //登录注册
    public static String login(String nickName, int sex, HttpSession session, ServletContext appliaction1) {
        User u = null;
        if (appliaction1.getAttribute("userLoginList") == null) {
            List<User> list = new ArrayList<User>();
            u = new User(nickName, sex);
            list.add(u);
            appliaction1.setAttribute("userLoginList", list);
        } else {
            List<User> list = (List<User>) appliaction1.getAttribute("userLoginList");
            u = new User(nickName, sex);
            list.add(u);
            appliaction1.setAttribute("userLoginList", list);
        }
        session.setAttribute("user", u);
        return "admin/index.jsp";
    }
}
