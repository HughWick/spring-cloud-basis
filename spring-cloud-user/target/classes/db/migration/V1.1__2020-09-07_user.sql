# Create database IF NOT EXISTS test_store;

CREATE TABLE user
(
    id    BIGINT(20)   NOT NULL AUTO_INCREMENT,
    name  VARCHAR(255) NOT NULL UNIQUE COMMENT '唯一序列号',
    age   int(255) COMMENT 'IP',
    money double(25, 5) COMMENT '金额',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息表';



insert into user (id,name,age,money) values  (1,'张三',22,100.00);
insert into user (id,name,age,money) values  (2,'李四',23,220.00);
insert into user (id,name,age,money) values  (3,'王五',24,330.00);
insert into user (id,name,age,money) values  (4,'赵六',25,440.00);
insert into user (id,name,age,money) values  (5,'孙膑',26,550.00);