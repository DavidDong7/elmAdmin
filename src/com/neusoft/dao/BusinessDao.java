package com.neusoft.dao;

import com.neusoft.domain.Business;

import java.util.List;

public interface BusinessDao {
    //显示所有商家列表  可选输入businessName和businessAddress
    public List<Business>  showBusinessAll(String businessName,String businessAddress);
    public int saveBusiness(String businessName);

    public Business getBusinessByNameByPass(Integer businessId,String password);
    public Integer remove(Integer businessId);
    public int upDate(Business business);
    public Business getBusinessBybusinessId(Integer businessId);
}