package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Synonym;
import cn.edu.sdjzu.mocige_server.service.SynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 同义词控制器
 * 提供同义词相关的RESTful API
 */
@RestController
@RequestMapping("/api/synonyms")
public class SynonymController {

    @Autowired
    private SynonymService synonymService;
    
    /**
     * 获取所有同义词关系
     * 
     * @return 同义词关系列表
     */
    @GetMapping
    public ResponseEntity<List<Synonym>> getAllSynonyms() {
        List<Synonym> synonyms = synonymService.getAllSynonyms();
        return ResponseEntity.ok(synonyms);
    }
    
    /**
     * 根据ID获取同义词关系
     * 
     * @param id 同义词关系ID
     * @return 同义词关系对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Synonym> getSynonymById(@PathVariable("id") Integer id) {
        Synonym synonym = synonymService.getSynonymById(id);
        if (synonym == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(synonym);
    }
    
    /**
     * 根据单词ID获取同义词关系
     * 
     * @param wordId 单词ID
     * @return 同义词关系列表
     */
    @GetMapping("/word/{wordId}")
    public ResponseEntity<List<Synonym>> getSynonymsByWordId(@PathVariable("wordId") Integer wordId) {
        List<Synonym> synonyms = synonymService.getSynonymsByWordId(wordId);
        return ResponseEntity.ok(synonyms);
    }
    
    /**
     * 根据相关单词获取同义词关系
     * 
     * @param relatedWord 相关单词
     * @return 同义词关系列表
     */
    @GetMapping("/related-word/{relatedWord}")
    public ResponseEntity<List<Synonym>> getSynonymsByRelatedWord(@PathVariable("relatedWord") String relatedWord) {
        List<Synonym> synonyms = synonymService.getSynonymsByRelatedWord(relatedWord);
        return ResponseEntity.ok(synonyms);
    }
    
    /**
     * 根据单词ID和相关单词获取同义词关系
     * 
     * @param wordId 单词ID
     * @param relatedWord 相关单词
     * @return 同义词关系对象
     */
    @GetMapping("/word/{wordId}/related-word")
    public ResponseEntity<Synonym> getSynonymByWordIdAndRelatedWord(
            @PathVariable("wordId") Integer wordId,
            @RequestParam("relatedWord") String relatedWord) {
        Synonym synonym = synonymService.getSynonymByWordIdAndRelatedWord(wordId, relatedWord);
        if (synonym == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(synonym);
    }
    
    /**
     * 创建同义词关系
     * 
     * @param synonym 同义词关系对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Synonym> createSynonym(@RequestBody Synonym synonym) {
        boolean success = synonymService.createSynonym(synonym);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(synonym);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新同义词关系
     * 
     * @param id 同义词关系ID
     * @param synonym 同义词关系对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Synonym> updateSynonym(
            @PathVariable("id") Integer id,
            @RequestBody Synonym synonym) {
        synonym.setSynonymId(id);
        boolean success = synonymService.updateSynonym(synonym);
        if (success) {
            return ResponseEntity.ok(synonym);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新相关单词
     * 
     * @param id 同义词关系ID
     * @param relatedWord 新的相关单词
     * @return 更新结果
     */
    @PatchMapping("/{id}/related-word")
    public ResponseEntity<Void> updateRelatedWord(
            @PathVariable("id") Integer id,
            @RequestParam("relatedWord") String relatedWord) {
        boolean success = synonymService.updateRelatedWord(id, relatedWord);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除同义词关系
     * 
     * @param id 同义词关系ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSynonym(@PathVariable("id") Integer id) {
        boolean success = synonymService.deleteSynonym(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}