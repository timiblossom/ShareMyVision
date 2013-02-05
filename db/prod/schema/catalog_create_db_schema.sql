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


DROP SCHEMA IF EXISTS catalog_db ;
CREATE SCHEMA IF NOT EXISTS catalog_db DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: TABLES 
-- -----------------------------------------------------
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table catalog_db.product
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.product ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.product (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NULL ,
  status_id BIGINT UNSIGNED NOT NULL ,
  start_date DATETIME NULL ,
  end_date DATETIME NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.product_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.product_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.product_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
  product_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.product_composition
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.product_composition ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.product_composition (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  parent_product_id BIGINT UNSIGNED NOT NULL ,
  child_product_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.product_user_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.product_user_type ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.product_user_type (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NULL ,
  product_id BIGINT UNSIGNED NOT NULL ,
  user_type_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.service
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.service ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.service (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NULL ,
  status_id BIGINT UNSIGNED NOT NULL ,
  start_date DATETIME NULL ,
  end_date DATETIME NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.service_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.service_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.service_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
  service_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.service_composition
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.service_composition ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.service_composition (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  parent_service_id BIGINT UNSIGNED NOT NULL ,
  child_service_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.service_user_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.service_user_type ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.service_user_type (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NULL ,
  service_id BIGINT UNSIGNED NOT NULL ,
  user_type_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.catalog
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.catalog ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.catalog (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NULL ,
  status_id BIGINT UNSIGNED NOT NULL ,
  start_date DATETIME NULL ,
  end_date DATETIME NULL ,
  product_id BIGINT UNSIGNED NULL ,
  service_id BIGINT UNSIGNED NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.catalog_key_value_pair
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.catalog_key_value_pair ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.catalog_key_value_pair (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  key_pair VARCHAR(50) NOT NULL ,
  value_pair VARCHAR(150) NOT NULL ,
  catalog_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.catalog_user_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.catalog_user_type ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.catalog_user_type (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NULL ,
  catalog_id BIGINT UNSIGNED NOT NULL ,
  user_type_id BIGINT UNSIGNED NOT NULL ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table catalog_db.status
-- -----------------------------------------------------
DROP TABLE IF EXISTS catalog_db.status ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS catalog_db.status (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NULL ,
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
	IDX_product_TBL_status_id 
	ON 
	catalog_db.product (status_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_product_key_value_pair_TBL_product_id 
	ON 
	catalog_db.product_key_value_pair (product_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_product_composition_TBL_parent_product_id 
	ON 
	catalog_db.product_composition (parent_product_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_product_composition_TBL_child_product_id 
	ON 
	catalog_db.product_composition (child_product_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_product_user_type_TBL_product_id 
	ON 
	catalog_db.product_user_type (product_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_product_user_type_TBL_user_type_id 
	ON 
	catalog_db.product_user_type (user_type_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_service_TBL_status_id 
	ON 
	catalog_db.service (status_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_service_key_value_pair_TBL_service_id 
	ON 
	catalog_db.service_key_value_pair (service_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_service_composition_TBL_parent_service_id 
	ON 
	catalog_db.service_composition (parent_service_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_service_composition_TBL_child_service_id 
	ON 
	catalog_db.service_composition (child_service_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_service_user_type_TBL_service_id 
	ON 
	catalog_db.service_user_type (service_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_service_user_type_TBL_user_type_id 
	ON 
	catalog_db.service_user_type (user_type_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_catalog_key_value_pair_TBL_catalog_id 
	ON 
	catalog_db.catalog_key_value_pair (catalog_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_catalog_TBL_product_id 
	ON 
	catalog_db.catalog (product_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_catalog_TBL_service_id 
	ON 
	catalog_db.catalog (service_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_catalog_user_type_TBL_catalog_id 
	ON 
	catalog_db.catalog_user_type (catalog_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_catalog_user_type_TBL_user_type_id 
	ON 
	catalog_db.catalog_user_type (user_type_id) ;

SHOW WARNINGS;
CREATE INDEX 
	IDX_catalog_TBL_status_id 
	ON 
	catalog_db.catalog (status_id) ;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: UNIQUE INDEXES 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_catalog_TBL_name 
	USING BTREE 
	ON catalog_db.catalog (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_product_TBL_name 
	USING BTREE 
	ON catalog_db.product (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_service_TBL_name 
	USING BTREE 
	ON catalog_db.service (name) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX 
	IDX_UNQ_status_TBL_name 
	USING BTREE 
	ON catalog_db.status (name) ;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: REFERENTIAL INTEGRITIES - FOREIGN KEYS 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
ALTER TABLE catalog_db.product 
    ADD FOREIGN KEY FK_product_TBL_status_id (status_id)
    REFERENCES catalog_db.status (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.product_key_value_pair 
    ADD FOREIGN KEY FK_product_key_value_pair_TBL_product_id (product_id)
    REFERENCES catalog_db.product (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.product_composition 
    ADD FOREIGN KEY FK_product_composition_TBL_parent_product_id (parent_product_id)
    REFERENCES catalog_db.product (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.product_composition 
    ADD FOREIGN KEY FK_product_composition_TBL_child_product_id (child_product_id)
    REFERENCES catalog_db.product (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.product_user_type 
    ADD FOREIGN KEY FK_product_user_type_TBL_product_id (product_id)
    REFERENCES catalog_db.product (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.product_user_type 
    ADD FOREIGN KEY FK_product_user_type_TBL_user_type_id (user_type_id)
    REFERENCES user_db.user_type (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.service 
    ADD FOREIGN KEY FK_service_TBL_status_id (status_id)
    REFERENCES catalog_db.status (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.service_key_value_pair 
    ADD FOREIGN KEY FK_service_key_value_pair_TBL_service_id (service_id)
    REFERENCES catalog_db.service (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.service_composition 
    ADD FOREIGN KEY FK_service_composition_TBL_parent_service_id (parent_service_id)
    REFERENCES catalog_db.service (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.service_composition 
    ADD FOREIGN KEY FK_service_composition_TBL_child_service_id (child_service_id)
    REFERENCES catalog_db.service (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.service_user_type 
    ADD FOREIGN KEY FK_service_user_type_TBL_service_id (service_id)
    REFERENCES catalog_db.service (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.service_user_type 
    ADD FOREIGN KEY FK_service_user_type_TBL_user_type_id (user_type_id)
    REFERENCES user_db.user_type (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.catalog 
    ADD FOREIGN KEY FK_catalog_TBL_status_id (status_id)
    REFERENCES catalog_db.status (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.catalog 
    ADD FOREIGN KEY FK_catalog_TBL_product_id (product_id)
    REFERENCES catalog_db.product (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.catalog 
    ADD FOREIGN KEY FK_catalog_TBL_service_id (service_id)
    REFERENCES catalog_db.service (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.catalog_key_value_pair 
    ADD FOREIGN KEY FK_catalog_key_value_pair_TBL_catalog_id (catalog_id)
    REFERENCES catalog_db.catalog (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE catalog_db.catalog_user_type 
    ADD FOREIGN KEY FK_catalog_user_type_TBL_catalog_id (catalog_id)
    REFERENCES catalog_db.catalog (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


SHOW WARNINGS;
ALTER TABLE catalog_db.catalog_user_type 
    ADD FOREIGN KEY FK_catalog_user_type_TBL_user_type_id (user_type_id)
    REFERENCES user_db.user_type (id)  
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
