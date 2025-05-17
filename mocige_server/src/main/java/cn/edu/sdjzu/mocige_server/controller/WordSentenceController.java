package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.WordSentence;
import cn.edu.sdjzu.mocige_server.service.WordSentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 单词-例句关联控制器
 * 提供单词-例句关联相关的RESTful API
 */
@RestController
@RequestMapping("/api/word-sentences")
public class WordSentenceController {

    @Autowired
    private WordSentenceService wordSentenceService;
    
    /**
     * 获取所有单词-例句关联
     * 
     * @return 单词-例句关联列表
     */
    @GetMapping
    public ResponseEntity<List<WordSentence>> getAllWordSentences() {
        List<WordSentence> wordSentences = wordSentenceService.getAllWordSentences();
        return ResponseEntity.ok(wordSentences);
    }
    
    /**
     * 根据ID获取单词-例句关联
     * 
     * @param id 关联ID
     * @return 单词-例句关联对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<WordSentence> getWordSentenceById(@PathVariable("id") Integer id) {
        WordSentence wordSentence = wordSentenceService.getWordSentenceById(id);
        if (wordSentence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wordSentence);
    }
    
    /**
     * 根据单词ID获取关联的例句
     * 
     * @param wordId 单词ID
     * @return 单词-例句关联列表
     */
    @GetMapping("/word/{wordId}")
    public ResponseEntity<List<WordSentence>> getWordSentencesByWordId(@PathVariable("wordId") Integer wordId) {
        List<WordSentence> wordSentences = wordSentenceService.getWordSentencesByWordId(wordId);
        return ResponseEntity.ok(wordSentences);
    }
    
    /**
     * 根据例句ID获取关联的单词
     * 
     * @param sentenceId 例句ID
     * @return 单词-例句关联列表
     */
    @GetMapping("/sentence/{sentenceId}")
    public ResponseEntity<List<WordSentence>> getWordSentencesBySentenceId(@PathVariable("sentenceId") Integer sentenceId) {
        List<WordSentence> wordSentences = wordSentenceService.getWordSentencesBySentenceId(sentenceId);
        return ResponseEntity.ok(wordSentences);
    }
    
    /**
     * 根据单词ID和例句ID获取关联
     * 
     * @param wordId 单词ID
     * @param sentenceId 例句ID
     * @return 单词-例句关联对象
     */
    @GetMapping("/word/{wordId}/sentence/{sentenceId}")
    public ResponseEntity<WordSentence> getWordSentenceByWordIdAndSentenceId(
            @PathVariable("wordId") Integer wordId,
            @PathVariable("sentenceId") Integer sentenceId) {
        WordSentence wordSentence = wordSentenceService.getWordSentenceByWordIdAndSentenceId(wordId, sentenceId);
        if (wordSentence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wordSentence);
    }
    
    /**
     * 创建单词-例句关联
     * 
     * @param wordSentence 单词-例句关联对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<WordSentence> createWordSentence(@RequestBody WordSentence wordSentence) {
        boolean success = wordSentenceService.createWordSentence(wordSentence);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(wordSentence);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 批量创建单词-例句关联
     * 
     * @param wordSentences 单词-例句关联列表
     * @return 创建结果
     */
    @PostMapping("/batch")
    public ResponseEntity<List<WordSentence>> batchCreateWordSentences(@RequestBody List<WordSentence> wordSentences) {
        boolean success = wordSentenceService.batchCreateWordSentences(wordSentences);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(wordSentences);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新单词-例句关联
     * 
     * @param id 关联ID
     * @param wordSentence 单词-例句关联对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<WordSentence> updateWordSentence(
            @PathVariable("id") Integer id,
            @RequestBody WordSentence wordSentence) {
        wordSentence.setWordId(id);
        boolean success = wordSentenceService.updateWordSentence(wordSentence);
        if (success) {
            return ResponseEntity.ok(wordSentence);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新关联备注
     * 
     * @param id 关联ID
     * @param note 新的备注
     * @return 更新结果
     */
    @PatchMapping("/{id}/note")
    public ResponseEntity<Void> updateNote(
            @PathVariable("id") Integer id,
            @RequestParam("note") String note) {
        boolean success = wordSentenceService.updateNote(id, note);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除单词-例句关联
     * 
     * @param id 关联ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWordSentence(@PathVariable("id") Integer id) {
        boolean success = wordSentenceService.deleteWordSentence(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}