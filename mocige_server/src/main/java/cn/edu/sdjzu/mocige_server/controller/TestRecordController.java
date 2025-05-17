package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.TestRecord;
import cn.edu.sdjzu.mocige_server.service.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试记录控制器
 * 提供测试记录相关的RESTful API
 */
@RestController
@RequestMapping("/api/test-records")
public class TestRecordController {

    @Autowired
    private TestRecordService testRecordService;
    
    /**
     * 获取所有测试记录
     * 
     * @return 测试记录列表
     */
    @GetMapping
    public ResponseEntity<List<TestRecord>> getAllTestRecords() {
        List<TestRecord> records = testRecordService.getAllTestRecords();
        return ResponseEntity.ok(records);
    }
    
    /**
     * 根据ID获取测试记录
     * 
     * @param id 测试记录ID
     * @return 测试记录对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<TestRecord> getTestRecordById(@PathVariable("id") Integer id) {
        TestRecord record = testRecordService.getTestRecordById(id);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }
    
    /**
     * 根据用户ID获取测试记录
     * 
     * @param userId 用户ID
     * @return 测试记录列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestRecord>> getTestRecordsByUserId(@PathVariable("userId") Integer userId) {
        List<TestRecord> records = testRecordService.getTestRecordsByUserId(userId);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 根据测试日期范围获取测试记录
     * 
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 测试记录列表
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<TestRecord>> getTestRecordsByDateRange(
            @RequestParam("start") LocalDateTime startDate,
            @RequestParam("end") LocalDateTime endDate) {
        List<TestRecord> records = testRecordService.getTestRecordsByDateRange(startDate, endDate);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 根据分数范围获取测试记录
     * 
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @return 测试记录列表
     */
    @GetMapping("/score-range")
    public ResponseEntity<List<TestRecord>> getTestRecordsByScoreRange(
            @RequestParam("min") Integer minScore,
            @RequestParam("max") Integer maxScore) {
        List<TestRecord> records = testRecordService.getTestRecordsByScoreRange(minScore, maxScore);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 获取用户最近的测试记录
     * 
     * @param userId 用户ID
     * @param limit 限制数量
     * @return 测试记录列表
     */
    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<TestRecord>> getRecentTestRecordsByUserId(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        List<TestRecord> records = testRecordService.getRecentTestRecordsByUserId(userId, limit);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 创建测试记录
     * 
     * @param testRecord 测试记录对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<TestRecord> createTestRecord(@RequestBody TestRecord testRecord) {
        boolean success = testRecordService.createTestRecord(testRecord);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(testRecord);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新测试记录
     * 
     * @param id 测试记录ID
     * @param testRecord 测试记录对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<TestRecord> updateTestRecord(
            @PathVariable("id") Integer id,
            @RequestBody TestRecord testRecord) {
        testRecord.setTestRecordId(id);
        boolean success = testRecordService.updateTestRecord(testRecord);
        if (success) {
            return ResponseEntity.ok(testRecord);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除测试记录
     * 
     * @param id 测试记录ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestRecord(@PathVariable("id") Integer id) {
        boolean success = testRecordService.deleteTestRecord(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}