package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.LearningProgress;
import cn.edu.sdjzu.mocige_server.mapper.LearningProgressMapper;
import cn.edu.sdjzu.mocige_server.service.LearningProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 学习进度服务实现类
 */
@Service
public class LearningProgressServiceImpl implements LearningProgressService {

    @Autowired
    private LearningProgressMapper learningProgressMapper;
    
    @Override
    public LearningProgress getProgressById(Integer progressId) {
        return learningProgressMapper.selectById(progressId);
    }
    
    @Override
    public List<LearningProgress> getAllProgress() {
        return learningProgressMapper.selectAll();
    }
    
    @Override
    public List<LearningProgress> getProgressByUserId(Integer userId) {
        return learningProgressMapper.selectByUserId(userId);
    }
    
    @Override
    public List<LearningProgress> getProgressByWordId(Integer wordId) {
        return learningProgressMapper.selectByWordId(wordId);
    }
    
    @Override
    public LearningProgress getProgressByUserIdAndWordId(Integer userId, Integer wordId) {
        return learningProgressMapper.selectByUserIdAndWordId(userId, wordId);
    }
    
    @Override
    public List<LearningProgress> getProgressByLastReviewedRange(LocalDateTime startTime, LocalDateTime endTime) {
        return learningProgressMapper.selectByLastReviewedRange(startTime, endTime);
    }
    
    @Override
    public boolean createProgress(LearningProgress progress) {
        return learningProgressMapper.insert(progress) > 0;
    }
    
    @Override
    public boolean updateProgress(LearningProgress progress) {
        return learningProgressMapper.update(progress) > 0;
    }
    
    @Override
    public boolean updateLastReviewed(Integer progressId, LocalDateTime lastReviewed) {
        return learningProgressMapper.updateLastReviewed(progressId, lastReviewed) > 0;
    }
    
    @Override
    public boolean updateReviewsCount(Integer progressId, Integer reviewsCount) {
        return learningProgressMapper.updateReviewsCount(progressId, reviewsCount) > 0;
    }
    
    @Override
    public boolean incrementReviewsCount(Integer progressId) {
        LearningProgress progress = learningProgressMapper.selectById(progressId);
        if (progress == null) {
            return false;
        }
        Integer currentCount = progress.getReviewsCount();
        return learningProgressMapper.updateReviewsCount(progressId, currentCount + 1) > 0;
    }
    
    @Override
    public boolean updateMasteryLevel(Integer progressId, Integer masteryLevel) {
        return learningProgressMapper.updateMasteryLevel(progressId, masteryLevel) > 0;
    }
    
    @Override
    public boolean deleteProgress(Integer progressId) {
        return learningProgressMapper.deleteById(progressId) > 0;
    }
}