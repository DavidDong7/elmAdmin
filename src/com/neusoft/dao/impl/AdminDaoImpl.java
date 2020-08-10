package com.neusoft.dao.impl;

import com.neusoft.dao.AdminDao;
import com.neusoft.domain.Admin;
import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    private Connection conn=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;

    @Override
    public Admin getAdminByNameByPass(String adminName, String password)
    {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE adminName = ? AND password = ?";
        try{
            conn = JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,adminName);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setPassword(rs.getNString("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return admin;
    }
}
