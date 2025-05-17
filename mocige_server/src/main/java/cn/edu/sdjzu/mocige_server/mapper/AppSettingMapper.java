package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.AppSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 应用设置Mapper接口
 */
@Mapper
public interface AppSettingMapper extends BaseMapper<AppSetting, Integer> {
    
    /**
     * 根据主题查询应用设置
     * 
     * @param theme 主题
     * @return 应用设置列表
     */
    AppSetting selectByTheme(@Param("theme") String theme);
}