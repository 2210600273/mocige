package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 反馈实体类
 */
@Data
public class Feedback {
    /**
     * 反馈ID
     */
    private Integer feedbackId;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 评论
     */
    private String comment;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}