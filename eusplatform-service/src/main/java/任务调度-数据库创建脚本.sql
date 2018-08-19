DROP DATABASE mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
-- 1、创建部门信息表
CREATE TABLE dept (
   did                  bigint	AUTO_INCREMENT ,
   dname                varchar(50),
   eid					varchar(50),
   maxnum				int ,
   currnum				int ,
   CONSTRAINT pk_did1 primary key (did)
) engine='innodb';
-- 2、创建职位信息表
CREATE TABLE level (
   lid                  bigint ,
   title                varchar(50),
   losal				double ,
   hisal				double ,
   CONSTRAINT pk_lid2 PRIMARY KEY (lid)
) engine='innodb';

-- 3、创建雇员信息表
CREATE TABLE emp (
   eid                  varchar(50) not null,
   lid                  bigint,
   did                  bigint,
   ename                varchar(50),
   salary				double ,
   phone                varchar(20),
   password             varchar(32),
   photo                varchar(200),
   note                 text,
   hiredate             date,
   ineid                varchar(50) ,
   locked				int default 0 ,
   CONSTRAINT pk_eid3 PRIMARY KEY (eid) ,
   CONSTRAINT fk_lid3 FOREIGN KEY(lid) REFERENCES level(lid) ,
   CONSTRAINT fk_did3 FOREIGN KEY(did) REFERENCES dept(did) 
) engine='innodb';
-- 4、创建角色信息表
CREATE TABLE role (
   rid                  varchar(50) ,
   title                varchar(50),
   CONSTRAINT pk_rid4 PRIMARY KEY (rid)
) engine='innodb';
-- 5、创建权限信息表
CREATE TABLE action (
   actid                varchar(50),
   rid                  varchar(50),
   title                varchar(50),
   CONSTRAINT pk_actid5 PRIMARY KEY (actid) ,
   CONSTRAINT fk_rid5 FOREIGN KEY(rid) REFERENCES role(rid) 
) engine='innodb';
-- 6、创建职务角色关系表
CREATE TABLE dept_role(
   did                  bigint,
   rid                  varchar(50),
   CONSTRAINT fk_did6 FOREIGN KEY(did) REFERENCES dept(did) ,
   CONSTRAINT fk_rid6 FOREIGN KEY(rid) REFERENCES role(rid) 
) engine='innodb';
-- 7、业务分类表
CREATE TABLE item (
   iid                  bigint  AUTO_INCREMENT ,
   title                varchar(50),
   CONSTRAINT pk_iid7 PRIMARY KEY (iid)
) engine='innodb';
-- 8、调度安排表
CREATE TABLE schedule (
   sid                  bigint auto_increment,
   seid                 varchar(50),
   aeid                 varchar(50),
   iid                  bigint,
   title                varchar(50),
   sdate                date,
   subdate              date,
   audit                int,
   note                 text,
   auddate              date,
   anote                text,
   ecount               int,
   CONSTRAINT pk_sid8 PRIMARY KEY (sid) ,
   CONSTRAINT fk_seid8 FOREIGN KEY(seid) REFERENCES emp(eid) ,
   CONSTRAINT fk_aeid8 FOREIGN KEY(aeid) REFERENCES emp(eid) ,
   CONSTRAINT fk_iid8 FOREIGN KEY(iid) REFERENCES item(iid) 
) engine='innodb';
-- 9、人员安排
CREATE TABLE schedule_emp(
   seid                 bigint auto_increment,
   sid                  bigint,
   eid                  varchar(50),
   CONSTRAINT pk_seid9 PRIMARY KEY (seid) ,
   CONSTRAINT fk_sid9 FOREIGN KEY(sid) REFERENCES schedule(sid)  ON DELETE CASCADE,
   CONSTRAINT fk_eid9 FOREIGN KEY(eid) REFERENCES emp(eid) 
) engine='innodb';
-- 10、调度报告
CREATE TABLE report (
   srid                  bigint auto_increment,
   sid                 bigint,
   eid                 varchar(50),
   subdate              date,
   note                text,
   CONSTRAINT pk_srid10 PRIMARY KEY (srid) ,
   CONSTRAINT fk_sid10 FOREIGN KEY(sid) REFERENCES schedule(sid)  ON DELETE CASCADE ,
   CONSTRAINT fk_eid10 FOREIGN KEY(eid) REFERENCES emp(eid) 
) engine='innodb';

-- 增加测试数据
-- 增加员工等级信息
INSERT INTO level(lid,title,losal,hisal) VALUES (0,'总裁',30001.00,99999.00) ;
INSERT INTO level(lid,title,losal,hisal) VALUES (1,'总监',15001.00,30000.00) ;
INSERT INTO level(lid,title,losal,hisal) VALUES (2,'部门经理',8001.00,15000.00) ;
INSERT INTO level(lid,title,losal,hisal) VALUES (3,'普通员工',3000.00,8000.00) ;

-- 增加部门信息
INSERT INTO dept (dname,maxnum,currnum) VALUES ('管理部',3,0) ;
INSERT INTO dept (dname,maxnum,currnum) VALUES ('人事部',3,0) ;
INSERT INTO dept (dname,maxnum,currnum) VALUES ('财务部',5,0) ;
INSERT INTO dept (dname,maxnum,currnum) VALUES ('市场部',10,0) ;
INSERT INTO dept (dname,maxnum,currnum) VALUES ('开发部',10,0) ;
INSERT INTO dept (dname,maxnum,currnum) VALUES ('营销部',10,0) ;
INSERT INTO dept (dname,maxnum,currnum) VALUES ('后勤部',20,0) ;

-- 增加员工信息
-- 管理部总裁，用户名：mldn-president / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate) VALUES ('mldn-president',0,1,'老李','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-10') ;
-- 管理部总监，用户名：mldn-chief / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate) VALUES ('mldn-chief',1,1,'老张','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-10') ;

-- 人事部经理，用户名：mldn-human / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate) VALUES ('mldn-human',2,2,'老闽','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-11') ;

-- 财务部经理，用户名：mldn-finance / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn-finance',2,3,'老王','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-12','mldn-human') ;

-- 市场部经理，用户名：mldn-market / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn-market',2,4,'老林','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-13','mldn-human') ;

-- 开发部经理，用户名：mldn-dev / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn-dev',2,5,'老赵','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-14','mldn-human') ;

-- 营销部经理，用户名：mldn-sale / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn-sale',2,6,'老孙','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-15','mldn-human') ;

-- 后勤部经理，用户名：mldn-log / 密码：hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn-log',2,7,'老孙','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-10-16','mldn-human') ;

-- 增加部门经理信息
UPDATE dept SET eid='mldn-chief' WHERE did=1 ;
UPDATE dept SET eid='mldn-human' WHERE did=2 ;
UPDATE dept SET eid='mldn-finance' WHERE did=3 ;
UPDATE dept SET eid='mldn-market' WHERE did=4 ;
UPDATE dept SET eid='mldn-dev' WHERE did=5 ;
UPDATE dept SET eid='mldn-sale' WHERE did=6 ;
UPDATE dept SET eid='mldn-log' WHERE did=7 ;

-- 增加普通员工信息
-- 增加人事部普通员工：mldn1 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn1',3,2,'魔乐科技-1','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-11','mldn-human') ;

-- 增加人事部普通员工：mldn2 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn2',3,2,'魔乐科技-2','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-11','mldn1') ;

-- 增加财务部员工：mldn3 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn3',3,3,'魔乐科技-3','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-12','mldn1') ;

-- 增加财务部员工：mldn4 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn4',3,3,'魔乐科技-4','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-12','mldn1') ;

-- 增加财务部员工：mldn5 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn5',3,3,'魔乐科技-5','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-12','mldn1') ;

-- 增加财务部员工：mldn6 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn6',3,4,'魔乐科技-6','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-13','mldn1') ;

-- 增加财务部员工：mldn7 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn7',3,4,'魔乐科技-7','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-13','mldn1') ;

-- 增加市场部员工：mldn8 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn8',3,4,'魔乐科技-8','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn1') ;

-- 增加市场部员工：mldn9 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn9',3,4,'魔乐科技-9','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn1') ;

-- 增加市场部员工：mldn10 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn10',3,4,'魔乐科技-10','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn1') ;

-- 增加市场部员工：mldn11 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn11',3,4,'魔乐科技-11','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn1') ;

-- 增加市场部员工：mldn12 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn12',3,4,'魔乐科技-12','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn1') ;


-- 增加开发部员工：mldn13 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn13',3,5,'魔乐科技-13','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加开发部员工：mldn14 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn14',3,5,'魔乐科技-14','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加开发部员工：mldn15 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn15',3,5,'魔乐科技-15','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加开发部员工：mldn16 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn16',3,5,'魔乐科技-16','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加开发部员工：mldn17 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn17',3,5,'魔乐科技-17','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn18 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn18',3,6,'魔乐科技-18','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn19 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn19',3,6,'魔乐科技-19','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn20 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn20',3,6,'魔乐科技-20','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn21 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn21',3,6,'魔乐科技-21','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn22 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn22',3,6,'魔乐科技-22','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn23 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn23',3,6,'魔乐科技-23','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn24 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn24',3,6,'魔乐科技-24','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加营销部员工：mldn25 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn25',3,6,'魔乐科技-25','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-11-15','mldn2') ;

-- 增加后勤部员工：mldn26 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn26',3,7,'魔乐科技-26','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn27 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn27',3,7,'魔乐科技-27','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn28 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn28',3,7,'魔乐科技-28','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn29 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn29',3,7,'魔乐科技-29','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn30 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn30',3,7,'魔乐科技-30','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn31 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn31',3,7,'魔乐科技-31','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn32 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn32',3,7,'魔乐科技-32','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn33 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn33',3,7,'魔乐科技-33','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn34 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn34',3,7,'魔乐科技-34','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn35 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn35',3,7,'魔乐科技-35','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn36 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn36',3,7,'魔乐科技-36','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn37 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn37',3,7,'魔乐科技-37','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn38 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn38',3,7,'魔乐科技-38','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn39 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn39',3,7,'魔乐科技-39','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 增加后勤部员工：mldn40 / hello
INSERT INTO emp (eid,lid,did,ename,phone,password,photo,note,hiredate,ineid) VALUES ('mldn40',3,7,'魔乐科技-40','01051283346','EAB62A7769F0313F8D69CEBA32F4347E','nophoto.png','很好的员工','2010-12-16','mldn2') ;

-- 更新部门对应人数
UPDATE dept SET currnum=2 WHERE did=1 ;
UPDATE dept SET currnum=3 WHERE did=2 ;
UPDATE dept SET currnum=4 WHERE did=3 ;
UPDATE dept SET currnum=8 WHERE did=4 ;
UPDATE dept SET currnum=6 WHERE did=5 ;
UPDATE dept SET currnum=9 WHERE did=6 ;
UPDATE dept SET currnum=16 WHERE did=7 ;

-- 增加调度任务类型
INSERT INTO item(title) VALUES ('扩展新客户') ;
INSERT INTO item(title) VALUES ('项目支撑') ;
INSERT INTO item(title) VALUES ('宣传活动') ;
INSERT INTO item(title) VALUES ('项目维护') ;
INSERT INTO item(title) VALUES ('系统升级') ;
INSERT INTO item(title) VALUES ('项目部署') ;

-- 增加角色信息
INSERT INTO role(rid,title) VALUES ('dept','【人事部】部门信息管理') ;
INSERT INTO role(rid,title) VALUES ('emp','【人事部】雇员信息管理') ;
INSERT INTO role(rid,title) VALUES ('schedule','【所有部门】调度安排') ;
INSERT INTO role(rid,title) VALUES ('audit','【人事部】调度审核') ;
INSERT INTO role(rid,title) VALUES ('center','【所有部门】个人任务') ;
INSERT INTO role(rid,title) VALUES ('chief','【管理部】查看信息') ;

-- 增加权限信息
INSERT INTO action(actid,rid,title) VALUES ('dept:list','dept','部门列表') ;
INSERT INTO action(actid,rid,title) VALUES ('dept:edit','dept','部门编辑') ;
INSERT INTO action(actid,rid,title) VALUES ('dept:add','dept','部门增加') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:list','emp','雇员列表') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:add','emp','雇员增加') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:edit','emp','雇员编辑') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:delete','emp','雇员删除') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:get','emp','雇员查看') ;
INSERT INTO action(actid,rid,title) VALUES ('schedule:add','schedule','调度申请') ;
INSERT INTO action(actid,rid,title) VALUES ('schedule:edit','schedule','调度申请修改') ;
INSERT INTO action(actid,rid,title) VALUES ('schedule:self','schedule','我的申请') ;
INSERT INTO action(actid,rid,title) VALUES ('schedule:submit','schedule','提交申请') ;
INSERT INTO action(actid,rid,title) VALUES ('schedule:delete','schedule','删除申请') ;
INSERT INTO action(actid,rid,title) VALUES ('schedule:show','schedule','查看申请') ;

INSERT INTO action(actid,rid,title) VALUES ('audit:list','audit','差旅申请列表') ;
INSERT INTO action(actid,rid,title) VALUES ('audit:prepare','audit','待审核申请列表') ;
INSERT INTO action(actid,rid,title) VALUES ('audit:handle','audit','申请处理') ;
INSERT INTO action(actid,rid,title) VALUES ('audit:show','audit','查看申请') ;

INSERT INTO action(actid,rid,title) VALUES ('chief:deptlist','chief','部门列表') ;
INSERT INTO action(actid,rid,title) VALUES ('chief:emplist','chief','雇员列表') ;
INSERT INTO action(actid,rid,title) VALUES ('chief:get','chief','雇员查看') ;

INSERT INTO action(actid,rid,title) VALUES ('center:prepare','center','雇员待处理任务') ;
INSERT INTO action(actid,rid,title) VALUES ('center:history','center','雇员历史任务') ;
INSERT INTO action(actid,rid,title) VALUES ('center:report','center','雇员任务报告') ;

-- 部门与角色关联
INSERT INTO dept_role(did,rid) VALUES (1,'schedule') ;
INSERT INTO dept_role(did,rid) VALUES (2,'schedule') ;
INSERT INTO dept_role(did,rid) VALUES (3,'schedule') ;
INSERT INTO dept_role(did,rid) VALUES (4,'schedule') ;
INSERT INTO dept_role(did,rid) VALUES (5,'schedule') ;
INSERT INTO dept_role(did,rid) VALUES (6,'schedule') ;
INSERT INTO dept_role(did,rid) VALUES (7,'schedule') ;

INSERT INTO dept_role(did,rid) VALUES (1,'center') ;
INSERT INTO dept_role(did,rid) VALUES (2,'center') ;
INSERT INTO dept_role(did,rid) VALUES (3,'center') ;
INSERT INTO dept_role(did,rid) VALUES (4,'center') ;
INSERT INTO dept_role(did,rid) VALUES (5,'center') ;
INSERT INTO dept_role(did,rid) VALUES (6,'center') ;
INSERT INTO dept_role(did,rid) VALUES (7,'center') ;

INSERT INTO dept_role(did,rid) VALUES (1,'chief') ;
INSERT INTO dept_role(did,rid) VALUES (2,'audit') ;
INSERT INTO dept_role(did,rid) VALUES (2,'dept') ;
INSERT INTO dept_role(did,rid) VALUES (2,'emp') ;
