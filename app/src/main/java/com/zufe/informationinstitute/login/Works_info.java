package com.zufe.informationinstitute.login;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class Works_info extends BmobObject{
    private Integer Works_No;
    private Integer Designer_No;
    private String Works_Name;
    private String Works_Price;
    private String Works_Type;

    public String getWorks_Type() {
        return Works_Type;
    }

    public void setWorks_Type(String works_Type) {
        Works_Type = works_Type;
    }

    public Integer getDesigner_No() {
        return Designer_No;
    }

    public void setDesigner_No(Integer designer_No) {
        Designer_No = designer_No;
    }

    public Integer getWorks_No() {
        return Works_No;
    }

    public void setWorks_No(Integer works_No) {
        Works_No = works_No;
    }

    public String getWorks_Name() {
        return Works_Name;
    }

    public void setWorks_Name(String works_Name) {
        Works_Name = works_Name;
    }

    public String getWorks_Price() {
        return Works_Price;
    }

    public void setWorks_Price(String works_Price) {
        Works_Price = works_Price;
    }
}
