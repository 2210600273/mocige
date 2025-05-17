package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 词根实体类
 */
@Data
public class Root {
    /**
     * 词根ID
     */
    private Integer rootId;
    
    /**
     * 词根
     */
    private String root;
    
    /**
     * 定义
     */
    private String definition;
}