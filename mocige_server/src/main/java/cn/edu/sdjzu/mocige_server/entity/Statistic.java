package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 统计实体类
 */
@Data
public class Statistic {
    /**
     * 统计ID
     */
    private Integer statId;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 总学习时间
     */
    private Integer totalStudyTime;
    
    /**
     * 总学习单词数
     */
    private Integer totalWordsLearned;
}