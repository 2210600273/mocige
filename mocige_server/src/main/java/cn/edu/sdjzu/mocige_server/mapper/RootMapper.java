package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Root;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 词根Mapper接口
 */
@Mapper
public interface RootMapper extends BaseMapper<Root, Integer> {
    
    /**
     * 根据词根名称查询
     * 
     * @param rootName 词根名称
     * @return 词根列表
     */
    List<Root> selectByName(@Param("rootName") String rootName);
    
    /**
     * 根据词根含义查询
     * 
     * @param meaning 词根含义
     * @return 词根列表
     */
    List<Root> selectByMeaning(@Param("meaning") String meaning);
    
    /**
     * 根据词源查询
     * 
     * @param etymology 词源
     * @return 词根列表
     */
    List<Root> selectByEtymology(@Param("etymology") String etymology);
    
    /**
     * 模糊查询词根
     * 
     * @param keyword 关键词
     * @return 词根列表
     */
    List<Root> searchByKeyword(@Param("keyword") String keyword);
    
    /**
     * 更新词根含义
     * 
     * @param rootId 词根ID
     * @param meaning 新的含义
     * @return 影响行数
     */
    int updateMeaning(@Param("rootId") Integer rootId, @Param("meaning") String meaning);
}