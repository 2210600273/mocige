package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.TestRecord;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试记录服务接口
 */
public interface TestRecordService {
    
    /**
     * 根据ID查询测试记录
     * 
     * @param recordId 测试记录ID
     * @return 测试记录对象
     */
    TestRecord getTestRecordById(Integer recordId);
    
    /**
     * 获取所有测试记录
     * 
     * @return 测试记录列表
     */
    List<TestRecord> getAllTestRecords();
    
    /**
     * 根据用户ID查询测试记录
     * 
     * @param userId 用户ID
     * @return 测试记录列表
     */
    List<TestRecord> getTestRecordsByUserId(Integer userId);
    
    /**
     * 根据测试日期范围查询
     * 
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 测试记录列表
     */
    List<TestRecord> getTestRecordsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * 根据分数范围查询
     * 
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @return 测试记录列表
     */
    List<TestRecord> getTestRecordsByScoreRange(Integer minScore, Integer maxScore);
    
    /**
     * 查询用户最近的测试记录
     * 
     * @param userId 用户ID
     * @param limit 限制数量
     * @return 测试记录列表
     */
    List<TestRecord> getRecentTestRecordsByUserId(Integer userId, Integer limit);
    
    /**
     * 创建测试记录
     * 
     * @param testRecord 测试记录对象
     * @return 是否成功
     */
    boolean createTestRecord(TestRecord testRecord);
    
    /**
     * 更新测试记录
     * 
     * @param testRecord 测试记录对象
     * @return 是否成功
     */
    boolean updateTestRecord(TestRecord testRecord);
    
    /**
     * 删除测试记录
     * 
     * @param recordId 测试记录ID
     * @return 是否成功
     */
    boolean deleteTestRecord(Integer recordId);
    
}