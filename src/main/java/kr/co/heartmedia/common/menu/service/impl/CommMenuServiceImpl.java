package kr.co.heartmedia.common.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.heartmedia.common.menu.mapper.CommMenuMapper;
import kr.co.heartmedia.common.menu.service.CommMenuService;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;



@Service
public class CommMenuServiceImpl implements CommMenuService {

	@Resource
	private CommMenuMapper commMenuMapper;


	@Override
	public List<TsMenuTbExVO> selectMenuList(TsMenuTbExVO vo) throws Exception {
		List<TsMenuTbExVO> list = commMenuMapper.selectMenuList(vo);
		return list;
	}

	@Override
	public List<TsMenuTbExVO> selectAuthMenuListSite(TsMenuTbExVO vo) throws Exception {
		List<TsMenuTbExVO> list = commMenuMapper.selectAuthMenuListSite(vo);
		return list;
	}


}
