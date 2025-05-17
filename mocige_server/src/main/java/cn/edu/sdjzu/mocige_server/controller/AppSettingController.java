package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.AppSetting;
import cn.edu.sdjzu.mocige_server.service.AppSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用设置控制器
 * 提供应用设置相关的RESTful API
 */
@RestController
@RequestMapping("/api/settings")
public class AppSettingController {

    @Autowired
    private AppSettingService appSettingService;
    
    /**
     * 获取所有应用设置
     * 
     * @return 应用设置列表
     */
    @GetMapping
    public ResponseEntity<List<AppSetting>> getAllSettings() {
        List<AppSetting> settings = appSettingService.getAllSettings();
        return ResponseEntity.ok(settings);
    }
    
    /**
     * 根据ID获取应用设置
     * 
     * @param id 设置ID
     * @return 应用设置对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppSetting> getSettingById(@PathVariable("id") Integer id) {
        AppSetting setting = appSettingService.getSettingById(id);
        if (setting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(setting);
    }
    
    /**
     * 根据键获取应用设置
     * 
     * @param key 设置键
     * @return 应用设置对象
     */
    @GetMapping("/key/{key}")
    public ResponseEntity<AppSetting> getSettingByKey(@PathVariable("key") String key) {
        AppSetting setting = appSettingService.getSettingByKey(key);
        if (setting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(setting);
    }
    
    /**
     * 根据类别获取应用设置
     * 
     * @param category 设置类别
     * @return 应用设置列表
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<AppSetting>> getSettingsByCategory(@PathVariable("category") String category) {
        List<AppSetting> settings = appSettingService.getSettingsByCategory(category);
        return ResponseEntity.ok(settings);
    }
    
    /**
     * 创建应用设置
     * 
     * @param appSetting 应用设置对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<AppSetting> createSetting(@RequestBody AppSetting appSetting) {
        boolean success = appSettingService.createSetting(appSetting);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(appSetting);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新应用设置
     * 
     * @param id 设置ID
     * @param appSetting 应用设置对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppSetting> updateSetting(
            @PathVariable("id") Integer id,
            @RequestBody AppSetting appSetting) {
        appSetting.setSettingId(id);
        boolean success = appSettingService.updateSetting(appSetting);
        if (success) {
            return ResponseEntity.ok(appSetting);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新设置值
     * 
     * @param id 设置ID
     * @param value 新的设置值
     * @return 更新结果
     */
    @PatchMapping("/{id}/value")
    public ResponseEntity<Void> updateSettingValue(
            @PathVariable("id") Integer id,
            @RequestParam("value") String value) {
        boolean success = appSettingService.updateSettingValue(id, value);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 根据键更新设置值
     * 
     * @param key 设置键
     * @param value 新的设置值
     * @return 更新结果
     */
    @PatchMapping("/key/{key}/value")
    public ResponseEntity<Void> updateSettingValueByKey(
            @PathVariable("key") String key,
            @RequestParam("value") String value) {
        boolean success = appSettingService.updateSettingValueByKey(key, value);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除应用设置
     * 
     * @param id 设置ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable("id") Integer id) {
        boolean success = appSettingService.deleteSetting(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 根据键删除应用设置
     * 
     * @param key 设置键
     * @return 删除结果
     */
    @DeleteMapping("/key/{key}")
    public ResponseEntity<Void> deleteSettingByKey(@PathVariable("key") String key) {
        boolean success = appSettingService.deleteSettingByKey(key);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}