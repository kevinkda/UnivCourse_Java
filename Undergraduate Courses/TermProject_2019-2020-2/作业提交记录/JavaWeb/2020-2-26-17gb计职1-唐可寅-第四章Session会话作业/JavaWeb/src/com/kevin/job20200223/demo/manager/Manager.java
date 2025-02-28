package com.kevin.job20200223.demo.manager;

import com.kevin.job20200223.demo.entity.UserAcc;

import java.util.ArrayList;

/**
 * @author Kevin KDA on 2020/2/21 20:03
 * @version 1.0
 * @project JavaTermProject
 * @package com.kevin.job20200223.demo.manager
 * @classname Manager
 * @description TODO
 * @interface/enum
 */
public class Manager {
    private ArrayList<UserAcc> userAccs = new ArrayList<UserAcc>();

    public int addUser(String[] args) {
        /**
         *检查系统中是否存在传入的用户名，如存在回1，不存在继续完成注册并返回0
         */
        if (duplicateCheck(args[0]) == 1) {
            return 1;
        }
        userAccs.add(new UserAcc(args[0], args[1], args[2], args[3], args[4]));
        return 0;
    }

    public UserAcc getUserByUserAcc(String searchUserAcc) {
        for (UserAcc u :
                userAccs) {
            if (searchUserAcc.equals(u.getStrUserAccount())) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<UserAcc> getUserAll() {
        return userAccs;
    }

    /**
     * @param args: 传入检查字符
     * @return int:返回0表示系统中不存在传入参数，1反之
     * @author Kevin KDA on 2020/2/21 20:31
     * @description Manager / duplicateCheck TODO 提供用于检查系统中是否存在重复项的的方法
     */
    private int duplicateCheck(String args) {

        return 0;
    }

    /**
     * @param args: 传入检查字符串
     * @return int:返回0表示系统中不存在传入参数，1反之
     * @author Kevin KDA on 2020/2/21 20:31
     * @description Manager / duplicateCheck TODO 提供用于检查系统中是否存在重复项的的方法
     */
    private int duplicateCheck(String[] args) {
        return 0;
    }
}
