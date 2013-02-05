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
-- ADDRESS DATA
-- -----------------------------------------------------
-- RESIDENT ADDRESS
SHOW WARNINGS;
INSERT INTO 
	user_db.address
	(
		id,
  		street,
  		city,
  		state,
  		country,
  		zip_code,
  		latitude,
  		longitude,
		updated_on
	) 
VALUES
    (
    	1,
    	'My RESIDENT Street Address',
    	'San Jose',
    	'California',
    	'United States',
    	'95131',
    	10.0, 
    	55.0,
    	NOW()
   	)
;
-- SHIPPING ADDRESS
SHOW WARNINGS;
INSERT INTO 
	user_db.address
	(
		id,
  		street,
  		city,
  		state,
  		country,
  		zip_code,
  		latitude,
  		longitude,
		updated_on
	) 
VALUES
    (
    	2,
    	'My SHIPPING Street Address',
    	'San Jose',
    	'California',
    	'United States',
    	'95131',
    	22.0, 
    	66.0,
    	NOW()
   	)
;
-- BILLING ADDRESS
SHOW WARNINGS;
INSERT INTO 
	user_db.address
	(
		id,
  		street,
  		city,
  		state,
  		country,
  		zip_code,
  		latitude,
  		longitude,
		updated_on
	) 
VALUES
    (
    	3,
    	'My BILLING Street Address',
    	'San Jose',
    	'California',
    	'United States',
    	'95131',
    	33.0, 
    	77.0,
    	NOW()
   	)
;

-- -----------------------------------------------------
-- CONTACT DATA
-- -----------------------------------------------------
SHOW WARNINGS;
INSERT INTO 
	user_db.contact
	(
  		id,
  		first_name,
  		last_name,
  		additional_email,
  		resident_address_id,
  		shipping_address_id,
  		billing_address_id,
  		work_phone,
  		mobile_phone,
  		home_phone,
  		aim,
  		yim,
  		skype,
  		facebook,
  		twitter,
		updated_on
	) 
VALUES
    (
    	1,
    	'My First name',
    	'My Last name',
    	'address@junk.com',
    	1,
    	2, 
    	3,
  		'6503901716',
  		'408-215-8179',
  		'(408) 646-0123',
  		'me@aol.com',
  		'me@yahoo.com',
  		'me@skype.com',
  		'me@facebook.com',
  		'me@yahoo.com',
    	NOW()
   	)
;

-- -----------------------------------------------------
-- ACCOUNT DATA
-- -----------------------------------------------------
SHOW WARNINGS;
INSERT INTO 
	user_db.account
	(
		id,
		name,
		description, 
		type,
		status,
		contact_id,
		updated_on
	) 
VALUES
    (
    	1,
    	'My account NAME',
    	'My account DESCRIPTION',
    	'My account TYPE',
    	'My account STATUS',
    	1,
    	NOW()
   	)
;

-- -----------------------------------------------------
-- TIME_ZONE DATA
-- -----------------------------------------------------
SHOW WARNINGS;
INSERT INTO 
	user_db.time_zone
	(
		id,
		time_zone,
		label,
		updated_on
	) 
VALUES
    (
    	1,
    	'Pacific Daylight Savings Time',
    	'Los Angeles/United States',
    	NOW()
   	)
;


-- -----------------------------------------------------
-- USER_EXTRA_INFO DATA
-- -----------------------------------------------------
SHOW WARNINGS;
INSERT INTO 
	user_db.user_extra_info
	(
		id,
		industry ,
		company ,
		company_size ,
		zip_code ,
		time_zone_id ,
		title ,
		job_title ,
		mobile_device_model ,
		mobile_manufacturer ,
		updated_on
	) 
VALUES
    (
    	1,
    	'My industry',
    	'My company',
    	'My companySize',
    	'My zipCode',
    	1,
    	'My title',
    	'My jobTitle',
    	'My mobileDeviceModel',
    	'My mobileManufacturer',
    	NOW()
   	)
;


-- -----------------------------------------------------
-- ROLE DATA
-- -----------------------------------------------------
SHOW WARNINGS;
INSERT INTO 
	user_db.role
	(
		id,
		name,
		description,
		updated_on
	) 
VALUES
    (
    	1,
    	'My Role name',
    	'My Role description',
    	NOW()
   	)
;


-- -----------------------------------------------------
-- USER DATA
-- -----------------------------------------------------
SHOW WARNINGS;
INSERT INTO 
	user_db.user
	(
		display_name,
		email,
		password,
		salt,
		status_id,
		type_id,
		activation_code,
		password_reset_code,
		account_id,
		contact_id,
		user_extra_info_id,
		role_id,
		language,
		updated_on
	) 
VALUES
    (
		'My displayName',
		'junk@gmail.com',
		'35SGzM6mMwc=',
		'7gl8DsAHHP1dq0Zrpm0xogl1ue2tHBo3',
		1,
		1,
		'My activationCode',
		'My passwordResetCode',
		1,
		1,
		1,
		1,
		'US_English',
    	NOW()  -- updated_on
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
