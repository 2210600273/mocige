package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.User;
import cn.edu.sdjzu.mocige_server.mapper.UserMapper;
import cn.edu.sdjzu.mocige_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
    
    @Override
    public List<User> getUsersByCreatedAtRange(LocalDateTime startTime, LocalDateTime endTime) {
        return userMapper.selectByCreatedAtRange(startTime, endTime);
    }
    
    @Override
    public boolean createUser(User user) {
        return userMapper.insert(user) > 0;
    }
    
    @Override
    public boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }
    
    @Override
    public boolean updateUsername(Integer userId, String username) {
        return userMapper.updateUsername(userId, username) > 0;
    }
    
    @Override
    public boolean updatePassword(Integer userId, String newPasswordHash) {
        return userMapper.updatePasswordHash(userId, newPasswordHash) > 0;
    }
    
    @Override
    public boolean updateEmail(Integer userId, String email) {
        return userMapper.updateEmail(userId, email) > 0;
    }
    
    @Override
    public boolean deleteUser(Integer userId) {
        return userMapper.deleteById(userId) > 0;
    }
    
    @Override
    public boolean verifyPassword(String username, String passwordHash) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPasswordHash().equals(passwordHash);
    }
}