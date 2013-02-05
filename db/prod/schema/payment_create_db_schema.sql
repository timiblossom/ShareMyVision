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


DROP SCHEMA IF EXISTS payment_db ;
CREATE SCHEMA IF NOT EXISTS payment_db DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: TABLES 
-- -----------------------------------------------------
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table payment_db.purchase_order
-- -----------------------------------------------------
DROP TABLE IF EXISTS payment_db.purchase_order ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS payment_db.purchase_order (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  order_number_id VARCHAR(100) NOT NULL ,
  total_order_amount DECIMAL NOT NULL ,
  total_order_quantity DECIMAL NOT NULL ,
  order_code VARCHAR(50) NULL ,
  order_description VARCHAR(300) NULL ,
  user_id BIGINT UNSIGNED NOT NULL ,
  account_id BIGINT UNSIGNED NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table payment_db.order_item
-- -----------------------------------------------------
DROP TABLE IF EXISTS payment_db.order_item ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS payment_db.order_item (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  order_id BIGINT UNSIGNED NOT NULL ,
  item_amount DECIMAL NOT NULL ,
  item_quantity DECIMAL NOT NULL ,
  item_code BIGINT UNSIGNED NOT NULL ,
  item_description VARCHAR(300) NULL ,
  is_service BIT NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table payment_db.invoice
-- -----------------------------------------------------
DROP TABLE IF EXISTS payment_db.invoice ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS payment_db.invoice (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  invoice_number_id VARCHAR(100) NOT NULL ,
  total_invoiced_amount DECIMAL NOT NULL ,
  total_invoiced_quantity DECIMAL NOT NULL ,
  order_id BIGINT UNSIGNED NOT NULL ,
  invoice_code VARCHAR(50) NOT NULL ,
  invoice_description VARCHAR(300) NULL ,
  user_id BIGINT UNSIGNED NOT NULL ,
  account_id BIGINT UNSIGNED NOT NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table payment_db.invoice_item
-- -----------------------------------------------------
DROP TABLE IF EXISTS payment_db.invoice_item ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS payment_db.invoice_item (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  invoice_id BIGINT UNSIGNED NOT NULL ,
  item_amount DECIMAL NOT NULL ,
  item_quantity DECIMAL NOT NULL ,
  item_code BIGINT UNSIGNED NOT NULL ,
  item_description VARCHAR(300) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table payment_db.payment_method
-- -----------------------------------------------------
DROP TABLE IF EXISTS payment_db.payment_method ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS payment_db.payment_method (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  method_name VARCHAR(100) NOT NULL ,
  description VARCHAR(300) NULL ,
  version_id BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  updated_on DATETIME NOT NULL ,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table payment_db.payment
-- -----------------------------------------------------
DROP TABLE IF EXISTS payment_db.payment ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS payment_db.payment (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  payment_id VARCHAR(100) NOT NULL ,
  payment_date DATETIME NOT NULL ,
  payment_method BIGINT UNSIGNED NOT NULL ,
  description VARCHAR(300) NULL ,
  invoice_id BIGINT UNSIGNED NOT NULL ,
  user_id BIGINT UNSIGNED NOT NULL ,
  account_id BIGINT UNSIGNED NOT NULL ,
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
CREATE INDEX IDX_order_item_TBL_order_id ON payment_db.order_item (order_id) ;

SHOW WARNINGS;
CREATE INDEX IDX_invoice_TBL_order_id ON payment_db.invoice (order_id) ;

SHOW WARNINGS;
CREATE INDEX IDX_invoice_item_TBL_invoice_id ON payment_db.invoice_item (invoice_id) ;

SHOW WARNINGS;
CREATE INDEX IDX_payment_TBL_payment_method ON payment_db.payment (payment_method) ;

SHOW WARNINGS;
CREATE INDEX IDX_payment_TBL_invoice_id ON payment_db.payment (invoice_id) ;

-- -----------------------------------------------------
-- -----------------------------------------------------
-- CREATION: REFERENTIAL INTEGRITIES - FOREIGN KEYS 
-- -----------------------------------------------------
-- -----------------------------------------------------
SHOW WARNINGS;
ALTER TABLE payment_db.order_item 
    ADD FOREIGN KEY FK_order_item_TBL_parent_product_id (order_id)
    REFERENCES payment_db.purchase_order (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE payment_db.invoice_item 
    ADD FOREIGN KEY FK_invoice_item_TBL_invoice_id (invoice_id)
    REFERENCES payment_db.invoice (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE payment_db.invoice 
    ADD FOREIGN KEY FK_invoice_TBL_order_id (order_id)
    REFERENCES payment_db.purchase_order (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE payment_db.payment 
    ADD FOREIGN KEY FK_payment_TBL_payment_method (payment_method)
    REFERENCES payment_db.payment_method (id)  
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SHOW WARNINGS;
ALTER TABLE payment_db.payment 
    ADD FOREIGN KEY FK_payment_TBL_invoice_id (invoice_id)
    REFERENCES payment_db.invoice (id)  
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
