package com.ebook.springboot.serviceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.ebook.springboot.dao.UserDAO;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.pojo.Alert;
import com.ebook.springboot.pojo.LoginState;
import com.ebook.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 用户进行注册，登录等操作的业务实现层
 *
 * @author lz
 * @date 2019-5-13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Value("${name.user}")
    String userAttribute;

    @Value("${root.user}")
    String rootOfUser;

    @Value("${root.admin}")
    String rootOfAdmin;

    @Value("${root.banned}")
    String rootOfBanned;

    @Override
    public Alert registry(String userName, String password,
                          String Email, String tel, HttpServletRequest request){
        Alert alert = new Alert("注册");
        if(!checkEmail(Email)){
            alert.setAlertType("illegal");
            alert.setTips("邮箱");
            return alert;
        }
        if(!checkTel(tel)){
            alert.setAlertType("illegal");
            alert.setTips("手机号码");
            return alert;
        }
        if(userDAO.findByUserName(userName)!=null){
            alert.setAlertType("duplicate");
            alert.setTips("用户名");
            return alert;
        }
        if(userDAO.findByEmailAddress(Email)!=null){
            alert.setAlertType("duplicate");
            alert.setTips("邮箱");
            return alert;
        }
        if(userDAO.findByUserTel(tel)!=null){
            alert.setAlertType("duplicate");
            alert.setTips("手机号码");
            return alert;
        }
        User user=new User();
        user.setUserName(userName);
        user.setEmailAddress(Email);
        user.setUserPassword(password);
        user.setUserTel(tel);
        user.setRoot(rootOfUser);
        userDAO.save(user);
        HttpSession session=request.getSession();
        session.setAttribute(userAttribute,user);
        alert.setAlertType("success");
        alert.setTips(userName);
        alert.setRoot(rootOfUser);
        return alert;
    }

    @Override
    public Alert login(String userName,String password,HttpServletRequest request){
        Alert alert=new Alert("登录");
        User user=userDAO.findByUserName(userName);
        if(user==null){
            alert.setAlertType("notFound");
            alert.setTips("用户名");
            return alert;
        }
        if(!user.getUserPassword().equals(password)){
            alert.setAlertType("notMatch");
            alert.setTips("密码");
            return alert;
        }
        HttpSession session=request.getSession();
        session.setAttribute(userAttribute,user);
        alert.setTips(userName);
        alert.setAlertType("success");
        alert.setRoot(user.getRoot());
        return alert;
    }

    @Override
    public LoginState getLoginState(HttpServletRequest request){
        HttpSession session=request.getSession();
        if(session.getAttribute(userAttribute)==null){
            LoginState loginState=new LoginState(false);
            return loginState;
        }
        else {
            LoginState loginState=new LoginState(true);
            User user=(User)session.getAttribute(userAttribute);
            loginState.setUserName(user.getUserName());
            loginState.setUserRoot(user.getRoot());
            return loginState;
        }
    }

    @Override
    public List<User> getAllUser(){
        return userDAO.findAll();
    }

    @Override
    public void banUser(int id){
        User user=userDAO.findByUserId(id);
        user.setRoot(rootOfBanned);
        userDAO.save(user);
    }

    @Override
    public void unBanUser(int id){
        User user=userDAO.findByUserId(id);
        user.setRoot(rootOfUser);
        userDAO.save(user);
    }
    private boolean checkEmail(String Email){
        String emailString =  "^(\\w)+@\\w+(\\.[a-zA-Z_]+)+$";
        Pattern p=Pattern.compile(emailString);
        Matcher m=p.matcher(Email);
        return m.find();
    }
    private boolean checkTel(String tel){
        String telString = "^1\\d{10}$";
        Pattern p=Pattern.compile(telString);
        Matcher m= p.matcher(tel);
        return m.find();
    }
}
