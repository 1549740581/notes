# 自定义数据库字段
DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(300) NOT NULL COMMENT '内容',
  `level_string` VARCHAR(254) NOT NULL COMMENT '级别',
  `created_time` DATETIME NOT NULL COMMENT '时间',
  `logger_name` VARCHAR(300) NOT NULL COMMENT '全类名',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='自定义日志记录表'