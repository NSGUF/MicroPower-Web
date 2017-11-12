create database MicroPower
use MicroPower
select * from T_USERINFO

drop database MicroPower

--1.�û���Ϣ�� ���ڴ洢�Ը�ƽ̨����ע����û��Ļ�����Ϣ
create table T_USERINFO(
user_id varchar(20) primary key,
head_portrait varchar(300) not null,
pet_name varchar(20) not null,
cell_phone_id varchar(20) not null,
wechat_id varchar(20),
micro_blog_id varchar(20),
qq_id varchar(20),
alipay_id varchar(20),
wallet_id varchar(100),--֧����/΢���˺Ŷ�ά��
is_volunteer smallint not null default 1,--1Ϊ�ǣ�0Ϊ��
verify_info varchar(1000),--�����Ϣ��
verify_image varchar(1000),--�����Ϣ��һ����ͼƬ������
ID_card varchar(150),--���֤��
verify_state smallint default 1,--1������ˣ�2��δͨ����ˣ�3�����ͨ����4�������
is_black smallint not null default 0,--�Ƿ����ڣ�1Ϊ�ǣ�0Ϊ��
open_date varchar(20),--���ڿ�ʼʱ��
user_by1 varchar(20),
user_by2 varchar(20)
)

--2.�ռ�����Ϣ�� ���ڴ洢�ռ��˵Ļ�����Ϣ
create table T_RECIPIENTINFO(
rece_id int identity(1,1) primary key,
rece_name varchar(20) not null,
rece_cell_id varchar(20) not null,--�����ֻ���
detailed_addr varchar(300) not null,
is_default smallint not null default 1,--1Ϊѡ��Ĭ��
is_delete smallint not null default 0,--�Ƿ�ɾ��
user_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
)
--3.΢������Ϣ�� ���ھ�����Ʒ�Ļ�����Ϣ
create table T_DONATIONINFO(
donation_id varchar(20) primary key,
donation_raise_goods varchar(20) not null,
donation_trans_cost money not null default 0,--���׻ر����
donation_open_date varchar(50) not null,--��ʼʱ��
donation_close_date varchar(50) not null,
donation_title varchar(300) not null,
donation_describe varchar(300) not null,
donation_image varchar(1000) not null,
donation_min_image varchar(1000) not null,
donation_select_need_or_dona smallint not null,--1����Ҫ������2��������
donation_addr varchar(300),--������λ�ã��Զ���λ//
is_donation_black smallint default 0,--�Ƿ����ڣ�1Ϊ�ǣ�0Ϊ��
is_success smallint default 0,--�Ƿ��Ѿ����׳ɹ�
is_delete smallint default 0,--�Ƿ�ɾ����
donation_verify_state smallint default 1,--1������ˣ�2��δͨ����ˣ�3�����ͨ����4�ѽ�����5ʧ�ܽ���
user_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
)
--4.����������Ϣ�� ���ڴ洢�����Ļ�����Ϣ
create table T_DO_DONATION(
do_donation_id varchar(20) primary key,
do_donation_user_id varchar(20) not null,--�������˵�userid
do_donation_send_time varchar(50) , --�����˷�����Ʒʱ��
express_id varchar(30) not null,--��ݵ���
do_donation_rece_time varchar(50),--����ʱ��
donation_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(do_donation_user_id) references T_USERINFO(user_id),
foreign key(donation_id) references T_DONATIONINFO(donation_id)
)

--5.����΢������Ϣ��
create table T_DONATIONINFO_COMMENT(
donationinfo_comment_id varchar(20) primary key,
donationinfo_user int,--����¥��
donationinfo_friend int,
donationinfo_sender int,
donationinfo_receiver int,
donationinfo_content varchar(500),
donationinfo_send_time varchar(50),
donation_id varchar(20),
foreign key (donation_id) references T_DONATIONINFO (donation_id)
)

--6.��֤��Ϣ��   ���ڴ洢������������������ƺ����Ŀǰ����״���Ļ�����Ϣ
create table T_WITNESSINFO(
witness_id varchar(20) primary key,
witness_title varchar(60) not null,
witness_open_date varchar(50) not null,
witness_describe varchar(300) not null,
witness_image varchar(3000) not null,
witness_min_image varchar(3000) not null,
witness_addr varchar(300),--������λ�ã��Զ���λ//
witness_verify_state smallint not null default 1,--1������ˣ�2��δͨ����ˣ�3�����ͨ����4�ѽ�����5ʧ�ܽ���
is_delete smallint not null default 0,--�Ƿ�ɾ����
is_witness_black smallint not null default 0,
user_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
)--7.���ۼ�֤��Ϣ��Ϣ��
create table T_WITNESSINFO_COMMENT(
witnessinfo_comment_id varchar(20) primary key,
witnessinfo_user int,--����¥��
witnessinfo_friend int,
witnessinfo_sender int,
witnessinfo_receiver int,
witnessinfo_content varchar(500),
witnessinfo_send_time varchar(50),
witness_id varchar(20),
foreign key (witness_id) references T_WITNESSINFO (witness_id)
)
select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and mircolove_verify_state=1 
--8.������ͯ��Ϣ��   һ��һ������ͯ
create table T_MIRCOLOVE_CHILDREN(
mircolove_id varchar(20) primary key,
mircolove_target_amount money not null,--Ŀ����
mircolove_raise_amount money not null default 0,--�ѳ���
mircolove_open_date varchar(50) not null,--��ʼʱ��//
mircolove_divid_num smallint,--��������
mircolove_list_title varchar(300) not null,--����//
mircolove_list_describe varchar(1000) not null,--ϸ������//
mircolove_list_image varchar(800) not null,--ͼƬ//
mircolove_list_min_image varchar(800) not null,--����ͼ//
mircolove_list_select int not null check(mircolove_list_select in (1,0)) default 0,--1.�ǣ�0.��--�Ƿ�ѡ�뾫ѡ
mircolove_list_addr varchar(300),--������λ�ã��Զ���λ//
mircolove_list_support_time int default 0,--�������ܴ���
mircolove_verify_state smallint default 1,--1������ˣ�2��δͨ����ˣ�3�����ͨ����4�ѽ�����5ʧ�ܽ���
is_delete smallint default 0,--�Ƿ�ɾ����//
user_id varchar(20),
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
);
--9.����΢������Ϣ��
create table T_MIRCOLOVE_CHILDREN_COMMENT(
mircolove_comment_id varchar(20) primary key,
mircolove_user int,--����¥��
mircolove_friend int,
mircolove_sender int,
mircolove_receiver int,
mircolove_content varchar(500),
mircolove_send_time varchar(50),
mircolove_id varchar(20),
foreign key (mircolove_id) references T_MIRCOLOVE_CHILDREN (mircolove_id)
)
--10.�����Ϣ�� ���ڴ洢�û���������ͯ���о��Ļ�����Ϣ
create table T_DO_MIRCOLOVE_CHILDREN(
do_mircolove_id varchar(20) primary key,
do_mircolove_time varchar(20) not null,--����ʱ��
do_mircolove_amount money,--�����ܽ��
user_id varchar(20),--������id
mircolove_id varchar(20),
mircolove_comment_id varchar(20),
user_by1 varchar(20),
user_by2 varchar(20),
foreign key (user_id) references T_USERINFO (user_id),
foreign key (mircolove_id) references T_MIRCOLOVE_CHILDREN (mircolove_id),
foreign key (mircolove_comment_id) references T_MIRCOLOVE_CHILDREN_COMMENT (mircolove_comment_id)
)
--11
create table T_REPORT(
report_id varchar(20) primary key,
report_name varchar(20) not null,
report_num varchar(20) not null,
report_reson varchar(300) not null,
report_image varchar(1000) not null,
report_ori smallint not null,--1��������ͯ��2��΢������3�������֤
item_id varchar(20) not null
)

create table T_BG(
user_id int primary key,
user_name varchar(20),
user_pwd varchar(20)
)


select count(*) count1 from T_DO_MIRCOLOVE_CHILDREN where mircolove_id='MIRCOLOVE0'
update T_MIRCOLOVE_CHILDREN set mircolove_raise_amount=mircolove_raise_amount+213 where mircolove_id='MIRCOLOVE0'
select * from T_DO_MIRCOLOVE_CHILDREN,T_MIRCOLOVE_CHILDREN_COMMENT,T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT where T_DO_MIRCOLOVE_CHILDREN.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id and T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT.mircolove_comment_id and T_DO_MIRCOLOVE_CHILDREN.mircolove_comment_id='MCOMMENTID0' order by mircolove_comment_rank,mircolove_comment_content_rank asc

drop table T_DONATIONINFO
select * from T_DONATIONINFO

select * from T_DONATIONINFO_COMMENT,T_DONATIONINFO_COMMENT_CONTENT where T_DONATIONINFO_COMMENT.donationinfo_comment_id= T_DONATIONINFO_COMMENT_CONTENT.donationinfo_comment_id


select * from T_DO_MIRCOLOVE_CHILDREN,T_MIRCOLOVE_CHILDREN_COMMENT,T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT where 
T_DO_MIRCOLOVE_CHILDREN.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id and
T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT.mircolove_comment_id
 and T_DO_MIRCOLOVE_CHILDREN.mircolove_comment_id='MCOMMENTID0' order by mircolove_comment_rank,mircolove_comment_content_rank asc
select * from T_MIRCOLOVE_CHILDREN_COMMENT
select * from T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT
select * from T_DO_MIRCOLOVE_CHILDREN


select * from T_MIRCOLOVE_CHILDREN
select * from T_MIRCOLOVE_CHILDREN
drop table T_MIRCOLOVE_CHILDREN_COMMENT
drop table T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT
drop table T_MIRCOLOVE_CHILDREN
drop table T_WITNESSINFO
update T_MIRCOLOVE_CHILDREN set mircolove_raise_amount=mircolove_raise_amount+12 where mircolove_id='MIRCOLOVE0'

select * from T_DONATIONINFO,T_DONATIONINFO_COMMENT,T_DONATIONINFO_COMMENT_CONTENT 
where T_DONATIONINFO.donation_id=T_DONATIONINFO_COMMENT.donation_id 
and T_DONATIONINFO_COMMENT.donationinfo_comment_id=T_DONATIONINFO_COMMENT_CONTENT.donationinfo_comment_id 
and T_DONATIONINFO.donation_id='' order by donationinfo_comment_rank,donationinfo_comment_content_rank asc

drop table T_WITNESSINFO
select * from T_WITNESSINFO
create table T_VIEWPAGERIMAGE(
viewpager_images varchar(1000)
);