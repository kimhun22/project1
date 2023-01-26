package kr.co.heartmedia.common.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.heartmedia.common.code.mapper.CommCodeMapper;
import kr.co.heartmedia.common.code.service.CommCodeService;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;


@Service
public class CommCodeServiceImpl implements CommCodeService {

	@Resource
	private CommCodeMapper commCodeMapper;


	/*@Override
	public List<TsCmmnCodeTbExVO> selectSubCodeList(String parntsCmmnCode) throws Exception {
		List<TsCmmnCodeTbExVO> list = commCodeMapper.selectSubCodeList(parntsCmmnCode);
		return list;
	}*/

	@Override
	public List<TsCmmnCodeTbExVO> selectSubCodeListSiteCode(TsCmmnCodeTbExVO vo) throws Exception {
		List<TsCmmnCodeTbExVO> list = commCodeMapper.selectSubCodeListSiteCode(vo);
		return list;
	}

}
