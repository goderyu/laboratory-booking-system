/*���ݿ�����ʵ���Ұ���ԤԼϵͳ*/
----------------------------------------------
-----------------����ű�1.0------------------
----------------------------------------------



/*1. ������ʦ������Ϣ��
���������༶����Ϊ�գ�Ϊְλ��ʵ���ҹ���Ա�Ľ�ʦ��δ���༶�Ľ�ʦ�ṩ*/
create table ��ʦ��Ϣ��(
	��ʦ���	varchar(50)	primary key,
	��ʦ����	varchar(50)	not null,
	����Ժϵ	varchar(50)	not null,
	��ϵ��ʽ	varchar(50)
)
go

/*2. �����û���¼��Ϣ��
�����û����Ϊ�û��ĵ�¼���Ҳ��ս�ʦ��Ϣ��Ľ�ʦ���
�û����ֻ��Ϊ0��1Ϊ0��ʾ���Ϊ��ʦΪ1���Ϊ����Ա*/
create table �û���¼��Ϣ��(
	�û����	varchar(50)	primary key references ��ʦ��Ϣ��(��ʦ���),
	�û�����	varchar(50)	not null,
	�û����	int check(�û���� in (0,1)) not null
)
go

/*3. ����ʵ������Ϣ��*/
create table ʵ������Ϣ��(
	ʵ���ұ��	varchar(50)	primary key,
	λ��		varchar(50)	not null,
	ʵ��������	varchar(50)	not null,
	����������	int	not null,
	��ע		varchar(200)
)
go


/*4. ����ʵ����ͣ��ʱ�����ñ�*/
create table ʵ���ҹر�ʱ���(
	ʵ���ұ��	varchar(50)	primary key references ʵ������Ϣ��(ʵ���ұ��),
	��ʼ��   	int	not null,
  	������   	int	not null,
  	��     		int	not null,
  	�ܼ�    	int	not null
)
go

/*5. �����༶�γ̱�*/
create table �༶�γ̱�(
	�ſα��	varchar(50)	primary key,
	�γ̱��	varchar(50) not null,
	���ұ��	varchar(50) not null,
	�γ�����	varchar(50) not null,
	�༶���	varchar(50) not null,
	��ʦ���	varchar(50) references ��ʦ��Ϣ��(��ʦ���),
	��ʼ��	int	check(��ʼ�� between 1 and 20),
	������	int	check(������ between 1 and 20),
	�ܼ�	int	check(�ܼ� between 1 and 7),
	�ڴ�	int	check(�ڴ� between 1 and 6)
)
go

/*6. ����ʵ����ԤԼ��¼��
����ʵ���ұ��Ϊ�������ʦ���Ϊ������༶���Ϊ���*/
create table ʵ����ԤԼ��¼��(
	ԤԼ���	int identity(1,1) primary key,	
	�γ̱��	varchar(50)	,
	ʵ���ұ��	varchar(50)	references ʵ������Ϣ��(ʵ���ұ��),
	��ʦ���	varchar(50)	references ��ʦ��Ϣ��(��ʦ���),
	��ʼ��		int	check(��ʼ�� between 1 and 20),
	������		int	check(������ between 1 and 20),
	�ܼ�		int	check(�ܼ� between 1 and 7),
	�ڴ�		int	check(�ڴ� between 1 and 6)
)
go

