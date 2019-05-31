package com.ebook.springboot.pojo;

/**
 * 用于前端呈现弹出窗口的辅助类
 * @author lz
 * @date 2019-5-6
 */
public class Alert {
    private String usingType;

    private String tips;

    private String alertType;

    private String root;

    public String getUsingType() {
        return usingType;
    }

    public void setUsingType(String usingType) {
        this.usingType = usingType;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public void setRoot(String root){
        this.root=root;
    }

    public String getRoot(){
        return root;
    }

    public Alert(String usingType){
        this.usingType=usingType;
    }
}
