package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.UserPreference;

import java.util.List;

/**
 * 用户偏好服务接口
 */
public interface UserPreferenceService {
    
    /**
     * 根据ID查询用户偏好
     * 
     * @param preferenceId 偏好ID
     * @return 用户偏好对象
     */
    UserPreference getUserPreferenceById(Integer preferenceId);
    
    /**
     * 获取所有用户偏好
     * 
     * @return 用户偏好列表
     */
    List<UserPreference> getAllUserPreferences();
    
    /**
     * 根据用户ID查询偏好设置
     * 
     * @param userId 用户ID
     * @return 用户偏好对象
     */
    UserPreference getUserPreferenceByUserId(Integer userId);
    
    /**
     * 根据学习模式查询
     * 
     * @param learningMode 学习模式
     * @return 用户偏好列表
     */
    List<UserPreference> getUserPreferencesByLearningMode(String learningMode);
    
    /**
     * 根据每日单词数范围查询
     * 
     * @param minCount 最小数量
     * @param maxCount 最大数量
     * @return 用户偏好列表
     */
    List<UserPreference> getUserPreferencesByDailyWordCountRange(Integer minCount, Integer maxCount);
    
    /**
     * 创建用户偏好
     * 
     * @param userPreference 用户偏好对象
     * @return 是否成功
     */
    boolean createUserPreference(UserPreference userPreference);
    
    /**
     * 更新用户偏好
     * 
     * @param userPreference 用户偏好对象
     * @return 是否成功
     */
    boolean updateUserPreference(UserPreference userPreference);
    
    /**
     * 更新学习模式
     * 
     * @param preferenceId 偏好ID
     * @param learningMode 新的学习模式
     * @return 是否成功
     */
    boolean updateLearningMode(Integer preferenceId, String learningMode);
    
    /**
     * 更新每日单词数
     * 
     * @param preferenceId 偏好ID
     * @param dailyWordCount 新的每日单词数
     * @return 是否成功
     */
    boolean updateDailyWordCount(Integer preferenceId, Integer dailyWordCount);
    
    /**
     * 删除用户偏好
     * 
     * @param preferenceId 偏好ID
     * @return 是否成功
     */
    boolean deleteUserPreference(Integer preferenceId);
}