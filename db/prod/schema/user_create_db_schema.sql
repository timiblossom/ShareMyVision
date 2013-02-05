-- -----------------------------------------------------
-- -----------------------------------------------------
-- SCRIPT AND CONNECTION SESSION SETTINGS
-- -----------------------------------------------------
-- -----------------------------------------------------
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- -----------------------------------------------------
-- START OF TRANSACTION
-- SO TIME OF COMMIT CAN BE CONTROLLED
-- -----------------------------------------------------
-- -----------------------------------------------------
SET AUTOCOMMIT = 0;
START TRANSACTION;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: DATABASE SCHEMA
-- -----------------------------------------------------
-- -----------------------------------------------------


DROP SCHEMA IF EXISTS user_db ;
CREATE SCHEMA IF NOT EXISTS user_db DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: TABLES 
-- -----------------------------------------------------
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table user_db.account_extra_info
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.account_extra_info ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.account_extra_info (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  key_pair VARCHAR(50) NULL ,
  value_pair VARCHAR(50) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.cc_info
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.cc_info ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.cc_info (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  last_four VARCHAR(45) NULL ,
  expire_mmyy VARCHAR(45) NULL ,
  type VARCHAR(45) NULL ,
  status VARCHAR(45) NULL ,
  address_id BIGINT UNSIGNED NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.account
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.account ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.account (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(100) NOT NULL ,
  description VARCHAR(255) NULL ,
  type VARCHAR(45) NOT NULL ,
  status VARCHAR(45) NOT NULL ,
  contact_id BIGINT UNSIGNED NULL ,
  cc_info_id BIGINT UNSIGNED NULL ,
  extra_info_id BIGINT UNSIGNED NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.address
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.address ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.address (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  street VARCHAR(200) NULL ,
  city VARCHAR(100) NULL ,
  state VARCHAR(100) NULL ,
  country VARCHAR(45) NULL ,
  zip_code VARCHAR(45) NULL ,
  latitude FLOAT NULL ,
  longitude FLOAT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.contact
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.contact ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.contact (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  first_name VARCHAR(100) NULL ,
  last_name VARCHAR(100) NULL ,
  additional_email VARCHAR(255) NULL ,
  resident_address_id BIGINT UNSIGNED NULL ,
  shipping_address_id BIGINT UNSIGNED NULL ,
  billing_address_id BIGINT UNSIGNED NULL ,
  work_phone VARCHAR(20) NULL ,
  mobile_phone VARCHAR(20) NULL ,
  home_phone VARCHAR(20) NULL ,
  aim VARCHAR(45) NULL ,
  yim VARCHAR(45) NULL ,
  skype VARCHAR(45) NULL ,
  facebook VARCHAR(45) NULL ,
  twitter VARCHAR(45) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.permission
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.permission ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.permission (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NULL ,
  description VARCHAR(45) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.role_permission
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.role_permission ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.role_permission (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  role_id BIGINT UNSIGNED NOT NULL ,
  permission_id BIGINT UNSIGNED NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.role
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.role ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.role (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NULL ,
  description VARCHAR(45) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.time_zone
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.time_zone ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.time_zone (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  time_zone VARCHAR(45) NULL ,
  label VARCHAR(45) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.user_extra_info
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.user_extra_info ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.user_extra_info (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  industry VARCHAR(100) NULL ,
  company VARCHAR(100) NULL ,
  company_size VARCHAR(45) NULL ,
  zip_code VARCHAR(45) NULL ,
  time_zone_id BIGINT UNSIGNED NULL ,
  title VARCHAR(100) NULL ,
  job_title VARCHAR(45) NULL ,
  mobile_device_model VARCHAR(45) NULL ,
  mobile_manufacturer VARCHAR(45) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.user_status
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.user_status ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.user_status (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.user_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.user_type ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.user_type (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.user
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.user ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.user (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  display_name VARCHAR(45) NULL ,
  email VARCHAR(255) NOT NULL ,
  password VARCHAR(100) NOT NULL ,
  salt VARCHAR(45) NULL ,
  status_id BIGINT UNSIGNED NOT NULL ,
  type_id BIGINT UNSIGNED NOT NULL ,
  activation_code VARCHAR(100) NULL ,
  password_reset_code VARCHAR(100) NULL ,
  account_id BIGINT UNSIGNED NULL ,
  contact_id BIGINT UNSIGNED NULL ,
  user_extra_info_id BIGINT UNSIGNED NULL ,
  role_id BIGINT UNSIGNED NULL ,
  language VARCHAR(45) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.user_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.user_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.user_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  user_id BIGINT UNSIGNED NOT NULL ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.login_channel_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.login_channel_type ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.login_channel_type (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NOT NULL ,
  prefix VARCHAR(5) NOT NULL ,
  description VARCHAR(200) NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.login_channel_type_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.login_channel_type_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.login_channel_type_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  login_channel_type_id BIGINT UNSIGNED NOT NULL ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.user_login_channel
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.user_login_channel ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.user_login_channel (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  login_channel_type_id BIGINT UNSIGNED NOT NULL ,
  user_id BIGINT UNSIGNED NOT NULL ,
  user_name VARCHAR(50) NOT NULL ,
  access_token VARCHAR(200) NULL ,
  expires BIGINT UNSIGNED NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.user_login_channel_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.user_login_channel_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.user_login_channel_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  user_login_channel_id BIGINT UNSIGNED NOT NULL ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table user_db.session
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_db.session ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS user_db.session (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  guid VARCHAR(100) NOT NULL ,
  uid BIGINT UNSIGNED NOT NULL ,
  client_ip VARCHAR(45) NOT NULL DEFAULT '127.0.0.1' ,
  last_login DATETIME NOT NULL ,
  user_agent VARCHAR(255) NOT NULL ,
  logged_in BIT NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: INDEXES 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
CREATE INDEX 
	IDX_account_TBL_cc_info_id 
	ON 
	user_db.account (cc_info_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_account_TBL_contact_id 
	ON 
	user_db.account (contact_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_account_TBL_extra_info_id 
	ON 
	user_db.account (extra_info_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_contact_TBL_address 
	ON 
	user_db.contact (resident_address_id ASC, shipping_address_id ASC, billing_address_id ASC) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_role_permission_TBL_permission_id 
	ON 
	user_db.role_permission (permission_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_role_permission_TBL_role_id 
	ON 
	user_db.role_permission (role_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_role_TBL_id 
	ON 
	user_db.role (id ASC) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_extra_info_TBL_time_zone_id 
	ON 
	user_db.user_extra_info (time_zone_id ASC) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_TBL_account_id 
	ON 
	user_db.user (account_id ASC) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_TBL_contact_id 
	ON 
	user_db.user (contact_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_TBL_role_id 
	ON 
	user_db.user (role_id ASC) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_TBL_user_extra_info_id 
	ON 
	user_db.user (user_extra_info_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_session_TBL_uid 
	ON 
	user_db.session (uid) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_TBL_status_id 
	ON 
	user_db.user (status_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_TBL_type_id 
	ON 
	user_db.user (type_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_cc_info_TBL_address_id 
	ON 
	user_db.cc_info (address_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_TBL_activation_code 
	ON 
	user_db.user (activation_code) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_login_channel_TBL_login_channel_type_id 
	ON 
	user_db.user_login_channel (login_channel_type_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_login_channel_TBL_user_id 
	ON 
	user_db.user_login_channel (user_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_key_value_pair_TBL_user_id 
	ON 
	user_db.user_key_value_pair (user_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_login_channel_type_key_value_pair_TBL_login_channel_type_id 
	ON 
	user_db.login_channel_type_key_value_pair (login_channel_type_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_login_channel_key_value_pair_TBL_user_login_channel_id 
	ON 
	user_db.user_login_channel_key_value_pair (user_login_channel_id) ;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: UNIQUE INDEXES 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_user_TBL_email 
	USING BTREE 
	ON user_db.user (email) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_user_status_TBL_name 
	USING BTREE 
	ON user_db.user_status (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_user_type_TBL_name 
	USING BTREE 
	ON user_db.user_type (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_login_channel_type_TBL_name 
	USING BTREE 
	ON user_db.login_channel_type (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_login_channel_type_TBL_prefix 
	USING BTREE 
	ON user_db.login_channel_type (prefix) ;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: REFERENTIAL INTEGRITIES - FOREIGN KEYS 
-- -----------------------------------------------------
-- -----------------------------------------------------
ALTER TABLE user_db.account 
    ADD FOREIGN KEY FK_account_TBL_cc_info_id (cc_info_id)
    REFERENCES user_db.cc_info (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.account 
    ADD FOREIGN KEY FK_account_TBL_contact_id (contact_id)
    REFERENCES user_db.contact (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.account 
    ADD FOREIGN KEY FK_account_TBL_extra_info_id (extra_info_id)
    REFERENCES user_db.account_extra_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.contact 
    ADD FOREIGN KEY FK_contact_TBL_resident_address_id (resident_address_id)
    REFERENCES user_db.address (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.contact 
    ADD FOREIGN KEY FK_contact_TBL_shipping_address_id (shipping_address_id)
    REFERENCES user_db.address (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.contact 
    ADD FOREIGN KEY FK_contact_TBL_billing_address_id (billing_address_id)
    REFERENCES user_db.address (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.role_permission 
    ADD FOREIGN KEY FK_role_permission_TBL_permission_id (permission_id)
    REFERENCES user_db.permission (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.role_permission 
    ADD FOREIGN KEY FK_role_permission_TBL_role_id (role_id)
    REFERENCES user_db.role (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE user_db.user_extra_info 
    ADD FOREIGN KEY FK_user_extra_info_TBL_time_zone_id (time_zone_id)
    REFERENCES user_db.time_zone (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user 
    ADD FOREIGN KEY FK_user_TBL_account_id (account_id)
    REFERENCES user_db.account (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user 
    ADD FOREIGN KEY FK_user_TBL_contact_id (contact_id)
    REFERENCES user_db.contact (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user 
    ADD FOREIGN KEY FK_user_TBL_user_extra_info_id (user_extra_info_id)
    REFERENCES user_db.user_extra_info (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user 
    ADD FOREIGN KEY FK_user_TBL_role_id (role_id)
    REFERENCES user_db.role (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.session 
    ADD FOREIGN KEY FK_session_TBL_uid (uid)
    REFERENCES user_db.user (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user 
    ADD FOREIGN KEY FK_user_TBL_status_id (status_id)
    REFERENCES user_db.user_status (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user 
    ADD FOREIGN KEY FK_user_TBL_type_id (type_id)
    REFERENCES user_db.user_type (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.cc_info 
    ADD FOREIGN KEY FK_cc_info_TBL_address_id (address_id)
    REFERENCES user_db.address (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user_login_channel 
    ADD FOREIGN KEY FK_user_login_channel_TBL_login_channel_type_id (login_channel_type_id)
    REFERENCES user_db.login_channel_type (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE user_db.user_login_channel 
    ADD FOREIGN KEY FK_user_login_channel_TBL_user_id (user_id)
    REFERENCES user_db.user (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE user_db.user_key_value_pair 
    ADD FOREIGN KEY FK_user_key_value_pair_TBL_user_id (user_id)
    REFERENCES user_db.user (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE user_db.login_channel_type_key_value_pair 
    ADD FOREIGN KEY FK_login_channel_type_key_value_pair_TBL_login_channel_type_id (login_channel_type_id)
    REFERENCES user_db.login_channel_type (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE user_db.user_login_channel_key_value_pair 
    ADD FOREIGN KEY FK_user_login_channel_key_value_pair_TBL_user_login_channel_id (user_login_channel_id)
    REFERENCES user_db.user_login_channel (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- END OF TRANSACTION
-- SO TIME OF COMMIT CAN BE CONTROLLED
-- -----------------------------------------------------
-- -----------------------------------------------------
COMMIT;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- SCRIPT AND CONNECTION SESSION SETTINGS
-- -----------------------------------------------------
-- -----------------------------------------------------
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
