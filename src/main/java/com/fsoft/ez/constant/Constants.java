package com.fsoft.ez.constant;

public final class Constants {
	/** role ADMIN group */
	public static final int GROUP_ADMIN = 1;

	/** role MEMBER group */
	public static final int GROUP_MEMBER = 0;

	/** role ADMIN */
	public static final String ROLE_ADMIN = "super_admin";

	/** role USER */
	public static final String ROLE_USER = "user";

	public static final String APPROVER = "approver";

	public static final String DEFAULT_BANNER_GROUP = "/news/file/group_thumbnail/banner-default.png";

	public static final String DEFAULT_BANNER_USER = "/news/file/user-image/default-cover-person.png";
	/** Message success */
	public static final String SUCCESS_MSG = "OK";
	public static final Integer REACTION_LIKE = 1;
	public static final Integer REACTION_UNLIKE = 0;

	public static final Integer ACTION_VOTE = 1;
	public static final Integer ACTION_UNVOTE = 0;

	// 0: chờ duyệt, 1: đã duyệt, 2: từ chối 3: nhap
	public static final int NEWS_STATUS_WATTING = 0;
	public static final int NEWS_STATUS_APPROVED = 1;
	public static final int NEWS_STATUS_REJECT = 2;
	public static final int NEWS_DRAF = 3;

	/**
	 * wall
	 */
	public static final String PERSIONAL = "PERSIONAL";
	public static final String COMPANY = "COMPANY";
	public static final String GROUP = "GROUP";
	public static final String NEWSFEED = "NEWSFEED";

	/**
	 * news type normal
	 */
	public static final Integer NEWS_TYPE_NORMAL = 0;

	/**
	 * news type notification
	 */
	public static final Integer NEWS_TYPE_NOTIFICATION = 1;

	/**
	 * news type happy birthday greetings
	 */
	public static final Integer NEWS_TYPE_HAPPY_BIRTHDAY = 2;

	/**
	 * date format MM/dd/yyyy
	 */
	public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

	public static final String VALIDATE_CATEGORY = " DANH MUC DA TON TAI BAI VIET! ";

	public static final String VALIDATE_CATEGORY_REGEX = " TEN DANH MUC KHONG DUOC CHUA KY TU DAC BIET ";

	public static final String VALIDATE_CATEGORY_EXXISTED = " DANH MUC DA TON TAI!";

	public static final String STATUS_FORBIDDEN = " KHONG DUOC CAP QUYEN ";
	/**
	 * Message code
	 */
	public static final String MSG_001 = "MSG_001";
	public static final String MSG_002 = "MSG_002";
	public static final String MSG_003 = "MSG_003";
	public static final String MSG_004 = "MSG_004";
	public static final String MSG_005 = "MSG_005";
	public static final String MSG_006 = "MSG_006";
	public static final String MSG_007 = "MSG_007";
	public static final String MSG_008 = "MSG_008";
	public static final String MSG_009 = "MSG_009";
	public static final String MSG_010 = "MSG_010";
	public static final String MSG_011 = "MSG_011";
	public static final String MSG_012 = "MSG_012";
	public static final String MSG_013 = "MSG_013";

	/**
	 * Regex image-video
	 */
	public static final String REGEX_MEDIA = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.jpg|.jpeg|.png|.bmp|.gif|.jfif|.avi|.mov|.mp4|.flv|.3gp|.wmv)$";

	/**
	 * Regex document attachment
	 */
	public static final String REGEX_DOCUMENT = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.doc|.docx|.txt|.ppt|.pptx|.xls|.xlsx|.pdf)$";

	/**
	 * Regex image
	 */
	public static final String REGEX_IMAGES = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.jpg|.jpeg|.png)$";

	/**
	 * notifications form
	 *
	 */
	public static final Integer NEWS_GROUP_APPROVED = 7;
	public static final Integer NEW_NEWS_GROUP = 8;
	public static final Integer NEW_NEWS_COMPANY = 3;
	public static final Integer NEWS_APPROVED_OR_REJECT = 2;
	public static final Integer NEWS_APPROVE = 0;
	public static final Integer NEWS_UNAPPROVE_GROUP = 6;
	public static final Integer NEW_MEMBER_JOIN_GROUP = 5;
	public static final Integer HAPPYBIRTHDAY_TODAY = 9;
	public static final Integer HAPPYBIRTHDAY = 10;

}
