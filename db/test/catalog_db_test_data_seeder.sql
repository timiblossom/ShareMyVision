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
-- INSERT DATA INTO DATABASE
-- -----------------------------------------------------
-- -----------------------------------------------------

-- -----------------------------------------------------
-- PRODUCT DATA
-- -----------------------------------------------------
-- Stand Alone Product
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	1,
    	'My stand alone product',
    	NOW()
   	)
;

-- Child Product # 1
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	2,
    	'My child product 1',
    	NOW()
   	)
;

-- Child Product # 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	3,
    	'My child product 2',
    	NOW()
   	)
;

-- Parent Product
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	4,
    	'My parent product',
    	NOW()
   	)
;


-- -----------------------------------------------------
-- PRODUCT COMPOSITION DATA
-- -----------------------------------------------------
-- Parent-Child Relationship # 1
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_composition
	(
		parent_product_id,
		child_product_id,
  		updated_on
	) 
VALUES
    (
		4,
		2,
    	NOW()
   	)
;

-- Parent-Child Relationship # 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_composition
	(
		parent_product_id,
		child_product_id,
  		updated_on
	) 
VALUES
    (
		4,
		3,
    	NOW()
   	)
;


-- -----------------------------------------------------
-- PRODUCT KEY-VALUE PAIR DATA
-- -----------------------------------------------------
-- Key-Value Pair # 1 for Stand Alone Product
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
VALUES
    (
    	1,
    	'key_1_of_My stand alone product',
    	'value_1_of_My stand alone product',
    	1,
    	NOW()
   	)
;

-- Key-Value Pair # 2 for Stand Alone Product
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
VALUES
    (
    	2,
    	'key_2_of_My stand alone product',
    	'value_2_of_My stand alone product',
    	1,
    	NOW()
   	)
;

-- Key-Value Pair # 1 for My child product 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
VALUES
    (
    	3,
    	'key_1_of_My child product 2',
    	'value_1_of_My child product 2',
    	3,
    	NOW()
   	)
;


-- -----------------------------------------------------
-- SERVICE DATA
-- -----------------------------------------------------
-- Stand Alone Service
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	1,
    	'My stand alone service',
    	NOW()
   	)
;

-- Child Service # 1
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	2,
    	'My child service 1',
    	NOW()
   	)
;

-- Child Service # 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	3,
    	'My child service 2',
    	NOW()
   	)
;

-- Child Service # 3
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	4,
    	'My child service 3',
    	NOW()
   	)
;

-- Parent Service
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service
	(
		id,
		name,
  		updated_on
	) 
VALUES
    (
    	5,
    	'My parent service',
    	NOW()
   	)
;


-- -----------------------------------------------------
-- SERVICE COMPOSITION DATA
-- -----------------------------------------------------
-- Parent-Child Relationship # 1
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_composition
	(
		parent_service_id,
		child_service_id,
  		updated_on
	) 
VALUES
    (
		5,
		2,
    	NOW()
   	)
;

-- Parent-Child Relationship # 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_composition
	(
		parent_service_id,
		child_service_id,
  		updated_on
	) 
VALUES
    (
		5,
		3,
    	NOW()
   	)
;


-- Parent-Child Relationship # 3
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_composition
	(
		parent_service_id,
		child_service_id,
  		updated_on
	) 
VALUES
    (
		5,
		4,
    	NOW()
   	)
;


-- -----------------------------------------------------
-- SERVICE KEY-VALUE PAIR DATA
-- -----------------------------------------------------
-- Key-Value Pair # 1 for Parent Service
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		service_id,
  		updated_on
	) 
VALUES
    (
    	1,
    	'key_1_of_My parent service',
    	'value_1_of_My parent service',
    	5,
    	NOW()
   	)
;

-- Key-Value Pair # 2 for Parent Service
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		service_id,
  		updated_on
	) 
VALUES
    (
    	2,
    	'key_2_of_My parent service',
    	'value_2_of_My parent service',
    	5,
    	NOW()
   	)
;

-- Key-Value Pair # 1 for Child Service # 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		service_id,
  		updated_on
	) 
VALUES
    (
    	3,
    	'key_1_of_My child service 2',
    	'value_1_of_My child service 2',
    	3,
    	NOW()
   	)
;

-- Key-Value Pair # 2 for Child Service # 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		service_id,
  		updated_on
	) 
VALUES
    (
    	4,
    	'key_2_of_My child service 2',
    	'value_2_of_My child service 2',
    	3,
    	NOW()
   	)
;

-- Key-Value Pair # 3 for Child Service # 2
SHOW WARNINGS;
INSERT INTO 
	catalog_db.service_key_value_pair
	(
		id,
		key_pair,
		value_pair,
		service_id,
  		updated_on
	) 
VALUES
    (
    	5,
    	'key_3_of_My child service 2',
    	'value_3_of_My child service 2',
    	3,
    	NOW()
   	)
;


-- -----------------------------------------------------
-- CATALOG DATA
-- -----------------------------------------------------
-- Stand Alone Product as Catalog Item
SHOW WARNINGS;
INSERT INTO 
	catalog_db.catalog
	(
  		product_id,
  		name,
  		updated_on
	) 
VALUES
    (
  		1,
  		'Catalog Item 1',
    	NOW()
   	)
;

-- Composite Product as Catalog Item
SHOW WARNINGS;
INSERT INTO 
	catalog_db.catalog
	(
  		product_id,
  		name,
  		updated_on
	) 
VALUES
    (
  		4,
  		'Catalog Item 2',
    	NOW()
   	)
;

-- Stand Alone Service as Catalog Item
SHOW WARNINGS;
INSERT INTO 
	catalog_db.catalog
	(
  		service_id,
  		name,
  		updated_on
	) 
VALUES
    (
  		1,
  		'Catalog Item 3',
    	NOW()
   	)
;

-- Composite Service as Catalog Item
SHOW WARNINGS;
INSERT INTO 
	catalog_db.catalog
	(
  		service_id,
  		name,
  		updated_on
	) 
VALUES
    (
  		5,
  		'Catalog Item 4',
    	NOW()
   	)
;

-- Child Service as Catalog Item
SHOW WARNINGS;
INSERT INTO 
	catalog_db.catalog
	(
  		service_id,
  		name,
  		updated_on
	) 
VALUES
    (
  		2,
  		'Catalog Item 5',
    	NOW()
   	)
;

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
