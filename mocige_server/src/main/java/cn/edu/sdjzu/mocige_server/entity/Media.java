package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 媒体实体类
 */
@Data
public class Media {
    /**
     * 媒体ID
     */
    private Integer mediaId;
    
    /**
     * 单词ID
     */
    private Integer wordId;
    
    /**
     * 媒体类型
     */
    private String mediaType;
    
    /**
     * 媒体URL
     */
    private String mediaUrl;
}