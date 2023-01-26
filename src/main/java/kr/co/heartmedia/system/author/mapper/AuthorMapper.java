package kr.co.heartmedia.system.author.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;



@Mapper
public interface AuthorMapper {

	/**
	 * 역할 조회
	 * @return
	 * @throws Exception
	 */
	List<TsCmmnCodeTbExVO> selectAuthorList(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

}
