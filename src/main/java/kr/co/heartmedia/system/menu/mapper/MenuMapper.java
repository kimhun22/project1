package kr.co.heartmedia.system.menu.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;


@Mapper
public interface MenuMapper {

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
	 * 하위 메뉴 목록 조회
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	List<TsMenuTbExVO> selectSubMenuList(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 다음 메뉴 순서 조회
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	int selectNextMenuOrd(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 다음 메뉴 CODE 조회
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	String selectNextMenuId(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 등록
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	int insertMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 수정
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	int updateMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

	/**
	 * 삭제
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	int deleteMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception;

}
