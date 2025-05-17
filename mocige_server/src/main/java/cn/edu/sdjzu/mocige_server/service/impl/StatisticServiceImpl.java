package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Statistic;
import cn.edu.sdjzu.mocige_server.mapper.StatisticMapper;
import cn.edu.sdjzu.mocige_server.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 统计服务实现类
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticMapper statisticMapper;
    
    @Override
    public Statistic getStatisticById(Integer statId) {
        return statisticMapper.selectById(statId);
    }
    
    @Override
    public List<Statistic> getAllStatistics() {
        return statisticMapper.selectAll();
    }
    
    @Override
    public Statistic getStatisticByUserId(Integer userId) {
        return statisticMapper.selectByUserId(userId);
    }
    
    @Override
    public List<Statistic> getStatisticsByTotalStudyTimeRange(Integer minTime, Integer maxTime) {
        return statisticMapper.selectByTotalStudyTimeRange(minTime, maxTime);
    }
    
    @Override
    public List<Statistic> getStatisticsByTotalWordsLearnedRange(Integer minCount, Integer maxCount) {
        return statisticMapper.selectByTotalWordsLearnedRange(minCount, maxCount);
    }
    
    @Override
    public boolean createStatistic(Statistic statistic) {
        return statisticMapper.insert(statistic) > 0;
    }
    
    @Override
    public boolean updateStatistic(Statistic statistic) {
        return statisticMapper.update(statistic) > 0;
    }
    
    @Override
    public boolean updateTotalStudyTime(Integer statId, Integer totalStudyTime) {
        return statisticMapper.updateTotalStudyTime(statId, totalStudyTime) > 0;
    }
    
    @Override
    public boolean updateTotalWordsLearned(Integer statId, Integer totalWordsLearned) {
        return statisticMapper.updateTotalWordsLearned(statId, totalWordsLearned) > 0;
    }
    
    @Override
    public boolean incrementStudyTime(Integer userId, Integer additionalTime) {
        return statisticMapper.incrementStudyTime(userId, additionalTime) > 0;
    }
    
    @Override
    public boolean incrementWordsLearned(Integer userId, Integer additionalWords) {
        return statisticMapper.incrementWordsLearned(userId, additionalWords) > 0;
    }
    
    @Override
    public boolean deleteStatistic(Integer statId) {
        return statisticMapper.deleteById(statId) > 0;
    }
}