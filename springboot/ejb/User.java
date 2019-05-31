package com.ebook.springboot.ejb;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 用户的实体类
 * @author lz
 * @date 2019-5-6
 */
@Entity
@Table(name="user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private String userPassword;
    private String userTel;
    private String emailAddress;
    private String root;
    private Timestamp registryTime;
    private Set<Orders> orders;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 15)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password", nullable = false, length = 20)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_tel", nullable = false, length = 15)
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "email_address", nullable = true, length = 50)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "root", nullable = true, length = 10)
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Basic
    @Column(name = "registry_time",insertable = false,updatable = false)
    public Timestamp getRegistryTime() {
        return registryTime;
    }

    public void setRegistryTime(Timestamp registryTime) {
        this.registryTime = registryTime;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.MERGE)
    @JsonBackReference
    public Set<Orders> getOrders(){
        return orders;
    }

    public void setOrders(Set<Orders> orders){
        this.orders=orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
