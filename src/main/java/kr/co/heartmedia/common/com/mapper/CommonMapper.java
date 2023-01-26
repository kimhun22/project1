package kr.co.heartmedia.common.com.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.LoginVO;
import kr.co.heartmedia.vo.TnDeptTbVO;


@Mapper
public interface CommonMapper {

	public List<LoginVO> selectDepartmentList(TnDeptTbVO tnDeptTbVO) throws Exception;

}
