/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 5.7.19 : Database - users
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`users` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `users`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` varchar(40) COLLATE utf8_bin NOT NULL,
  `admin_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `admin_password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin` */

insert  into `admin`(`id`,`admin_name`,`admin_password`,`role`) values 
('be4a23f79d6e4cd3b9f2ebf31af06912','ts','4297f44b13955235245b2497399d7a93',0),
('be4a23f79d6e4cd3b9f2ebf31ag06912','admin','202cb962ac59075b964b07152d234b70',1);

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `card` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `money` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `bank` */

insert  into `bank`(`card`,`user`,`money`) values 
('1234567890123456','iyghjkbyd5er6ttghvhbjn',393911);

/*Table structure for table `parking_lot` */

DROP TABLE IF EXISTS `parking_lot`;

CREATE TABLE `parking_lot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'parkingID外键',
  `pid` varchar(10) DEFAULT NULL,
  `identify` int(11) DEFAULT NULL COMMENT '是否认筹',
  `open` int(11) DEFAULT NULL COMMENT '是否开盘',
  `number` varchar(50) NOT NULL COMMENT '车位编号',
  `price` double DEFAULT NULL COMMENT '价钱',
  `explain` varchar(50) DEFAULT NULL COMMENT '特殊说明',
  `sale` int(11) DEFAULT NULL COMMENT '销售情况',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `parking_lot` */

insert  into `parking_lot`(`id`,`pid`,`identify`,`open`,`number`,`price`,`explain`,`sale`) values 
(1,'1',0,1,'001',100000,'0',1),
(3,'1',1,0,'003',69000,'0',0),
(4,'1',0,1,'004',78000,'0',1),
(5,'1',0,1,'005',89000,'0',1),
(6,'1',0,1,'006',65555,'0',1),
(7,'1',0,1,'007',12345,'0',1),
(8,'1',0,1,'008',12345,'0',1),
(9,'1',0,1,'009',111111,'ff',1),
(10,'1',0,0,'010',21111,'gg',0),
(11,'1',0,1,'011',111111,'ff',1),
(12,'1',0,1,'012',43332,'jj',0),
(13,'3',0,0,'001',123123,'好车位',0),
(14,'3',0,0,'002',111111,'！！！好',0),
(15,'3',1,1,'004',1241234,'阿松大',0),
(16,'3',1,1,'003',123,'说明',0);

/*Table structure for table `parking_one` */

DROP TABLE IF EXISTS `parking_one`;

CREATE TABLE `parking_one` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(10) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `owner_phone` varchar(20) DEFAULT NULL,
  `owner_name` varchar(20) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `parking_one` */

insert  into `parking_one`(`id`,`city`,`address`,`owner_phone`,`owner_name`,`number`) values 
(1,'浙江省杭州市','滨江区钱江彩虹城','1222222222','张三',11),
(3,'浙江省杭州市','滨江区1小区','111111111','ykw',4);

/*Table structure for table `real_user` */

DROP TABLE IF EXISTS `real_user`;

CREATE TABLE `real_user` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `rname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `id_number` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `real_user` */

insert  into `real_user`(`id`,`name`,`phone_number`,`email`,`rname`,`id_number`) values 
('2966891721452440','admin','1234567890','2693151039@qq.com','da','wad'),
('iyghjkbyd5er6ttghvhbjn','user','111111111','2447873803@qq.com','ykw','11111111111111111111');

/*Table structure for table `renchou` */

DROP TABLE IF EXISTS `renchou`;

CREATE TABLE `renchou` (
  `parkingLot_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `renchou` */

insert  into `renchou`(`parkingLot_id`,`user_id`) values 
('3','iyghjkbyd5er6ttghvhbjn');

/*Table structure for table `shoppingcart` */

DROP TABLE IF EXISTS `shoppingcart`;

CREATE TABLE `shoppingcart` (
  `parkingid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `userid` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `shoppingcart` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(55) COLLATE utf8_bin NOT NULL,
  `name` varchar(55) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `password` char(32) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`phone_number`,`password`,`email`,`state`) values 
('2966891721452440','admin','1234567890','202cb962ac59075b964b07152d234b70','2693151039@qq.com',0),
('5231954140526466','dasdas','13765683760  ','e10adc3949ba59abbe56e057f20f883e','278996583@qq.com',0),
('iyghjkbyd5er6ttghvhbjn','帅气的人','13387032627','202cb962ac59075b964b07152d234b70','2447873803@qq.com',1);

/*Table structure for table `user_loan` */

DROP TABLE IF EXISTS `user_loan`;

CREATE TABLE `user_loan` (
  `id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(265) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(122) COLLATE utf8_bin DEFAULT NULL,
  `contact` varchar(121) COLLATE utf8_bin DEFAULT NULL,
  `company` varchar(111) COLLATE utf8_bin DEFAULT NULL,
  `money` double DEFAULT NULL,
  `interest` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `uid` varchar(111) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user_loan` */

insert  into `user_loan`(`id`,`name`,`address`,`contact`,`company`,`money`,`interest`,`date`,`uid`) values 
('a6cab0a0cddc430a8a33a989bcc42c59','唐诗','立业元','110','人人贷',9996,4.998,'2021-10-03','iyghjkbyd5er6ttghvhbjn');

/*Table structure for table `user_order` */

DROP TABLE IF EXISTS `user_order`;

CREATE TABLE `user_order` (
  `oid` varchar(50) COLLATE utf8_bin NOT NULL,
  `uid` varchar(50) COLLATE utf8_bin NOT NULL,
  `date` date DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `number` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `city` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `plid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user_order` */

insert  into `user_order`(`oid`,`uid`,`date`,`pid`,`number`,`city`,`address`,`state`,`price`,`plid`) values 
('TK644609984164016128','iyghjkbyd5er6ttghvhbjn','2021-10-10',1,'004','浙江省杭州市','滨江区钱江彩虹城',1,78000,4),
('HO644631553208758272','iyghjkbyd5er6ttghvhbjn','2021-10-10',1,'005','浙江省杭州市','滨江区钱江彩虹城',1,89000,5),
('QS644637180672880640','iyghjkbyd5er6ttghvhbjn','2021-10-10',1,'006','浙江省杭州市','滨江区钱江彩虹城',1,65555,6),
('DL644665098312626176','iyghjkbyd5er6ttghvhbjn','2021-10-10',1,'007','浙江省杭州市','滨江区钱江彩虹城',1,12345,7),
('FX644671810629283840','iyghjkbyd5er6ttghvhbjn','2021-10-10',1,'008','浙江省杭州市','滨江区钱江彩虹城',1,12345,8),
('XM644680157805948928','iyghjkbyd5er6ttghvhbjn','2021-10-10',1,'009','浙江省杭州市','滨江区钱江彩虹城',1,111111,9),
('WH644695097077936128','iyghjkbyd5er6ttghvhbjn','2021-10-10',1,'011','浙江省杭州市','滨江区钱江彩虹城',1,111111,11);

/*Table structure for table `user_owner` */

DROP TABLE IF EXISTS `user_owner`;

CREATE TABLE `user_owner` (
  `uid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `state` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user_owner` */

insert  into `user_owner`(`uid`,`phone`,`city`,`address`,`state`) values 
('iyghjkbyd5er6ttghvhbjn','13387032627','浙江省杭州市','滨江区1小区',1),
('1213735741152952','123','浙江省杭州市','奥斯觉得你',0);

/*Table structure for table `user_parking` */

DROP TABLE IF EXISTS `user_parking`;

CREATE TABLE `user_parking` (
  `uid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `plid` int(11) DEFAULT NULL COMMENT '判断购买和认筹',
  `city` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `number` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `id_number` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(30) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user_parking` */

insert  into `user_parking`(`uid`,`pid`,`plid`,`city`,`address`,`number`,`name`,`id_number`,`phone_number`) values 
('iyghjkbyd5er6ttghvhbjn',1,1,'浙江省杭州市','滨江区钱江彩虹城','001','user','11111111111111111111','111111111'),
('iyghjkbyd5er6ttghvhbjn',1,4,'浙江省杭州市','滨江区钱江彩虹城','004','user','11111111111111111111','111111111'),
('iyghjkbyd5er6ttghvhbjn',1,5,'浙江省杭州市','滨江区钱江彩虹城','005','user','11111111111111111111','111111111'),
('iyghjkbyd5er6ttghvhbjn',1,6,'浙江省杭州市','滨江区钱江彩虹城','006','user','11111111111111111111','111111111'),
('iyghjkbyd5er6ttghvhbjn',1,7,'浙江省杭州市','滨江区钱江彩虹城','007','user','11111111111111111111','111111111'),
('iyghjkbyd5er6ttghvhbjn',1,8,'浙江省杭州市','滨江区钱江彩虹城','008','user','11111111111111111111','111111111'),
('iyghjkbyd5er6ttghvhbjn',1,9,'浙江省杭州市','滨江区钱江彩虹城','009','user','11111111111111111111','111111111'),
('iyghjkbyd5er6ttghvhbjn',1,11,'浙江省杭州市','滨江区钱江彩虹城','011','user','11111111111111111111','111111111');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
