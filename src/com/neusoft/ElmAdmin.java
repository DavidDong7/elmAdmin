package com.neusoft;

import com.neusoft.domain.Business;
import com.neusoft.view.BusinessView;
import com.neusoft.view.impl.BusinessViewImpl;

import java.util.Scanner;

public class ElmAdmin {
    public static void main(String[] args)
    {
        work();
    }

    public static void work()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统\t\t\t\t|");
        System.out.println("---------------------------------------------------------");

        //调用登录方法

        BusinessView businessView = new BusinessViewImpl();
        Business business = businessView.login();
        if ( business != null) {
            int menu = 0;
            System.out.println("欢迎！");
            //创建一个菜单

            while (menu != 5) {
                System.out.println("=========一级菜单1.查看商家信息  2.修改商家信息  3.更新密码  4.所属商品管理  5.退出系统 =========");
                System.out.println("请选择相应的菜单编号");
                menu = input.nextInt();

                switch (menu) {
                    case 1:
                        businessView.showBusinessInfo(business.getBusinessId());
                        break;
                    case 2:
                        businessView.upDateBusinessInfo(business.getBusinessId());
                        break;
                    case 3:
                        businessView.remove();
                        break;
                    case 4:
                        System.out.println("删除商家");
                        break;
                    case 5:
                        System.out.println("=========欢迎下次光临=========");
                        break;
                    default:
                        System.out.println("没有菜单项");

                }
            }

        }else {
            System.out.println("用户名或密码错误！请重新输入！");
        }
    }
}
