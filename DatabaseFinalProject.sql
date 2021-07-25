create schema `Database_final_project`; 
create table `Database_final_project`.`Train_schedule` (
	train_id int primary key,
	from_des varchar(50),
    to_des varchar(50),
    start_time time,
    arrival_time time,
    column_date date
);

create table `Database_final_project`.`Employee` (
	eid int primary key,
	SSN int, 
    Fname varchar(50),
    MI varchar(50),
    Lname varchar(50),
    age int, 
    salary int, 
    job varchar(50)
);

create table `Database_final_project`.`Work_on` (
	train_id int,
	eid int,	
	PRIMARY KEY(train_id, eid),
	FOREIGN KEY (train_id) REFERENCES `Database_final_project`.`Train_schedule`(`train_id`), 
	FOREIGN KEY (eid) REFERENCES `Database_final_project`.`Employee`(`eid`)
);

create table `Database_final_project`.`Customer` (
	cid int primary key,
    Fname varchar(50),
    MI varchar(50),
    Lname varchar(50)
);

create table `Database_final_project`.`Ticket_purchase` (
	cid int,
	train_id int,	
    class varchar(10),
    date_time datetime, 
    method varchar(10), 
    used varchar(10),
	PRIMARY KEY(cid, train_id),
	FOREIGN KEY (cid) REFERENCES `Database_final_project`.`Customer` (`cid`), 
	FOREIGN KEY (train_id) REFERENCES `Database_final_project`.`Train_schedule`(`train_id`)
);


use `Database_final_project`; 
insert into Train_schedule values (000001, 'Boston', 'Worcester', '08:00:00', '09:30:00', '2020-05-04');
insert into Train_schedule values (000002, 'Wocester', 'Boston', '09:45:00', '11:15:00', '2020-05-04');
insert into Train_schedule values (000003, 'Worcester', 'Springfield', '13:00:00', '14:30:00', '2020-05-04');
insert into Train_schedule values (000004, 'Worcester', 'Hartford', '20:00:00', '22:00:00', '2020-05-05');
insert into Train_schedule values (000005, 'Hartfort', 'Springfield', '15:00:00', '17:00:00', '2020-05-05');

insert into Employee values(100001, 11111111, 'Minh', 'Q', 'Vu', 20, 100000, 'Conductor');
insert into Employee values(100002, 11111112, 'Justin', 'D', 'Bieber', 26, 110000, 'Ticket Collector');
insert into Employee values(100003, 11111113, 'Adam', 'N', 'Levine', 41, 150000, 'Conductor');
insert into Employee values(100004, 11111114, 'Miley', 'R', 'Cyrus', 28, 100000, 'Ticket Collector');

insert into Work_on values(1, 100001);
insert into Work_on values(1, 100002);
insert into Work_on values(1, 100004);
insert into Work_on values(2, 100001);
insert into Work_on values(2, 100002);
insert into Work_on values(2, 100004);
insert into Work_on values(3, 100002);
insert into Work_on values(3, 100004);

insert into Customer values(110001, 'Beyoncé', 'G', 'Knowles-Carter');
insert into Customer values(110002, 'Rihanna', 'R', 'Fenty');
insert into Customer values(110003, 'Ariana', null, 'Grande');
insert into Customer values(110004, 'Billie', 'PBO', 'Eilish');
insert into Customer values(110005, 'Shawn', 'PR', 'Mendes');

insert into Ticket_purchase values(110001, 1, 'economic', '2020-05-03 18:00:00', 'At station', 'Yes');
insert into Ticket_purchase values(110005, 4, 'business', '2020-05-04 18:00:00', 'Online', 'Yes');
insert into Ticket_purchase values(110003, 2, 'economic', '2020-05-04 09:30:00', 'At station', 'Yes');
insert into Ticket_purchase values(110004, 3, 'business', '2020-05-03 09:00:00', 'Online', 'No');


CREATE VIEW TopEmployee (Fname, Lname, Salary) AS SELECT E.Fname, E.Lname, E.salary FROM Employee E WHERE E.salary > 105000;


select train_id, eid
from Work_on
where train_id = 1; 




