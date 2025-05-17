package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 根据ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象
     */
    User getUserById(Integer userId);
    
    /**
     * 获取所有用户
     * 
     * @return 用户列表
     */
    List<User> getAllUsers();
    
    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUsername(String username);
    
    /**
     * 根据邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象
     */
    User getUserByEmail(String email);
    
    /**
     * 根据创建时间范围查询用户
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 用户列表
     */
    List<User> getUsersByCreatedAtRange(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 创建用户
     * 
     * @param user 用户对象
     * @return 是否成功
     */
    boolean createUser(User user);
    
    /**
     * 更新用户信息
     * 
     * @param user 用户对象
     * @return 是否成功
     */
    boolean updateUser(User user);
    
    /**
     * 更新用户名
     * 
     * @param userId 用户ID
     * @param username 新的用户名
     * @return 是否成功
     */
    boolean updateUsername(Integer userId, String username);
    
    /**
     * 更新用户密码
     * 
     * @param userId 用户ID
     * @param newPasswordHash 新的密码哈希
     * @return 是否成功
     */
    boolean updatePassword(Integer userId, String newPasswordHash);
    
    /**
     * 更新用户邮箱
     * 
     * @param userId 用户ID
     * @param email 新的邮箱
     * @return 是否成功
     */
    boolean updateEmail(Integer userId, String email);
    
    /**
     * 删除用户
     * 
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean deleteUser(Integer userId);
    
    /**
     * 验证用户密码
     * 
     * @param username 用户名
     * @param passwordHash 密码哈希
     * @return 是否验证成功
     */
    boolean verifyPassword(String username, String passwordHash);
}