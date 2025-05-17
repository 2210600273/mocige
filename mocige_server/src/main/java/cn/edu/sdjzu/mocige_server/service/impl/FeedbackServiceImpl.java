package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Feedback;
import cn.edu.sdjzu.mocige_server.mapper.FeedbackMapper;
import cn.edu.sdjzu.mocige_server.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 反馈服务实现类
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;
    
    @Override
    public Feedback getFeedbackById(Integer feedbackId) {
        return feedbackMapper.selectById(feedbackId);
    }
    
    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackMapper.selectAll();
    }
    
    @Override
    public List<Feedback> getFeedbacksByUserId(Integer userId) {
        return feedbackMapper.selectByUserId(userId);
    }
    
    @Override
    public List<Feedback> getFeedbacksByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return feedbackMapper.selectByTimeRange(startTime, endTime);
    }
    
    @Override
    public List<Feedback> getFeedbacksByComment(String comment) {
        return feedbackMapper.selectByComment(comment);
    }
    
    @Override
    public boolean createFeedback(Feedback feedback) {
        return feedbackMapper.insert(feedback) > 0;
    }
    
    @Override
    public boolean updateFeedback(Feedback feedback) {
        return feedbackMapper.update(feedback) > 0;
    }
    
    @Override
    public boolean deleteFeedback(Integer feedbackId) {
        return feedbackMapper.deleteById(feedbackId) > 0;
    }
}