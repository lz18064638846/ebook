package com.ebook.springboot.daoimpl;

import java.util.List;
import com.ebook.springboot.dao.UserDAO;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDAO {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmailAddress(String Email) {
        return userRepository.findByEmailAddress(Email);
    }

    @Override
    public User findByUserTel(String tel) {
        return userRepository.findByUserTel(tel);
    }

    @Override
    public User findByUserId(int id){
        return userRepository.findByUserId(id);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }
}
