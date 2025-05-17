package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.TestRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试记录Mapper接口
 */
@Mapper
public interface TestRecordMapper extends BaseMapper<TestRecord, Integer> {
    
    /**
     * 根据用户ID查询测试记录
     * 
     * @param userId 用户ID
     * @return 测试记录列表
     */
    List<TestRecord> selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 根据测试日期范围查询
     * 
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 测试记录列表
     */
    List<TestRecord> selectByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    /**
     * 根据分数范围查询
     * 
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @return 测试记录列表
     */
    List<TestRecord> selectByScoreRange(@Param("minScore") Integer minScore, @Param("maxScore") Integer maxScore);
    
    /**
     * 查询用户最近的测试记录
     * 
     * @param userId 用户ID
     * @param limit 限制数量
     * @return 测试记录列表
     */
    List<TestRecord> selectRecentByUserId(@Param("userId") Integer userId, @Param("limit") Integer limit);
    
    /**
     * 更新测试分数
     * 
     * @param testRecordId 测试记录ID
     * @param score 新的分数
     * @return 影响行数
     */
    int updateScore(@Param("testRecordId") Integer testRecordId, @Param("score") Integer score);
}