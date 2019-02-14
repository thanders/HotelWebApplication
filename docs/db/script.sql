
-- Create table
create table Room
(
Room_Number INT UNIQUE not null ,
Room_Type    VARCHAR(20) not null,
Room_Price float not null,
primary key (Room_Number)
);
 
-- Create table
create table Reservations
(
Id  int AUTO_INCREMENT not null ,
Room_Number INT not null,
Room_Owner VARCHAR(128) not null,
Book_Date Date not null, 
End_Date Date not null,
Price float ,
-- guest/member 
Member_Status VARCHAR(280) not null,
primary key (Id)
) ;

-- Create table
create table Credit_Card
(
Card_Number  INT UNIQUE not null ,
Card_Owner  VARCHAR(128) not null,
primary key (Card_Number)
) ;

-- Create table
create table Guest
(
Id  int AUTO_INCREMENT not null ,
Guest_Name  VARCHAR(128) not null,
Guest_Surname  VARCHAR(128) not null,
Address  VARCHAR(280) not null,
Card_Number  INT not null UNIQUE,
Phone_Number  INT not null UNIQUE ,
Email_Address VARCHAR(128) not null,
primary key (Id)
) ;

-- Create table
create table Starwood
(
Id  int AUTO_INCREMENT not null ,
Member_Name VARCHAR(128) not null,
Member_Surname  VARCHAR(128) not null,
User_Name  VARCHAR(128) not null,
User_Password VARCHAR(128) not null,
Address  VARCHAR(280) not null,
Card_Number  INT  UNIQUE not null ,
Memebership_Status VARCHAR(128) not null, 
Phone_Number  INT  UNIQUE not null ,
Email_Address VARCHAR(128) not null,
primary key (Id)
) ;


-- Insert data: ---------------------------------------------------------------
 
insert into Room (Room_Number, Room_Type, Room_Price)
values (1, 'Double', 20);

insert into Reservations (Room_Number,Room_Owner, Book_Date, End_Date,Price,Member_Status)
values (1, 'Abdul', '2015-12-11','2015-12-21',20,'Guest');

insert into Credit_Card (Card_Number,Card_Owner)
values (1233232, 'Abdul');

insert into Guest (Guest_Name,Guest_Surname,Card_Number,Address,Phone_Number,Email_Address)
values ( 'Abdul','Salim',1233232,'Dublin 5',0852222432, 'Abdulj947@gmail');

insert into Starwood (Member_Name,Member_Surname,User_Name,User_Password,Memebership_Status,Card_Number,Address,Phone_Number,Email_Address)
values ( 'Tom','Yates','Tom.yates','Password1','Active',1233232,'Dublin 5',0852222432,'Abdulj947@gmail');
