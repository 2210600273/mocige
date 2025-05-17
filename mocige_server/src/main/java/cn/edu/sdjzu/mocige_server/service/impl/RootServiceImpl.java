package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Root;
import cn.edu.sdjzu.mocige_server.mapper.RootMapper;
import cn.edu.sdjzu.mocige_server.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 词根服务实现类
 */
@Service
public class RootServiceImpl implements RootService {

    @Autowired
    private RootMapper rootMapper;
    
    @Override
    public Root getRootById(Integer rootId) {
        return rootMapper.selectById(rootId);
    }
    
    @Override
    public List<Root> getAllRoots() {
        return rootMapper.selectAll();
    }
    
    @Override
    public List<Root> getRootsByName(String rootName) {
        return rootMapper.selectByName(rootName);
    }
    
    @Override
    public List<Root> getRootsByMeaning(String meaning) {
        return rootMapper.selectByMeaning(meaning);
    }
    
    @Override
    public List<Root> getRootsByEtymology(String etymology) {
        return rootMapper.selectByEtymology(etymology);
    }
    
    @Override
    public List<Root> searchRootsByKeyword(String keyword) {
        return rootMapper.searchByKeyword(keyword);
    }
    
    @Override
    public boolean createRoot(Root root) {
        return rootMapper.insert(root) > 0;
    }
    
    @Override
    public boolean updateRoot(Root root) {
        return rootMapper.update(root) > 0;
    }
    
    @Override
    public boolean updateRootMeaning(Integer rootId, String meaning) {
        return rootMapper.updateMeaning(rootId, meaning) > 0;
    }
    
    @Override
    public boolean deleteRoot(Integer rootId) {
        return rootMapper.deleteById(rootId) > 0;
    }
}