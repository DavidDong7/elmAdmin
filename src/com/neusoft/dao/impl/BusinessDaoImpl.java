package com.neusoft.dao.impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.domain.Business;
import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;


    @Override
    public List<Business> showBusinessAll(String businessName, String businessAddress)
    {
        List<Business> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where 1=1");
        if (businessName != null && !businessName.equals("")){
            //传入了商家名
            sql.append(" and businessName like '%").append(businessName).append("%'");
            System.out.println(sql);
        }
        if (businessAddress != null && !businessAddress.equals("")){
            //传入了商家名
            sql.append(" and businessAddress like '%").append(businessAddress).append("%'");
            System.out.println(sql);
        }

        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Business business = new Business();
                business.setBusinessId(rs.getInt("BusinessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("DeliveryPrice"));
                //TODO
                list.add(business);
            }
          } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return list;
    }


    @Override
    public int saveBusiness(String businessName)
    {
        int businessId = 0;
        //附带一个初始密码
        String sql = "insert into business(businessName,password)values(?,'123456')";
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,businessName);//输入商家名之后 在rs.getInt()中调用第一列 然后自增，再返回值建立新的商家Id
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                businessId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return businessId;
    }

    @Override
    public Business getBusinessByNameByPass(Integer businessId, String password)
    {
        Business business = null;
        String sql = "select * from business where businessId = ? and password = ?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            while (rs.next()){
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return business;
    }


    @Override
    public Integer remove(Integer businessId)
    {
        Integer result = 0;
        String sql = "delete from business where businessId = ?";
        try{
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            result = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {

            result = 0;
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return result;
    }



    @Override
    public Business getBusinessBybusinessId(Integer businessId)
    {
        Business business = new Business();
        StringBuffer sql = new StringBuffer("select * from business where businessId = ?");
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1,businessId);
            rs = pstmt.executeQuery();
            while (rs.next()){
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return business;
    }


    @Override
    public int upDate(Business business)
    {
        int result = 0;
        String sql ="update business set businessName = ?, " +
                "businessAddress =?,businessExplain=?" +
                ",starPrice=?,deliveryPrice=? where businessId = ? ";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, business.getBusinessName());
            pstmt.setString(2, business.getBusinessAddress());
            pstmt.setString(3, business.getBusinessExplain());
            pstmt.setDouble(4, business.getStarPrice());
            pstmt.setDouble(5, business.getDeliveryPrice());
            pstmt.setInt(6, business.getBusinessId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return result;
    }
}

