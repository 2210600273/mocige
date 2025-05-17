package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Synonym;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 同义词Mapper接口
 */
@Mapper
public interface SynonymMapper extends BaseMapper<Synonym, Integer> {
    
    /**
     * 根据单词ID查询同义词
     * 
     * @param wordId 单词ID
     * @return 同义词列表
     */
    List<Synonym> selectByWordId(@Param("wordId") Integer wordId);
    
    /**
     * 根据相关单词查询同义词
     * 
     * @param relatedWord 相关单词
     * @return 同义词列表
     */
    List<Synonym> selectByRelatedWord(@Param("relatedWord") String relatedWord);
    
    /**
     * 根据单词ID和相关单词查询同义词
     * 
     * @param wordId 单词ID
     * @param relatedWord 相关单词
     * @return 同义词
     */
    Synonym selectByWordIdAndRelatedWord(@Param("wordId") Integer wordId, @Param("relatedWord") String relatedWord);
    
    /**
     * 更新相关单词
     * 
     * @param synonymId 同义词ID
     * @param relatedWord 新的相关单词
     * @return 影响行数
     */
    int updateRelatedWord(@Param("synonymId") Integer synonymId, @Param("relatedWord") String relatedWord);
}