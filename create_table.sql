use invests;
CREATE TABLE IF NOT EXISTS INVESTMENTS(
	INV_ID INT AUTO_INCREMENT PRIMARY KEY,
    INV_BUY BOOLEAN NOT NULL,
	INV_TYPE VARCHAR (255) NOT NULL,
    INV_KIND VARCHAR (255) NOT NULL,
    INV_DATE DATE NOT NULL,
    INV_COST DOUBLE,
	INV_AMOUNT DOUBLE ,
    INV_INFO VARCHAR (255) ,
    INV_BONUS VARCHAR (255) ,
    INV_COMM TEXT,
    INV_ENTER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE INNODB
 