package kr.co.heartmedia.common.menu.service;

import java.util.List;

import kr.co.heartmedia.vo.extend.TsMenuTbExVO;



public interface CommMenuService {

	/**
	 * 메뉴 전체 목록 조회
	 * @return
	 * @throws Exception
	 */
	List<TsMenuTbExVO> selectMenuList(TsMenuTbExVO vo) throws Exception;

	/**
	 * 권한별 메뉴 목록 조회 (사이트코드 추가)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<TsMenuTbExVO> selectAuthMenuListSite(TsMenuTbExVO vo) throws Exception;

}
