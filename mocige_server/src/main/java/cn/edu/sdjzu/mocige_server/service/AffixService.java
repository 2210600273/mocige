package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Affix;

import java.util.List;

/**
 * 词缀服务接口
 */
public interface AffixService {
    
    /**
     * 根据ID查询词缀
     * 
     * @param affixId 词缀ID
     * @return 词缀对象
     */
    Affix getAffixById(Integer affixId);
    
    /**
     * 获取所有词缀
     * 
     * @return 词缀列表
     */
    List<Affix> getAllAffixes();
    
    
    /**
     * 创建词缀
     * 
     * @param affix 词缀对象
     * @return 是否成功
     */
    boolean createAffix(Affix affix);
    
    /**
     * 更新词缀信息
     * 
     * @param affix 词缀对象
     * @return 是否成功
     */
    boolean updateAffix(Affix affix);
    
    
    /**
     * 删除词缀
     * 
     * @param affixId 词缀ID
     * @return 是否成功
     */
    boolean deleteAffix(Integer affixId);
}