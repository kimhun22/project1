package kr.co.heartmedia.board.notice.service;

import org.springframework.ui.ModelMap;

import kr.co.heartmedia.vo.extend.TnBbsTbExVO;






public interface NoticeService {

	/**
	 * 공지사항 목록 조회
	 * @param model
	 * @param tnBbsTbExVO
	 * @throws Exception
	 */
	void selectNoticeList(ModelMap model, TnBbsTbExVO tnBbsTbExVO) throws Exception;

	/**
	 * 공지사항 단일 조회
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	TnBbsTbExVO selectNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception;

	/**
	 * 공지사항 등록
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	void insertNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception;

	/**
	 * 공지사항 수정
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	void updateNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception;

	/**
	 * 공지사항 삭제
	 * @param tnBbsTbExVO
	 * @throws Exception
	 */
	void deleteNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception;

}
