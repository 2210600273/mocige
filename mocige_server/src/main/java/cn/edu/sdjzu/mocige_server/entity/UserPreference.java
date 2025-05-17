package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 用户偏好实体类
 */
@Data
public class UserPreference {
    /**
     * 偏好ID
     */
    private Integer preferenceId;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 学习模式
     */
    private String learningMode;
    
    /**
     * 每日单词数
     */
    private Integer dailyWordCount;
}