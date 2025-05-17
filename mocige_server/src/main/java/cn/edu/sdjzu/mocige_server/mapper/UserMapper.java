package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User, Integer> {
    
    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    User selectByUsername(@Param("username") String username);
    
    /**
     * 根据邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象
     */
    User selectByEmail(@Param("email") String email);
    
    /**
     * 根据创建时间范围查询用户
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 用户列表
     */
    List<User> selectByCreatedAtRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    
    /**
     * 更新用户名
     * 
     * @param userId 用户ID
     * @param username 新的用户名
     * @return 影响行数
     */
    int updateUsername(@Param("userId") Integer userId, @Param("username") String username);
    
    /**
     * 更新密码哈希
     * 
     * @param userId 用户ID
     * @param passwordHash 新的密码哈希
     * @return 影响行数
     */
    int updatePasswordHash(@Param("userId") Integer userId, @Param("passwordHash") String passwordHash);
    
    /**
     * 更新邮箱
     * 
     * @param userId 用户ID
     * @param email 新的邮箱
     * @return 影响行数
     */
    int updateEmail(@Param("userId") Integer userId, @Param("email") String email);
}