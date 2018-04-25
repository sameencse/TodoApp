ToDo Application Prerequisites:
===================================
Technologies: 
	Angular 1.5.8, 
	HTML5,
	JavaScript,
	bootstrap 3.3.6,
	JAX-RS 2.0,
	EJB 3.2,Hibernate
	Mysql 8.0,
	Maven 3.0.3,
	Payara,
	Micro payara
	

Payara domain configuration:
===================
Add the below configuration

 <jdbc-connection-pool driver-classname="com.mysql.cj.jdbc.Driver" datasource-classname="com.mysql.jdbc.Driver" name="MysqlPool" res-type="java.sql.Driver">
      <property name="password" value="root"></property>
      <property name="databaseName" value="ngp"></property>
      <property name="serverName" value="127.0.0.1"></property>
      <property name="user" value="root"></property>
      <property name="portNumber" value="3306"></property>
      <property name="url" value="jdbc:mysql://localhost:3306/ngp?useSSL=false"></property>
    </jdbc-connection-pool>
 <jdbc-resource pool-name="MysqlPool" jndi-name="jdbc/todo"></jdbc-resource>
	
 <resource-ref ref="jdbc/todo"></resource-ref>
 
 Download payara micro:
 Command to Deploy on payara micro
 
 D:\server> java -cp "C:\Tools\MySQL\mysql-connector-java-8.0.11.jar;D:\server\payara-micro-4.1.2.174.jar" fish.payara.micro.PayaraMicro --deploy "C:\To
ols\project\todo-apps-ui\target\todo-apps-ui-1.0-SNAPSHOT.war" --domainConfig D:\server\domain.xml
 
 
Note: download  both the mysql-connector-java-8.0.11.jar & payara-micro-4.1.2.174.jar and modify the domain.xml file to add the JDBC connection pool


Mysql Database Setup:
===================

create database ngp;
use database ngp;

create table todo_items 
( id int PRIMARY KEY AUTO_INCREMENT,
name varchar(50) not null,
description varchar(200),
todo_date date, 
status varchar(20) not null);



DB Connection Details:
=======================
URL: jdbc:mysql://localhost:3306/ngp
Username: root
Password: root
port :3306
