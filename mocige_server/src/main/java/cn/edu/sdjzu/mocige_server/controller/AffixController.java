package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Affix;
import cn.edu.sdjzu.mocige_server.service.AffixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 词缀控制器
 * 提供词缀相关的RESTful API
 */
@RestController
@RequestMapping("/api/affixes")
public class AffixController {

    @Autowired
    private AffixService affixService;
    
    /**
     * 获取所有词缀
     * 
     * @return 词缀列表
     */
    @GetMapping
    public ResponseEntity<List<Affix>> getAllAffixes() {
        List<Affix> affixes = affixService.getAllAffixes();
        return ResponseEntity.ok(affixes);
    }
    
    /**
     * 根据ID获取词缀
     * 
     * @param id 词缀ID
     * @return 词缀对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Affix> getAffixById(@PathVariable("id") Integer id) {
        Affix affix = affixService.getAffixById(id);
        if (affix == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(affix);
    }
    
    /**
     * 创建词缀
     * 
     * @param affix 词缀对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Affix> createAffix(@RequestBody Affix affix) {
        boolean success = affixService.createAffix(affix);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(affix);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新词缀
     * 
     * @param id 词缀ID
     * @param affix 词缀对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Affix> updateAffix(
            @PathVariable("id") Integer id,
            @RequestBody Affix affix) {
        affix.setAffixId(id);
        boolean success = affixService.updateAffix(affix);
        if (success) {
            return ResponseEntity.ok(affix);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除词缀
     * 
     * @param id 词缀ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffix(@PathVariable("id") Integer id) {
        boolean success = affixService.deleteAffix(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}