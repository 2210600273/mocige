package cn.edu.sdjzu.mocige_server.controller;

import cn.edu.sdjzu.mocige_server.entity.User;
import cn.edu.sdjzu.mocige_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户控制器
 * 提供用户相关的RESTful API
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    /**
     * 获取所有用户
     * 
     * @return 用户列表
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    /**
     * 根据ID获取用户
     * 
     * @param id 用户ID
     * @return 用户对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    /**
     * 根据用户名获取用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    /**
     * 根据邮箱获取用户
     * 
     * @param email 邮箱
     * @return 用户对象
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    /**
     * 根据创建时间范围查询用户
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 用户列表
     */
    @GetMapping("/created-between")
    public ResponseEntity<List<User>> getUsersByCreatedAtRange(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<User> users = userService.getUsersByCreatedAtRange(startTime, endTime);
        return ResponseEntity.ok(users);
    }
    
    /**
     * 创建用户
     * 
     * @param user 用户对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        boolean success = userService.createUser(user);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 更新用户信息
     * 
     * @param id 用户ID
     * @param user 用户对象
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody User user) {
        user.setUserId(id);
        boolean success = userService.updateUser(user);
        if (success) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新用户名
     * 
     * @param id 用户ID
     * @param username 新的用户名
     * @return 更新结果
     */
    @PatchMapping("/{id}/username")
    public ResponseEntity<Void> updateUsername(
            @PathVariable("id") Integer id,
            @RequestParam("username") String username) {
        boolean success = userService.updateUsername(id, username);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新用户密码
     * 
     * @param id 用户ID
     * @param passwordHash 新的密码哈希
     * @return 更新结果
     */
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable("id") Integer id,
            @RequestParam("passwordHash") String passwordHash) {
        boolean success = userService.updatePassword(id, passwordHash);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 更新用户邮箱
     * 
     * @param id 用户ID
     * @param email 新的邮箱
     * @return 更新结果
     */
    @PatchMapping("/{id}/email")
    public ResponseEntity<Void> updateEmail(
            @PathVariable("id") Integer id,
            @RequestParam("email") String email) {
        boolean success = userService.updateEmail(id, email);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        boolean success = userService.deleteUser(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 验证用户密码
     * 
     * @param username 用户名
     * @param passwordHash 密码哈希
     * @return 验证结果
     */
    @PostMapping("/verify-password")
    public ResponseEntity<Boolean> verifyPassword(
            @RequestParam("username") String username,
            @RequestParam("passwordHash") String passwordHash) {
        boolean verified = userService.verifyPassword(username, passwordHash);
        return ResponseEntity.ok(verified);
    }
}