package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;
import java.time.LocalTime;

/**
 * 应用设置实体类
 */
@Data
public class AppSetting {
    /**
     * 设置ID
     */
    private Integer settingId;
    
    /**
     * 提醒时间
     */
    private LocalTime reminderTime;
    
    /**
     * 主题
     */
    private String theme;
}