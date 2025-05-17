package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.WordSentence;

import java.util.List;

/**
 * 单词-例句关联服务接口
 */
public interface WordSentenceService {
    
    /**
     * 根据ID查询关联
     * 
     * @param id 关联ID
     * @return 单词-例句关联对象
     */
    WordSentence getWordSentenceById(Integer id);
    
    /**
     * 获取所有关联
     * 
     * @return 单词-例句关联列表
     */
    List<WordSentence> getAllWordSentences();
    
    /**
     * 根据单词ID查询关联的例句
     * 
     * @param wordId 单词ID
     * @return 单词-例句关联列表
     */
    List<WordSentence> getWordSentencesByWordId(Integer wordId);
    
    /**
     * 根据例句ID查询关联的单词
     * 
     * @param sentenceId 例句ID
     * @return 单词-例句关联列表
     */
    List<WordSentence> getWordSentencesBySentenceId(Integer sentenceId);
    
    /**
     * 根据单词ID和例句ID查询关联
     * 
     * @param wordId 单词ID
     * @param sentenceId 例句ID
     * @return 单词-例句关联
     */
    WordSentence getWordSentenceByWordIdAndSentenceId(Integer wordId, Integer sentenceId);
    
    /**
     * 创建单词-例句关联
     * 
     * @param wordSentence 单词-例句关联对象
     * @return 是否成功
     */
    boolean createWordSentence(WordSentence wordSentence);
    
    /**
     * 更新单词-例句关联
     * 
     * @param wordSentence 单词-例句关联对象
     * @return 是否成功
     */
    boolean updateWordSentence(WordSentence wordSentence);
    
    /**
     * 更新关联备注
     * 
     * @param id 关联ID
     * @param note 新的备注
     * @return 是否成功
     */
    boolean updateNote(Integer id, String note);
    
    /**
     * 删除单词-例句关联
     * 
     * @param id 关联ID
     * @return 是否成功
     */
    boolean deleteWordSentence(Integer id);
    
    /**
     * 批量创建单词-例句关联
     * 
     * @param wordSentences 单词-例句关联列表
     * @return 是否成功
     */
    boolean batchCreateWordSentences(List<WordSentence> wordSentences);
}