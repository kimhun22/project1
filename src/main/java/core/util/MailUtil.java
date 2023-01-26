package core.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Component;

import core.web.jstl.Function;

@Component
public class MailUtil {

	public String host = "222.111.209.101";                   // 호스트 아이피
	public String port = "25";                                      // 포트
	public String smtpUserName = "iags@kcredit.or.kr";    // SMTP 인증 email

	public String fromEmail = "iags@kcredit.or.kr";           // 보내는 사람 email
	public String fromNm = "정보활용동의등급관리자";             // 보내는 사람 이름

	public List<String> toEmail;                                   // 받는 사람 email
	public void setToEmail(List<String> toEmail) {
		this.toEmail = toEmail;
	}

	public String subject;                                           // 메일 제목
	public String contents;                                         // 메일 내용

	/**
	 * 메일 발송
	 * @throws Exception
	 */
	public void sendMail() throws Exception {

		// 서버 프로퍼티 설정
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", smtpUserName);
		props.put("mail.smtp.port", port);

		// Session 객체 생성
		Session session = Session.getDefaultInstance(props);

		// 보내는 사람 email 세팅
		InternetAddress fromAddr = new InternetAddress();
		fromAddr.setPersonal(fromNm, "UTF-8");
		fromAddr.setAddress(fromEmail);

		// 받는 사람 email 세팅
		InternetAddress[] toAddrs = new InternetAddress[toEmail.size()];
		for  ( int i = 0 ; i < toEmail.size() ; i++ )  {
			toAddrs[i] = new InternetAddress(toEmail.get(i));
		}

		// 메세지 설정
		Message msg = new MimeMessage(session);
		msg.setFrom(fromAddr);
		msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
		msg.setContent(contents, "text/html;charset=UTF-8");
		msg.setRecipients(Message.RecipientType.TO, toAddrs);

		// 메일 발송
		Transport.send(msg);

	}

	/**
	 * 회원가입 승인 안내 메일 세팅
	 */
	public void setMemberJoinApproval() {

		this.subject = "[정보활용동의등급평가시스템] 회원가입 승인 안내";

		StringBuffer sb = new StringBuffer();
		sb.append("회원가입이 정상적으로 승인되었습니다.");
		sb.append("<br/>");
		sb.append("가입 시 입력한 아이디와 패스워드를 이용하여 서비스 이용이 가능합니다.");
		sb.append("<br/><br/>");
		sb.append("- 한국신용정보원 -");
		this.contents = sb.toString();

	}

	/**
	 * 회원가입 거절 안내 메일 세팅
	 * @param returnReason
	 */
	public void setMemberJoinReturn(String returnReason) {

		this.subject = "[정보활용동의등급평가시스템] 회원가입 거절 안내";

		StringBuffer sb = new StringBuffer();
		sb.append("회원가입이 거절되었습니다.");
		sb.append("<br/><br/>");
		sb.append("[거절 사유]");
		sb.append("<br/>");
		sb.append("'" + Function.nl2br(returnReason) + "'");
		sb.append("<br/><br/>");
		sb.append("- 한국신용정보원 -");
		this.contents = sb.toString();

	}

}
