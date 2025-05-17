package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Sentence;
import cn.edu.sdjzu.mocige_server.mapper.SentenceMapper;
import cn.edu.sdjzu.mocige_server.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 句子服务实现类
 */
@Service
public class SentenceServiceImpl implements SentenceService {

    @Autowired
    private SentenceMapper sentenceMapper;
    
    @Override
    public Sentence getSentenceById(Integer sentenceId) {
        return sentenceMapper.selectById(sentenceId);
    }
    
    @Override
    public List<Sentence> getAllSentences() {
        return sentenceMapper.selectAll();
    }
    
    @Override
    public List<Sentence> getSentencesByContent(String content) {
        return sentenceMapper.selectByContent(content);
    }
    
    @Override
    public List<Sentence> getSentencesByContext(String context) {
        return sentenceMapper.selectByContext(context);
    }
    
    @Override
    public List<Sentence> searchSentencesByKeyword(String keyword) {
        return sentenceMapper.searchByKeyword(keyword);
    }
    
    @Override
    public boolean createSentence(Sentence sentence) {
        return sentenceMapper.insert(sentence) > 0;
    }
    
    @Override
    public boolean updateSentence(Sentence sentence) {
        return sentenceMapper.update(sentence) > 0;
    }
    
    @Override
    public boolean updateSentenceContent(Integer sentenceId, String content) {
        return sentenceMapper.updateSentenceContent(sentenceId, content) > 0;
    }
    
    @Override
    public boolean updateContext(Integer sentenceId, String context) {
        return sentenceMapper.updateContext(sentenceId, context) > 0;
    }
    
    @Override
    public boolean deleteSentence(Integer sentenceId) {
        return sentenceMapper.deleteById(sentenceId) > 0;
    }
}