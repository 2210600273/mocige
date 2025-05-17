package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 句子实体类
 */
@Data
public class Sentence {
    /**
     * 句子ID
     */
    private Integer sentenceId;
    
    /**
     * 句子
     */
    private String sentence;
    
    /**
     * 上下文
     */
    private String context;
}