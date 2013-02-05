package com.smv.common;

public class Constant {
	//OK status
	public final static int OK = 0;
	public final static String OK_MSG = "Okay";
	
	//Proxies
	public static final String ADMIN_PROXY     = "adminProxy";
	public static final String CATALOG_PROXY   = "catalogProxy";
	public static final String FILE_PROXY      = "fileProxy";
	public static final String IMAGE_PROXY     = "imageProxy";
	public static final String MAILER_PROXY    = "mailerProxy";
	public static final String OUTLET_PROXY    = "outletProxy";
	public static final String PAYMENT_PROXY   = "paymentProxy";
	public static final String USER_PROXY      = "userProxy";
	
	//for file service
	public final static String SIGN_UPLOAD_URL                   = "suu";
	public final static String SIGN_INTERNAL_UPLOAD_URL          = "siuu";
	public final static String SIGN_DOWNLOAD_URL                 = "sdu";
	public final static String SIGN_DELETE_URL                   = "sddu";
	public final static String SIGN_FILE_DETAIL_URL              = "sfdu";
	
	public final static String CREATE_NEW_EVENT                  = "cne";
	public final static String UPSERT_EVENT                      = "upsert_event";
	
	//for User Service
	public final static String REGISTER                 = "register";
	public final static String LOGIN                    = "login";
	public final static String USER                     = "user";
	public final static String SESSION                  = "session";
	
	
	//for Image Service
	public final static String THUMBNAIL_WIDTH_PREFIX    = "width_";
	public final static String THUMBNAIL_HEIGHT_PREFIX   = "height_";
	public final static String NUM_THUMBNAILS_FOR_WIDTH  = "num_thumbnails_for_width";
	public final static String NUM_THUMBNAILS_FOR_HEIGHT = "num_thumbnails_for_height";

	
	//object identification
	public final static String EVENT               = "event";
	public final static String ACTION              = "action";
	public final static String EVENT_ID            = "eventId";
	public final static String TITLE               = "title";
	public final static String DESCRIPTION         = "description";
	public final static String UID                 = "uid";
	public final static String AID                 = "aid";
	public final static String ITEM                = "item";
	
	
	public final static String LOGINREQUEST        = "LoginRequest";
	
	//HTTP HEADER
	public final static String  ASYNC_REQUEST                = "ASYNC_REQUEST";
	public final static String  HTTP_ASYNC_REQUEST           = "HTTP_ASYNC_REQUEST";
	public final static String  CONTENT_TYPE                 = "CONTENT_TYPE";
	public final static String  CONTENT_LENGTH               = "CONTENT_LENGTH";
	public final static String  REQUEST_METHOD               = "REQUEST_METHOD";
	public final static String  SCRIPT_NAME                  = "SCRIPT_NAME";
	public final static String  REQUEST_URI                  = "REQUEST_URI";
	public final static String  PATH_INFO                    = "PATH_INFO";
	public final static String  QUERY_STRING                 = "QUERY_STRING";
	public final static String  SERVER_NAME                  = "SERVER_NAME";
	public final static String  REMOTE_HOST                  = "REMOTE_HOST";
	public final static String  REMOTE_ADDR                  = "REMOTE_ADDR";
	public final static String  REMOTE_USER                  = "REMOTE_USER";
	public final static String  SERVER_PORT                  = "SERVER_PORT";
	public final static String  HTTP_USER_AGENT              = "HTTP_USER_AGENT";
	public final static String  HTTP_ACCEPT                  = "HTTP_ACCEPT";
	public final static String  HTTP_ACCEPT_LANGUAGE         = "HTTP_ACCEPT_LANGUAGE";
	public final static String  HTTP_ACCEPT_ENCODING         = "HTTP_ACCEPT_ENCODING";
	public final static String  HTTP_CACHE_CONTROL           = "HTTP_CACHE_CONTROL";
	public final static String  RAILS_RELATIVE_URL_ROOT      = "RAILS_RELATIVE_URL_ROOT";
	
	//TriDesEncryption key
	public final static String TRIPLE_DES_KEY     = "qG7NihDgzSOFOyrE5YkarctG6ewpZGHm";
	
	
	//Num threads in async threads
	public final static int NUM_ASYNC_THREADS     = 5;
	
	//HTML's form prefix
	public final static String HTML_FORM_PREFIX   = "prefix";
	
	//Status values
	public final static String ACTIVE_STATUS = "active";
	
	//URI
	public final static String FILE_SERVICE_REQUEST = "/web/mo/filerequest";
	public final static String USER_SERVICE_REQUEST = "/web/mo/userrequest";
	
	
	//Constant Strings
	public final static String TRUE              = "true";
	public final static String FALSe             = "false";

	//Status
	public final static String NEW_STATUS        = "new";
	
	
	//Folder image size
	public final static int FOLDER_IMAGE_MAX_WIDTH   = 130;
	public final static int FOLDER_IMAGE_MAX_HEIGHT  = 110;
	
	//Admin Email
	public final static String SMV_EMAIL_ADDRESS  = "smvEmailAddress";
	public final static String SMV_EMAIL_NAME     = "smvEmailName";
	public final static String SMV_HOST_NAME     = "smvHostName";
}
