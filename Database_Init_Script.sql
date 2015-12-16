-- MySQL Init Script
-- Run after installation to create database structure needed for the application

DROP DATABASE IF EXISTS `wub`;
CREATE DATABASE `wub` /*!40100 DEFAULT CHARACTER SET latin1 */;

DROP TABLE IF EXISTS `wub`.`administrators`;
CREATE TABLE  `wub`.`administrators` (
  `admin_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(10) unsigned NOT NULL default '8',
  PRIMARY KEY  (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COMMENT='Table for administrators';

DROP TABLE IF EXISTS `wub`.`appraisal_basic`;
CREATE TABLE  `wub`.`appraisal_basic` (
  `appraisal_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `vote_style` int(10) unsigned NOT NULL,
  `locked` tinyint(1) NOT NULL,
  PRIMARY KEY  USING BTREE (`appraisal_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `wub`.`appraisal_conf`;
CREATE TABLE  `wub`.`appraisal_conf` (
  `conf_id` int(10) unsigned NOT NULL auto_increment,
  `appraisal_id` int(10) unsigned NOT NULL,
  `text_id` int(10) unsigned NOT NULL,
  `item_order` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`conf_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='configuration of the appraisals';

DROP TABLE IF EXISTS `wub`.`appraisal_main`;
CREATE TABLE  `wub`.`appraisal_main` (
  `class_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `admin_id` int(10) unsigned NOT NULL COMMENT 'the teacher',
  `subject_id` int(10) unsigned NOT NULL,
  `appraisal_id` int(10) unsigned NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `subject_year` datetime NOT NULL,
  `sent` int(1) unsigned NOT NULL,
  PRIMARY KEY  (`class_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `wub`.`appraisal_results`;
CREATE TABLE  `wub`.`appraisal_results` (
  `result_id` int(10) unsigned NOT NULL auto_increment,
  `text_id` int(10) unsigned NOT NULL,
  `status_id` int(10) unsigned NOT NULL,
  `vote` tinyint(3) unsigned NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY  (`result_id`)
) ENGINE=MyISAM AUTO_INCREMENT=211 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `wub`.`appraisal_status`;
CREATE TABLE  `wub`.`appraisal_status` (
  `status_id` int(10) unsigned NOT NULL auto_increment,
  `class_id` int(10) unsigned NOT NULL,
  `user_id` int(11) NOT NULL,
  `token` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=160 DEFAULT CHARSET=latin1 COMMENT='user states per appraisal';

DROP TABLE IF EXISTS `wub`.`roles`;
CREATE TABLE  `wub`.`roles` (
  `role_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `usermanager` tinyint(1) NOT NULL,
  `reportmanager` tinyint(1) NOT NULL,
  `appraisalmanager` tinyint(1) NOT NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COMMENT='table for security roles';

DROP TABLE IF EXISTS `wub`.`settings`;
CREATE TABLE  `wub`.`settings` (
  `setting_id` int(10) unsigned NOT NULL auto_increment,
  `setting_name` varchar(45) NOT NULL,
  `setting_value` varchar(255) NOT NULL,
  PRIMARY KEY  (`setting_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `wub`.`subjects`;
CREATE TABLE  `wub`.`subjects` (
  `subject_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY  (`subject_id`)
) ENGINE=MyISAM AUTO_INCREMENT=84 DEFAULT CHARSET=latin1 COMMENT='all subjects';

DROP TABLE IF EXISTS `wub`.`text_elements`;
CREATE TABLE  `wub`.`text_elements` (
  `text_id` int(10) unsigned NOT NULL auto_increment,
  `text` text NOT NULL,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY  (`text_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COMMENT='saves text elements for appraisals';

DROP TABLE IF EXISTS `wub`.`users`;
CREATE TABLE  `wub`.`users` (
  `user_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `main_class` varchar(10) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=latin1 COMMENT='User table';

DROP TABLE IF EXISTS `wub`.`vote_styles`;
CREATE TABLE  `wub`.`vote_styles` (
  `vote_style_id` int(10) unsigned NOT NULL auto_increment,
  `vote_count` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`vote_style_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='saves the vote configurations,  do not change! (HARDCODED)';



--
-- Data for tables (roles and vote_styles shouldn't be changed!)
--

/*!40000 ALTER TABLE `wub`.`roles` DISABLE KEYS */;
INSERT INTO `wub`.`roles` (`role_id`,`name`,`usermanager`,`reportmanager`,`appraisalmanager`) VALUES
 (1,'Alle Rechte',1,1,1),
 (2,'Benutzermanager',1,0,0),
 (3,'Berichtmanager',0,1,0),
 (4,'Beurteilungsmanager',0,0,1),
 (5,'Benutzer-/Berichtsmanager',1,1,0),
 (6,'Benutzer-/Beurteilungsmanager',1,0,1),
 (7,'Berichts-/Beurteilungsmanager',0,1,1),
 (8,'Keine Rechte',0,0,0);
/*!40000 ALTER TABLE `wub`.`roles` ENABLE KEYS */;

/*!40000 ALTER TABLE `wub`.`vote_styles` DISABLE KEYS */;
INSERT INTO `wub`.`vote_styles` (`vote_style_id`,`vote_count`) VALUES
 (1,4),
 (2,6);
/*!40000 ALTER TABLE `wub`.`vote_styles` ENABLE KEYS */;

/*!40000 ALTER TABLE `wub`.`settings` DISABLE KEYS */;
INSERT INTO `wub`.`settings` (`setting_id`,`setting_name`,`setting_value`) VALUES
 (1,'SmtpServer','mail.yourmailserver.ch'),
 (2,'SmtpFrom','your@mailadress.ch'),
 (10,'SmtpPassword','password'),
 (9,'SmtpUsername','username'),
 (8,'SmtpUseAuth','0'),
 (7,'WubUri','http://www.yourservername.ch:8080/wub/');
/*!40000 ALTER TABLE `wub`.`settings` ENABLE KEYS */;

/*!40000 ALTER TABLE `wub`.`administrators` DISABLE KEYS */;
INSERT INTO `wub`.`administrators` (`admin_id`,`name`,`email`,`password`,`role_id`) VALUES 
 (1,'admin','change@me.com','wub',1);
/*!40000 ALTER TABLE `wub`.`administrators` ENABLE KEYS */;
