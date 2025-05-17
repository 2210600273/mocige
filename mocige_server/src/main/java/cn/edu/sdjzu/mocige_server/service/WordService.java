package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Word;

import java.util.List;

/**
 * 单词服务接口
 */
public interface WordService {
    
    /**
     * 根据ID查询单词
     * 
     * @param wordId 单词ID
     * @return 单词对象
     */
    Word getWordById(Integer wordId);
    
    /**
     * 获取所有单词
     * 
     * @return 单词列表
     */
    List<Word> getAllWords();
    
    /**
     * 根据单词查询
     * 
     * @param word 单词
     * @return 单词对象
     */
    Word getWordByWord(String word);
    
    /**
     * 根据词性查询
     * 
     * @param partOfSpeech 词性
     * @return 单词列表
     */
    List<Word> getWordsByPartOfSpeech(String partOfSpeech);
    
    /**
     * 根据定义模糊查询
     * 
     * @param definition 定义
     * @return 单词列表
     */
    List<Word> getWordsByDefinition(String definition);
    
    /**
     * 根据关键词搜索单词
     * 
     * @param keyword 关键词
     * @return 单词列表
     */
    List<Word> searchWordsByKeyword(String keyword);
    
    /**
     * 创建单词
     * 
     * @param word 单词对象
     * @return 是否成功
     */
    boolean createWord(Word word);
    
    /**
     * 更新单词信息
     * 
     * @param word 单词对象
     * @return 是否成功
     */
    boolean updateWord(Word word);
    
    /**
     * 更新单词发音
     * 
     * @param wordId 单词ID
     * @param pronunciation 新的发音
     * @return 是否成功
     */
    boolean updatePronunciation(Integer wordId, String pronunciation);
    
    /**
     * 更新单词定义
     * 
     * @param wordId 单词ID
     * @param definition 新的定义
     * @return 是否成功
     */
    boolean updateDefinition(Integer wordId, String definition);
    
    /**
     * 删除单词
     * 
     * @param wordId 单词ID
     * @return 是否成功
     */
    boolean deleteWord(Integer wordId);
}