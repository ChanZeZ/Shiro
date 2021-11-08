/*
用户表
 */

CREATE TABLE user (
                      id int(11) NOT NULL AUTO_INCREMENT,
                      user_id varchar(20) NOT NULL COMMENT '用户id',
                      username varchar(50) NOT NULL COMMENT '用户名',
                      password varchar(50) NOT NULL,
                      salt varchar(128) DEFAULT NULL COMMENT '加密盐值',
                      PRIMARY KEY (id,user_id)
)

/*
角色表
 */

CREATE TABLE role (
                      id int(11) NOT NULL AUTO_INCREMENT,
                      name varchar(50) NOT NULL COMMENT '角色名称',
                      PRIMARY KEY (id)
)

/*
权限表
 */

CREATE TABLE permission (
                            id int(11) NOT NULL AUTO_INCREMENT,
                            name varchar(100) NOT NULL COMMENT '权限名称',
                            url varchar(255) DEFAULT NULL COMMENT '权限访问路径',
                            PRIMARY KEY (id)
)

/*
用户角色关系表
 */

CREATE TABLE user_role (
                           id int(11) NOT NULL AUTO_INCREMENT,
                           user_id varchar(20) NOT NULL COMMENT '用户id',
                           role_id varchar(20) NOT NULL COMMENT '角色id',
                           PRIMARY KEY (id)
)

/*
角色权限关系表
 */

CREATE TABLE role_permission (
                                 id int(11) NOT NULL AUTO_INCREMENT,
                                 role_id varchar(20) NOT NULL COMMENT '角色id',
                                 permission_id varchar(20) NOT NULL COMMENT '权限id',
                                 PRIMARY KEY (id)
)
