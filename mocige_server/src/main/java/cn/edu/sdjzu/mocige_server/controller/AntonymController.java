package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Antonym;
import cn.edu.sdjzu.mocige_server.service.AntonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 反义词控制器
 * 提供反义词相关的RESTful API
 */
@RestController
@RequestMapping("/api/antonyms")
public class AntonymController {

    @Autowired
    private AntonymService antonymService;
    
    /**
     * 获取所有反义词关系
     * 
     * @return 反义词关系列表
     */
    @GetMapping
    public ResponseEntity<List<Antonym>> getAllAntonyms() {
        List<Antonym> antonyms = antonymService.getAllAntonyms();
        return ResponseEntity.ok(antonyms);
    }
    
    /**
     * 根据ID获取反义词关系
     * 
     * @param id 反义词关系ID
     * @return 反义词关系对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Antonym> getAntonymById(@PathVariable("id") Integer id) {
        Antonym antonym = antonymService.getAntonymById(id);
        if (antonym == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(antonym);
    }
    
    /**
     * 根据单词ID获取反义词关系
     * 
     * @param wordId 单词ID
     * @return 反义词关系列表
     */
    @GetMapping("/word/{wordId}")
    public ResponseEntity<List<Antonym>> getAntonymsByWordId(@PathVariable("wordId") Integer wordId) {
        List<Antonym> antonyms = antonymService.getAntonymsByWordId(wordId);
        return ResponseEntity.ok(antonyms);
    }
    
    /**
     * 创建反义词关系
     * 
     * @param antonym 反义词关系对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Antonym> createAntonym(@RequestBody Antonym antonym) {
        boolean success = antonymService.createAntonym(antonym);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(antonym);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新反义词关系
     * 
     * @param id 反义词关系ID
     * @param antonym 反义词关系对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Antonym> updateAntonym(
            @PathVariable("id") Integer id,
            @RequestBody Antonym antonym) {
        antonym.setAntonymId(id);
        boolean success = antonymService.updateAntonym(antonym);
        if (success) {
            return ResponseEntity.ok(antonym);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除反义词关系
     * 
     * @param id 反义词关系ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAntonym(@PathVariable("id") Integer id) {
        boolean success = antonymService.deleteAntonym(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除单词的所有反义词关系
     * 
     * @param wordId 单词ID
     * @return 删除结果
     */
    @DeleteMapping("/word/{wordId}")
    public ResponseEntity<Void> deleteAntonymsByWordId(@PathVariable("wordId") Integer wordId) {
        boolean success = antonymService.deleteAntonymsByWordId(wordId);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}