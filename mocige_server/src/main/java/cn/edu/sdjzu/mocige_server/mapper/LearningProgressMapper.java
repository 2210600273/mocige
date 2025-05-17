package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.LearningProgress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 学习进度Mapper接口
 */
@Mapper
public interface LearningProgressMapper extends BaseMapper<LearningProgress, Integer> {
    
    /**
     * 根据用户ID查询学习进度
     * 
     * @param userId 用户ID
     * @return 学习进度列表
     */
    List<LearningProgress> selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 根据单词ID查询学习进度
     * 
     * @param wordId 单词ID
     * @return 学习进度列表
     */
    List<LearningProgress> selectByWordId(@Param("wordId") Integer wordId);
    
    /**
     * 根据用户ID和单词ID查询学习进度
     * 
     * @param userId 用户ID
     * @param wordId 单词ID
     * @return 学习进度
     */
    LearningProgress selectByUserIdAndWordId(@Param("userId") Integer userId, @Param("wordId") Integer wordId);
    
    /**
     * 根据最后复习时间范围查询
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 学习进度列表
     */
    List<LearningProgress> selectByLastReviewedRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    
    /**
     * 根据掌握程度查询
     * 
     * @param masteryLevel 掌握程度
     * @return 学习进度列表
     */
    List<LearningProgress> selectByMasteryLevel(@Param("masteryLevel") Integer masteryLevel);
    
    /**
     * 更新复习次数
     * 
     * @param progressId 进度ID
     * @param reviewsCount 复习次数
     * @return 影响行数
     */
    int updateReviewsCount(@Param("progressId") Integer progressId, @Param("reviewsCount") Integer reviewsCount);
    
    /**
     * 更新掌握程度
     * 
     * @param progressId 进度ID
     * @param masteryLevel 掌握程度
     * @return 影响行数
     */
    int updateMasteryLevel(@Param("progressId") Integer progressId, @Param("masteryLevel") Integer masteryLevel);
    
    /**
     * 更新最后复习时间
     * 
     * @param progressId 进度ID
     * @param lastReviewed 最后复习时间
     * @return 影响行数
     */
    int updateLastReviewed(@Param("progressId") Integer progressId, @Param("lastReviewed") LocalDateTime lastReviewed);
}