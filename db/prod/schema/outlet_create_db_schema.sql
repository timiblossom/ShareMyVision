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


DROP SCHEMA IF EXISTS outlet_db ;
CREATE SCHEMA IF NOT EXISTS outlet_db DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: TABLES 
-- -----------------------------------------------------
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table outlet_db.outlet_status_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS outlet_db.outlet_status_type ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS outlet_db.outlet_status_type (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NOT NULL ,
  description VARCHAR(200) NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table outlet_db.outlet_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS outlet_db.outlet_type ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS outlet_db.outlet_type (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NOT NULL ,
  prefix VARCHAR(5) NOT NULL ,
  description VARCHAR(200) NOT NULL ,
  status_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table outlet_db.user_outlet
-- -----------------------------------------------------
DROP TABLE IF EXISTS outlet_db.user_outlet ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS outlet_db.user_outlet (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NULL ,
  description VARCHAR(200) NULL ,
  outlet_id BIGINT UNSIGNED NOT NULL ,
  user_id BIGINT UNSIGNED NOT NULL ,
  status_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table outlet_db.user_outlet_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS outlet_db.user_outlet_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS outlet_db.user_outlet_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  user_outlet_id BIGINT UNSIGNED NOT NULL ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table outlet_db.user_outlet_content
-- -----------------------------------------------------
DROP TABLE IF EXISTS outlet_db.user_outlet_content ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS outlet_db.user_outlet_content (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NULL ,
  description VARCHAR(200) NULL ,
  user_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table outlet_db.user_outlet_content_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS outlet_db.user_outlet_content_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS outlet_db.user_outlet_content_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  content_id BIGINT UNSIGNED NOT NULL ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
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
	IDX_outlet_type_TBL_status_id 
	ON 
	outlet_db.outlet_type (status_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_outlet_TBL_outlet_id 
	ON 
	outlet_db.user_outlet (outlet_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_outlet_TBL_status_id 
	ON 
	outlet_db.user_outlet (status_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_outlet_key_value_pair_TBL_user_outlet_id 
	ON 
	outlet_db.user_outlet_key_value_pair (user_outlet_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_user_outlet_content_key_value_pair_TBL_content_id 
	ON 
	outlet_db.user_outlet_content_key_value_pair (content_id) ;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: UNIQUE INDEXES 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_outlet_status_type_TBL_name 
	USING BTREE 
	ON outlet_db.outlet_status_type (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_outlet_type_TBL_name 
	USING BTREE 
	ON outlet_db.outlet_type (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_outlet_type_TBL_prefix 
	USING BTREE 
	ON outlet_db.outlet_type (prefix) ;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: REFERENTIAL INTEGRITIES - FOREIGN KEYS 
-- -----------------------------------------------------
-- -----------------------------------------------------
ALTER TABLE outlet_db.outlet_type 
    ADD FOREIGN KEY FK_outlet_type_TBL_status_id (status_id)
    REFERENCES outlet_db.outlet_status_type (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE outlet_db.user_outlet 
    ADD FOREIGN KEY FK_user_outlet_TBL_outlet_id (outlet_id)
    REFERENCES outlet_db.outlet_type (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE outlet_db.user_outlet 
    ADD FOREIGN KEY FK_user_outlet_TBL_status_id (status_id)
    REFERENCES outlet_db.outlet_status_type (id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE outlet_db.user_outlet_key_value_pair 
    ADD FOREIGN KEY FK_user_outlet_key_value_pair_TBL_user_outlet_id (user_outlet_id)
    REFERENCES outlet_db.user_outlet (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE outlet_db.user_outlet_content_key_value_pair 
    ADD FOREIGN KEY FK_user_outlet_content_key_value_pair_TBL_content_id (content_id)
    REFERENCES outlet_db.user_outlet_content (id)  
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
