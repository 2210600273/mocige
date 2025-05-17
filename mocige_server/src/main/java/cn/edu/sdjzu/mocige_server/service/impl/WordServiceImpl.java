package cn.edu.sdjzu.mocige_server.service.impl;

import cn.edu.sdjzu.mocige_server.entity.Word;
import cn.edu.sdjzu.mocige_server.mapper.WordMapper;
import cn.edu.sdjzu.mocige_server.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单词服务实现类
 */
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordMapper wordMapper;
    
    @Override
    public Word getWordById(Integer wordId) {
        return wordMapper.selectById(wordId);
    }
    
    @Override
    public List<Word> getAllWords() {
        return wordMapper.selectAll();
    }
    
    @Override
    public Word getWordByWord(String word) {
        return wordMapper.selectByWord(word);
    }
    
    @Override
    public List<Word> getWordsByPartOfSpeech(String partOfSpeech) {
        return wordMapper.selectByPartOfSpeech(partOfSpeech);
    }
    
    @Override
    public List<Word> getWordsByDefinition(String definition) {
        return wordMapper.selectByDefinition(definition);
    }
    
    @Override
    public List<Word> searchWordsByKeyword(String keyword) {
        return wordMapper.searchByKeyword(keyword);
    }
    
    @Override
    public boolean createWord(Word word) {
        return wordMapper.insert(word) > 0;
    }
    
    @Override
    public boolean updateWord(Word word) {
        return wordMapper.update(word) > 0;
    }
    
    @Override
    public boolean updatePronunciation(Integer wordId, String pronunciation) {
        return wordMapper.updatePronunciation(wordId, pronunciation) > 0;
    }
    
    @Override
    public boolean updateDefinition(Integer wordId, String definition) {
        return wordMapper.updateDefinition(wordId, definition) > 0;
    }
    
    @Override
    public boolean deleteWord(Integer wordId) {
        return wordMapper.deleteById(wordId) > 0;
    }
}