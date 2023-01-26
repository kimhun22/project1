package  kr.co.heartmedia.system.deptInfo.service;

import org.springframework.ui.ModelMap;

import  kr.co.heartmedia.vo.TnDeptTbVO;



public interface AdminDeptService {


	/**
	 * 부서 목록 조회
	 * @param deptVO
	 * @param model
	 * @throws Exception
	 */
	public void selectDeptList(TnDeptTbVO tnDeptTbVO, ModelMap model) throws Exception;

	/**
	 * 부서 상세 조회
	 * @param deptVO
	 * @param model
	 * @throws Exception
	 */
	public void selectDeptDetail(TnDeptTbVO tnDeptTbVO, ModelMap model) throws Exception;

}
