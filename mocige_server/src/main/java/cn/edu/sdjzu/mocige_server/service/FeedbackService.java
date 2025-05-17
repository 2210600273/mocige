package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Feedback;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 反馈服务接口
 */
public interface FeedbackService {
    
    /**
     * 根据ID查询反馈
     * 
     * @param feedbackId 反馈ID
     * @return 反馈对象
     */
    Feedback getFeedbackById(Integer feedbackId);
    
    /**
     * 获取所有反馈
     * 
     * @return 反馈列表
     */
    List<Feedback> getAllFeedbacks();
    
    /**
     * 根据用户ID查询反馈
     * 
     * @param userId 用户ID
     * @return 反馈列表
     */
    List<Feedback> getFeedbacksByUserId(Integer userId);
    
    /**
     * 根据创建时间范围查询反馈
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 反馈列表
     */
    List<Feedback> getFeedbacksByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 根据评论内容模糊查询
     * 
     * @param comment 评论内容
     * @return 反馈列表
     */
    List<Feedback> getFeedbacksByComment(String comment);
    
    /**
     * 创建反馈
     * 
     * @param feedback 反馈对象
     * @return 是否成功
     */
    boolean createFeedback(Feedback feedback);
    
    /**
     * 更新反馈
     * 
     * @param feedback 反馈对象
     * @return 是否成功
     */
    boolean updateFeedback(Feedback feedback);
    
    /**
     * 删除反馈
     * 
     * @param feedbackId 反馈ID
     * @return 是否成功
     */
    boolean deleteFeedback(Integer feedbackId);
}