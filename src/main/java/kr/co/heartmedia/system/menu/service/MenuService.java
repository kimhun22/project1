package kr.co.heartmedia.system.menu.service;

import java.util.List;
import java.util.Map;

import kr.co.heartmedia.vo.extend.TsMenuTbExVO;



public interface MenuService {

	/**
	 * 목록 조회
	 * @return
	 * @throws Exception
	 */
	List<TsMenuTbExVO> selectMenuList(TsMenuTbExVO vo) throws Exception;

	/**
	 * 상세 조회
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	TsMenuTbExVO selectMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 다음 메뉴 정보 조회(순번, 메뉴 Code)
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	TsMenuTbExVO selectNextMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 등록
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> insertMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 수정
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> updateMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 삭제
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> deleteMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

}
