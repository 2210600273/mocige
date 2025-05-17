package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Sentence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 句子Mapper接口
 */
@Mapper
public interface SentenceMapper extends BaseMapper<Sentence, Integer> {
    
    /**
     * 根据句子内容模糊查询
     * 
     * @param content 句子内容
     * @return 句子列表
     */
    List<Sentence> selectByContent(@Param("content") String content);
    
    /**
     * 根据上下文模糊查询
     * 
     * @param context 上下文
     * @return 句子列表
     */
    List<Sentence> selectByContext(@Param("context") String context);
    
    /**
     * 根据关键词搜索句子
     * 
     * @param keyword 关键词
     * @return 句子列表
     */
    List<Sentence> searchByKeyword(@Param("keyword") String keyword);
    
    /**
     * 更新句子内容
     * 
     * @param sentenceId 句子ID
     * @param sentence 新的句子内容
     * @return 影响行数
     */
    int updateSentenceContent(@Param("sentenceId") Integer sentenceId, @Param("sentence") String sentence);
    
    /**
     * 更新上下文
     * 
     * @param sentenceId 句子ID
     * @param context 新的上下文
     * @return 影响行数
     */
    int updateContext(@Param("sentenceId") Integer sentenceId, @Param("context") String context);
}