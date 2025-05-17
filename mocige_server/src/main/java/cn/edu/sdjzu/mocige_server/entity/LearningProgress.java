package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 学习进度实体类
 */
@Data
public class LearningProgress {
    /**
     * 进度ID
     */
    private Integer progressId;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 单词ID
     */
    private Integer wordId;
    
    /**
     * 最后复习时间
     */
    private LocalDateTime lastReviewed;
    
    /**
     * 复习次数
     */
    private Integer reviewsCount;
    
    /**
     * 掌握程度
     */
    private Integer masteryLevel;
}