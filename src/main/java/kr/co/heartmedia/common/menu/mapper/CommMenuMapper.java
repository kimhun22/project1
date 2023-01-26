package kr.co.heartmedia.common.menu.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;


@Mapper
public interface CommMenuMapper {

	/**
	 * 메뉴 전체 목록 조회
	 * @return
	 * @throws Exception
	 */
	List<TsMenuTbExVO> selectMenuList(TsMenuTbExVO vo) throws Exception;

	/**
	 * 권한별 메뉴 목록 조회(사이트 코드 추가)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<TsMenuTbExVO> selectAuthMenuListSite(TsMenuTbExVO vo) throws Exception;

}
