create table UserDTO(
userid varchar2(20) primary key,
upassword varchar2(20) not null,
uname varchar2(20) not null,
uaccount number not null
);

select * from tab;
desc UserDTO
select * from Paydto;
select *from USERDTO;
SELECT * FROM USERDTO WHERE USERID='jo3791'
delete from PAYDTO where paccount=1111111112;
select * from PayDTO where paccount=(select uaccount from UserDTO where userid='11' and upassword='1111')
select paccount ,ppayment,sum(ppayment)over(partition by paccount order by paccount) as "total"  from PAYDTO;  

select sum(ppayment) as "dd" ,paccount from paydto group by paccount order by paccount; 
select * from UserDTO where userId='dkfl3791' and upassword='1111';
select sum(ppayment) from PayDTO group by paccount;

select paccount,sum(ppayment),pdetail,pmemo, from PayDTO group by paccount having paccount=(select uaccount from UserDTO where userId='dkfl3791' and upassword='')
select * from PayDTO where paccount=(select uaccount from UserDTO where userId='tnwls0525' and upassword='1234')
insert into PAYDTO values('11111111111','80000','입금','조희진','저축');
insert into PAYDTO values('11111111111','50000','카드','조희진','편의점');
insert into PAYDTO values('11111111111','80000','입금','조희진','저축');
insert into PAYDTO values('11111111112','80000','입금','김수진','저축');
insert into PAYDTO values('11111111112','30000','카드','김수진','식당');



create table PayDTO(
Paccount number  not null,
Ppayment number ,
Pdetail varchar(20),
Pmemo varchar(100)
);
select * from COMMDTO;
insert into COMMDTO values('11111111115','50000','dkfl3791','tnwls0525','1111');
delete from PAYDTO where ppayment='-60000';

SELECT * FROM COMMDTO WHERE FC_User='dkfl3791'

select * from CommDTO where fc_User='jo391' or cs_User='jo3791'

INSERT INTO COMMDTO(Caccount,fc_User)  VALUES( UACCOUNT_NUM.NEXTVAL,'jo3791')

update commdto set caccount= '11111111111',Fc_User='jo3791'
drop table Commdto;

create sequence UACCOUNT_NUM.NEXTVAL start with 11111111111 increment by 1 nocycle nocache;

select  UACCOUNT_NUM.NEXTVAL from tab

SELECT * FROM COMMDTO WHERE CACCOUNT='11111111181' AND CPWD='1234'


create table CommDTO(
Caccount number not null,
C_limit number,
FC_User varchar2(20),
CS_User varchar2(20),
Cpwd varchar2(20),
C_save number
);

select * from tab;
select * from otherbank;
select * from userdto;
select * from commdto;

describe otherbank;
desc commdto;

create otherbank(
	oaccount number primary key,
	obank varchar(20),
	oname varchar(20)
);

insert into PAYDTO values('11111111250','80000','입금','조희진','저축',sysdate);

