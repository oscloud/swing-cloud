package com.cloudfoundry.vmc;

public interface CloudConstants {
    
	public interface system {
	    public static final String PWD = "i1l2o3v4";
		public static final String TEMP = "temp";
		public static final String TOKEN = "token";
		public static final String USER = "user";
		public static final String TIPS = "tips";
		public static final String TAG_LIST = "tag_list";
		public static final String TAG_BEAN = "tag_bean";
		public static final String PAGE_BEAN = "page_bean";
		public static final String URL_PAGE_SYMBOL = "p_";
		public static final String UID = "uid";
		public static final String ID = "id";
		public static final String DATE = "date";
		public static final String PAGE_START = "start";
		public static final String PAGE_SIZE = "size";
		public static final Integer PAGE_SIZE2 = 11;
	}
	
	public interface session {
	    public static final String IS_LOGIN = "isLogin";
		public static final String RE_LOGIN = "reLogin";
		public static final String EXPIRY = "expiry";
		public static final String USER = "user";
		public static final String LOGS = "logs";
	}
	
	public interface status {
		public static final String NORMAL = "NORMAL";
		public static final String WARN = "WARN";
		public static final String FAILED = "FAILED";
		public static final String SUCCESS = "SUCCESS";
	}

	public interface conf {
		public final String DB_INI = "/db.ini";
	    public final String IMG_PATH = "/com/cloudfoundry/vmc/swing/img/";
		public final String CONF_PATH = "/conf/jdbc.properties";
		public final String SQL_PATH = "/conf/sql.properties";
	}
	
	public interface icon {
	    public final String LOGIN = "login.jpg";
		public final String MAIN = "main.jpg";
		public final String DESKTOP = "icon_desktop.jpg";
		public final String LOADING = "icon_loading.gif";
		public final String QUIT = "icon_quit.gif";
	}
}
