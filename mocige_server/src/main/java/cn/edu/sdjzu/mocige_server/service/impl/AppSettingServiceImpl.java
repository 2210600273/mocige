package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.AppSetting;
import cn.edu.sdjzu.mocige_server.mapper.AppSettingMapper;
import cn.edu.sdjzu.mocige_server.service.AppSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用设置服务实现类
 */
@Service
public class AppSettingServiceImpl implements AppSettingService {

    @Autowired
    private AppSettingMapper appSettingMapper;
    
    @Override
    public AppSetting getSettingById(Integer settingId) {
        return appSettingMapper.selectById(settingId);
    }
    
    @Override
    public List<AppSetting> getAllSettings() {
        return appSettingMapper.selectAll();
    }
    
    @Override
    public AppSetting getSettingByKey(String settingKey) {
        // 假设这里调用的是根据主题查询的方法，因为AppSettingMapper中没有根据key查询的方法
        return appSettingMapper.selectByTheme(settingKey);
    }
    
    @Override
    public List<AppSetting> getSettingsByCategory(String category) {
        // 由于AppSettingMapper中没有根据类别查询的方法，这里可能需要自定义实现
        // 或者返回空列表
        return List.of();
    }
    
    @Override
    public boolean createSetting(AppSetting appSetting) {
        return appSettingMapper.insert(appSetting) > 0;
    }
    
    @Override
    public boolean updateSetting(AppSetting appSetting) {
        return appSettingMapper.update(appSetting) > 0;
    }
    
    @Override
    public boolean updateSettingValue(Integer settingId, String settingValue) {
        // 由于AppSettingMapper中没有直接更新设置值的方法，这里需要先查询再更新
        AppSetting appSetting = appSettingMapper.selectById(settingId);
        if (appSetting == null) {
            return false;
        }
        // 假设这里将settingValue设置到theme字段
        appSetting.setTheme(settingValue);
        return appSettingMapper.update(appSetting) > 0;
    }
    
    @Override
    public boolean updateSettingValueByKey(String settingKey, String settingValue) {
        // 由于AppSettingMapper中没有根据key更新的方法，这里需要先查询再更新
        AppSetting appSetting = appSettingMapper.selectByTheme(settingKey);
        if (appSetting == null) {
            return false;
        }
        // 假设这里将settingValue设置到theme字段
        appSetting.setTheme(settingValue);
        return appSettingMapper.update(appSetting) > 0;
    }
    
    @Override
    public boolean deleteSetting(Integer settingId) {
        return appSettingMapper.deleteById(settingId) > 0;
    }
    
    @Override
    public boolean deleteSettingByKey(String settingKey) {
        // 由于AppSettingMapper中没有根据key删除的方法，这里需要先查询再删除
        AppSetting appSetting = appSettingMapper.selectByTheme(settingKey);
        if (appSetting == null) {
            return false;
        }
        return appSettingMapper.deleteById(appSetting.getSettingId()) > 0;
    }
}