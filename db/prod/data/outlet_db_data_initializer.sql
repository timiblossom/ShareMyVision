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
-- INSERTION: OUTLET_STATUS_TYPE TABLE
-- -----------------------------------------------------
-- -----------------------------------------------------

-- -----------------------------------------------------
-- STATUS DATA
-- -----------------------------------------------------

-- 'ACTIVE' Status
SHOW WARNINGS;
INSERT INTO 
	outlet_db.outlet_status_type 
	(
		name,
		description,
  		updated_on
	) 
    SELECT 
    	'ACTIVE',
    	'Status of Outlet Types which are Active.',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                outlet_db.outlet_status_type
            WHERE
                name = 'ACTIVE'
        )
;

-- 'INACTIVE' Status
SHOW WARNINGS;
INSERT INTO 
	outlet_db.outlet_status_type 
	(
		name,
		description,
  		updated_on
	) 
    SELECT 
    	'INACTIVE',
    	'Status of Outlet Types which are NOT Active.',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                outlet_db.outlet_status_type
            WHERE
                name = 'INACTIVE'
        )
;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- INSERTION: OUTLET_TYPE TABLE
-- -----------------------------------------------------
-- -----------------------------------------------------

-- -----------------------------------------------------
-- OUTLET_TYPE DATA
-- -----------------------------------------------------

-- Outlet Type: ShareMyVision
SHOW WARNINGS;
INSERT INTO 
    outlet_db.outlet_type
    (
        name,
        prefix,
        description,
        status_id,
        updated_on
    ) 
    SELECT 
        'ShareMyVision',
        'SMV',
        'ShareMyVision implementation of User Engine Module',
        outlet_db.outlet_status_type.id,
        NOW()
    FROM 
        outlet_db.outlet_status_type
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                outlet_db.outlet_type
            WHERE
                name = 'ShareMyVision'
        )
        AND
        outlet_db.outlet_status_type.name = 'ACTIVE'
;

-- Outlet Type: Facebook
SHOW WARNINGS;
INSERT INTO 
    outlet_db.outlet_type
	(
        name,
        prefix,
        description,
        status_id,
        updated_on
	) 
    SELECT 
    	'Facebook',
    	'FB',
    	'Reference to Facebook which is the largest social network.',
        outlet_db.outlet_status_type.id,
    	NOW()
    FROM 
        outlet_db.outlet_status_type
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                outlet_db.outlet_type
            WHERE
                name = 'Facebook'
        )
        AND
        outlet_db.outlet_status_type.name = 'ACTIVE'
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
