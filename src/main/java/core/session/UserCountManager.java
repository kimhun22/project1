package core.session;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import core.util.StringUtil;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;

public class UserCountManager {

	public static Hashtable<String, HttpSession> userInfo = new Hashtable<String, HttpSession>();

	/**
	 * 세션 리스터 적용
	 * @param session
	 */
	public void setSession(HttpSession session) {
		session.setAttribute("listener", new CustomBindingListener());
	}

	/**
	 * 접속자 체크 여부 조회
	 * @param sessionId
	 * @return
	 */
	public static boolean isNewSessinCheck(String sessionId) {
		boolean resultBool = (null == userInfo.get(sessionId)) ? true : false;

		return resultBool;
	}

	/**
	 * 접속자 추가 및 로그인 여부 수정
	 * @param session
	 * @param loginId
	 */
	@SuppressWarnings("rawtypes")
	public static void putUserInfo(HttpSession session, String loginId) {
		userInfo.put(session.getId(), session);

		if  ( StringUtil.isNotEmpty(loginId) )  {
			Enumeration e = userInfo.elements();
			while  ( e.hasMoreElements() )  {
				HttpSession targetSession = (HttpSession) e.nextElement();
				if  ( session.getId().equals(targetSession.getId()) )
					continue;

				TnUserTbExVO loginInfo = new TnUserTbExVO();
				try {
					loginInfo = (TnUserTbExVO) targetSession.getAttribute("loginInfo");
				} catch (Exception e2) {
					continue;
				}

				if  ( null != loginInfo && loginId.equals(loginInfo.getLoginId()) )  {
					targetSession.invalidate();
				}
			}
		}

//		session.getServletContext().log("세션 : " + session.getId() + ", 총 접속자수 : " + getTotalUserCount() + ", 로그인 접속자수 : " + getLoginUserCount());
	}

	/**
	 * 접속자 제거
	 * @param session
	 */
	public static void removeUserInfo(HttpSession session) {
		userInfo.remove(session.getId());

//		session.getServletContext().log("세션 : " + session.getId() + ", 총 접속자수 : " + getTotalUserCount() + ", 로그인 접속자수 : " + getLoginUserCount());
	}

    /**
     * 총 접속자수 조회
     * @return
     */
    public static int getTotalUserCount() {
        int totalUserCnt = userInfo.size();

        return totalUserCnt;
    }

    /**
     * 로그인 접속자수 조회
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static int getLoginUserCount() {
        int loginUserCnt = 0;

        Enumeration e = userInfo.elements();
        while  ( e.hasMoreElements() )  {
        	HttpSession targetSession = (HttpSession) e.nextElement();
        	if  ( null != targetSession.getAttribute("loginInfo") )  {
        		loginUserCnt++;
        	}
        }

        return loginUserCnt;
    }

}

class CustomBindingListener implements HttpSessionBindingListener {

	public void valueBound(HttpSessionBindingEvent event) {
		// 접속자 추가(비로그인)
		UserCountManager.putUserInfo(event.getSession(), "");
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
 		// 접속자 제거
 		UserCountManager.removeUserInfo(event.getSession());
	}

}
