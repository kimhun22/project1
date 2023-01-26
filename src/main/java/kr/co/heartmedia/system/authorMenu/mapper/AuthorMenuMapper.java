package kr.co.heartmedia.system.authorMenu.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.TsAuthorMenuTbVO;

@Mapper
public interface AuthorMenuMapper {

	/**
	 * 메뉴 권한 매핑 목록 조회
	 * @param authorCode
	 * @return
	 * @throws Exception
	 */
	List<TsAuthorMenuTbVO> selectAuthorMenuMappingList(TsAuthorMenuTbVO tsAuthorMenuTbVO) throws Exception;

	/**
	 * 메뉴 권한 매핑 등록
	 * @param tsAuthorMenuTbVO
	 * @return
	 * @throws Exception
	 */
	int insertAuthorMenuMapping(TsAuthorMenuTbVO tsAuthorMenuTbVO) throws Exception;

	/**
	 * 메뉴 권한 매핑 일괄 삭제
	 * @param tsAuthorMenuTbVO
	 * @return
	 * @throws Exception
	 */
    int deleteAuthorMenuMapping(TsAuthorMenuTbVO tsAuthorMenuTbVO) throws Exception;

}
