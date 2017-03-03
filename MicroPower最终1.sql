create database MicroPower
use MicroPower
select * from T_USERINFO

drop database MicroPower
 
--1.用户信息表 用于存储对该平台进行注册的用户的基本信息
create table T_USERINFO(
user_id varchar(20) primary key,
head_portrait varchar(300) not null,
pet_name varchar(20) not null,
cell_phone_id varchar(20) not null,
wallet_id money not null default 0,--钱包金额
wechat_id varchar(20),
micro_blog_id varchar(20),
qq_id varchar(20),
alipay_id varchar(20),
is_volunteer smallint not null default 0,--1为是，0为否
verify_info varchar(1000),--审核信息，一般是图片和文字
verify_state smallint,--1，待审核，2，未通过审核，3审核已通过
is_black smallint not null default 0,--是否拉黑，1为是，0为否
open_date varchar(20),--拉黑开始时间
user_by1 varchar(20),
user_by2 varchar(20)
)

--2.收件人信息表 用于存储收件人的基本信息
create table T_RECIPIENTINFO(
rece_id varchar(20) primary key,
rece_name varchar(20) not null,
rece_cell_id varchar(20) not null,--接收手机号
detailed_addr varchar(300) not null,
is_default smallint not null default 1,--1为选中默认
is_delete smallint not null default 0,--是否删除
user_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
)

--3.微捐赠信息表 用于捐赠物品的基本信息
create table T_DONATIONINFO(
donation_id varchar(20) primary key,
donation_raise_goods varchar(20) not null,
donation_trans_cost money not null default 0,--交易回报金额
donation_open_date varchar(50) not null,--开始时间
donation_close_date varchar(50) not null,
donation_title varchar(300) not null,
donation_describe varchar(300) not null,
donation_image varchar(1000) not null,
donation_min_image varchar(1000) not null,
donation_select_need_or_dona smallint not null,--1，我要捐赠，2求助捐赠
donation_addr varchar(300),--发布的位置，自动定位//
is_donation_black smallint default 0,--是否拉黑，1为是，0为否
is_success smallint default 0,--是否已经交易成功
is_delete smallint default 0,--是否删除了
donation_verify_state smallint default 1,--1，待审核，2，未通过审核，3审核已通过，4已结束，5失败结束
user_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
)
--4.帮助捐赠信息表 用于存储捐赠的基本信息
create table T_DO_DONATION(
do_donation_id varchar(20) primary key,
do_donation_user_id varchar(20) not null,--帮助的人的userid
do_donation_send_time varchar(50) , --帮助人发出物品时间
express_id varchar(30) not null,--快递单号
do_donation_rece_time varchar(50),--接收时间
donation_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(do_donation_user_id) references T_USERINFO(user_id),
foreign key(donation_id) references T_DONATIONINFO(donation_id)
)
--5.评论微捐助基本信息表
create table T_DONATIONINFO_COMMENT(
donationinfo_comment_id varchar(20) primary key,
donationinfo_comment_rank int not null,--帖子楼数
donation_id varchar(20) not null,
foreign key (donation_id) references T_DONATIONINFO (donation_id)
)
--6.评论内容信息表
create table T_DONATIONINFO_COMMENT_CONTENT(
donationinfo_comment_content_id varchar(20) primary key,
from_user_id varchar(20) not null,--评论者
to_user_id varchar(20) not null,--被评论者
donationinfo_comment_content varchar(60) not null,--评论的主要内容
donationinfo_comment_content_time date not null,--评论时间
donationinfo_comment_content_rank int not null,--对于楼主的评论整个消息的个数，根据这个可以排序
donationinfo_comment_id varchar(20) not null,
foreign key(from_user_id) references T_USERINFO (user_id),
foreign key(to_user_id) references T_USERINFO (user_id),
foreign key(donationinfo_comment_id) references T_DONATIONINFO_COMMENT(donationinfo_comment_id)
)

--7.见证信息表   用于存储被捐助人情况有所改善后分享目前生活状况的基本信息
create table T_WITNESSINFO(
witness_id varchar(20) primary key,
witness_title varchar(60) not null,
witness_open_date varchar(50) not null,
witness_describe varchar(300) not null,
witness_image varchar(3000) not null,
witness_min_image varchar(3000) not null,
witness_addr varchar(300),--发布的位置，自动定位//
witness_verify_state smallint not null default 1,--1，待审核，2，未通过审核，3审核已通过，4已结束，5失败结束
is_delete smallint not null default 0,--是否删除了
is_witness_black smallint not null default 0,
user_id varchar(20) not null,
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
)
--8.评论见证基本信息表
create table T_WITNESSINFO_COMMENT(
witnessinfo_comment_id varchar(20) primary key,
witnessinfo_comment_rank int,--帖子楼数
witness_id varchar(20),
foreign key (witness_id) references T_WITNESSINFO (witness_id)
)

--9.评论见证详细内容信息表
create table T_WITNESSINFO_COMMENT_CONTENT(
witnessinfo_comment_content_id varchar(20) primary key,
from_user_id varchar(20),--评论者
to_user_id varchar(20),--被评论者
witnessinfo_comment_content varchar(60),--评论的主要内容
witnessinfo_comment_time date,--评论时间
witnessinfo_comment_content_rank int,--对于楼主的评论整个消息的个数，根据这个可以排序
witnessinfo_comment_id varchar(20),
foreign key(from_user_id) references T_USERINFO (user_id),
foreign key(to_user_id) references T_USERINFO (user_id),
foreign key(witnessinfo_comment_id) references T_WITNESSINFO_COMMENT(witnessinfo_comment_id)
)
select * from T_WITNESSINFO,T_WITNESSINFO_COMMENT,T_WITNESSINFO_COMMENT_CONTENT where T_WITNESSINFO.witness_id=T_WITNESSINFO_COMMENT.witness_id and T_WITNESSINFO_COMMENT.witnessinfo_comment_id=T_WITNESSINFO_COMMENT_CONTENT.witnessinfo_comment_id and T_WITNESSINFO.witness_id='' order by witnessinfo_comment_rank,witnessinfo_comment_content_rank asc



--10.助力儿童信息表   一对一助力儿童
create table T_MIRCOLOVE_CHILDREN(
mircolove_id varchar(20) primary key,
mircolove_target_amount money not null,--目标金额
mircolove_raise_amount money not null default 0,--已筹金额
mircolove_open_date varchar(50) not null,--开始时间//
mircolove_divid_num smallint,--分期期数
mircolove_list_title varchar(300) not null,--标题//
mircolove_list_describe varchar(1000) not null,--细节描述//
mircolove_list_image varchar(800) not null,--图片//
mircolove_list_min_image varchar(800) not null,--缩略图//
mircolove_list_select int not null check(mircolove_list_select in (1,0)) default 0,--1.是，0.否--是否选入精选
mircolove_list_addr varchar(300),--发布的位置，自动定位//
mircolove_list_support_time int default 0,--帮助的总次数
mircolove_verify_state smallint default 1,--1，待审核，2，未通过审核，3审核已通过，4已结束，5失败结束
is_delete smallint default 0,--是否删除了//
user_id varchar(20),
user_by1 varchar(20),
user_by2 varchar(20),
foreign key(user_id) references T_USERINFO(user_id)
);

--11.评论微捐助基本信息表
create table T_MIRCOLOVE_CHILDREN_COMMENT(
mircolove_comment_id varchar(20) primary key,
mircolove_comment_rank int,--帖子楼数
mircolove_id varchar(20),
foreign key (mircolove_id) references T_MIRCOLOVE_CHILDREN (mircolove_id)
)
--12.评论内容信息表
create table T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT(
mircolove_comment_content_id varchar(20) primary key,
from_user_id varchar(20),--评论者
to_user_id varchar(20),--被评论者
mircolove_comment_content varchar(60),--评论的主要内容
mircolove_comment_content_time varchar(20),--评论时间
mircolove_comment_content_rank int,--对于楼主的评论整个消息的个数，根据这个可以排序
mircolove_comment_id varchar(20),
foreign key(mircolove_comment_id) references T_MIRCOLOVE_CHILDREN_COMMENT(mircolove_comment_id),
foreign key(from_user_id) references T_USERINFO (user_id),
foreign key(to_user_id) references T_USERINFO (user_id)
)
--13.捐款信息表 用于存储用户对助力儿童进行捐款的基本信息
create table T_DO_MIRCOLOVE_CHILDREN(
do_mircolove_id varchar(20) primary key,
do_mircolove_time varchar(20) not null,--帮助时间
do_mircolove_amount money,--帮助总金额
user_id varchar(20),--帮助人id
mircolove_id varchar(20),
mircolove_comment_id varchar(20),
user_by1 varchar(20),
user_by2 varchar(20),
foreign key (user_id) references T_USERINFO (user_id),
foreign key (mircolove_id) references T_MIRCOLOVE_CHILDREN (mircolove_id),
foreign key (mircolove_comment_id) references T_MIRCOLOVE_CHILDREN_COMMENT (mircolove_comment_id)
)

create table T_REPORT(
report_id varchar(20) primary key,
report_name varchar(20) not null,
report_num varchar(20) not null,
report_reson varchar(300) not null,
report_image varchar(1000) not null,
report_ori smallint not null,--1，助力儿童，2，微捐赠，3，分享见证
item_id varchar(20) not null
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