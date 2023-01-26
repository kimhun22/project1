package kr.co.heartmedia.system.author.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.heartmedia.system.author.mapper.AuthorMapper;
import kr.co.heartmedia.system.author.service.AuthorService;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;



@Service
public class AuthorServiceImpl implements AuthorService {

	@Resource
	private AuthorMapper authorMapper;

	@Override
	public List<TsCmmnCodeTbExVO> selectAuthorList(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		List<TsCmmnCodeTbExVO> list = authorMapper.selectAuthorList(tsCmmnCodeTbExVO);

		return list;

	}

}