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
-- INSERTION: USER_OUTLET TABLE
-- -----------------------------------------------------
-- -----------------------------------------------------

-- -----------------------------------------------------
-- USER_OUTLET DATA
-- -----------------------------------------------------

-- SMV Outlet Type with 'ACTIVE' status for User # 1 (junk@gmail.com)
SHOW WARNINGS;
INSERT INTO 
	outlet_db.user_outlet 
	(
		name,
		description,
		outlet_id,
		user_id,
		status_id,
  		updated_on
	) 
    SELECT 
    	'Active ShareMyVision Outlet User 1',
    	'Where User # 1 is junk@gmail.com and password is smv123',
    	1,
    	1,
    	1,
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                outlet_db.user_outlet
            WHERE
            	outlet_id = 1
            	AND
            	user_id = 1 
        )
;

-- Facebook Outlet Type with 'ACTIVE' status for User # 1 (junk@gmail.com)
SHOW WARNINGS;
INSERT INTO 
	outlet_db.user_outlet 
	(
		name,
		description,
		outlet_id,
		user_id,
		status_id,
  		updated_on
	) 
    SELECT 
    	'Inactive Facebook Outlet User 1',
    	'Where User # 1 is junk@gmail.com and password is smv123',
    	2,
    	1,
    	2,
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                outlet_db.user_outlet
            WHERE
            	outlet_id = 2
            	AND
            	user_id = 1 
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
