package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Affix;
import cn.edu.sdjzu.mocige_server.mapper.AffixMapper;
import cn.edu.sdjzu.mocige_server.service.AffixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 词缀服务实现类
 */
@Service
public class AffixServiceImpl implements AffixService {

    @Autowired
    private AffixMapper affixMapper;
    
    @Override
    public Affix getAffixById(Integer affixId) {
        return affixMapper.selectById(affixId);
    }
    
    @Override
    public List<Affix> getAllAffixes() {
        return affixMapper.selectAll();
    }

    
    @Override
    public boolean createAffix(Affix affix) {
        return affixMapper.insert(affix) > 0;
    }
    
    @Override
    public boolean updateAffix(Affix affix) {
        return affixMapper.update(affix) > 0;
    }
    
    @Override
    public boolean deleteAffix(Integer affixId) {
        return affixMapper.deleteById(affixId) > 0;
    }
}