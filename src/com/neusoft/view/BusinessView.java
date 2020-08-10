package com.neusoft.view;

import com.neusoft.domain.Business;

public interface BusinessView {
    public void showBusinessAll();
    public void showBusinessBySearch();
    public void saveBusiness();


    public Business login();
    public void remove();
    public void showBusinessInfo(Integer businessId);
    public void upDateBusinessInfo(Integer businessId);
}
