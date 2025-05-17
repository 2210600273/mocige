package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.UserPreference;
import cn.edu.sdjzu.mocige_server.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户偏好控制器
 * 提供用户偏好相关的RESTful API
 */
@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;
    
    /**
     * 获取所有用户偏好
     * 
     * @return 用户偏好列表
     */
    @GetMapping
    public ResponseEntity<List<UserPreference>> getAllUserPreferences() {
        List<UserPreference> preferences = userPreferenceService.getAllUserPreferences();
        return ResponseEntity.ok(preferences);
    }
    
    /**
     * 根据ID获取用户偏好
     * 
     * @param id 偏好ID
     * @return 用户偏好对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserPreference> getUserPreferenceById(@PathVariable("id") Integer id) {
        UserPreference preference = userPreferenceService.getUserPreferenceById(id);
        if (preference == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(preference);
    }
    
    /**
     * 根据用户ID获取偏好设置
     * 
     * @param userId 用户ID
     * @return 用户偏好对象
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserPreference> getUserPreferenceByUserId(@PathVariable("userId") Integer userId) {
        UserPreference preference = userPreferenceService.getUserPreferenceByUserId(userId);
        if (preference == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(preference);
    }
    
    /**
     * 根据学习模式获取用户偏好列表
     * 
     * @param mode 学习模式
     * @return 用户偏好列表
     */
    @GetMapping("/mode/{mode}")
    public ResponseEntity<List<UserPreference>> getUserPreferencesByLearningMode(@PathVariable("mode") String mode) {
        List<UserPreference> preferences = userPreferenceService.getUserPreferencesByLearningMode(mode);
        return ResponseEntity.ok(preferences);
    }
    
    /**
     * 根据每日单词数范围获取用户偏好列表
     * 
     * @param minCount 最小数量
     * @param maxCount 最大数量
     * @return 用户偏好列表
     */
    @GetMapping("/daily-word-count")
    public ResponseEntity<List<UserPreference>> getUserPreferencesByDailyWordCountRange(
            @RequestParam("min") Integer minCount,
            @RequestParam("max") Integer maxCount) {
        List<UserPreference> preferences = userPreferenceService.getUserPreferencesByDailyWordCountRange(minCount, maxCount);
        return ResponseEntity.ok(preferences);
    }
    
    /**
     * 创建用户偏好
     * 
     * @param userPreference 用户偏好对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<UserPreference> createUserPreference(@RequestBody UserPreference userPreference) {
        boolean success = userPreferenceService.createUserPreference(userPreference);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userPreference);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新用户偏好
     * 
     * @param id 偏好ID
     * @param userPreference 用户偏好对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserPreference> updateUserPreference(
            @PathVariable("id") Integer id,
            @RequestBody UserPreference userPreference) {
        userPreference.setPreferenceId(id);
        boolean success = userPreferenceService.updateUserPreference(userPreference);
        if (success) {
            return ResponseEntity.ok(userPreference);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新学习模式
     * 
     * @param id 偏好ID
     * @param mode 新的学习模式
     * @return 更新结果
     */
    @PatchMapping("/{id}/mode")
    public ResponseEntity<Void> updateLearningMode(
            @PathVariable("id") Integer id,
            @RequestParam("mode") String mode) {
        boolean success = userPreferenceService.updateLearningMode(id, mode);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新每日单词数
     * 
     * @param id 偏好ID
     * @param count 新的每日单词数
     * @return 更新结果
     */
    @PatchMapping("/{id}/daily-word-count")
    public ResponseEntity<Void> updateDailyWordCount(
            @PathVariable("id") Integer id,
            @RequestParam("count") Integer count) {
        boolean success = userPreferenceService.updateDailyWordCount(id, count);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除用户偏好
     * 
     * @param id 偏好ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPreference(@PathVariable("id") Integer id) {
        boolean success = userPreferenceService.deleteUserPreference(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}