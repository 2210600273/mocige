package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Media;
import cn.edu.sdjzu.mocige_server.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 媒体资源控制器
 * 提供媒体资源相关的RESTful API
 */
@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;
    
    /**
     * 获取所有媒体资源
     * 
     * @return 媒体资源列表
     */
    @GetMapping
    public ResponseEntity<List<Media>> getAllMedia() {
        List<Media> mediaList = mediaService.getAllMedia();
        return ResponseEntity.ok(mediaList);
    }
    
    /**
     * 根据ID获取媒体资源
     * 
     * @param id 媒体资源ID
     * @return 媒体资源对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaById(@PathVariable("id") Integer id) {
        Media media = mediaService.getMediaById(id);
        if (media == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(media);
    }
    
    /**
     * 根据类型获取媒体资源
     * 
     * @param type 媒体类型
     * @return 媒体资源列表
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Media>> getMediaByType(@PathVariable("type") String type) {
        List<Media> mediaList = mediaService.getMediaByType(type);
        return ResponseEntity.ok(mediaList);
    }
    
    /**
     * 根据URL获取媒体资源
     * 
     * @param url 媒体URL
     * @return 媒体资源对象
     */
    @GetMapping("/url")
    public ResponseEntity<Media> getMediaByUrl(@RequestParam("url") String url) {
        Media media = mediaService.getMediaByUrl(url);
        if (media == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(media);
    }
    
    /**
     * 根据关联ID获取媒体资源
     * 
     * @param relatedId 关联ID
     * @return 媒体资源列表
     */
    @GetMapping("/related-id/{relatedId}")
    public ResponseEntity<List<Media>> getMediaByRelatedId(@PathVariable("relatedId") Integer relatedId) {
        List<Media> mediaList = mediaService.getMediaByRelatedId(relatedId);
        return ResponseEntity.ok(mediaList);
    }
    
    /**
     * 根据关联类型获取媒体资源
     * 
     * @param relatedType 关联类型
     * @return 媒体资源列表
     */
    @GetMapping("/related-type/{relatedType}")
    public ResponseEntity<List<Media>> getMediaByRelatedType(@PathVariable("relatedType") String relatedType) {
        List<Media> mediaList = mediaService.getMediaByRelatedType(relatedType);
        return ResponseEntity.ok(mediaList);
    }
    
    /**
     * 创建媒体资源
     * 
     * @param media 媒体资源对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Media> createMedia(@RequestBody Media media) {
        boolean success = mediaService.createMedia(media);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(media);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新媒体资源
     * 
     * @param id 媒体资源ID
     * @param media 媒体资源对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Media> updateMedia(
            @PathVariable("id") Integer id,
            @RequestBody Media media) {
        media.setMediaId(id);
        boolean success = mediaService.updateMedia(media);
        if (success) {
            return ResponseEntity.ok(media);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新媒体URL
     * 
     * @param id 媒体ID
     * @param url 新的URL
     * @return 更新结果
     */
    @PatchMapping("/{id}/url")
    public ResponseEntity<Void> updateMediaUrl(
            @PathVariable("id") Integer id,
            @RequestParam("url") String url) {
        boolean success = mediaService.updateMediaUrl(id, url);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除媒体资源
     * 
     * @param id 媒体资源ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable("id") Integer id) {
        boolean success = mediaService.deleteMedia(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}