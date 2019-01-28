package org.lyg.stable;
public interface StableData {
	//DB
	public static final String DB_BASE_NAME = "baseName";
	//LOGIN
	public static final String LOGIN_TOKEN = "token";
	public static final String LOGIN_EMAIL = "email";
	public static final String LOGIN_AUTH = "auth";
	//STRING
	public static final String STRING_EMPTY = "";
	public static final String STRING_QUATE = ".";
	public static final String STRING_JUNCTION = "&";
	//NUMBER
	public static final int INT_ZERO = 0;
	public static final int INT_ONE = 1;
	public static final int INT_TWO = 2;
	public static final int INT_THREE = 3;
	public static final int INT_FOUR = 4;
	public static final int INT_FIVE = 5;
	public static final int INT_SIX = 6;
	//REST
	public static final String REST_GET_DB_CATEGORY = "/getDBCategory";
	public static final String REST_GET_ALL_DB_CATEGORY = "/getAllDBCategory";
	//SLEEPERS
	public static final int SLEEPERS_RANGE = 1500;
	//TCP
	public static final String TCP_PORT = "port";
	public static final String STRING_SPACE = " ";
	public static final String STRING_SLASH_QUESTION = "\\?";
	//MATH
	public static final String MATH_EQUAL = "=";
	//HTTP 
	public static final int HTTP_500 = 500;
	public static final int HTTP_400 = 400;
	public static final int HTTP_200 = 200;
	public static final int HTTP_404 = 404;
	public static final int HTTP_300 = 300;
	//CHARSET
	public static final String CHARSET_UTF8 = "UTF-8";
	//FILE FORMAT
	public static final String FILE_EOT = ".eot";
	public static final String FILE_SVG = ".svg";
	public static final String FILE_OTF = ".otf";
	public static final String FILE_WOFF = ".woff";
	public static final String FILE_WOFF2 = ".woff2";
	public static final String FILE_TTF = ".ttf";
	public static final String FILE_PNG = ".png";
	public static final String FILE_JPG = ".jpg";
	public static final String FILE_JPEG = ".jpeg";
	public static final String FILE_WAV = ".wav";
	public static final String FILE_GIF = ".gif";
	public static final String FILE_JS = ".js";
	public static final String FILE_CSS = ".css";
	public static final String FILE_HTML = ".html";	
	//FILE Stream
	public static final String STREAM_BUFFER = "buffer";	
	public static final String STREAM_BYTES = "bytes";	
	public static final String STREAM_BYTES_BUFFER = "bytesBuffer";	
	public static final String STREAM_REST = "rest";	
	//HTTP HEADER
	public static final String HEADER_CONTENT_TYPE_PNG = "Content-Type: image/png \n\n";	
	public static final String HEADER_CONTENT_TYPE_JPEG = "Content-Type: image/jpeg \n\n";	
	public static final String HEADER_CONTENT_TYPE_JPG = "Content-Type: image/jpg \n\n";	
	public static final String HEADER_CONTENT_TYPE_GIF = "Content-Type: image/gif \n\n";	
	public static final String HEADER_CONTENT_TYPE_CSS = "Content-Type: text/css \n\n";	
	public static final String HEADER_CONTENT_TYPE_HTML = "Content-Type: text/html \n\n";	
	public static final String HEADER_CONTENT_TYPE_WAV = "Content-Type: audio/x-wav \n\n";	
	public static final String HEADER_CONTENT_TYPE_FONT_WOFF = "Content-Type: image/font-woff \n\n";	
	public static final String HEADER_CONTENT_TYPE_JS = "content-type: text/javascript; charset:UTF-8 \n\n";
	//REST PATH
	public static final String REST_PATH_SELECT = "/select";	
	public static final String REST_PATH_SETDB = "/setDB";	
	public static final String REST_PATH_INSERT = "/insert";	
	public static final String REST_PATH_DELETE = "/delete";	
	public static final String REST_PATH_UPDATE = "/update";	
	public static final String REST_PATH_DB_CATEGORY = "DBCategory";	
	public static final String REST_PATH_EXEC_DETA_PLSQL = "/execDetaPLSQL";	
	public static final String REST_PATH_LOGIN = "/login";	
	public static final String REST_PATH_FIND = "/find";	
	public static final String REST_PATH_LOGOUT = "/logout";	
	public static final String REST_PATH_REGISTER = "/register";	
	public static final String REST_PATH_CHANGE = "/change";	
	public static final String REST_PATH_CHECK_STATUS = "/checkStatus";	
}
