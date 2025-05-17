package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.WordSentence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单词-例句关联Mapper接口
 */
@Mapper
public interface WordSentenceMapper extends BaseMapper<WordSentence, Integer> {
    
    /**
     * 根据单词ID查询关联的例句
     * 
     * @param wordId 单词ID
     * @return 单词-例句关联列表
     */
    List<WordSentence> selectByWordId(@Param("wordId") Integer wordId);
    
    /**
     * 根据例句ID查询关联的单词
     * 
     * @param sentenceId 例句ID
     * @return 单词-例句关联列表
     */
    List<WordSentence> selectBySentenceId(@Param("sentenceId") Integer sentenceId);
    
    /**
     * 根据单词ID和例句ID查询关联
     * 
     * @param wordId 单词ID
     * @param sentenceId 例句ID
     * @return 单词-例句关联
     */
    WordSentence selectByWordIdAndSentenceId(@Param("wordId") Integer wordId, @Param("sentenceId") Integer sentenceId);
    
    /**
     * 更新关联备注
     * 
     * @param id 关联ID
     * @param note 新的备注
     * @return 影响行数
     */
    int updateNote(@Param("id") Integer id, @Param("note") String note);
    
    /**
     * 批量插入单词-例句关联
     * 
     * @param wordSentences 单词-例句关联列表
     * @return 影响行数
     */
    int batchInsert(@Param("list") List<WordSentence> wordSentences);
}