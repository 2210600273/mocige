package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 反义词实体类
 */
@Data
public class Antonym {
    /**
     * 反义词ID
     */
    private Integer antonymId;
    
    /**
     * 单词ID
     */
    private Integer wordId;
    
    /**
     * 相关单词
     */
    private String relatedWord;
}