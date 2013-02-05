package com.smv.common.exception;

public class SmvErrorCode {
	
	//1-1000 for System Service	
	public final static int SYSTEM_UNKNOWN_EXCEPTION         = 1;
	public final static String SYSTEM_UNKNOWN_EXCEPTION_MSG  = "Unknown exception";
	
	public final static int SYSTEM_ILLEGALL_INPUT            = 2;
	public final static String SYSTEM_ILLEGALL_INPUT_MSG     = "Illegal Input(s)";
	
	public final static int SYSTEM_DATABASE_ISSUE_EXCEPTION        = 3;
	public final static String SYSTEM_DATABASE_ISSUE_EXCEPTION_MSG = "There is an db issue occured";
	
	
	//1001-2000 for FileService
	public final static int FS_INVALID_REQUEST           = 1001;
	public final static String FS_INVALID_REQUEST_MSG    = "Invalid request";
	
	public final static int FS_NULL_EVENT_INPUT          = 1002;
	public final static String FS_NULL_EVENT_INPUT_MSG   = "Input EventDTO must not be NULL";
	
	public final static int FS_NULL_USER_ID_INPUT        = 1003;
	public final static String FS_NULL_USER_ID_INPUT_MSG = "Input must provide a user id";
	
	public final static int FS_NULL_EVENT_TITLE_INPUT    = 1004;
	public final static String FS_NULL_EVENT_TITLE_INPUT_MSG = "Input must provide an event title";
	
	public final static int FS_NO_ITEM_IN_EVENT_INPUT    = 1005;
	public final static String FS_NO_ITEM_IN_EVENT_INPUT_MSG = "Must provide at least an item";
	
	public final static int FS_AN_ITEM_MISSING_PATH      = 1006;
	public final static String FS_AN_ITEM_MISSING_PATH_MSG = "All items need to have a path";
	
	public final static int FS_MISSING_EVENT_CODE        = 1007;
	public final static String FS_MISSING_EVENT_CODE_MSG = "Missing event code in EventDTO";
	
	public final static int FS_AN_ITEM_MISSING_FILENAME = 1008;
	public final static String FS_AN_ITEM_MISSING_FILENAME_MSG = "Item missing filename";
	
	public final static int FS_AN_ITEM_MISSING_FILESIZE = 1009;
	public final static String FS_AN_ITEM_MISSING_FILESIZE_MSG = "Item missing filesize";
	
	public final static int FS_NULL_EVENT_ID_EXCEPTION   = 1010;
	public final static String  FS_NULL_EVENT_ID_EXCEPTION_MSG = "Null event id";
	
	public final static int FS_NULL_USER_EXCEPTION = 1011;
	public final static String FS_NULL_USER_EXCEPTION_MSG = "Null user input";
	
	public final static int FS_ITEM_ID_MISSING_EXCEPTION = 1012;
	public final static String FS_ITEM_ID_MISSING_EXCEPTION_MSG = "No item id";
	
	public final static int FS_NULL_ITEM_EXCEPTION = 1023;
	public final static String FS_NULL_ITEM_EXCEPTION_MSG = "Null item in input";
	
	
	//3000-4000 for UserService
	public final static int USER_SERVICE_EXCEPTION                   = 3000;
	public final static String USER_SERVICE_EXCEPTION_MSG            = "Exception occurred. ";
	
	public final static int USER_SERVICE_ILLEGAL_ARGUMENT            = 3001;
	public final static String USER_SERVICE_ILLEGAL_ARGUMENT_MSG     = "Arguments/parameters are invalid.";
	
	public final static int USER_SERVICE_NULL_USER_PARAMETER         = 3010;
	public final static String USER_SERVICE_NULL_USER_PARAMETER_MSG  = "User parameter is null.";
	
	public final static int USER_SERVICE_NULL_USER_EMAIL             = 3011;
	public final static String USER_SERVICE_NULL_USER_EMAIL_MSG      = "User Email is null, empty, or blank.";

	public final static int USER_SERVICE_NULL_USER_PASSWORD          = 3012;
	public final static String USER_SERVICE_NULL_USER_PASSWORD_MSG   = "User Password is null, empty, or blank.";

	public final static int USER_SERVICE_NONE_EXISTENT_USER          = 3013;
	public final static String USER_SERVICE_NONE_EXISTENT_USER_MSG   = "User does not exist.";

	public final static int USER_SERVICE_USER_ALREADY_EXISTS         = 3014;
	public final static String USER_SERVICE_USER_ALREADY_EXISTS_MSG  = "User already exists.";

	public final static int USER_SERVICE_NULL_BLANK_EMPTY_ACTIVATION_CODE = 3015;
	public final static String USER_SERVICE_NULL_BLANK_EMPTY_ACTIVATION_CODE_MSG = "Activation code is null, empty, or blank.";

	public final static int USER_SERVICE_NONE_EXISTENT_USER_WITH_SPECIFIED_ACTIVATION_CODE = 3016;
	public final static String USER_SERVICE_NONE_EXISTENT_USER_WITH_SPECIFIED_ACTIVATION_CODE_MSG = "User does not exist with specified activation code.";

	public final static int USER_SERVICE_NULL_USER_PASSWORDS_DO_NOT_MATCH = 3017;
	public final static String USER_SERVICE_NULL_USER_PASSWORDS_DO_NOT_MATCH_MSG = "Provided User Password does not match User Password in the system.";

	public final static int USER_SERVICE_NULL_USER_PASSWORD_RESET_CODES_DO_NOT_MATCH = 3018;
	public final static String USER_SERVICE_NULL_USER_PASSWORD_RESET_CODES_DO_NOT_MATCH_MSG = "Provided User Password Reset Code does not match User Password Reset Code in the system.";

	public final static int USER_SERVICE_NULL_USER_ACTIVATION_CODES_DO_NOT_MATCH = 3019;
	public final static String USER_SERVICE_NULL_USER_ACTIVATION_CODES_DO_NOT_MATCH_MSG = "Provided User Activation Code does not match User Activation Code in the system.";

	public final static int USER_SERVICE_NULL_BLANK_EMPTY_FIRST_NAME = 3020;
	public final static String USER_SERVICE_NULL_BLANK_EMPTY_FIRST_NAME_MSG = "User First Name is null, empty, or blank.";

	public final static int USER_SERVICE_NULL_BLANK_EMPTY_LAST_NAME = 3021;
	public final static String USER_SERVICE_NULL_BLANK_EMPTY_LAST_NAME_MSG = "User Last Name is null, empty, or blank.";

	public final static int USER_SERVICE_INVALID_USER_EMAIL = 3022;
	public final static String USER_SERVICE_INVALID_USER_EMAIL_MSG = "User Email is invalid.";

	public final static int USER_SERVICE_INVALID_FIRST_NAME = 3023;
	public final static String USER_SERVICE_INVALID_FIRST_NAME_MSG = "User First Name is invalid.";

	public final static int USER_SERVICE_INVALID_LAST_NAME = 3024;
	public final static String USER_SERVICE_INVALID_LAST_NAME_MSG = "User Last Name is invalid.";

	public final static int USER_SERVICE_NULL_BLANK_EMPTY_NAME = 3025;
	public final static String USER_SERVICE_NULL_BLANK_EMPTY_NAME_MSG = "Name is null, empty, or blank.";

	public final static int USER_SERVICE_INVALID_NAME = 3026;
	public final static String USER_SERVICE_INVALID_NAME_MSG = "Name is invalid.";

	public final static int USER_SERVICE_INVALID_PASSWORD = 3027;
	public final static String USER_SERVICE_INVALID_PASSWORD_MSG = "Password is invalid.";

	public final static int USER_SERVICE_NONE_EXISTENT_USER_WITH_SPECIFIED_ID = 3028;
	public final static String USER_SERVICE_NONE_EXISTENT_USER_WITH_SPECIFIED_ID_MSG = "User does not exist with specified id.";


	//6000-7000 for PaymentService
	
	//8000-10000 for CatalogService
	public final static int CATALOG_SERVICE_EXCEPTION = 8000;
	public final static String CATALOG_SERVICE_EXCEPTION_MSG = "Exception occurred. ";
	
	public final static int CATALOG_SERVICE_ILLEGAL_ARGUMENT = 8001;
	public final static String CATALOG_SERVICE_ILLEGAL_ARGUMENT_MSG = "Arguments/parameters are invalid.";
	
	
	
	//12000-14000 for MailerService
	public final static int MAILER_SERVICE_EXCEPTION = 12000;
	public final static String MAILER_SERVICE_EXCEPTION_MSG = "Exception occurred. ";
	
	public final static int MAILER_SERVICE_ILLEGAL_ARGUMENT = 12001;
	public final static String MAILER_SERVICE_ILLEGAL_ARGUMENT_MSG = "Arguments/parameters are invalid.";
	
	public final static int MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT = 12002;
	public final static String MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT_MSG = "The event code was not found. ";

	
	
	
	//15000-17000 for Image Processing Service
	public final static int IP_NULL_EVENT_ARGUMENT_EXCEPTION           = 15000;
	public final static String IP_NULL_EVENT_ARGUMENT_EXCEPTION_MSG    = "Event argument is null!";
	
	public final static int IP_NULL_POLICIES_IN_EVENT_EXCEPTION        = 15001;
	public final static String IP_NULL_POLICIES_IN_EVENT_EXCEPTION_MSG = "Need to have policies in event.";
	
	public final static int IP_NULL_ITEM_IN_EVENT_EXCEPTION            = 15002;
	public final static String IP_NULL_ITEM_IN_EVENT_EXCEPTION_MSG     = "Need to have at least one item in event!";
	
	public final static int IP_NULL_URL_INPUT_EXCEPTION                = 15003;
	public final static String IP_NULL_URL_INPUT_EXCEPTION_MSG         = "Need to supply a s3 url";
	
	public final static int IP_NEGATIVE_OR_ZERO_IMAGE_WIDTH_EXCEPTION  = 15004;
	public final static String IP_NEGATIVE_OR_ZERO_IMAGE_WIDTH_EXCEPTION_MSG = "Image cannot have a non-positive width ";
	
	public final static int IP_NULL_USERID_IN_EVENT_EXCEPTION          = 15005;
	public final static String IP_NULL_USERID_IN_EVENT_EXCEPTION_MSG   = "Event needs to include a valid userId";
	
	public final static int IP_NON_POSITIVE_IMAGE_DIMENSION_EXCEPTION  = 15006;
	public final static String IP_NON_POSITIVE_IMAGE_DIMENSION_EXCEPTION_MSG = "Image dimension must be positive";
	
	public final static int IP_ITEM_MUST_INCLUDE_URL_VALUE_EXCEPTION   = 15007;
	public final static String IP_ITEM_MUST_INCLUDE_URL_VALUE_EXCEPTION_MSG = "Item must include an url value";
	
	public final static int IP_POLICY_MISSING_ENTRIES_EXCEPTION        = 15008;
	public final static String IP_POLICY_MISSING_ENTRIES_EXCEPTION_MSG = "Policy is missing entries";
	
	
	//18000-20000 for OutletService
	public final static int OUTLET_SERVICE_EXCEPTION = 18000;
	public final static String OUTLET_SERVICE_EXCEPTION_MSG = "Exception occurred. ";
	
	public final static int OUTLET_SERVICE_ILLEGAL_ARGUMENT = 18001;
	public final static String OUTLET_SERVICE_ILLEGAL_ARGUMENT_MSG = "Arguments/parameters are invalid.";
	
	public final static int OUTLET_SERVICE_OUTLET_TYPE_NOT_FOUND_EXCEPTION = 18002;
	public final static String OUTLET_SERVICE_OUTLET_TYPE_NOT_FOUND_EXCEPTION_MSG = "Outlet not found. ";
	
	public final static int OUTLET_SERVICE_USER_NOT_FOUND_EXCEPTION = 18003;
	public final static String OUTLET_SERVICE_USER_NOT_FOUND_EXCEPTION_MSG = "User not found. ";
	
	public final static int OUTLET_SERVICE_OUTLET_STATUS_NOT_FOUND_EXCEPTION = 18004;
	public final static String OUTLET_SERVICE_OUTLET_STATUS_NOT_FOUND_EXCEPTION_MSG = "Outlet status not found. ";
	
}
