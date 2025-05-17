package cn.edu.sdjzu.mocige_server.service;

import cn.edu.sdjzu.mocige_server.entity.Root;

import java.util.List;

/**
 * 词根服务接口
 */
public interface RootService {
    
    /**
     * 根据ID查询词根
     * 
     * @param rootId 词根ID
     * @return 词根对象
     */
    Root getRootById(Integer rootId);
    
    /**
     * 获取所有词根
     * 
     * @return 词根列表
     */
    List<Root> getAllRoots();
    
    /**
     * 根据词根名称查询
     * 
     * @param rootName 词根名称
     * @return 词根列表
     */
    List<Root> getRootsByName(String rootName);
    
    /**
     * 根据词根含义查询
     * 
     * @param meaning 词根含义
     * @return 词根列表
     */
    List<Root> getRootsByMeaning(String meaning);
    
    /**
     * 根据词源查询
     * 
     * @param etymology 词源
     * @return 词根列表
     */
    List<Root> getRootsByEtymology(String etymology);
    
    /**
     * 模糊查询词根
     * 
     * @param keyword 关键词
     * @return 词根列表
     */
    List<Root> searchRootsByKeyword(String keyword);
    
    /**
     * 创建词根
     * 
     * @param root 词根对象
     * @return 是否成功
     */
    boolean createRoot(Root root);
    
    /**
     * 更新词根
     * 
     * @param root 词根对象
     * @return 是否成功
     */
    boolean updateRoot(Root root);
    
    /**
     * 更新词根含义
     * 
     * @param rootId 词根ID
     * @param meaning 新的含义
     * @return 是否成功
     */
    boolean updateRootMeaning(Integer rootId, String meaning);
    
    /**
     * 删除词根
     * 
     * @param rootId 词根ID
     * @return 是否成功
     */
    boolean deleteRoot(Integer rootId);
}