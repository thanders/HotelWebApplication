-- Create table
create table User_Account
(
User_Name VARCHAR(30) not null,
Gender    VARCHAR(1) not null,
Password  VARCHAR(30) not null,
primary key (User_Name)
);
 
-- Create table
create table Product
(
Code  VARCHAR(20) not null,
Name  VARCHAR(128) not null,
Price FLOAT not null,
primary key (Code)
) ;

-- Insert data: ---------------------------------------------------------------
 
insert into User_Account (User_Name, Gender, Password)
values ('tom', 'M', 'tom001');
 
insert into User_Account (User_Name, Gender, Password)
values ('jerry', 'M', 'jerry001');
 
insert into Product (Code, Name, Price)
values ('P001', 'Java Core', 100);
 
insert into Product (Code, Name, Price)
values ('P002', 'C# Core', 90);
