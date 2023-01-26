package kr.co.heartmedia.system.authorMenu.service;

import java.util.List;
import java.util.Map;

import kr.co.heartmedia.vo.TsAuthorMenuTbVO;




public interface AuthorMenuService {

	/**
	 * 메뉴 권한 매핑 목록 조회
	 * @param authorCode
	 * @return
	 * @throws Exception
	 */
	List<TsAuthorMenuTbVO> selectAuthorMenuMappingList(TsAuthorMenuTbVO tsAuthorMenuTbVO) throws Exception;

	/**
	 * 저장
	 * @param tsAuthorMenuTbVO
	 * @param menuIdList
	 * @return
	 */
	Map<String, Object> saveAuthorMenuMapping(TsAuthorMenuTbVO tsAuthorMenuTbVO, List<String> menuList) throws Exception;

}
