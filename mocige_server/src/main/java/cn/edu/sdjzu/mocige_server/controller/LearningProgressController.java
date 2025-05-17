package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.LearningProgress;
import cn.edu.sdjzu.mocige_server.service.LearningProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 学习进度控制器
 * 提供学习进度相关的RESTful API
 */
@RestController
@RequestMapping("/api/progress")
public class LearningProgressController {

    @Autowired
    private LearningProgressService learningProgressService;
    
    /**
     * 获取所有学习进度
     * 
     * @return 学习进度列表
     */
    @GetMapping
    public ResponseEntity<List<LearningProgress>> getAllProgress() {
        List<LearningProgress> progressList = learningProgressService.getAllProgress();
        return ResponseEntity.ok(progressList);
    }
    
    /**
     * 根据ID获取学习进度
     * 
     * @param id 进度ID
     * @return 学习进度对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<LearningProgress> getProgressById(@PathVariable("id") Integer id) {
        LearningProgress progress = learningProgressService.getProgressById(id);
        if (progress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(progress);
    }
    
    /**
     * 根据用户ID获取学习进度
     * 
     * @param userId 用户ID
     * @return 学习进度列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningProgress>> getProgressByUserId(@PathVariable("userId") Integer userId) {
        List<LearningProgress> progressList = learningProgressService.getProgressByUserId(userId);
        return ResponseEntity.ok(progressList);
    }
    
    /**
     * 根据单词ID获取学习进度
     * 
     * @param wordId 单词ID
     * @return 学习进度列表
     */
    @GetMapping("/word/{wordId}")
    public ResponseEntity<List<LearningProgress>> getProgressByWordId(@PathVariable("wordId") Integer wordId) {
        List<LearningProgress> progressList = learningProgressService.getProgressByWordId(wordId);
        return ResponseEntity.ok(progressList);
    }
    
    /**
     * 根据用户ID和单词ID获取学习进度
     * 
     * @param userId 用户ID
     * @param wordId 单词ID
     * @return 学习进度对象
     */
    @GetMapping("/user/{userId}/word/{wordId}")
    public ResponseEntity<LearningProgress> getProgressByUserIdAndWordId(
            @PathVariable("userId") Integer userId,
            @PathVariable("wordId") Integer wordId) {
        LearningProgress progress = learningProgressService.getProgressByUserIdAndWordId(userId, wordId);
        if (progress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(progress);
    }
    
    /**
     * 根据最后复习时间范围获取学习进度
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 学习进度列表
     */
    @GetMapping("/last-reviewed-range")
    public ResponseEntity<List<LearningProgress>> getProgressByLastReviewedRange(
            @RequestParam("start") LocalDateTime startTime,
            @RequestParam("end") LocalDateTime endTime) {
        List<LearningProgress> progressList = learningProgressService.getProgressByLastReviewedRange(startTime, endTime);
        return ResponseEntity.ok(progressList);
    }
    
    /**
     * 创建学习进度
     * 
     * @param progress 学习进度对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<LearningProgress> createProgress(@RequestBody LearningProgress progress) {
        boolean success = learningProgressService.createProgress(progress);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(progress);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新学习进度
     * 
     * @param id 进度ID
     * @param progress 学习进度对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<LearningProgress> updateProgress(
            @PathVariable("id") Integer id,
            @RequestBody LearningProgress progress) {
        progress.setProgressId(id);
        boolean success = learningProgressService.updateProgress(progress);
        if (success) {
            return ResponseEntity.ok(progress);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新最后复习时间
     * 
     * @param id 进度ID
     * @param lastReviewed 最后复习时间
     * @return 更新结果
     */
    @PatchMapping("/{id}/last-reviewed")
    public ResponseEntity<Void> updateLastReviewed(
            @PathVariable("id") Integer id,
            @RequestParam("lastReviewed") LocalDateTime lastReviewed) {
        boolean success = learningProgressService.updateLastReviewed(id, lastReviewed);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新复习次数
     * 
     * @param id 进度ID
     * @param reviewsCount 复习次数
     * @return 更新结果
     */
    @PatchMapping("/{id}/reviews-count")
    public ResponseEntity<Void> updateReviewsCount(
            @PathVariable("id") Integer id,
            @RequestParam("count") Integer reviewsCount) {
        boolean success = learningProgressService.updateReviewsCount(id, reviewsCount);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 增加复习次数
     * 
     * @param id 进度ID
     * @return 更新结果
     */
    @PatchMapping("/{id}/increment-reviews")
    public ResponseEntity<Void> incrementReviewsCount(@PathVariable("id") Integer id) {
        boolean success = learningProgressService.incrementReviewsCount(id);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新熟悉度
     * 
     * @param id 进度ID
     * @param familiarity 熟悉度
     * @return 更新结果
     */
    @PatchMapping("/{id}/familiarity")
    public ResponseEntity<Void> updateFamiliarity(
            @PathVariable("id") Integer id,
            @RequestParam("familiarity") Integer familiarity) {
        boolean success = learningProgressService.updateReviewsCount(id, familiarity);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除学习进度
     * 
     * @param id 进度ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable("id") Integer id) {
        boolean success = learningProgressService.deleteProgress(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}