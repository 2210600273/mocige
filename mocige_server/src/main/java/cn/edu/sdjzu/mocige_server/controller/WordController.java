package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Word;
import cn.edu.sdjzu.mocige_server.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 单词控制器
 * 提供单词相关的RESTful API
 */
@RestController
@RequestMapping("/api/words")
public class WordController {

    @Autowired
    private WordService wordService;
    
    /**
     * 获取所有单词
     * 
     * @return 单词列表
     */
    @GetMapping
    public ResponseEntity<List<Word>> getAllWords() {
        List<Word> words = wordService.getAllWords();
        return ResponseEntity.ok(words);
    }
    
    /**
     * 根据ID获取单词
     * 
     * @param id 单词ID
     * @return 单词对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable("id") Integer id) {
        Word word = wordService.getWordById(id);
        if (word == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(word);
    }
    
    /**
     * 根据单词获取详情
     * 
     * @param word 单词
     * @return 单词对象
     */
    @GetMapping("/word/{word}")
    public ResponseEntity<Word> getWordByWord(@PathVariable("word") String word) {
        Word wordObj = wordService.getWordByWord(word);
        if (wordObj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wordObj);
    }
    
    /**
     * 根据词性获取单词列表
     * 
     * @param partOfSpeech 词性
     * @return 单词列表
     */
    @GetMapping("/part-of-speech/{partOfSpeech}")
    public ResponseEntity<List<Word>> getWordsByPartOfSpeech(@PathVariable("partOfSpeech") String partOfSpeech) {
        List<Word> words = wordService.getWordsByPartOfSpeech(partOfSpeech);
        return ResponseEntity.ok(words);
    }
    
    /**
     * 根据定义模糊查询单词
     * 
     * @param definition 定义
     * @return 单词列表
     */
    @GetMapping("/definition")
    public ResponseEntity<List<Word>> getWordsByDefinition(@RequestParam("definition") String definition) {
        List<Word> words = wordService.getWordsByDefinition(definition);
        return ResponseEntity.ok(words);
    }
    
    /**
     * 根据关键词搜索单词
     * 
     * @param keyword 关键词
     * @return 单词列表
     */
    @GetMapping("/search")
    public ResponseEntity<List<Word>> searchWordsByKeyword(@RequestParam("keyword") String keyword) {
        List<Word> words = wordService.searchWordsByKeyword(keyword);
        return ResponseEntity.ok(words);
    }
    
    /**
     * 创建单词
     * 
     * @param word 单词对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        boolean success = wordService.createWord(word);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(word);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新单词
     * 
     * @param id 单词ID
     * @param word 单词对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(
            @PathVariable("id") Integer id,
            @RequestBody Word word) {
        word.setWordId(id);
        boolean success = wordService.updateWord(word);
        if (success) {
            return ResponseEntity.ok(word);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新单词发音
     * 
     * @param id 单词ID
     * @param pronunciation 新的发音
     * @return 更新结果
     */
    @PatchMapping("/{id}/pronunciation")
    public ResponseEntity<Void> updatePronunciation(
            @PathVariable("id") Integer id,
            @RequestParam("pronunciation") String pronunciation) {
        boolean success = wordService.updatePronunciation(id, pronunciation);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新单词定义
     * 
     * @param id 单词ID
     * @param definition 新的定义
     * @return 更新结果
     */
    @PatchMapping("/{id}/definition")
    public ResponseEntity<Void> updateDefinition(
            @PathVariable("id") Integer id,
            @RequestParam("definition") String definition) {
        boolean success = wordService.updateDefinition(id, definition);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除单词
     * 
     * @param id 单词ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable("id") Integer id) {
        boolean success = wordService.deleteWord(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}