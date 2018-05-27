/*数据库名：实验室安排预约系统*/
----------------------------------------------
-----------------建表脚本1.0------------------
----------------------------------------------



/*1. 创建教师基本信息表
其中所带班级可以为空，为职位是实验室管理员的教师和未带班级的教师提供*/
create table 教师信息表(
	教师编号	varchar(50)	primary key,
	教师姓名	varchar(50)	not null,
	所属院系	varchar(50)	not null,
	联系方式	varchar(50)
)
go

/*2. 创建用户登录信息表
其中用户编号为用户的登录名且参照教师信息表的教师编号
用户类别只能为0或1为0表示身份为教师为1身份为管理员*/
create table 用户登录信息表(
	用户编号	varchar(50)	primary key references 教师信息表(教师编号),
	用户密码	varchar(50)	not null,
	用户类别	int check(用户类别 in (0,1)) not null
)
go

/*3. 创建实验室信息表*/
create table 实验室信息表(
	实验室编号	varchar(50)	primary key,
	位置		varchar(50)	not null,
	实验室类型	varchar(50)	not null,
	可容纳人数	int	not null,
	备注		varchar(200)
)
go


/*4. 创建实验室停用时间设置表*/
create table 实验室关闭时间表(
	实验室编号	varchar(50)	primary key references 实验室信息表(实验室编号),
	开始周   	int	not null,
  	结束周   	int	not null,
  	课     		int	not null,
  	周几    	int	not null
)
go

/*5. 创建班级课程表*/
create table 班级课程表(
	排课编号	varchar(50)	primary key,
	课程编号	varchar(50) not null,
	教室编号	varchar(50) not null,
	课程名称	varchar(50) not null,
	班级编号	varchar(50) not null,
	教师编号	varchar(50) references 教师信息表(教师编号),
	起始周	int	check(起始周 between 1 and 20),
	结束周	int	check(结束周 between 1 and 20),
	周几	int	check(周几 between 1 and 7),
	节次	int	check(节次 between 1 and 6)
)
go

/*6. 创建实验室预约记录表
其中实验室编号为外键，教师编号为外键，班级编号为外键*/
create table 实验室预约记录表(
	预约编号	int identity(1,1) primary key,	
	课程编号	varchar(50)	,
	实验室编号	varchar(50)	references 实验室信息表(实验室编号),
	教师编号	varchar(50)	references 教师信息表(教师编号),
	起始周		int	check(起始周 between 1 and 20),
	结束周		int	check(结束周 between 1 and 20),
	周几		int	check(周几 between 1 and 7),
	节次		int	check(节次 between 1 and 6)
)
go

