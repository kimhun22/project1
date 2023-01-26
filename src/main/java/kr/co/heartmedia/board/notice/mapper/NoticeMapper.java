package kr.co.heartmedia.board.notice.mapper;

import java.util.List;


import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;

@Mapper
public interface NoticeMapper {

	/**
	 * 공지사항 목록 갯수 조회
	 * @param tnBbsTbExVO
	 * @return
	 */
	int selectNoticeListCnt(TnBbsTbExVO tnBbsTbExVO) throws Exception;

	/**
	 * 공지사항 목록 조회
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	List<TnBbsTbExVO> selectNoticeList(TnBbsTbExVO tnBbsTbExVO) throws Exception;

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
	int insertNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception;

	/**
	 * 공지사항 수정
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	int updateNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception;

	/**
	 * 공지사항 삭제
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	int deleteNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception;

}
