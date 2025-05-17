package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Antonym;

import java.util.List;

/**
 * 反义词服务接口
 */
public interface AntonymService {
    
    /**
     * 根据ID查询反义词关系
     * 
     * @param antonymId 反义词关系ID
     * @return 反义词关系对象
     */
    Antonym getAntonymById(Integer antonymId);
    
    /**
     * 获取所有反义词关系
     * 
     * @return 反义词关系列表
     */
    List<Antonym> getAllAntonyms();
    
    /**
     * 根据单词ID查询反义词关系
     * 
     * @param wordId 单词ID
     * @return 反义词关系列表
     */
    List<Antonym> getAntonymsByWordId(Integer wordId);
    
    
    
    /**
     * 创建反义词关系
     * 
     * @param antonym 反义词关系对象
     * @return 是否成功
     */
    boolean createAntonym(Antonym antonym);
    
    /**
     * 更新反义词关系
     * 
     * @param antonym 反义词关系对象
     * @return 是否成功
     */
    boolean updateAntonym(Antonym antonym);
    
    /**
     * 删除反义词关系
     * 
     * @param antonymId 反义词关系ID
     * @return 是否成功
     */
    boolean deleteAntonym(Integer antonymId);
    
    /**
     * 删除单词的所有反义词关系
     * 
     * @param wordId 单词ID
     * @return 是否成功
     */
    boolean deleteAntonymsByWordId(Integer wordId);
}