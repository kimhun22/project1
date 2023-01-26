package kr.co.heartmedia.common.com.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import kr.co.heartmedia.vo.TnDeptTbVO;




@Service
public interface CommonService {

	public void selectDepartmentList(TnDeptTbVO tnDeptTbVO, ModelMap model) throws Exception;

	/**
	 * 메시지를 출력하고 history.back 한다.
	 * @param model
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	String backMsg(ModelMap model, String msg) throws Exception;

	/**
	 * 메시지 출력 후 History.back
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	String historyBackMsg(String msg) throws Exception;
}