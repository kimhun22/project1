package kr.co.heartmedia.admin.main.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.heartmedia.admin.main.mapper.MainMapper;
import kr.co.heartmedia.admin.main.service.MainService;
import kr.co.heartmedia.common.file.service.CommFileService;


@Service
public class MainServiceImpl implements MainService {

	@Resource
	private MainMapper mainMapper;

	@Resource
	private CommFileService commFileService;

}
