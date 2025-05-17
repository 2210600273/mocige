package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.UserPreference;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户偏好Mapper接口
 */
@Mapper
public interface UserPreferenceMapper extends BaseMapper<UserPreference, Integer> {
    
    /**
     * 根据用户ID查询偏好设置
     * 
     * @param userId 用户ID
     * @return 用户偏好
     */
    UserPreference selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 根据学习模式查询
     * 
     * @param learningMode 学习模式
     * @return 用户偏好列表
     */
    List<UserPreference> selectByLearningMode(@Param("learningMode") String learningMode);
    
    /**
     * 根据每日单词数范围查询
     * 
     * @param minCount 最小数量
     * @param maxCount 最大数量
     * @return 用户偏好列表
     */
    List<UserPreference> selectByDailyWordCountRange(@Param("minCount") Integer minCount, @Param("maxCount") Integer maxCount);
    
    /**
     * 更新学习模式
     * 
     * @param preferenceId 偏好ID
     * @param learningMode 新的学习模式
     * @return 影响行数
     */
    int updateLearningMode(@Param("preferenceId") Integer preferenceId, @Param("learningMode") String learningMode);
    
    /**
     * 更新每日单词数
     * 
     * @param preferenceId 偏好ID
     * @param dailyWordCount 新的每日单词数
     * @return 影响行数
     */
    int updateDailyWordCount(@Param("preferenceId") Integer preferenceId, @Param("dailyWordCount") Integer dailyWordCount);
}