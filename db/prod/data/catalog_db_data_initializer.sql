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
-- STATUS DATA
-- -----------------------------------------------------
-- 'ACTIVE' Status
SHOW WARNINGS;
INSERT INTO 
	catalog_db.status 
	(
		name,
		description,
  		updated_on
	) 
    SELECT 
    	'ACTIVE',
    	'Status of Products, Services, or Catalog Items which are Active.',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                catalog_db.status
            WHERE
                name = 'ACTIVE'
        )
;

-- 'INACTIVE' Status
SHOW WARNINGS;
INSERT INTO 
	catalog_db.status 
	(
		name,
		description,
  		updated_on
	) 
    SELECT 
    	'INACTIVE',
    	'Status of Products, Services, or Catalog Items which are NOT Active.',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                catalog_db.status
            WHERE
                name = 'INACTIVE'
        )
;


-- -----------------------------------------------------
-- PRODUCT DATA
-- -----------------------------------------------------
-- 'Lite' Product
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product 
	(
		name,
		description, 
		status_id,
		start_date,
		end_date,
  		updated_on
	) 
    SELECT 
    	'Lite',
    	'Free product for non-paying users',
    	status.id,
    	NOW(),
    	DATE_ADD(curdate(), INTERVAL 1 YEAR),
    	NOW()
    FROM 
        catalog_db.status status
    WHERE 
    	status.name = 'ACTIVE'
    	AND 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                catalog_db.product
            WHERE
                name = 'Lite'
        )
;

-- 'Pro' Product
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product 
	(
		name,
		description, 
		status_id,
		start_date,
		end_date,
  		updated_on
	) 
    SELECT 
    	'Pro',
    	'Product for first tier paying users',
    	status.id,
    	NOW(),
    	DATE_ADD(curdate(), INTERVAL 1 YEAR),
    	NOW()
    FROM 
        catalog_db.status status
    WHERE 
    	status.name = 'ACTIVE'
    	AND 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                catalog_db.product
            WHERE
                name = 'Pro'
        )
;


-- -----------------------------------------------------
-- PRODUCT KEY-VALUE PAIR DATA
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Key-Value pairs for 'Lite' User
-- -----------------------------------------------------

-- 200 Picture Storage for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Picture Storage',
    	'200',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Picture Storage'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Facebook Publication for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Facebook Publication',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Facebook Publication'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Web Album for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Web Album',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Web Album'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Email Publishing for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Email Publishing',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Email Publishing'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Number of Downloads for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Number of Downloads',
    	'Unlimited',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Number of Downloads'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Viewing of Original Pictures for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Viewing of Original Pictures',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Viewing of Original Pictures'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Unlimited Photo Sharing for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Photo Sharing',
    	'Unlimited',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Photo Sharing'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Slide Show for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Slide Show',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Slide Show'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Android Support for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Android Support',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Android Support'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- iPhone Support for 'Lite' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'iPhone Support',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Lite'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Lite'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'iPhone Support'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- -----------------------------------------------------
-- Key-Value pairs for 'Pro' User
-- -----------------------------------------------------

-- 1000 Picture Storage for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Picture Storage',
    	'1000',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Picture Storage'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Facebook Publication for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Facebook Publication',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Facebook Publication'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Web Album for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Web Album',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Web Album'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Email Publishing for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Email Publishing',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Email Publishing'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Number of Downloads for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Number of Downloads',
    	'Unlimited',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Number of Downloads'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Viewing of Original Pictures for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Viewing of Original Pictures',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Viewing of Original Pictures'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Unlimited Photo Sharing for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Photo Sharing',
    	'Unlimited',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Photo Sharing'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Slide Show for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Slide Show',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Slide Show'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- Android Support for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'Android Support',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'Android Support'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
        )
;

-- iPhone Support for 'Pro' User
SHOW WARNINGS;
INSERT INTO 
	catalog_db.product_key_value_pair
	(
		key_pair,
		value_pair,
		product_id,
  		updated_on
	) 
    SELECT 
    	'iPhone Support',
    	'Yes',
    	product.id,
    	NOW()
    FROM 
        catalog_db.product product 
    WHERE 
    	product.name = 'Pro'
    	AND
        NOT EXISTS
        (
            SELECT
                *
            FROM 
		        catalog_db.product innerProduct, 
				catalog_db.product_key_value_pair innerProductKeyValuePair
            WHERE
		    	innerProduct.name = 'Pro'
		    	AND
		    	innerProductKeyValuePair.key_pair = 'iPhone Support'
		    	AND
		    	innerProductKeyValuePair.product_id = innerProduct.id
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
