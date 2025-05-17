package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Statistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 统计Mapper接口
 */
@Mapper
public interface StatisticMapper extends BaseMapper<Statistic, Integer> {
    
    /**
     * 根据用户ID查询统计信息
     * 
     * @param userId 用户ID
     * @return 统计信息
     */
    Statistic selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 根据总学习时间范围查询
     * 
     * @param minTime 最小时间
     * @param maxTime 最大时间
     * @return 统计信息列表
     */
    List<Statistic> selectByTotalStudyTimeRange(@Param("minTime") Integer minTime, @Param("maxTime") Integer maxTime);
    
    /**
     * 根据总学习单词数范围查询
     * 
     * @param minCount 最小数量
     * @param maxCount 最大数量
     * @return 统计信息列表
     */
    List<Statistic> selectByTotalWordsLearnedRange(@Param("minCount") Integer minCount, @Param("maxCount") Integer maxCount);
    
    /**
     * 更新总学习时间
     * 
     * @param statId 统计ID
     * @param totalStudyTime 总学习时间
     * @return 影响行数
     */
    int updateTotalStudyTime(@Param("statId") Integer statId, @Param("totalStudyTime") Integer totalStudyTime);
    
    /**
     * 更新总学习单词数
     * 
     * @param statId 统计ID
     * @param totalWordsLearned 总学习单词数
     * @return 影响行数
     */
    int updateTotalWordsLearned(@Param("statId") Integer statId, @Param("totalWordsLearned") Integer totalWordsLearned);
    
    /**
     * 增加学习时间
     * 
     * @param userId 用户ID
     * @param additionalTime 增加的时间
     * @return 影响行数
     */
    int incrementStudyTime(@Param("userId") Integer userId, @Param("additionalTime") Integer additionalTime);
    
    /**
     * 增加学习单词数
     * 
     * @param userId 用户ID
     * @param additionalWords 增加的单词数
     * @return 影响行数
     */
    int incrementWordsLearned(@Param("userId") Integer userId, @Param("additionalWords") Integer additionalWords);
}