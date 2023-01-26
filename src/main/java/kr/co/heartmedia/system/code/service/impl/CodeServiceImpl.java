package kr.co.heartmedia.system.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.heartmedia.system.code.mapper.CodeMapper;
import kr.co.heartmedia.system.code.service.CodeService;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;

@Service
public class CodeServiceImpl implements CodeService {

	@Resource
	private CodeMapper codeMapper;

	@Autowired
	private HttpSession session;


	@Override
	public List<TsCmmnCodeTbExVO> selectCodeList(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		List<TsCmmnCodeTbExVO> list = codeMapper.selectCodeList(tsCmmnCodeTbExVO);

		return list;

	}

	@Override
	public TsCmmnCodeTbExVO selectCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		TsCmmnCodeTbExVO data = codeMapper.selectCode(tsCmmnCodeTbExVO);

		return data;

	}

	@Override
	public TsCmmnCodeTbExVO selectNextCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		TsCmmnCodeTbExVO nextCodeData = new TsCmmnCodeTbExVO();
		nextCodeData.setSortOrdr(codeMapper.selectNextCodeOrd(tsCmmnCodeTbExVO));

		return nextCodeData;

	}

	@Override
	@Transactional
	public Map<String, Object> insertCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 코드 중복 체크
		 ***********************************************************************/
		int overlapCheck = codeMapper.selectOverlapCodeCheck(tsCmmnCodeTbExVO);
		// 코드 체계가 존재할 경우
		if  ( overlapCheck > 0 )  {
			resultMap.put("result", false);
			resultMap.put("message", "이미 사용 중인 코드 체계입니다.");

			return resultMap;
		}

		/***********************************************************************
		 * 2. 등록 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tsCmmnCodeTbExVO.setRegistUserId(loginInfo.getLoginId());
		tsCmmnCodeTbExVO.setUpdateUserId(loginInfo.getLoginId());

		// 등록 처리
		int result = codeMapper.insertCode(tsCmmnCodeTbExVO);

		// 성공
		if  ( result > 0 )  {
			resultMap.put("result", true);
			resultMap.put("message", "저장 되었습니다.");
		}
		// 실패
		else  {
			resultMap.put("result", false);
			resultMap.put("message", "저장에 실패하였습니다.");
		}

		return resultMap;

	}

	@Override
	@Transactional
	public Map<String, Object> updateCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 수정 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tsCmmnCodeTbExVO.setUpdateUserId(loginInfo.getLoginId());

		// 수정 처리
		int result = codeMapper.updateCode(tsCmmnCodeTbExVO);

		// 성공
		if  ( result > 0 )  {
			// 사용 여부가 0(사용 안함) 일 경우
			if  ( "0".equals(tsCmmnCodeTbExVO.getUseAt()) )  {
				// 하위 메뉴 전체 사용 여부 0(사용 안함) 처리
				for  ( TsCmmnCodeTbExVO subCodeData : codeMapper.selectSubCodeList(tsCmmnCodeTbExVO) )  {
					subCodeData.setUpdateUserId(loginInfo.getLoginId());
					subCodeData.setUseAt("0");
					codeMapper.updateCode(subCodeData);
				}
			}

			resultMap.put("result", true);
			resultMap.put("message", "저장 되었습니다.");
		}
		// 실패
		else  {
			resultMap.put("result", false);
			resultMap.put("message", "저장에 실패하였습니다.");
		}

		return resultMap;

	}

	@Override
	@Transactional
	public Map<String, Object> deleteCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 하위 메뉴 존재 여부 검증
		 ***********************************************************************/
		// 상세 조회
		TsCmmnCodeTbExVO codeData = codeMapper.selectCode(tsCmmnCodeTbExVO);
		// 하위 메뉴가 존재할 경우
		if  ( codeData.getChildCnt() > 0 )  {
			resultMap.put("result", false);
			resultMap.put("message", "하위 메뉴가 존재하여 삭제할 수 없습니다.");

			return resultMap;
		}

		/***********************************************************************
		 * 2. 삭제 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tsCmmnCodeTbExVO.setUpdateUserId(loginInfo.getLoginId());

		// 삭제 처리
		int result = codeMapper.deleteCode(tsCmmnCodeTbExVO);

		// 성공
		if  ( result > 0 )  {
			resultMap.put("result", true);
			resultMap.put("message", "삭제 되었습니다.");
		}
		// 실패
		else  {
			resultMap.put("result", false);
			resultMap.put("message", "삭제에 실패하였습니다.");
		}

		return resultMap;

	}

}
