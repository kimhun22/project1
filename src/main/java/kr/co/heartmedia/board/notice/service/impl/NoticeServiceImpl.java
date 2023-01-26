package kr.co.heartmedia.board.notice.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;

import core.exception.HMException;
import core.util.PropertiesUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.heartmedia.board.notice.mapper.NoticeMapper;
import kr.co.heartmedia.board.notice.service.NoticeService;
import kr.co.heartmedia.common.file.service.CommFileService;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;



@Service
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private NoticeMapper noticeMapper;

	@Resource
	private CommFileService commFileService;

	@Autowired
	private HttpSession session;


	@Override
	public void selectNoticeList(ModelMap model, TnBbsTbExVO tnBbsTbExVO) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(tnBbsTbExVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(tnBbsTbExVO.getPageUnit());
		paginationInfo.setPageSize(tnBbsTbExVO.getPageSize());

		tnBbsTbExVO.setFirstIndex(paginationInfo.getFirstRecordIndex() + 1);
		tnBbsTbExVO.setLastIndex(paginationInfo.getLastRecordIndex());
		tnBbsTbExVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int listCnt = noticeMapper.selectNoticeListCnt(tnBbsTbExVO);

		paginationInfo.setTotalRecordCount(listCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		if (listCnt > 0) {
			List<TnBbsTbExVO> list = noticeMapper.selectNoticeList(tnBbsTbExVO);
			model.addAttribute("list", list);
		}

		model.addAttribute("listCnt", listCnt);

	}

	@Override
	public TnBbsTbExVO selectNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception {

		TnBbsTbExVO data = noticeMapper.selectNotice(tnBbsTbExVO);

		return data;

	}

	@Override
	@Transactional
	public void insertNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception {

		/***********************************************************************
		 * 1. 첨부파일 처리
		 ***********************************************************************/
		// 파일 처리
		Map<String, Object> resultFile = commFileService.crudFiles(PropertiesUtil.getProperty("globals.sample.allowFileType"), "notice", tnBbsTbExVO.getAtchFileNo(), tnBbsTbExVO.getAtchFileDelSn(), Integer.parseInt(PropertiesUtil.getProperty("globals.notice.maxFileCnt")), tnBbsTbExVO.getAtchFiles(), tnBbsTbExVO.getHmSiteCode());

		// 파일 처리 실패일 경우
		if  ( !(boolean) resultFile.get("bool") )  {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			throw new HMException("파일 처리에 실패하였습니다.", null, false);
		}

		/***********************************************************************
		 * 2. 등록 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tnBbsTbExVO.setAtchFileNo((Integer) resultFile.get("fileNo"));
		tnBbsTbExVO.setRegistUserId(loginInfo.getLoginId());
		tnBbsTbExVO.setUpdateUserId(loginInfo.getLoginId());

		noticeMapper.insertNotice(tnBbsTbExVO);

	}

	@Override
	@Transactional
	public void updateNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception {

		/***********************************************************************
		 * 1. 첨부파일 처리
		 ***********************************************************************/
		// 파일 처리
		Map<String, Object> resultFile = commFileService.crudFiles(PropertiesUtil.getProperty("globals.sample.allowFileType"), "notice", tnBbsTbExVO.getAtchFileNo(), tnBbsTbExVO.getAtchFileDelSn(), Integer.parseInt(PropertiesUtil.getProperty("globals.notice.maxFileCnt")), tnBbsTbExVO.getAtchFiles(),tnBbsTbExVO.getHmSiteCode());

		// 파일 처리 실패일 경우
		if  ( !(boolean) resultFile.get("bool") )  {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			throw new HMException("파일 처리에 실패하였습니다.", null, false);
		}

		/***********************************************************************
		 * 2. 수정 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tnBbsTbExVO.setAtchFileNo((Integer) resultFile.get("fileNo"));
		tnBbsTbExVO.setUpdateUserId(loginInfo.getLoginId());

		noticeMapper.updateNotice(tnBbsTbExVO);

	}

	@Override
	@Transactional
	public void deleteNotice(TnBbsTbExVO tnBbsTbExVO) throws Exception {

		/***********************************************************************
		 * 1. 삭제 처리
		 ***********************************************************************/
		noticeMapper.deleteNotice(tnBbsTbExVO);

	}

}
