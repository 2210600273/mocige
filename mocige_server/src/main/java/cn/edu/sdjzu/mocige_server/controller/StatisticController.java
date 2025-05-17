package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Statistic;
import cn.edu.sdjzu.mocige_server.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统计控制器
 * 提供统计信息相关的RESTful API
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;
    
    /**
     * 获取所有统计信息
     * 
     * @return 统计信息列表
     */
    @GetMapping
    public ResponseEntity<List<Statistic>> getAllStatistics() {
        List<Statistic> statistics = statisticService.getAllStatistics();
        return ResponseEntity.ok(statistics);
    }
    
    /**
     * 根据ID获取统计信息
     * 
     * @param id 统计ID
     * @return 统计信息对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Statistic> getStatisticById(@PathVariable("id") Integer id) {
        Statistic statistic = statisticService.getStatisticById(id);
        if (statistic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statistic);
    }
    
    /**
     * 根据用户ID获取统计信息
     * 
     * @param userId 用户ID
     * @return 统计信息对象
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Statistic> getStatisticByUserId(@PathVariable("userId") Integer userId) {
        Statistic statistic = statisticService.getStatisticByUserId(userId);
        if (statistic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statistic);
    }
    
    /**
     * 根据总学习时间范围查询
     * 
     * @param minTime 最小时间
     * @param maxTime 最大时间
     * @return 统计信息列表
     */
    @GetMapping("/study-time-range")
    public ResponseEntity<List<Statistic>> getStatisticsByTotalStudyTimeRange(
            @RequestParam("minTime") Integer minTime,
            @RequestParam("maxTime") Integer maxTime) {
        List<Statistic> statistics = statisticService.getStatisticsByTotalStudyTimeRange(minTime, maxTime);
        return ResponseEntity.ok(statistics);
    }
    
    /**
     * 根据总学习单词数范围查询
     * 
     * @param minCount 最小数量
     * @param maxCount 最大数量
     * @return 统计信息列表
     */
    @GetMapping("/words-learned-range")
    public ResponseEntity<List<Statistic>> getStatisticsByTotalWordsLearnedRange(
            @RequestParam("minCount") Integer minCount,
            @RequestParam("maxCount") Integer maxCount) {
        List<Statistic> statistics = statisticService.getStatisticsByTotalWordsLearnedRange(minCount, maxCount);
        return ResponseEntity.ok(statistics);
    }
    
    /**
     * 创建统计信息
     * 
     * @param statistic 统计信息对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Statistic> createStatistic(@RequestBody Statistic statistic) {
        boolean success = statisticService.createStatistic(statistic);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(statistic);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新统计信息
     * 
     * @param id 统计ID
     * @param statistic 统计信息对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Statistic> updateStatistic(
            @PathVariable("id") Integer id,
            @RequestBody Statistic statistic) {
        statistic.setStatId(id);
        boolean success = statisticService.updateStatistic(statistic);
        if (success) {
            return ResponseEntity.ok(statistic);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新总学习时间
     * 
     * @param id 统计ID
     * @param totalStudyTime 总学习时间
     * @return 更新结果
     */
    @PatchMapping("/{id}/total-study-time")
    public ResponseEntity<Void> updateTotalStudyTime(
            @PathVariable("id") Integer id,
            @RequestParam("totalStudyTime") Integer totalStudyTime) {
        boolean success = statisticService.updateTotalStudyTime(id, totalStudyTime);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新总学习单词数
     * 
     * @param id 统计ID
     * @param totalWordsLearned 总学习单词数
     * @return 更新结果
     */
    @PatchMapping("/{id}/total-words-learned")
    public ResponseEntity<Void> updateTotalWordsLearned(
            @PathVariable("id") Integer id,
            @RequestParam("totalWordsLearned") Integer totalWordsLearned) {
        boolean success = statisticService.updateTotalWordsLearned(id, totalWordsLearned);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 增加学习时间
     * 
     * @param userId 用户ID
     * @param additionalTime 增加的时间
     * @return 更新结果
     */
    @PatchMapping("/user/{userId}/increment-study-time")
    public ResponseEntity<Void> incrementStudyTime(
            @PathVariable("userId") Integer userId,
            @RequestParam("additionalTime") Integer additionalTime) {
        boolean success = statisticService.incrementStudyTime(userId, additionalTime);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 增加学习单词数
     * 
     * @param userId 用户ID
     * @param additionalWords 增加的单词数
     * @return 更新结果
     */
    @PatchMapping("/user/{userId}/increment-words-learned")
    public ResponseEntity<Void> incrementWordsLearned(
            @PathVariable("userId") Integer userId,
            @RequestParam("additionalWords") Integer additionalWords) {
        boolean success = statisticService.incrementWordsLearned(userId, additionalWords);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除统计信息
     * 
     * @param id 统计ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistic(@PathVariable("id") Integer id) {
        boolean success = statisticService.deleteStatistic(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}