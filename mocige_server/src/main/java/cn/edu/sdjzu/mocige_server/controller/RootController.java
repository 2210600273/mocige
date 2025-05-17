package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.Root;
import cn.edu.sdjzu.mocige_server.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 词根控制器
 * 提供词根相关的RESTful API
 */
@RestController
@RequestMapping("/api/roots")
public class RootController {

    @Autowired
    private RootService rootService;
    
    /**
     * 获取所有词根
     * 
     * @return 词根列表
     */
    @GetMapping
    public ResponseEntity<List<Root>> getAllRoots() {
        List<Root> roots = rootService.getAllRoots();
        return ResponseEntity.ok(roots);
    }
    
    /**
     * 根据ID获取词根
     * 
     * @param id 词根ID
     * @return 词根对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<Root> getRootById(@PathVariable("id") Integer id) {
        Root root = rootService.getRootById(id);
        if (root == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(root);
    }
    
    /**
     * 根据词根名称查询
     * 
     * @param name 词根名称
     * @return 词根列表
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Root>> getRootsByName(@PathVariable("name") String name) {
        List<Root> roots = rootService.getRootsByName(name);
        return ResponseEntity.ok(roots);
    }
    
    /**
     * 根据词根含义查询
     * 
     * @param meaning 词根含义
     * @return 词根列表
     */
    @GetMapping("/meaning")
    public ResponseEntity<List<Root>> getRootsByMeaning(@RequestParam("meaning") String meaning) {
        List<Root> roots = rootService.getRootsByMeaning(meaning);
        return ResponseEntity.ok(roots);
    }
    
    /**
     * 根据词源查询
     * 
     * @param etymology 词源
     * @return 词根列表
     */
    @GetMapping("/etymology")
    public ResponseEntity<List<Root>> getRootsByEtymology(@RequestParam("etymology") String etymology) {
        List<Root> roots = rootService.getRootsByEtymology(etymology);
        return ResponseEntity.ok(roots);
    }
    
    /**
     * 模糊查询词根
     * 
     * @param keyword 关键词
     * @return 词根列表
     */
    @GetMapping("/search")
    public ResponseEntity<List<Root>> searchRootsByKeyword(@RequestParam("keyword") String keyword) {
        List<Root> roots = rootService.searchRootsByKeyword(keyword);
        return ResponseEntity.ok(roots);
    }
    
    /**
     * 创建词根
     * 
     * @param root 词根对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Root> createRoot(@RequestBody Root root) {
        boolean success = rootService.createRoot(root);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(root);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新词根
     * 
     * @param id 词根ID
     * @param root 词根对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Root> updateRoot(
            @PathVariable("id") Integer id,
            @RequestBody Root root) {
        root.setRootId(id);
        boolean success = rootService.updateRoot(root);
        if (success) {
            return ResponseEntity.ok(root);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新词根含义
     * 
     * @param id 词根ID
     * @param meaning 新的含义
     * @return 更新结果
     */
    @PatchMapping("/{id}/meaning")
    public ResponseEntity<Void> updateRootMeaning(
            @PathVariable("id") Integer id,
            @RequestParam("meaning") String meaning) {
        boolean success = rootService.updateRootMeaning(id, meaning);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除词根
     * 
     * @param id 词根ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoot(@PathVariable("id") Integer id) {
        boolean success = rootService.deleteRoot(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}