package com.neusoft.view.impl;

import com.neusoft.dao.AdminDao;
import com.neusoft.dao.impl.AdminDaoImpl;
import com.neusoft.domain.Admin;
import com.neusoft.view.AdminView;

import java.util.Scanner;

public class AminViewImpl implements AdminView {
    private Scanner input = new Scanner(System.in);

    @Override
    public Admin login() {
        System.out.println("请输入管理员的用户名：");
        String adminName = input.next();
        System.out.println("请输入管理员的密码：");
        String password = input.next();
        AdminDao dao = new AdminDaoImpl();

        return dao.getAdminByNameByPass(adminName,password);
    }
}
