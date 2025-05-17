package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 单词句子关联实体类
 */
@Data
public class WordSentence {
    /**
     * 单词ID
     */
    private Integer wordId;
    
    /**
     * 句子ID
     */
    private Integer sentenceId;
}