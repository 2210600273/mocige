package cn.edu.sdjzu.mocige_server.entity;

import lombok.Data;

/**
 * 词缀实体类
 */
@Data
public class Affix {
    /**
     * 词缀ID
     */
    private Integer affixId;
    
    /**
     * 词缀
     */
    private String affix;
    
    /**
     * 定义
     */
    private String definition;
}