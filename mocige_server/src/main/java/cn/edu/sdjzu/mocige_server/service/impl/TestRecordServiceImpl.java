package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.TestRecord;
import cn.edu.sdjzu.mocige_server.mapper.TestRecordMapper;
import cn.edu.sdjzu.mocige_server.service.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试记录服务实现类
 */
@Service
public class TestRecordServiceImpl implements TestRecordService {

    @Autowired
    private TestRecordMapper testRecordMapper;
    
    @Override
    public TestRecord getTestRecordById(Integer recordId) {
        return testRecordMapper.selectById(recordId);
    }
    
    @Override
    public List<TestRecord> getAllTestRecords() {
        return testRecordMapper.selectAll();
    }
    
    @Override
    public List<TestRecord> getTestRecordsByUserId(Integer userId) {
        return testRecordMapper.selectByUserId(userId);
    }
    
    @Override
    public List<TestRecord> getTestRecordsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return testRecordMapper.selectByDateRange(startDate, endDate);
    }
    
    @Override
    public List<TestRecord> getTestRecordsByScoreRange(Integer minScore, Integer maxScore) {
        return testRecordMapper.selectByScoreRange(minScore, maxScore);
    }
    
    @Override
    public List<TestRecord> getRecentTestRecordsByUserId(Integer userId, Integer limit) {
        return testRecordMapper.selectRecentByUserId(userId, limit);
    }
    
    @Override
    public boolean createTestRecord(TestRecord testRecord) {
        return testRecordMapper.insert(testRecord) > 0;
    }
    
    @Override
    public boolean updateTestRecord(TestRecord testRecord) {
        return testRecordMapper.update(testRecord) > 0;
    }
    
    @Override
    public boolean deleteTestRecord(Integer recordId) {
        return testRecordMapper.deleteById(recordId) > 0;
    }
    
}