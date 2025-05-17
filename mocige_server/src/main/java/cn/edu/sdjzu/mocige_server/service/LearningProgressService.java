package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.LearningProgress;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 学习进度服务接口
 */
public interface LearningProgressService {
    
    /**
     * 根据ID查询学习进度
     * 
     * @param progressId 进度ID
     * @return 学习进度对象
     */
    LearningProgress getProgressById(Integer progressId);
    
    /**
     * 获取所有学习进度
     * 
     * @return 学习进度列表
     */
    List<LearningProgress> getAllProgress();
    
    /**
     * 根据用户ID查询学习进度
     * 
     * @param userId 用户ID
     * @return 学习进度列表
     */
    List<LearningProgress> getProgressByUserId(Integer userId);
    
    /**
     * 根据单词ID查询学习进度
     * 
     * @param wordId 单词ID
     * @return 学习进度列表
     */
    List<LearningProgress> getProgressByWordId(Integer wordId);
    
    /**
     * 根据用户ID和单词ID查询学习进度
     * 
     * @param userId 用户ID
     * @param wordId 单词ID
     * @return 学习进度
     */
    LearningProgress getProgressByUserIdAndWordId(Integer userId, Integer wordId);
    
    /**
     * 根据最后复习时间范围查询
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 学习进度列表
     */
    List<LearningProgress> getProgressByLastReviewedRange(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 创建学习进度
     * 
     * @param progress 学习进度对象
     * @return 是否成功
     */
    boolean createProgress(LearningProgress progress);
    
    /**
     * 更新学习进度
     * 
     * @param progress 学习进度对象
     * @return 是否成功
     */
    boolean updateProgress(LearningProgress progress);
    
    /**
     * 更新最后复习时间
     * 
     * @param progressId 进度ID
     * @param lastReviewed 最后复习时间
     * @return 是否成功
     */
    boolean updateLastReviewed(Integer progressId, LocalDateTime lastReviewed);
    
    /**
     * 更新复习次数
     * 
     * @param progressId 进度ID
     * @param reviewsCount 复习次数
     * @return 是否成功
     */
    boolean updateReviewsCount(Integer progressId, Integer reviewsCount);
    
    /**
     * 增加复习次数
     * 
     * @param progressId 进度ID
     * @return 是否成功
     */
    boolean incrementReviewsCount(Integer progressId);
    
    /**
     * 更新掌握程度
     * 
     * @param progressId 进度ID
     * @param masteryLevel 掌握程度
     * @return 是否成功
     */
    boolean updateMasteryLevel(Integer progressId, Integer masteryLevel);
    
    /**
     * 删除学习进度
     * 
     * @param progressId 进度ID
     * @return 是否成功
     */
    boolean deleteProgress(Integer progressId);
}