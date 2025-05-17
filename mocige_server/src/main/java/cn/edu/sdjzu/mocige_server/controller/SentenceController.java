package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Sentence;
import cn.edu.sdjzu.mocige_server.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 句子控制器
 * 提供句子相关的RESTful API
 */
@RestController
@RequestMapping("/api/sentences")
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;
    
    /**
     * 获取所有句子
     * 
     * @return 句子列表
     */
    @GetMapping
    public ResponseEntity<List<Sentence>> getAllSentences() {
        List<Sentence> sentences = sentenceService.getAllSentences();
        return ResponseEntity.ok(sentences);
    }
    
    /**
     * 根据ID获取句子
     * 
     * @param id 句子ID
     * @return 句子对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sentence> getSentenceById(@PathVariable("id") Integer id) {
        Sentence sentence = sentenceService.getSentenceById(id);
        if (sentence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sentence);
    }
    
    /**
     * 根据句子内容模糊查询
     * 
     * @param content 句子内容
     * @return 句子列表
     */
    @GetMapping("/content")
    public ResponseEntity<List<Sentence>> getSentencesByContent(@RequestParam("content") String content) {
        List<Sentence> sentences = sentenceService.getSentencesByContent(content);
        return ResponseEntity.ok(sentences);
    }
    
    /**
     * 根据上下文模糊查询
     * 
     * @param context 上下文
     * @return 句子列表
     */
    @GetMapping("/context")
    public ResponseEntity<List<Sentence>> getSentencesByContext(@RequestParam("context") String context) {
        List<Sentence> sentences = sentenceService.getSentencesByContext(context);
        return ResponseEntity.ok(sentences);
    }
    
    /**
     * 根据关键词搜索句子
     * 
     * @param keyword 关键词
     * @return 句子列表
     */
    @GetMapping("/search")
    public ResponseEntity<List<Sentence>> searchSentencesByKeyword(@RequestParam("keyword") String keyword) {
        List<Sentence> sentences = sentenceService.searchSentencesByKeyword(keyword);
        return ResponseEntity.ok(sentences);
    }
    
    /**
     * 创建句子
     * 
     * @param sentence 句子对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Sentence> createSentence(@RequestBody Sentence sentence) {
        boolean success = sentenceService.createSentence(sentence);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sentence);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新句子
     * 
     * @param id 句子ID
     * @param sentence 句子对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sentence> updateSentence(
            @PathVariable("id") Integer id,
            @RequestBody Sentence sentence) {
        sentence.setSentenceId(id);
        boolean success = sentenceService.updateSentence(sentence);
        if (success) {
            return ResponseEntity.ok(sentence);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新句子内容
     * 
     * @param id 句子ID
     * @param content 新的句子内容
     * @return 更新结果
     */
    @PatchMapping("/{id}/content")
    public ResponseEntity<Void> updateSentenceContent(
            @PathVariable("id") Integer id,
            @RequestParam("content") String content) {
        boolean success = sentenceService.updateSentenceContent(id, content);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新上下文
     * 
     * @param id 句子ID
     * @param context 新的上下文
     * @return 更新结果
     */
    @PatchMapping("/{id}/context")
    public ResponseEntity<Void> updateContext(
            @PathVariable("id") Integer id,
            @RequestParam("context") String context) {
        boolean success = sentenceService.updateContext(id, context);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除句子
     * 
     * @param id 句子ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSentence(@PathVariable("id") Integer id) {
        boolean success = sentenceService.deleteSentence(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}