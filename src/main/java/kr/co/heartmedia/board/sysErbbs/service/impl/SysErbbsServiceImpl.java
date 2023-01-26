package kr.co.heartmedia.board.sysErbbs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;

import core.util.PropertiesUtil;
import core.util.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.heartmedia.board.sysErbbs.mapper.SysErbbsMapper;
import kr.co.heartmedia.board.sysErbbs.service.SysErbbsService;
import kr.co.heartmedia.common.file.mapper.CommFileMapper;
import kr.co.heartmedia.common.file.service.CommFileService;
import kr.co.heartmedia.vo.TnAtchFileTbVO;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;


@Service
public class SysErbbsServiceImpl implements SysErbbsService {

    @Resource
    private SysErbbsMapper sysErbbsMapper;

    @Resource
    private CommFileService commFileService;

    @Resource
    private CommFileMapper commFileMapper;


    @Autowired
    private HttpSession session;

    @Override
    public void selectSysErbbsList(ModelMap model, TnBbsTbExVO tnBbsTbExVO) throws Exception {

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(tnBbsTbExVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(tnBbsTbExVO.getPageUnit());
        paginationInfo.setPageSize(tnBbsTbExVO.getPageSize());

        tnBbsTbExVO.setFirstIndex(paginationInfo.getFirstRecordIndex() + 1);
        tnBbsTbExVO.setLastIndex(paginationInfo.getLastRecordIndex());
        tnBbsTbExVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        int listCnt = sysErbbsMapper.selectSysErbbsListCnt(tnBbsTbExVO);

        paginationInfo.setTotalRecordCount(listCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        if (listCnt > 0) {
            List<TnBbsTbExVO> list = sysErbbsMapper.selectSysErbbsList(tnBbsTbExVO);
            model.addAttribute("list", list);
        }

        model.addAttribute("listCnt", listCnt);

    }

    @Override
    public TnBbsTbExVO selectSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception {
        return sysErbbsMapper.selectSysErbbs(tnBbsTbExVO);
    }

    @Override
    @Transactional
    public void insertSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception {

        /***********************************************************************
         * 1. 첨부파일 처리
         ***********************************************************************/
        // 파일 처리
    	Map<String, Object> resultFile = commFileService.crudFiles(PropertiesUtil.getProperty("globals.sample.allowFileType"), "sysErbbs", tnBbsTbExVO.getAtchFileNo(), tnBbsTbExVO.getAtchFileDelSn(), Integer.parseInt(PropertiesUtil.getProperty("globals.notice.maxFileCnt")), tnBbsTbExVO.getAtchFiles(), tnBbsTbExVO.getHmSiteCode());
    	//Map<String, Object> resultFile = commFileService.crudFiles("jpg,jpeg,png,PNG,JPGE,gif,bmp,pcx,pdf,xls,xlsx,ppt,pptx,hwp,doc,docx", "sysErbbs", tnBbsTbExVO.getAtchFileNo(), tnBbsTbExVO.getAtchFileDelSn(), Integer.parseInt(PropertiesUtil.getProperty("globals.jobKnwhow.maxFileCnt")), tnBbsTbExVO.getAtchFiles());

        // 파일 처리 실패일 경우
        if  ( !(boolean) resultFile.get("bool") )  {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        /***********************************************************************
         * 2. 등록 처리
         ***********************************************************************/
        TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
        tnBbsTbExVO.setAtchFileNo((Integer) resultFile.get("fileNo"));
        tnBbsTbExVO.setRegistUserId(loginInfo.getLoginId());
        tnBbsTbExVO.setUpdateUserId(loginInfo.getLoginId());

        if(loginInfo.getAuthorCode().equals("1") && StringUtil.isNotEmpty(tnBbsTbExVO.getNttCn2())) {
        	//답변 내용이 있는 경우 답변 여부 Y처리
        	tnBbsTbExVO.setKwrd7("Y");
        	tnBbsTbExVO.setAnswerDt(new Date());
        } else {
        	tnBbsTbExVO.setKwrd7("N");
        }

        sysErbbsMapper.insertSysErbbs(tnBbsTbExVO);

    }

    @Override
    @Transactional
    public void updateSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception {

        /***********************************************************************
         * 1. 첨부파일 처리
         ***********************************************************************/
        // 파일 처리
    	if(StringUtil.isNotEmpty(tnBbsTbExVO.getAtchFiles()) || StringUtil.isNotEmpty(tnBbsTbExVO.getAtchFileDelSn())) {
    		//Map<String, Object> resultFile = commFileService.crudFiles("jpg,jpeg,png,PNG,JPGE,gif,bmp,pcx,pdf,xls,xlsx,ppt,pptx,hwp,doc,docx", "sysErbbs", tnBbsTbExVO.getAtchFileNo(), tnBbsTbExVO.getAtchFileDelSn(), Integer.parseInt(PropertiesUtil.getProperty("globals.jobKnwhow.maxFileCnt")), tnBbsTbExVO.getAtchFiles());
    		Map<String, Object> resultFile = commFileService.crudFiles(PropertiesUtil.getProperty("globals.sample.allowFileType"), "notice", tnBbsTbExVO.getAtchFileNo(), tnBbsTbExVO.getAtchFileDelSn(), Integer.parseInt(PropertiesUtil.getProperty("globals.notice.maxFileCnt")), tnBbsTbExVO.getAtchFiles(), tnBbsTbExVO.getHmSiteCode());
    		// 파일 처리 실패일 경우
    		if  ( !(boolean) resultFile.get("bool") )  {
    			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    		}

    		tnBbsTbExVO.setAtchFileNo((Integer) resultFile.get("fileNo"));

    	}

        /***********************************************************************
         * 2. 수정 처리
         ***********************************************************************/
        TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");

        tnBbsTbExVO.setUpdateUserId(loginInfo.getLoginId());

        if("N".equals(tnBbsTbExVO.getKwrd7()) || StringUtil.isEmpty(tnBbsTbExVO.getKwrd7())) {
        	if(StringUtil.isNotEmpty(tnBbsTbExVO.getNttCn2())
        			&& !"<p>&nbsp;</p>".equals(tnBbsTbExVO.getNttCn2())
        			&& !"&nbsp;".equals(tnBbsTbExVO.getNttCn2()) ) {
        		tnBbsTbExVO.setKwrd7("Y");
        		tnBbsTbExVO.setAnswerDt(new Date());
        	} else {
        		tnBbsTbExVO.setAnswerUserId("");
        		tnBbsTbExVO.setNttCn2("");
        	}
        }
        sysErbbsMapper.updateSysErbbs(tnBbsTbExVO);


    }

    @Override
    public void deleteSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception {

        /***********************************************************************
         * 1. 게시글 삭제 처리
         ***********************************************************************/
        sysErbbsMapper.deleteSysErbbs(tnBbsTbExVO);

    }

    @Override
    public Map<String, Object> deleteSysErbbsAtchFile(TnAtchFileTbVO tnAtchFileTb, ModelMap modelMap)
            throws Exception {

        /***********************************************************************
         * 1. 파일 삭제 처리
         ***********************************************************************/
        Map<String, Object> resultMap = new HashMap<String, Object>();

        TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
        int upseted = 0;

        tnAtchFileTb.setDeleteAt("1");
        tnAtchFileTb.setUpdateUserId(loginInfo.getLoginId());

        upseted = commFileMapper.deleteFile(tnAtchFileTb);

        if (upseted == 1) {
            resultMap.put("result", true);
            resultMap.put("message", "삭제 되었습니다.");
        } else {
            resultMap.put("result", false);
            resultMap.put("message", "삭제에 실패하였습니다.");
        }

        return resultMap;
    }


}
