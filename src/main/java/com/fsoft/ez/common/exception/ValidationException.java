package com.fsoft.ez.common.exception;

/**
 * Validation例外クラス
 *
 * @author FPT.ThanhND20
 */
public class ValidationException {
//
//    /**
//     * シリアルバージョン
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDは同一のものとして管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード・エラーメッセージID
//     */
//    public ValidationException(String errorCode) {
//        super(errorCode, errorCode, null, null, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDは同一のものとして管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード・エラーメッセージID
//     * @param params    エラーメッセージパラメータ
//     */
//    public ValidationException(String errorCode, Object[] params) {
//        super(errorCode, errorCode, params, null, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDは同一のものとして管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード・エラーメッセージID
//     * @param detail    エラー詳細
//     */
//    public ValidationException(String errorCode, Object detail) {
//        super(errorCode, errorCode, null, detail, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDは同一のものとして管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード・エラーメッセージID
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, Throwable throwable) {
//        super(errorCode, errorCode, null, null, throwable);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param params    エラーメッセージパラメータ
//     * @param detail    エラー詳細
//     */
//    public ValidationException(String errorCode, Object[] params, Object detail) {
//        super(errorCode, errorCode, params, detail, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDは同一のものとして管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード・エラーメッセージID
//     * @param detail    エラー詳細
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, Object detail, Throwable throwable) {
//        super(errorCode, errorCode, null, detail, throwable);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDは同一のものとして管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード・エラーメッセージID
//     * @param params    エラーメッセージパラメータ
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, Object[] params, Throwable throwable) {
//        super(errorCode, errorCode, params, null, throwable);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDは同一のものとして管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード・エラーメッセージID
//     * @param params    エラーメッセージパラメータ
//     * @param detail    エラー詳細
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, Object[] params, Object detail, Throwable throwable) {
//        super(errorCode, errorCode, params, detail, throwable);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     */
//    public ValidationException(String errorCode, String messageId) {
//        super(errorCode, messageId, null, null, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     * @param params    エラーメッセージパラメータ
//     */
//    public ValidationException(String errorCode, String messageId, Object[] params) {
//        super(errorCode, messageId, params, null, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     * @param detail    エラー詳細
//     */
//    public ValidationException(String errorCode, String messageId, Object detail) {
//        super(errorCode, messageId, null, detail, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, String messageId, Throwable throwable) {
//        super(errorCode, messageId, null, null, throwable);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     * @param params    エラーメッセージパラメータ
//     * @param detail    エラー詳細
//     */
//    public ValidationException(String errorCode, String messageId, Object[] params, Object detail) {
//        super(errorCode, messageId, params, detail, null);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     * @param detail    エラー詳細
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, String messageId, Object detail, Throwable throwable) {
//        super(errorCode, messageId, null, detail, throwable);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     * @param params    エラーメッセージパラメータ
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, String messageId, Object[] params, Throwable throwable) {
//        super(errorCode, messageId, params, null, throwable);
//    }
//
//    /**
//     * インスタンスを作成します。
//     *
//     * <p>
//     * エラーコードとエラーメッセージIDはそれぞれ別で管理されます。
//     * </p>
//     *
//     * @param errorCode エラーコード
//     * @param messageId エラーメッセージID
//     * @param params    エラーメッセージパラメータ
//     * @param detail    エラー詳細
//     * @param throwable 例外
//     */
//    public ValidationException(String errorCode, String messageId, Object[] params, Object detail,
//            Throwable throwable) {
//        super(errorCode, messageId, params, detail, throwable);
//    }
}
