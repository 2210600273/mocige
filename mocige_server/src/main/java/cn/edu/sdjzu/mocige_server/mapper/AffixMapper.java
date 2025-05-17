package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Affix;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 词缀Mapper接口
 */
@Mapper
public interface AffixMapper {
    
    /**
     * 根据ID查询词缀
     * 
     * @param affixId 词缀ID
     * @return 词缀对象
     */
    Affix selectById(@Param("affixId") Integer affixId);
    
    /**
     * 查询所有词缀
     * 
     * @return 词缀列表
     */
    List<Affix> selectAll();
    
    /**
     * 根据词缀名称模糊查询
     * 
     * @param affix 词缀名称
     * @return 词缀列表
     */
    List<Affix> selectByAffix(@Param("affix") String affix);
    
    /**
     * 插入词缀
     * 
     * @param affix 词缀对象
     * @return 影响行数
     */
    int insert(Affix affix);
    
    /**
     * 更新词缀
     * 
     * @param affix 词缀对象
     * @return 影响行数
     */
    int update(Affix affix);
    
    /**
     * 删除词缀
     * 
     * @param affixId 词缀ID
     * @return 影响行数
     */
    int deleteById(@Param("affixId") Integer affixId);
}