package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单词Mapper接口
 */
@Mapper
public interface WordMapper extends BaseMapper<Word, Integer> {
    
    /**
     * 根据单词查询
     * 
     * @param word 单词
     * @return 单词对象
     */
    Word selectByWord(@Param("word") String word);
    
    /**
     * 根据词性查询
     * 
     * @param partOfSpeech 词性
     * @return 单词列表
     */
    List<Word> selectByPartOfSpeech(@Param("partOfSpeech") String partOfSpeech);
    
    /**
     * 根据定义模糊查询
     * 
     * @param definition 定义
     * @return 单词列表
     */
    List<Word> selectByDefinition(@Param("definition") String definition);
    
    /**
     * 根据关键词搜索单词
     * 
     * @param keyword 关键词
     * @return 单词列表
     */
    List<Word> searchByKeyword(@Param("keyword") String keyword);
    
    /**
     * 更新单词发音
     * 
     * @param wordId 单词ID
     * @param pronunciation 新的发音
     * @return 影响行数
     */
    int updatePronunciation(@Param("wordId") Integer wordId, @Param("pronunciation") String pronunciation);
    
    /**
     * 更新单词定义
     * 
     * @param wordId 单词ID
     * @param definition 新的定义
     * @return 影响行数
     */
    int updateDefinition(@Param("wordId") Integer wordId, @Param("definition") String definition);
}