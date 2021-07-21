package com.fsoft.ez.common.constants;

/**
 * 絶え間ない
 *
 * @author LamNH8
 */
public final class Constants {

    /**
     * sites constant
     *
     */
    public static final int True = 1;

    public static final class Site {

        public final static String COOKIE_HEADER = "dump";

        public final static String KURA_SECRET = "fdsfsDFGYRHYTRHR!@#@!#@13212fdsfds3";

        public final static String SALE_SECRET = "vbxcbvc!@#$@$#@3454TYUYYTdsadsadsa";
    }

    public static final class MessageType {

        public static final String ANNOUNCEMENT = "1";

        public static final String CHAT = "0";

    }

    public static final class MessageContent {

        public static final String STR_CHANGED_TO = "に変更しました。";

        public static final String STR_NAME_PROJECT = "様がプロジェクト名を";

        public static final String E0012 = "このメールアドレスは登録済みです。";

        public static final String N0011 = "新しいアカウントが作成しました。";

        public static final String N0007 =
            "データの保存が失敗しました。しばらく待ってから再度お試しください。エラーが長時間つづく場合は、お問い合わせください。";

    }

    public static final class statusMessage {

        public static final String STR_PENDING = "Pending";

        public static final String STR_INPLANNING = "InPlanning";

        public static final String STR_PROPOSALSUBMITTED = "ProposalSubmitted";

    }

    public static final String STR_CP = "様";

    public static final String SALE_COMPANY = "株式会社オープンハウス・アーキテクト";

    public static final String SUCCESS_RESPONSE = "Success";

    public static final String FAIL_RESPONSE = "Fail";

    public static final String STR_ACTION = "アクティブ";

    public static final String STR_NOT_ACTION = "未ログイン";

    public static final String STR_ONE = "1";

    public static final String STR_TEN = "10";

    public static final String EMAIL = "email";

    public static final String IDEOGRAPHIC_COMMA = "、";

    /** コンマ */
    public static final String COMMA = ",";

    /** スペース */
    public static final String SPACE = " ";

    /** スペース */
    public static final String SPACE_FULL_SIZE = "　";

    /** 空白 */
    public static final String BLANK = "";

    public static final String SOUT_STR = "-------------------------------------";

    /** 空白 */
    public static final String CLAIM_DASH_USER = "dashUser";

    public static final String CLAIM_DASH_TERMS = "dashTerms";

    public static final String CLAIM_DASH_USER_ID = "dashUserId";

    public static final String STR_SALE_LOCATIONS = "/sales-locations/";

    public static final String STR_STAFF = "/staff";

    public static final String STR_CHANNELS = "channels";

    public static final String STR_USER_CHANNELS = "user_channels";

    public static final String STR_USER = "users";

    public static final String KEY_AUTHORIZATION = "Authorization";

    public static final String STR_SYSTEMS = "systems";

    public static final String STR_DASH = "dash";

    public static final String ROLE_DEVELOPER_EMPLOYEE = "844b57128402422082cfebc1bcaa80f3";

    public static final String ROLE_DEVELOPER_ADMIN = "9d09a124283e46b2be45f6677ddf8a67";

    public static final String ROLE_SALES_BRANCH_CHIEF = "df2deac5395d4f7cba442eefda63dfc7";

    public static final String ROLE_SALES_SUPPORT = "b3b2ec111ee544e8b222aaef5fee6ec5";

    public static final String ROLE_SALES_HEADQUARTERS = "2cc764123b6d42d58f70dcbf401ddf32";

    public static final String ROLE_DESIGN = "b5a453d84ca94cdd9b491461fa75c183";

    public static final String ROLE_SALES = "f455e2f8f0ad456882b60e8c3000d524";

    public static final String ROLE_PERSONAL = "42f421d779f24d1b9f56a861ccc2c321";

    public static final String ROLE_SYSTEM_ADMIN = "a5d9cff52098466dbc9235dc069dae4a";

    public static final String DASH_DEFAULT_PASS = "Dash1234";

    public static final String DASH_DEFAULT_SERCRET = "4f9e957ebcce490483b703f252000603";

    public static final class SiteEvent {

        public static final String CONTRACT_DATE = "契約日";
        public static final String SUBMISSION_DATE = "建築確認申請提出日";
        public static final String SCHEDULED_DATE_BUILDING_CONFIRMATION = "建築確認取得予定日";
        public static final String SCHEDULED_DATE_GROUND_IMPROVEMENT = "地盤改良予定日";
        public static final String SCHEDULED_DATE_DEMOLITION_WORK = "解体予定日";
        public static final String TEMPORARY_WATER_SUPPLY = "仮設水道引込予定日";
        public static final String DEFECT_GUARANTEE_INSURANCE = "瑕疵担保保険申請日";
        public static final String CONSTRUCTION_START_DATE = "着工日";
        public static final String BAR_ARRANGEMENT_DATE = "配筋検査日";
        public static final String FOUNDATION_COMPLETION_INSPECTION = "基礎完了検査";
        public static final String TOPPING_OUT_DATE = "上棟日";
        public static final String MIDDLE_INSPECTION = "中間検査";
        public static final String SCAFFOLDING_DEMOLITION_DATE = "足場解体日";
        public static final String MOKKAN_DATE = "木完日";
        public static final String CLEANING_COMPLETION = "クリーニング完了";
        public static final String INSPECTION_COMPLETION_DATE = "完成検査日（社内）";
        public static final String CONSTRUCTION_COMPLETION_DATE = "完工日";
        public static final String EXPECTED_SETTLEMENT_DATE = "決済予定日";
        public static final String SCHEDULED_PAYMENT_DATE = "支払予定日";
        public static final String DELIVERY_DATE = "引渡日";
        public static final String PROJECT_CREATION_DATE = "案件依頼日";
        public static final String MOVE_IN_DATE = "入居日";
        public static final String AFTER_COMPLETION_DATE = "アフター完了日";

    }

    /**
     * コンストラクタ
     */
    private Constants() {

    }
}
