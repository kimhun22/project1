package kr.co.heartmedia.system.deptInfo.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.TnDeptTbVO;


@Mapper
public interface AdminDeptMapper {

	/**
	 * 부서 목록 건수 조회
	 * @param sessionVO
	 * @return
	 * @throws Exception
	*/
	public int selectDeptListCnt(TnDeptTbVO sessionVO) throws Exception;

	/**
	 * 부서 목록 조회
	 * @param deptVO
	 * @return
	 * @throws Exception
	*/
	public List<TnDeptTbVO> selectDeptList(TnDeptTbVO deptVO) throws Exception;

	/**
	 * 부서 상세 조회
	 * @param deptVO
	 * @return
	 * @throws Exception
	*/
	public TnDeptTbVO selectDeptDetail(TnDeptTbVO deptVO) throws Exception;

}
