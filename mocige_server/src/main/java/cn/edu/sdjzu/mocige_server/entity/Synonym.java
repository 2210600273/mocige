package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 同义词实体类
 */
@Data
public class Synonym {
    /**
     * 同义词ID
     */
    private Integer synonymId;
    
    /**
     * 单词ID
     */
    private Integer wordId;
    
    /**
     * 相关单词
     */
    private String relatedWord;
}