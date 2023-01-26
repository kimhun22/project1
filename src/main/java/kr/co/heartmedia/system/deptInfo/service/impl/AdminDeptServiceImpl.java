package  kr.co.heartmedia.system.deptInfo.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.heartmedia.system.deptInfo.mapper.AdminDeptMapper;
import kr.co.heartmedia.system.deptInfo.service.AdminDeptService;
import kr.co.heartmedia.vo.TnDeptTbVO;

@Service
public class AdminDeptServiceImpl implements AdminDeptService {

	@Resource
	private AdminDeptMapper deptMapper;
	@Autowired
	private HttpSession session;


	@Override
	public void selectDeptList(TnDeptTbVO sessionVO, ModelMap model) throws Exception {

		// 페이징
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sessionVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sessionVO.getPageUnit());
		paginationInfo.setPageSize(sessionVO.getPageSize());

		sessionVO.setFirstIndex(paginationInfo.getFirstRecordIndex() + 1);
		sessionVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sessionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TnDeptTbVO> selectList = null;

		// 총 카운트
		int cnt = deptMapper.selectDeptListCnt(sessionVO);
		paginationInfo.setTotalRecordCount(cnt);

		if (cnt > 0) {
			// 리스트
			selectList = deptMapper.selectDeptList(sessionVO);
		}

		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("selectDeptList", selectList);
		model.addAttribute("selectDeptListCnt", cnt);

	}

	@Override
	public void selectDeptDetail(TnDeptTbVO deptVO, ModelMap model) throws Exception {
		model.addAttribute("deptDetail", deptMapper.selectDeptDetail(deptVO));

	}


}
