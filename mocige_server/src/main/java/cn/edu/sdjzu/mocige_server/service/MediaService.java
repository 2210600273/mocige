package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Media;

import java.util.List;

/**
 * 媒体资源服务接口
 */
public interface MediaService {
    
    /**
     * 根据ID查询媒体资源
     * 
     * @param mediaId 媒体资源ID
     * @return 媒体资源对象
     */
    Media getMediaById(Integer mediaId);
    
    /**
     * 获取所有媒体资源
     * 
     * @return 媒体资源列表
     */
    List<Media> getAllMedia();
    
    /**
     * 根据类型查询媒体资源
     * 
     * @param mediaType 媒体类型
     * @return 媒体资源列表
     */
    List<Media> getMediaByType(String mediaType);
    
    /**
     * 根据URL查询媒体资源
     * 
     * @param url 媒体URL
     * @return 媒体资源
     */
    Media getMediaByUrl(String url);
    
    /**
     * 根据关联ID查询媒体资源
     * 
     * @param relatedId 关联ID
     * @return 媒体资源列表
     */
    List<Media> getMediaByRelatedId(Integer relatedId);
    
    /**
     * 根据关联类型查询媒体资源
     * 
     * @param relatedType 关联类型
     * @return 媒体资源列表
     */
    List<Media> getMediaByRelatedType(String relatedType);
    
    /**
     * 创建媒体资源
     * 
     * @param media 媒体资源对象
     * @return 是否成功
     */
    boolean createMedia(Media media);
    
    /**
     * 更新媒体资源
     * 
     * @param media 媒体资源对象
     * @return 是否成功
     */
    boolean updateMedia(Media media);
    
    /**
     * 更新媒体URL
     * 
     * @param mediaId 媒体ID
     * @param url 新的URL
     * @return 是否成功
     */
    boolean updateMediaUrl(Integer mediaId, String url);
    
    /**
     * 删除媒体资源
     * 
     * @param mediaId 媒体资源ID
     * @return 是否成功
     */
    boolean deleteMedia(Integer mediaId);
}