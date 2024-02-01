-- auto Generated on 2024-02-01
-- DROP TABLE IF EXISTS quality_collect;
CREATE TABLE quality_collect(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	app_user_id INT (11) NOT NULL DEFAULT -1 COMMENT 'appUserId',
	quality_id INT (11) NOT NULL DEFAULT -1 COMMENT 'qualityId',
	create_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'quality_collect';
