package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Statistic;

import java.util.List;

/**
 * 统计服务接口
 */
public interface StatisticService {
    
    /**
     * 根据ID查询统计信息
     * 
     * @param statId 统计ID
     * @return 统计信息对象
     */
    Statistic getStatisticById(Integer statId);
    
    /**
     * 获取所有统计信息
     * 
     * @return 统计信息列表
     */
    List<Statistic> getAllStatistics();
    
    /**
     * 根据用户ID查询统计信息
     * 
     * @param userId 用户ID
     * @return 统计信息对象
     */
    Statistic getStatisticByUserId(Integer userId);
    
    /**
     * 根据总学习时间范围查询
     * 
     * @param minTime 最小时间
     * @param maxTime 最大时间
     * @return 统计信息列表
     */
    List<Statistic> getStatisticsByTotalStudyTimeRange(Integer minTime, Integer maxTime);
    
    /**
     * 根据总学习单词数范围查询
     * 
     * @param minCount 最小数量
     * @param maxCount 最大数量
     * @return 统计信息列表
     */
    List<Statistic> getStatisticsByTotalWordsLearnedRange(Integer minCount, Integer maxCount);
    
    /**
     * 创建统计信息
     * 
     * @param statistic 统计信息对象
     * @return 是否成功
     */
    boolean createStatistic(Statistic statistic);
    
    /**
     * 更新统计信息
     * 
     * @param statistic 统计信息对象
     * @return 是否成功
     */
    boolean updateStatistic(Statistic statistic);
    
    /**
     * 更新总学习时间
     * 
     * @param statId 统计ID
     * @param totalStudyTime 总学习时间
     * @return 是否成功
     */
    boolean updateTotalStudyTime(Integer statId, Integer totalStudyTime);
    
    /**
     * 更新总学习单词数
     * 
     * @param statId 统计ID
     * @param totalWordsLearned 总学习单词数
     * @return 是否成功
     */
    boolean updateTotalWordsLearned(Integer statId, Integer totalWordsLearned);
    
    /**
     * 增加学习时间
     * 
     * @param userId 用户ID
     * @param additionalTime 增加的时间
     * @return 是否成功
     */
    boolean incrementStudyTime(Integer userId, Integer additionalTime);
    
    /**
     * 增加学习单词数
     * 
     * @param userId 用户ID
     * @param additionalWords 增加的单词数
     * @return 是否成功
     */
    boolean incrementWordsLearned(Integer userId, Integer additionalWords);
    
    /**
     * 删除统计信息
     * 
     * @param statId 统计ID
     * @return 是否成功
     */
    boolean deleteStatistic(Integer statId);
}