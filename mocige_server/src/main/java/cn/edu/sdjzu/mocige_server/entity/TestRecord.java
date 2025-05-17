package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 测试记录实体类
 */
@Data
public class TestRecord {
    /**
     * 测试记录ID
     */
    private Integer testRecordId;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 测试日期
     */
    private LocalDateTime testDate;
    
    /**
     * 分数
     */
    private Integer score;
}