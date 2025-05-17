package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Sentence;

import java.util.List;

/**
 * 句子服务接口
 */
public interface SentenceService {
    
    /**
     * 根据ID查询句子
     * 
     * @param sentenceId 句子ID
     * @return 句子对象
     */
    Sentence getSentenceById(Integer sentenceId);
    
    /**
     * 获取所有句子
     * 
     * @return 句子列表
     */
    List<Sentence> getAllSentences();
    
    /**
     * 根据句子内容模糊查询
     * 
     * @param content 句子内容
     * @return 句子列表
     */
    List<Sentence> getSentencesByContent(String content);
    
    /**
     * 根据上下文模糊查询
     * 
     * @param context 上下文
     * @return 句子列表
     */
    List<Sentence> getSentencesByContext(String context);
    
    /**
     * 根据关键词搜索句子
     * 
     * @param keyword 关键词
     * @return 句子列表
     */
    List<Sentence> searchSentencesByKeyword(String keyword);
    
    /**
     * 创建句子
     * 
     * @param sentence 句子对象
     * @return 是否成功
     */
    boolean createSentence(Sentence sentence);
    
    /**
     * 更新句子
     * 
     * @param sentence 句子对象
     * @return 是否成功
     */
    boolean updateSentence(Sentence sentence);
    
    /**
     * 更新句子内容
     * 
     * @param sentenceId 句子ID
     * @param content 新的句子内容
     * @return 是否成功
     */
    boolean updateSentenceContent(Integer sentenceId, String content);
    
    /**
     * 更新上下文
     * 
     * @param sentenceId 句子ID
     * @param context 新的上下文
     * @return 是否成功
     */
    boolean updateContext(Integer sentenceId, String context);
    
    /**
     * 删除句子
     * 
     * @param sentenceId 句子ID
     * @return 是否成功
     */
    boolean deleteSentence(Integer sentenceId);
}