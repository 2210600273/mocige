package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Synonym;
import cn.edu.sdjzu.mocige_server.mapper.SynonymMapper;
import cn.edu.sdjzu.mocige_server.service.SynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 同义词服务实现类
 */
@Service
public class SynonymServiceImpl implements SynonymService {

    @Autowired
    private SynonymMapper synonymMapper;
    
    @Override
    public Synonym getSynonymById(Integer synonymId) {
        return synonymMapper.selectById(synonymId);
    }
    
    @Override
    public List<Synonym> getAllSynonyms() {
        return synonymMapper.selectAll();
    }
    
    @Override
    public List<Synonym> getSynonymsByWordId(Integer wordId) {
        return synonymMapper.selectByWordId(wordId);
    }
    
    @Override
    public List<Synonym> getSynonymsByRelatedWord(String relatedWord) {
        return synonymMapper.selectByRelatedWord(relatedWord);
    }
    
    @Override
    public Synonym getSynonymByWordIdAndRelatedWord(Integer wordId, String relatedWord) {
        return synonymMapper.selectByWordIdAndRelatedWord(wordId, relatedWord);
    }
    
    @Override
    public boolean createSynonym(Synonym synonym) {
        return synonymMapper.insert(synonym) > 0;
    }
    
    @Override
    public boolean updateSynonym(Synonym synonym) {
        return synonymMapper.update(synonym) > 0;
    }
    
    @Override
    public boolean updateRelatedWord(Integer synonymId, String relatedWord) {
        return synonymMapper.updateRelatedWord(synonymId, relatedWord) > 0;
    }
    
    @Override
    public boolean deleteSynonym(Integer synonymId) {
        return synonymMapper.deleteById(synonymId) > 0;
    }
}