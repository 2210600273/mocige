package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.UserPreference;
import cn.edu.sdjzu.mocige_server.mapper.UserPreferenceMapper;
import cn.edu.sdjzu.mocige_server.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户偏好服务实现类
 */
@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserPreferenceMapper userPreferenceMapper;
    
    @Override
    public UserPreference getUserPreferenceById(Integer preferenceId) {
        return userPreferenceMapper.selectById(preferenceId);
    }
    
    @Override
    public List<UserPreference> getAllUserPreferences() {
        return userPreferenceMapper.selectAll();
    }
    
    @Override
    public UserPreference getUserPreferenceByUserId(Integer userId) {
        return userPreferenceMapper.selectByUserId(userId);
    }
    
    @Override
    public List<UserPreference> getUserPreferencesByLearningMode(String learningMode) {
        return userPreferenceMapper.selectByLearningMode(learningMode);
    }
    
    @Override
    public List<UserPreference> getUserPreferencesByDailyWordCountRange(Integer minCount, Integer maxCount) {
        return userPreferenceMapper.selectByDailyWordCountRange(minCount, maxCount);
    }
    
    @Override
    public boolean createUserPreference(UserPreference userPreference) {
        return userPreferenceMapper.insert(userPreference) > 0;
    }
    
    @Override
    public boolean updateUserPreference(UserPreference userPreference) {
        return userPreferenceMapper.update(userPreference) > 0;
    }
    
    @Override
    public boolean updateLearningMode(Integer preferenceId, String learningMode) {
        return userPreferenceMapper.updateLearningMode(preferenceId, learningMode) > 0;
    }
    
    @Override
    public boolean updateDailyWordCount(Integer preferenceId, Integer dailyWordCount) {
        return userPreferenceMapper.updateDailyWordCount(preferenceId, dailyWordCount) > 0;
    }
    
    @Override
    public boolean deleteUserPreference(Integer preferenceId) {
        return userPreferenceMapper.deleteById(preferenceId) > 0;
    }
}