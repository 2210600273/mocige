package cn.edu.sdjzu.mocige_server.mapper;

import cn.edu.sdjzu.mocige_server.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 反馈Mapper接口
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback, Integer> {
    
    /**
     * 根据用户ID查询反馈
     * 
     * @param userId 用户ID
     * @return 反馈列表
     */
    List<Feedback> selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 根据创建时间范围查询反馈
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 反馈列表
     */
    List<Feedback> selectByTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    
    /**
     * 根据评论内容模糊查询
     * 
     * @param comment 评论内容
     * @return 反馈列表
     */
    List<Feedback> selectByComment(@Param("comment") String comment);
}