package cn.edu.sdjzu.mocige_server.common.constant;

/**
 * 系统常量类
 */
public class Constants {

    /**
     * 系统默认值
     */
    public static class Default {
        /**
         * 默认页码
         */
        public static final int PAGE_NUM = 1;
        
        /**
         * 默认每页记录数
         */
        public static final int PAGE_SIZE = 10;
        
        /**
         * 默认每日学习单词数
         */
        public static final int DAILY_WORD_COUNT = 20;
    }
    
    /**
     * 用户相关常量
     */
    public static class User {
        /**
         * 用户状态：正常
         */
        public static final int STATUS_NORMAL = 1;
        
        /**
         * 用户状态：禁用
         */
        public static final int STATUS_DISABLED = 0;
    }
    
    /**
     * 学习进度相关常量
     */
    public static class Learning {
        /**
         * 掌握程度：未学习
         */
        public static final int MASTERY_LEVEL_NONE = 0;
        
        /**
         * 掌握程度：初学
         */
        public static final int MASTERY_LEVEL_BEGINNER = 1;
        
        /**
         * 掌握程度：熟悉
         */
        public static final int MASTERY_LEVEL_FAMILIAR = 2;
        
        /**
         * 掌握程度：掌握
         */
        public static final int MASTERY_LEVEL_MASTERED = 3;
    }
    
    /**
     * 时间相关常量（毫秒）
     */
    public static class Time {
        /**
         * 一分钟的毫秒数
         */
        public static final long ONE_MINUTE = 60 * 1000L;
        
        /**
         * 一小时的毫秒数
         */
        public static final long ONE_HOUR = 60 * ONE_MINUTE;
        
        /**
         * 一天的毫秒数
         */
        public static final long ONE_DAY = 24 * ONE_HOUR;
    }
}