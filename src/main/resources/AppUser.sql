-- auto Generated on 2024-02-01
-- DROP TABLE IF EXISTS app_user;
CREATE TABLE app_user(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	nick_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT '昵称',
	user_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'userName',
	telephone VARCHAR (50) NOT NULL DEFAULT '' COMMENT '手机号',
	user_code VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户号',
	user_type INT (11) NOT NULL DEFAULT -1 COMMENT '用户类型：1供货商2经销商',
	id_num VARCHAR (50) NOT NULL DEFAULT '' COMMENT '身份证号',
	auth INT (11) NOT NULL DEFAULT -1 COMMENT '0未认证 1已认证',
	auth_amount BIGINT (15) NOT NULL DEFAULT -1 COMMENT '认证金额',
	invite_user_id VARCHAR (50) NOT NULL DEFAULT '' COMMENT '邀请人ID',
	open_id VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'openId',
	session_key VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'sessionKey',
	expire_time BIGINT (15) NOT NULL DEFAULT -1 COMMENT '',
	create_time BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'createTime',
	update_time BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'updateTime',
	avatar_url VARCHAR (50) NOT NULL DEFAULT '' COMMENT '头像',
	site_pic VARCHAR (50) NOT NULL DEFAULT '' COMMENT '认证图片 逗号割',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'app_user';
