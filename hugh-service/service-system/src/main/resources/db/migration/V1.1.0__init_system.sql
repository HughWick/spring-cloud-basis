CREATE TABLE `user_test` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `age` int(5) NOT NULL,
    `context` varchar(128),
    `status` int(11) NOT NULL,
    `created` datetime NOT NULL,
    `modified` datetime ,
    `delete_flag` varchar(1) DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`name`,`created`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
