package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Synonym;

import java.util.List;

/**
 * 同义词服务接口
 */
public interface SynonymService {
    
    /**
     * 根据ID查询同义词关系
     * 
     * @param synonymId 同义词关系ID
     * @return 同义词关系对象
     */
    Synonym getSynonymById(Integer synonymId);
    
    /**
     * 获取所有同义词关系
     * 
     * @return 同义词关系列表
     */
    List<Synonym> getAllSynonyms();
    
    /**
     * 根据单词ID查询同义词关系
     * 
     * @param wordId 单词ID
     * @return 同义词关系列表
     */
    List<Synonym> getSynonymsByWordId(Integer wordId);
    
    /**
     * 根据相关单词查询同义词关系
     * 
     * @param relatedWord 相关单词
     * @return 同义词关系列表
     */
    List<Synonym> getSynonymsByRelatedWord(String relatedWord);
    
    /**
     * 根据单词ID和相关单词查询同义词关系
     * 
     * @param wordId 单词ID
     * @param relatedWord 相关单词
     * @return 同义词关系
     */
    Synonym getSynonymByWordIdAndRelatedWord(Integer wordId, String relatedWord);
    
    /**
     * 创建同义词关系
     * 
     * @param synonym 同义词关系对象
     * @return 是否成功
     */
    boolean createSynonym(Synonym synonym);
    
    /**
     * 更新同义词关系
     * 
     * @param synonym 同义词关系对象
     * @return 是否成功
     */
    boolean updateSynonym(Synonym synonym);
    
    /**
     * 更新相关单词
     * 
     * @param synonymId 同义词ID
     * @param relatedWord 新的相关单词
     * @return 是否成功
     */
    boolean updateRelatedWord(Integer synonymId, String relatedWord);
    
    /**
     * 删除同义词关系
     * 
     * @param synonymId 同义词关系ID
     * @return 是否成功
     */
    boolean deleteSynonym(Integer synonymId);
}