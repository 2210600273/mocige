package cn.edu.sdjzu.mocige_server.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础Mapper接口，定义通用的CRUD方法
 * @param <T> 实体类型
 * @param <ID> 主键类型
 */
public interface BaseMapper<T, ID> {
    
    /**
     * 根据ID查询
     * 
     * @param id 主键ID
     * @return 实体对象
     */
    T selectById(@Param("id") ID id);
    
    /**
     * 查询所有
     * 
     * @return 实体列表
     */
    List<T> selectAll();
    
    /**
     * 插入
     * 
     * @param entity 实体对象
     * @return 影响行数
     */
    int insert(T entity);
    
    /**
     * 更新
     * 
     * @param entity 实体对象
     * @return 影响行数
     */
    int update(T entity);
    
    /**
     * 删除
     * 
     * @param id 主键ID
     * @return 影响行数
     */
    int deleteById(@Param("id") ID id);
}