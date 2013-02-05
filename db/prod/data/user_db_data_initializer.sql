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
-- INSERTION: USER_STATUS TABLE
-- -----------------------------------------------------
-- -----------------------------------------------------
-- User_Status: ActivationPending
SHOW WARNINGS;
INSERT INTO 
    user_db.user_status
		(
        name,
        updated_on
		) 
    SELECT 
        'ActivationPending',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'ActivationPending'
        )
;

-- User_Status: PaymentPending
SHOW WARNINGS;
INSERT INTO 
	user_db.user_status
	(
		name,
		updated_on
	) 
    SELECT 
        'PaymentPending',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'PaymentPending'
        )
;
    
-- User_Status: Activated
SHOW WARNINGS;
INSERT INTO 
	user_db.user_status
	(
		name,
		updated_on
	) 
    SELECT 
        'Activated',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'Activated'
        )
;
    
-- User_Status: Inactive
SHOW WARNINGS;
INSERT INTO 
	user_db.user_status
	(
		name,
		updated_on
	) 
    SELECT 
        'Inactive',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'Inactive'
        )
;
    
-- User_Status: Deleted
SHOW WARNINGS;
INSERT INTO 
	user_db.user_status
	(
		name,
		updated_on
	) 
    SELECT 
        'Deleted',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'Deleted'
        )
;

-- User_Status: Suspended (e.g. Payment Failed)
SHOW WARNINGS;
INSERT INTO 
	user_db.user_status
	(
		name,
		updated_on
	) 
    SELECT 
        'Suspended',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'Suspended'
        )
;


-- User_Status: Reset (Password Regenerated)
SHOW WARNINGS;
INSERT INTO 
	user_db.user_status
	(
		name,
		updated_on
	) 
    SELECT 
        'Reset',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'Reset'
        )
;

-- User_Status: Unknown (Invalid State)
SHOW WARNINGS;
INSERT INTO 
	user_db.user_status
	(
		name,
		updated_on
	) 
    SELECT 
        'Unknown',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_status
            WHERE
                name = 'Unknown'
        )
;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- INSERTION: USER_TYPE TABLE 
-- -----------------------------------------------------
-- -----------------------------------------------------
-- User_Type: Free
SHOW WARNINGS;
INSERT INTO 
    user_db.user_type
		(
        name,
        updated_on
		) 
    SELECT 
        'Free',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_type
            WHERE
                name = 'Free'
        )
;

-- User_Type: Corporate
SHOW WARNINGS;
INSERT INTO 
    user_db.user_type
		(
        name,
        updated_on
		) 
    SELECT 
        'Corporate',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_type
            WHERE
                name = 'Corporate'
        )
;

-- User_Type: SmallBusiness
SHOW WARNINGS;
INSERT INTO 
    user_db.user_type
		(
        name,
        updated_on
		) 
    SELECT 
        'SmallBusiness',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_type
            WHERE
                name = 'SmallBusiness'
        )
;

-- User_Type: SmallOfficeHomeOffice
SHOW WARNINGS;
INSERT INTO 
    user_db.user_type
		(
        name,
        updated_on
		) 
    SELECT 
        'SmallOfficeHomeOffice',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_type
            WHERE
                name = 'SmallOfficeHomeOffice'
        )
;

-- User_Type: SinglePayingUser
SHOW WARNINGS;
INSERT INTO 
    user_db.user_type
		(
        name,
        updated_on
		) 
    SELECT 
        'SinglePayingUser',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.user_type
            WHERE
                name = 'SinglePayingUser'
        )
;


-- -----------------------------------------------------
-- -----------------------------------------------------
-- INSERTION: login_channel_type TABLE
-- -----------------------------------------------------
-- -----------------------------------------------------

-- Social Network Type: ShareMyVision
SHOW WARNINGS;
INSERT INTO 
    user_db.login_channel_type
	(
        name,
        prefix,
        description,
  		updated_on
	) 
    SELECT 
    	'ShareMyVision',
    	'SMV',
    	'ShareMyVision implementation of User Engine Module',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.login_channel_type
            WHERE
                name = 'ShareMyVision'
        )
;

-- Social Network Type: Facebook
SHOW WARNINGS;
INSERT INTO 
    user_db.login_channel_type
	(
        name,
        prefix,
        description,
  		updated_on
	) 
    SELECT 
    	'Facebook',
    	'FB',
    	'Reference to Facebook vast network of users',
        NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                user_db.login_channel_type
            WHERE
                name = 'Facebook'
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
