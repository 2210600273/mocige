package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
public class User {
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码哈希
     */
    private String passwordHash;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}