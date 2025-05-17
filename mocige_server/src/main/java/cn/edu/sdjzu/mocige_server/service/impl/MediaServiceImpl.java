package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Media;
import cn.edu.sdjzu.mocige_server.mapper.MediaMapper;
import cn.edu.sdjzu.mocige_server.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体资源服务实现类
 */
@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaMapper mediaMapper;
    
    @Override
    public Media getMediaById(Integer mediaId) {
        return mediaMapper.selectById(mediaId);
    }
    
    @Override
    public List<Media> getAllMedia() {
        return mediaMapper.selectAll();
    }
    
    @Override
    public List<Media> getMediaByType(String mediaType) {
        return mediaMapper.selectByType(mediaType);
    }
    
    @Override
    public Media getMediaByUrl(String url) {
        return mediaMapper.selectByUrl(url);
    }
    
    @Override
    public List<Media> getMediaByRelatedId(Integer relatedId) {
        return mediaMapper.selectByRelatedId(relatedId);
    }
    
    @Override
    public List<Media> getMediaByRelatedType(String relatedType) {
        return mediaMapper.selectByRelatedType(relatedType);
    }
    
    @Override
    public boolean createMedia(Media media) {
        return mediaMapper.insert(media) > 0;
    }
    
    @Override
    public boolean updateMedia(Media media) {
        return mediaMapper.update(media) > 0;
    }
    
    @Override
    public boolean updateMediaUrl(Integer mediaId, String url) {
        return mediaMapper.updateUrl(mediaId, url) > 0;
    }
    
    @Override
    public boolean deleteMedia(Integer mediaId) {
        return mediaMapper.deleteById(mediaId) > 0;
    }
}