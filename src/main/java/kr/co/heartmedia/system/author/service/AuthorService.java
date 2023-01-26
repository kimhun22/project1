package kr.co.heartmedia.system.author.service;

import java.util.List;

import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;




public interface AuthorService {


	/**
	 * 목록 조회
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	List<TsCmmnCodeTbExVO> selectAuthorList(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

}
