package com.neusoft.view.impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.dao.impl.BusinessDaoImpl;
import com.neusoft.domain.Business;
import com.neusoft.view.BusinessView;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
    Scanner input = new Scanner(System.in);

    @Override
    public void showBusinessAll()
    {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        List<Business> list = dao.showBusinessAll(null, null);
        System.out.println("商家编号\\t商家名称\\t商家地址\\t商家介绍\\t起送费\\t配送费");
        for (Business l : list) {
            System.out.println(l.getBusinessName() + "\t" + l.getBusinessAddress() + "\t" + l.getBusinessExplain() + "\t" + l.getDeliveryPrice() + "\t" + l.getPassword() + "\t" + l.getStarPrice() + "\t" + l.getBusinessId());
        }
    }


    /**
     * 搜索商家
     */
    @Override
    public void showBusinessBySearch()
    {
        String businessName = "";
        String businessAddress = "";
        String inputStr = "";

        System.out.println("是否需要输入商家名称关键字(y/n):");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入商家名称关键字");
            businessName = input.next();
        }
        System.out.println("是否需要商家地址关键字");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入商家地址关键字");
            businessAddress = input.next();
        }
        BusinessDaoImpl dao = new BusinessDaoImpl();
        List<Business> list = dao.showBusinessAll(businessName, businessAddress);
        System.out.println("商家编号\\t商家名称\\t商家地址\\t商家介绍\\t起送费\\t配送费");
        for (Business l : list) {
            System.out.println(l.getBusinessName() + "\t" + l.getBusinessAddress() + "\t" + l.getBusinessExplain() + "\t" + l.getDeliveryPrice() + "\t" + l.getPassword() + "\t" + l.getStarPrice() + "\t" + l.getBusinessId());
        }
    }


    /**
     * 新建商家
     */
    @Override
    public void saveBusiness()
    {
        System.out.println("请输入商家名称");
        String businessName = input.next();

        BusinessDaoImpl dao = new BusinessDaoImpl();
        int businessId = dao.saveBusiness(businessName);
        if (businessId > 0) {
            System.out.println("新建商家成功！商家编号为：" + businessId);
        } else {
            System.out.println("新建商家失败！");
        }


    }


    @Override
    public Business login()
    {
        System.out.println("请输入管理员的用户名：");
        Integer businessId = input.nextInt();
        System.out.println("请输入管理员的密码：");
        String password = input.next();

        BusinessDaoImpl dao = new BusinessDaoImpl();
        return dao.getBusinessByNameByPass(businessId, password);
    }

    @Override
    public void remove()
    {
        System.out.println("请输入商家编号");
        Integer businessId = input.nextInt();

        BusinessDaoImpl dao = new BusinessDaoImpl();
        System.out.println("确认是否删除商家(y/n)");
        if (input.next().equals("y")) {
            int result = dao.remove(businessId);
            if (result == 1) {
                System.out.println("商家删除成功");
            } else {
                System.out.println("商家删除失败");
            }
        }
    }


    @Override
    public void showBusinessInfo(Integer businessId)
    {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        Business business = dao.getBusinessBybusinessId(businessId);
        System.out.println(business);
    }


    @Override
    public void upDateBusinessInfo(Integer businessId)
    {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        Business business = dao.getBusinessBybusinessId(businessId);
        String inputStr = "";
        System.out.println(business);
        System.out.println("请输入需要修改的商家信息（商家名称/商家位置/密码/商家介绍/起送费/配送费）");
        inputStr = input.next();
        if (inputStr.equals("商家名称")) {
            System.out.println("是否确认修改（y/n）");
            inputStr = input.next();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的商家名称");
                business.setBusinessName(input.next());
            }

            int rs = dao.upDate(business);
            if (rs > 0) {
                System.out.println("修改商家名称成功");
            } else {
                System.out.println("修改商家名称失败");
            }
        }else if (inputStr.equals("商家位置")) {
            System.out.println("是否确认修改新的位置(y/n)");
            inputStr = input.next();
            System.out.println();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的商家地址");
                business.setBusinessAddress(input.next());
            }
            int rs = dao.upDate(business);
            if (rs > 0) {
                System.out.println("修改商家地址成功");
            } else {
                System.out.println("修改商家地址失败");
            }
        }else if (inputStr.equals("密码")){
            System.out.println("是否确认修改新的密码(y/n)");
            inputStr = input.next();
            System.out.println();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的密码");
                business.setPassword(input.next());
            }
            int rs = dao.upDate(business);
            if (rs > 0) {
                System.out.println("修改密码成功");
            } else {
                System.out.println("修改密码失败");
            }
        }else if (inputStr.equals("商家介绍")){
            System.out.println("是否确认修改新的商家介绍(y/n)");
            inputStr = input.next();
            System.out.println();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的商家介绍");
                business.setBusinessExplain(input.next());
            }
            int rs = dao.upDate(business);
            if (rs > 0) {
                System.out.println("修改商家介绍成功");
            } else {
                System.out.println("修改商家介绍失败");
            }
        }else if (inputStr.equals("起送费")){
            System.out.println("是否确认修改新的起送费(y/n)");
            inputStr = input.next();
            System.out.println();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的起送费");
                business.setStarPrice(input.nextDouble());
            }
            int rs = dao.upDate(business);
            if (rs > 0) {
                System.out.println("修改起送费成功");
            } else {
                System.out.println("修改起送费失败");
            }
        }else if (inputStr.equals("配送费")){
            System.out.println("是否确认修改新的配送费(y/n)");
            inputStr = input.next();
            System.out.println();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的配送费");
                business.setDeliveryPrice(input.nextDouble());
            }
            int rs = dao.upDate(business);
            if (rs > 0) {
                System.out.println("修改配送费成功");
            } else {
                System.out.println("修改配送费失败");
            }
        }
    }
}