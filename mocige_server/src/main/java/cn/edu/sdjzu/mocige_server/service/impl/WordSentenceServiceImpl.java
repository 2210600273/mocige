package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.WordSentence;
import cn.edu.sdjzu.mocige_server.mapper.WordSentenceMapper;
import cn.edu.sdjzu.mocige_server.service.WordSentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单词-例句关联服务实现类
 */
@Service
public class WordSentenceServiceImpl implements WordSentenceService {

    @Autowired
    private WordSentenceMapper wordSentenceMapper;
    
    @Override
    public WordSentence getWordSentenceById(Integer id) {
        return wordSentenceMapper.selectById(id);
    }
    
    @Override
    public List<WordSentence> getAllWordSentences() {
        return wordSentenceMapper.selectAll();
    }
    
    @Override
    public List<WordSentence> getWordSentencesByWordId(Integer wordId) {
        return wordSentenceMapper.selectByWordId(wordId);
    }
    
    @Override
    public List<WordSentence> getWordSentencesBySentenceId(Integer sentenceId) {
        return wordSentenceMapper.selectBySentenceId(sentenceId);
    }
    
    @Override
    public WordSentence getWordSentenceByWordIdAndSentenceId(Integer wordId, Integer sentenceId) {
        return wordSentenceMapper.selectByWordIdAndSentenceId(wordId, sentenceId);
    }
    
    @Override
    public boolean createWordSentence(WordSentence wordSentence) {
        return wordSentenceMapper.insert(wordSentence) > 0;
    }
    
    @Override
    public boolean updateWordSentence(WordSentence wordSentence) {
        return wordSentenceMapper.update(wordSentence) > 0;
    }
    
    @Override
    public boolean updateNote(Integer id, String note) {
        return wordSentenceMapper.updateNote(id, note) > 0;
    }
    
    @Override
    public boolean deleteWordSentence(Integer id) {
        return wordSentenceMapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean batchCreateWordSentences(List<WordSentence> wordSentences) {
        return wordSentenceMapper.batchInsert(wordSentences) > 0;
    }
}