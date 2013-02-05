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
-- EVENT CODE DATA
-- -----------------------------------------------------
-- Event Code: AccountRegistration
SHOW WARNINGS;
INSERT INTO 
	mailer_db.event_code
	(
		id,
		name,
		description,
  		updated_on
	) 
    SELECT 
    	2,
    	'AccountRegistration',
    	'User creates and registers for SMV account',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.event_code
            WHERE
                name = 'AccountRegistration'
        )
;

-- Event Code: ForgotPassword
SHOW WARNINGS;
INSERT INTO 
	mailer_db.event_code
	(
		id,
		name,
		description,
  		updated_on
	) 
    SELECT 
    	3,
    	'ForgotPassword',
    	'User forgot password and needs new Password Reset Code',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.event_code
            WHERE
                name = 'ForgotPassword'
        )
;

-- Event Code: FolderNameNotification
SHOW WARNINGS;
INSERT INTO 
	mailer_db.event_code
	(
		id,
		name,
		description,
  		updated_on
	) 
    SELECT 
    	4,
    	'FolderNameNotification',
    	'User uploads photos and is notified of their SMV location',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.event_code
            WHERE
                name = 'FolderNameNotification'
        )
;


-- -----------------------------------------------------
-- SUBJECT TEMPLATE DATA
-- -----------------------------------------------------
-- Subject Template: AccountRegistrationSubjectTemplate
SHOW WARNINGS;
INSERT INTO 
	mailer_db.subject_template
	(
		id,
		name,
		description,
		file_location,
  		updated_on
	) 
    SELECT 
		2,
		'AccountRegistrationSubjectTemplate',
		'Subject Template for AccountRegistration Event',
		'registration_subject.vm',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.subject_template
            WHERE
                name = 'AccountRegistrationSubjectTemplate'
        )
;

-- Subject Template: ForgotPasswordSubjectTemplate
SHOW WARNINGS;
INSERT INTO 
	mailer_db.subject_template
	(
		id,
		name,
		description,
		file_location,
  		updated_on
	) 
    SELECT 
		3,
		'ForgotPasswordSubjectTemplate',
		'Subject Template for ForgotPassword Event',
		'forgotPassword_subject.vm',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.subject_template
            WHERE
                name = 'ForgotPasswordSubjectTemplate'
        )
;

-- Subject Template: FolderNameNotificationSubjectTemplate
SHOW WARNINGS;
INSERT INTO 
	mailer_db.subject_template
	(
		id,
		name,
		description,
		file_location,
  		updated_on
	) 
    SELECT 
		4,
		'FolderNameNotificationSubjectTemplate',
		'Subject Template for FolderNameNotification Event',
		'folderNameNotification_subject.vm',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.subject_template
            WHERE
                name = 'FolderNameNotificationSubjectTemplate'
        )
;


-- -----------------------------------------------------
-- BODY TEMPLATE DATA
-- -----------------------------------------------------
-- Body Template: AccountRegistrationBodyTemplate
SHOW WARNINGS;
INSERT INTO 
	mailer_db.body_template
	(
		id,
		name,
		description,
		file_location,
  		updated_on
	) 
    SELECT 
		2,
		'AccountRegistrationBodyTemplate',
		'Body Template for AccountRegistration Event',
		'registration_body.vm',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.body_template
            WHERE
                name = 'AccountRegistrationBodyTemplate'
        )
;

-- Body Template: ForgotPasswordBodyTemplate
SHOW WARNINGS;
INSERT INTO 
	mailer_db.body_template
	(
		id,
		name,
		description,
		file_location,
  		updated_on
	) 
    SELECT 
		3,
		'ForgotPasswordBodyTemplate',
		'Body Template for ForgotPassword Event',
		'forgotPassword_body.vm',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.body_template
            WHERE
                name = 'ForgotPasswordBodyTemplate'
        )
;

-- Body Template: FolderNameNotificationBodyTemplate
SHOW WARNINGS;
INSERT INTO 
	mailer_db.body_template
	(
		id,
		name,
		description,
		file_location,
  		updated_on
	) 
    SELECT 
		4,
		'FolderNameNotificationBodyTemplate',
		'Body Template for FolderNameNotification Event',
		'folderNameNotification_body.vm',
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.body_template
            WHERE
                name = 'FolderNameNotificationBodyTemplate'
        )
;


-- ---------------------------------------------------------------------------
-- RELATIONSHIP DATA BETWEEN EVENT CODE, SUBJECT TEMPLATE, AND BODY TEMPLATE
-- ---------------------------------------------------------------------------
-- Event Code Template: RegistrationEventBodySubjectTemplates
SHOW WARNINGS;
INSERT INTO 
	mailer_db.event_code_template
	(
		id,
		name,
		description,
		event_code_id,
		subject_template_id,
		body_template_id,
  		updated_on
	) 
    SELECT 
    	2,
    	'RegistrationEventBodySubjectTemplates',
    	'Relationship between Event Code, and Subject and Body Templates for Registration',
    	2,
    	2,
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
                mailer_db.event_code_template
            WHERE
                name = 'RegistrationEventBodySubjectTemplates'
        )
;

-- Event Code Template: ForgotPasswordEventBodySubjectTemplates
SHOW WARNINGS;
INSERT INTO 
	mailer_db.event_code_template
	(
		id,
		name,
		description,
		event_code_id,
		subject_template_id,
		body_template_id,
  		updated_on
	) 
    SELECT 
    	3,
    	'ForgotPasswordEventBodySubjectTemplates',
    	'Relationship between Event Code, and Subject and Body Templates for Forgot Password',
    	3,
    	3,
    	3,
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.event_code_template
            WHERE
                name = 'ForgotPasswordEventBodySubjectTemplates'
        )
;

-- Event Code Template: FolderNameNotificationEventBodySubjectTemplates
SHOW WARNINGS;
INSERT INTO 
	mailer_db.event_code_template
	(
		id,
		name,
		description,
		event_code_id,
		subject_template_id,
		body_template_id,
  		updated_on
	) 
    SELECT 
    	4,
    	'FolderNameNotificationEventBodySubjectTemplates',
    	'Relationship between Event Code, and Subject and Body Templates for FolderNameNotification',
    	4,
    	4,
    	4,
    	NOW()
    FROM 
        DUAL
    WHERE 
        NOT EXISTS
        (
            SELECT
                *
            FROM 
                mailer_db.event_code_template
            WHERE
                name = 'FolderNameNotificationEventBodySubjectTemplates'
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
