CREATE TABLE ADMINISTRATOR(
username VARCHAR(20) NOT NULL UNIQUE,
password VARCHAR(10) NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)PRIMARY KEY
);
CREATE TABLE BUSER(
username VARCHAR(20) NOT NULL UNIQUE,
password VARCHAR(10) NOT NULL,
email VARCHAR(50) NOT NULL,
status VARCHAR(15),
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)PRIMARY KEY
);
CREATE TABLE CUSER(
username VARCHAR(20) NOT NULL UNIQUE,
password VARCHAR(10) NOT NULL,
email VARCHAR(50) NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);
CREATE TABLE CPROFILE(
fname VARCHAR(20) NOT NULL,
lname VARCHAR(25) NOT NULL,
biography VARCHAR(1024),
picture BLOB(400K),
pictype VARCHAR(30),
cuserid INT NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);
CREATE TABLE BPROFILE(
bname VARCHAR(50) NOT NULL,
phone VARCHAR(15) NOT NULL,
address VARCHAR(50) NOT NULL,
city VARCHAR(50) NOT NULL,
bstate VARCHAR(15) NOT NULL,
zipcode VARCHAR(15) NOT NULL, 
website VARCHAR(70) NOT NULL,
picture BLOB(400K),
pictype VARCHAR(30),
buserid INT NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);
CREATE TABLE SPECIALS(
sdate DATE NOT NULL,
stime TIMESTAMP NOT NULL,
stime2 TIMESTAMP NOT NULL,
stype VARCHAR(25) NOT NULL,
special VARCHAR(500) NOT NULL,
buserid INT NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);
CREATE TABLE SUBSCRIPTION(
cuserid INT NOT NULL,
buserid INT NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);
CREATE TABLE REVIEW(
post VARCHAR(1024) NOT NULL,
rdatetime TIMESTAMP NOT NULL,
cuserid INT NOT NULL,
buserid INT NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);
CREATE TABLE BRATING(
rating INT NOT NULL,
cuserid INT NOT NULL,
buserid INT NOT NULL,
id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);
ALTER TABLE BPROFILE ADD FOREIGN KEY(buserid)REFERENCES BUSER(id)ON DELETE CASCADE;
ALTER TABLE SPECIALS ADD FOREIGN KEY(buserid)REFERENCES BUSER(id)ON DELETE CASCADE;
ALTER TABLE SUBSCRIPTION ADD FOREIGN KEY(buserid)REFERENCES BUSER(id)ON DELETE CASCADE;
ALTER TABLE SUBSCRIPTION ADD FOREIGN KEY(cuserid)REFERENCES CUSER(id)ON DELETE CASCADE;
ALTER TABLE REVIEW ADD FOREIGN KEY(buserid)REFERENCES BUSER(id)ON DELETE CASCADE;
ALTER TABLE REVIEW ADD FOREIGN KEY(cuserid)REFERENCES CUSER(id)ON DELETE CASCADE;
ALTER TABLE BRATING ADD FOREIGN KEY(buserid)REFERENCES BUSER(id)ON DELETE CASCADE;
ALTER TABLE BRATING ADD FOREIGN KEY(cuserid)REFERENCES CUSER(id)ON DELETE CASCADE;
ALTER TABLE CPROFILE ADD FOREIGN KEY(cuserid)REFERENCES CUSER(id)ON DELETE CASCADE;


