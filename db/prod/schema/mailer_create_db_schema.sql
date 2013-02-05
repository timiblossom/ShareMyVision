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


DROP SCHEMA IF EXISTS mailer_db ;
CREATE SCHEMA IF NOT EXISTS mailer_db DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: TABLES 
-- -----------------------------------------------------
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table mailer_db.event_code
-- -----------------------------------------------------
DROP TABLE IF EXISTS mailer_db.event_code ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS mailer_db.event_code (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(200) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table mailer_db.subject_template
-- -----------------------------------------------------
DROP TABLE IF EXISTS mailer_db.subject_template ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS mailer_db.subject_template (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(200) NULL ,
  file_location VARCHAR(500) NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table mailer_db.subject_template_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS mailer_db.subject_template_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS mailer_db.subject_template_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(200) NULL ,
  subject_template_id BIGINT UNSIGNED NOT NULL,
  key_pair VARCHAR(50) NOT NULL ,
  default_value_pair VARCHAR(150) NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table mailer_db.body_template
-- -----------------------------------------------------
DROP TABLE IF EXISTS mailer_db.body_template ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS mailer_db.body_template (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(200) NULL ,
  file_location VARCHAR(500) NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table mailer_db.body_template_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS mailer_db.body_template_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS mailer_db.body_template_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(200) NULL ,
  body_template_id BIGINT UNSIGNED NOT NULL,
  key_pair VARCHAR(50) NOT NULL ,
  default_value_pair VARCHAR(150) NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


SHOW WARNINGS;

-- -----------------------------------------------------
-- Table mailer_db.event_code_template
-- -----------------------------------------------------
DROP TABLE IF EXISTS mailer_db.event_code_template ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS mailer_db.event_code_template (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(200) NULL ,
  event_code_id BIGINT UNSIGNED NOT NULL,
  subject_template_id BIGINT UNSIGNED NOT NULL,
  body_template_id BIGINT UNSIGNED NOT NULL,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: INDEXES 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
CREATE INDEX IDX_subject_template_key_value_pair_TBL_subject_template_id ON mailer_db.subject_template_key_value_pair (subject_template_id) ;

SHOW WARNINGS;
CREATE INDEX IDX_body_template_key_value_pair_TBL_body_template_id ON mailer_db.body_template_key_value_pair (body_template_id) ;

SHOW WARNINGS;
CREATE INDEX IDX_event_code_template_TBL_event_code_id ON mailer_db.event_code_template (event_code_id) ;

SHOW WARNINGS;
CREATE INDEX IDX_event_code_template_TBL_subject_template_id ON mailer_db.event_code_template (subject_template_id) ;

SHOW WARNINGS;
CREATE INDEX IDX_event_code_template_TBL_body_template_id ON mailer_db.event_code_template (body_template_id) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_event_code_TBL_name 
	USING BTREE 
	ON mailer_db.event_code (name) ;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: REFERENTIAL INTEGRITIES - FOREIGN KEYS 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
ALTER TABLE mailer_db.subject_template_key_value_pair 
    ADD FOREIGN KEY FK_subject_template_key_value_pair_TBL_subject_template_id (subject_template_id)
    REFERENCES mailer_db.subject_template (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE mailer_db.body_template_key_value_pair 
    ADD FOREIGN KEY FK_body_template_key_value_pair_TBL_body_template_id (body_template_id)
    REFERENCES mailer_db.body_template (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE mailer_db.event_code_template 
    ADD FOREIGN KEY FK_event_code_template_TBL_event_code_id (event_code_id)
    REFERENCES mailer_db.event_code (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE mailer_db.event_code_template 
    ADD FOREIGN KEY FK_event_code_template_TBL_subject_template_id (subject_template_id)
    REFERENCES mailer_db.subject_template (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE mailer_db.event_code_template 
    ADD FOREIGN KEY FK_event_code_template_TBL_body_template_id (body_template_id)
    REFERENCES mailer_db.body_template (id)  
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
