package kr.co.heartmedia.system.logInqire.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.heartmedia.system.logInqire.mapper.LogInqireMapper;
import kr.co.heartmedia.system.logInqire.service.LogInqireService;
import kr.co.heartmedia.vo.extend.ThSysUseHistTbExVO;


@Service
public class LogInqireServiceImpl implements LogInqireService {

	@Resource
	private LogInqireMapper logInqireMapper;

	/**
	 * 시스템 접속이력 게시판 목록 조회
	 */
	@Override
	public void selectSystemConectList(ModelMap model, ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(thSysUseHistTbExVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(thSysUseHistTbExVO.getPageUnit());
        paginationInfo.setPageSize(thSysUseHistTbExVO.getPageSize());

        thSysUseHistTbExVO.setFirstIndex(paginationInfo.getFirstRecordIndex() + 1);
        thSysUseHistTbExVO.setLastIndex(paginationInfo.getLastRecordIndex());
        thSysUseHistTbExVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 시스템 접속이력 게시판 목록 조회 Count
        int listCnt = logInqireMapper.selectSystemConectListCnt(thSysUseHistTbExVO);
        paginationInfo.setTotalRecordCount(listCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        if (listCnt > 0) {
            List<ThSysUseHistTbExVO> systemConectList = logInqireMapper.selectSystemConectList(thSysUseHistTbExVO);
            model.addAttribute("systemConectList", systemConectList);
        }
        model.addAttribute("listCnt", listCnt);

	}

	/**
	 * 시스템 접속이력 단일 조회
	 */
	@Override
	public ThSysUseHistTbExVO selectSystemConectOne(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception {
		return logInqireMapper.selectSystemConectOne(thSysUseHistTbExVO);
	}


	/**
	 * 시스템 이용이력 게시판 목록 조회
	 */
	@Override
	public void selectSystemUseList(ModelMap model, ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(thSysUseHistTbExVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(thSysUseHistTbExVO.getPageUnit());
        paginationInfo.setPageSize(thSysUseHistTbExVO.getPageSize());

        thSysUseHistTbExVO.setFirstIndex(paginationInfo.getFirstRecordIndex() + 1);
        thSysUseHistTbExVO.setLastIndex(paginationInfo.getLastRecordIndex());
        thSysUseHistTbExVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 시스템 이용이력 게시판 목록 조회 Count
        int listCnt = logInqireMapper.selectSystemUseListCnt(thSysUseHistTbExVO);
        paginationInfo.setTotalRecordCount(listCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        if (listCnt > 0) {
            List<ThSysUseHistTbExVO> systemUseList = logInqireMapper.selectSystemUseList(thSysUseHistTbExVO);
            model.addAttribute("systemUseList", systemUseList);
        }
        model.addAttribute("listCnt", listCnt);
	}

	/**
	 * 시스템 이용이력 단일 조회
	 */
	@Override
	public ThSysUseHistTbExVO selectSystemUseOne(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception {
		return null;
	}
}
