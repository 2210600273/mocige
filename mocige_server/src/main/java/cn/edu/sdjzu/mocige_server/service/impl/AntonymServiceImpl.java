package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Antonym;
import cn.edu.sdjzu.mocige_server.mapper.AntonymMapper;
import cn.edu.sdjzu.mocige_server.service.AntonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 反义词服务实现类
 */
@Service
public class AntonymServiceImpl implements AntonymService {

    @Autowired
    private AntonymMapper antonymMapper;
    
    @Override
    public Antonym getAntonymById(Integer antonymId) {
        return antonymMapper.selectById(antonymId);
    }
    
    @Override
    public List<Antonym> getAllAntonyms() {
        return antonymMapper.selectAll();
    }
    
    @Override
    public List<Antonym> getAntonymsByWordId(Integer wordId) {
        return antonymMapper.selectByWordId(wordId);
    }
    
    
    @Override
    public boolean createAntonym(Antonym antonym) {
        return antonymMapper.insert(antonym) > 0;
    }
    
    @Override
    public boolean updateAntonym(Antonym antonym) {
        return antonymMapper.update(antonym) > 0;
    }
    
    @Override
    public boolean deleteAntonym(Integer antonymId) {
        return antonymMapper.deleteById(antonymId) > 0;
    }
    
    @Override
    public boolean deleteAntonymsByWordId(Integer wordId) {
        return antonymMapper.deleteByWordId(wordId) > 0;
    }
}