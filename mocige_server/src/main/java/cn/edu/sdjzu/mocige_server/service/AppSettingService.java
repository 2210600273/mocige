package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.AppSetting;

import java.util.List;

/**
 * 应用设置服务接口
 */
public interface AppSettingService {
    
    /**
     * 根据ID查询应用设置
     * 
     * @param settingId 设置ID
     * @return 应用设置对象
     */
    AppSetting getSettingById(Integer settingId);
    
    /**
     * 获取所有应用设置
     * 
     * @return 应用设置列表
     */
    List<AppSetting> getAllSettings();
    
    /**
     * 根据设置键查询
     * 
     * @param settingKey 设置键
     * @return 应用设置对象
     */
    AppSetting getSettingByKey(String settingKey);
    
    /**
     * 根据设置类别查询
     * 
     * @param category 设置类别
     * @return 应用设置列表
     */
    List<AppSetting> getSettingsByCategory(String category);
    
    /**
     * 创建应用设置
     * 
     * @param appSetting 应用设置对象
     * @return 是否成功
     */
    boolean createSetting(AppSetting appSetting);
    
    /**
     * 更新应用设置
     * 
     * @param appSetting 应用设置对象
     * @return 是否成功
     */
    boolean updateSetting(AppSetting appSetting);
    
    /**
     * 更新设置值
     * 
     * @param settingId 设置ID
     * @param settingValue 新的设置值
     * @return 是否成功
     */
    boolean updateSettingValue(Integer settingId, String settingValue);
    
    /**
     * 根据设置键更新设置值
     * 
     * @param settingKey 设置键
     * @param settingValue 新的设置值
     * @return 是否成功
     */
    boolean updateSettingValueByKey(String settingKey, String settingValue);
    
    /**
     * 删除应用设置
     * 
     * @param settingId 设置ID
     * @return 是否成功
     */
    boolean deleteSetting(Integer settingId);
    
    /**
     * 根据设置键删除应用设置
     * 
     * @param settingKey 设置键
     * @return 是否成功
     */
    boolean deleteSettingByKey(String settingKey);
}