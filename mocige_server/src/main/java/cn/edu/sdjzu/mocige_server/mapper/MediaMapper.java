package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Media;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 媒体资源Mapper接口
 */
@Mapper
public interface MediaMapper extends BaseMapper<Media, Integer> {
    
    /**
     * 根据类型查询媒体资源
     * 
     * @param mediaType 媒体类型
     * @return 媒体资源列表
     */
    List<Media> selectByType(@Param("mediaType") String mediaType);
    
    /**
     * 根据URL查询媒体资源
     * 
     * @param url 媒体URL
     * @return 媒体资源
     */
    Media selectByUrl(@Param("url") String url);
    
    /**
     * 根据关联ID查询媒体资源
     * 
     * @param relatedId 关联ID
     * @return 媒体资源列表
     */
    List<Media> selectByRelatedId(@Param("relatedId") Integer relatedId);
    
    /**
     * 根据关联类型查询媒体资源
     * 
     * @param relatedType 关联类型
     * @return 媒体资源列表
     */
    List<Media> selectByRelatedType(@Param("relatedType") String relatedType);
    
    /**
     * 更新媒体URL
     * 
     * @param mediaId 媒体ID
     * @param url 新的URL
     * @return 影响行数
     */
    int updateUrl(@Param("mediaId") Integer mediaId, @Param("url") String url);
}