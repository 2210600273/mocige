package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Antonym;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 反义词Mapper接口
 */
@Mapper
public interface AntonymMapper extends BaseMapper<Antonym, Integer> {
    
    /**
     * 根据单词ID查询反义词列表
     * 
     * @param wordId 单词ID
     * @return 反义词列表
     */
    List<Antonym> selectByWordId(@Param("wordId") Integer wordId);
    
    /**
     * 根据相关单词查询反义词
     * 
     * @param relatedWord 相关单词
     * @return 反义词列表
     */
    List<Antonym> selectByRelatedWord(@Param("relatedWord") String relatedWord);
    
    /**
     * 批量插入反义词
     * 
     * @param antonyms 反义词列表
     * @return 影响行数
     */
    int batchInsert(List<Antonym> antonyms);
    
    /**
     * 根据单词ID删除反义词
     * 
     * @param wordId 单词ID
     * @return 影响行数
     */
    int deleteByWordId(@Param("wordId") Integer wordId);
}