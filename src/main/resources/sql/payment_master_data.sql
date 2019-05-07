use mstore_db;

DROP TABLE IF EXISTS `master_card`;
DROP TABLE IF EXISTS `master_card_transaction`;
DROP TABLE IF EXISTS `master_card_balance`;
DROP TABLE IF EXISTS `visa_card`;
DROP TABLE IF EXISTS `visa_card_transaction`;
DROP TABLE IF EXISTS `visa_card_balance`;

CREATE TABLE `master_card` (
  `card_number` varchar(16),
  `card_name` varchar(50) DEFAULT NULL,
  `csv` varchar(3) DEFAULT NULL,
  `expire_date` varchar(5) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`card_number`)
);

CREATE TABLE `master_card_balance` (
  `card_number` varchar(16),
  `balance` int(11) DEFAULT NULL,
  `last_updated` DATE DEFAULT NULL,
  PRIMARY KEY (`card_number`)
);

CREATE TABLE `visa_card` (
  `card_number` varchar(16),
  `card_name` varchar(50) DEFAULT NULL,
  `csv` varchar(3) DEFAULT NULL,
  `expire_date` varchar(5) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`card_number`)
);

CREATE TABLE `visa_card_balance` (
  `card_number` varchar(16),
  `balance` int(11) DEFAULT NULL,
  `last_updated` DATE DEFAULT NULL,
  PRIMARY KEY (`card_number`)
);

CREATE TABLE `master_card_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(16),
  `card_name` varchar(50) DEFAULT NULL,
  `t_date` Date DEFAULT NULL,
  `t_amount` int(11) DEFAULT NULL,
  `prev_balance` int(11) DEFAULT NULL,
  `remain_balance` int(11) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `visa_card_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(16),
  `card_name` varchar(50) DEFAULT NULL,
  `t_date` Date DEFAULT NULL,
  `t_amount` int(11) DEFAULT NULL,
  `prev_balance` int(11) DEFAULT NULL,
  `remain_balance` int(11) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

insert into visa_card values('5678123412340001', 'Yee Mon', '123', '02/23', 10000);
insert into visa_card values('5678123412340002', 'Jean', '123', '02/23', 10000);
insert into visa_card values('5678123412340003', 'Stanley', '123', '02/23', 10000);
insert into visa_card values('5678123412340004', 'Niveen', '123', '02/23', 10000);
insert into visa_card values('5678123412340005', 'Ulugbek', '123', '02/23', 10000);
insert into visa_card values('5678123412340006', 'Bon', '123', '02/23', 10000);
                                       
insert into visa_card values('5678123412340079', 'Company', '123', '02/23', 1000000);                                   

insert into visa_card_balance values('5678123412340001', 10000, CURDATE());
insert into visa_card_balance values('5678123412340002', 10000, CURDATE());
insert into visa_card_balance values('5678123412340003', 10000, CURDATE());
insert into visa_card_balance values('5678123412340004', 10000, CURDATE());
insert into visa_card_balance values('5678123412340005', 10000, CURDATE());
insert into visa_card_balance values('5678123412340006', 10000, CURDATE());

insert into visa_card_balance values('5678123412340079', 10000, CURDATE());

insert into master_card values('1234123412340001', 'Yee Mon', '123', '02/23', 10000);
insert into master_card values('1234123412340002', 'Jean', '123', '02/23', 10000);
insert into master_card values('1234123412340003', 'Stanley', '123', '02/23', 10000);
insert into master_card values('1234123412340004', 'Niveen', '123', '02/23', 10000);
insert into master_card values('1234123412340005', 'Ulugbek', '123', '02/23', 10000);
insert into master_card values('1234123412340006', 'Bon', '123', '02/23', 10000);

insert into master_card values('1234123412340079', 'Company', '123', '02/23', 1000000);

insert into master_card_balance values('1234123412340001', 10000, CURDATE());
insert into master_card_balance values('1234123412340002', 10000, CURDATE());
insert into master_card_balance values('1234123412340003', 10000, CURDATE());
insert into master_card_balance values('1234123412340004', 10000, CURDATE());
insert into master_card_balance values('1234123412340005', 10000, CURDATE());
insert into master_card_balance values('1234123412340006', 10000, CURDATE());

insert into master_card_balance values('1234123412340079', 10000, CURDATE());
