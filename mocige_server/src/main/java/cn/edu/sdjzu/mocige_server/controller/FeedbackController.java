package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Feedback;
import cn.edu.sdjzu.mocige_server.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 反馈控制器
 * 提供反馈相关的RESTful API
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    
    /**
     * 获取所有反馈
     * 
     * @return 反馈列表
     */
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }
    
    /**
     * 根据ID获取反馈
     * 
     * @param id 反馈ID
     * @return 反馈对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") Integer id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedback);
    }
    
    /**
     * 根据用户ID获取反馈
     * 
     * @param userId 用户ID
     * @return 反馈列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable("userId") Integer userId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByUserId(userId);
        return ResponseEntity.ok(feedbacks);
    }
    
    /**
     * 根据创建时间范围获取反馈
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 反馈列表
     */
    @GetMapping("/time-range")
    public ResponseEntity<List<Feedback>> getFeedbacksByTimeRange(
            @RequestParam("start") LocalDateTime startTime,
            @RequestParam("end") LocalDateTime endTime) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByTimeRange(startTime, endTime);
        return ResponseEntity.ok(feedbacks);
    }
    
    /**
     * 根据评论内容模糊查询反馈
     * 
     * @param comment 评论内容
     * @return 反馈列表
     */
    @GetMapping("/comment")
    public ResponseEntity<List<Feedback>> getFeedbacksByComment(@RequestParam("comment") String comment) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByComment(comment);
        return ResponseEntity.ok(feedbacks);
    }
    
    /**
     * 创建反馈
     * 
     * @param feedback 反馈对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        boolean success = feedbackService.createFeedback(feedback);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新反馈
     * 
     * @param id 反馈ID
     * @param feedback 反馈对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(
            @PathVariable("id") Integer id,
            @RequestBody Feedback feedback) {
        feedback.setFeedbackId(id);
        boolean success = feedbackService.updateFeedback(feedback);
        if (success) {
            return ResponseEntity.ok(feedback);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除反馈
     * 
     * @param id 反馈ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("id") Integer id) {
        boolean success = feedbackService.deleteFeedback(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}