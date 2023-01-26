package core.util;

/**
 * 공통적으로 사용하는 상수를 정의하는 Enum 클래스이다.<br>
 * <br>
 * 사용예:) GlobalEnum.LOGIN_SESSION_KEY.toString()<br>
 * <br>
 *
 *
 */
public enum GlobalEnum {

	// 세션에 로그인 정보 키값
	LOGIN_SESSION_KEY("loginInfo")

	// 사용자권한 디폴트값
	, USER_AUTHORITY_DEFAULT("ROL-000001")

	/* 일반. */
	, NOT_AVAILABLE("N/A")

	/* 외부 시스템. */
	, EXTERNAL_SYSTEM_ID_SAEOL("SAEOL")		// 새올행정 시스템.
	, EXTERNAL_SYSTEM_ID_ONNARA("ONNARA")	// 온나라 시스템.

	/* 새올행정 시스템 연계 관련 설정값 */
	, SAEOL_IF_ID("SOINN00001") // 연계 아이디
	, SAEOL_REQUEST_ORG_CODE("5530009") // 요청 기관 코드
	, SAEOL_QUERY_ID_USER("5530000SOI082") // 쿼리아이디(사용자)
	, SAEOL_QUERY_ID_DEPT("5530000SOI081") // 쿼리아이디(부서)
	;


	private String statusCode;

	private GlobalEnum(String s) {
		statusCode = s;
	}

	public String toString() {
		return statusCode;
	}
}
