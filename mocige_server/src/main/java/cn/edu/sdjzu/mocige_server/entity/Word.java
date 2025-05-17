package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 单词实体类
 */
@Data
public class Word {
    /**
     * 单词ID
     */
    private Integer wordId;
    
    /**
     * 单词
     */
    private String word;
    
    /**
     * 发音
     */
    private String pronunciation;
    
    /**
     * 词性
     */
    private String partOfSpeech;
    
    /**
     * 定义
     */
    private String definition;
}