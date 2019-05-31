package com.ebook.springboot.pojo;

/**
 * 用于反馈前端目前用户登录状态的辅助类
 *
 * @author lz
 * @date 2019-5-6
 */
public class LoginState {
    private boolean loginState;

    private String userName;

    private String userRoot;

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRoot() {
        return userRoot;
    }

    public void setUserRoot(String userRoot) {
        this.userRoot = userRoot;
    }

    public LoginState(boolean loginState){
        this.loginState=loginState;
    }
}
