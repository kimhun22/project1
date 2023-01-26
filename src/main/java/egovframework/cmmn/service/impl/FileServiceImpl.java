package egovframework.cmmn.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cmmn.service.FileService;
import egovframework.cmmn.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @author mjkim
 *
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

	@Resource(name = "fileMapper")
	private FileMapper fileMapper;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService fileIdgenService;

	@Override
	public List<FileVO> selectFileInfs(FileVO fileVO) throws Exception {
		return fileMapper.selectFileList(fileVO);
	}

	@Override
	public String insertFileInf(FileVO fileVO) throws Exception {
		String attachedFileId = fileVO.getAttachedFileId();
		fileMapper.insertFileMaster(fileVO);
		fileMapper.insertFileDetail(fileVO);
		return attachedFileId;
	}

	@Override
	public String insertFileInfs(List<?> fileList) throws Exception {
		String atchFileId = "";

		if(fileList.size() != 0) {
			FileVO fileVO = (FileVO) fileList.get(0);
			atchFileId = fileVO.getAttachedFileId();

			fileMapper.insertFileMaster(fileVO);

			Iterator<?> iter = fileList.iterator();
			while (iter.hasNext()) {
				fileVO = (FileVO) iter.next();
				fileMapper.insertFileDetail(fileVO);
			}
		}

		if (atchFileId == "") {
			atchFileId = null;
		}
		return atchFileId;
	}

	@Override
	public void updateFileInfs(List<?> fileList) throws Exception {
		FileVO fileVO = null;

		Iterator<?> iter = fileList.iterator();
		while (iter.hasNext()) {
			fileVO = (FileVO) iter.next();
			fileMapper.insertFileDetail(fileVO);
		}
	}

	@Override
	public void deleteFileInfs(List<?> fileList) throws Exception {
		Iterator<?> iter = fileList.iterator();
		FileVO fileVO;
		while (iter.hasNext()) {
			fileVO = (FileVO) iter.next();
			fileMapper.deleteFileDetail(fileVO);
		}
	}

	@Override
	public void deleteFileInf(FileVO fileVO) throws Exception {
		fileMapper.deleteFileDetail(fileVO);
	}

	@Override
	public FileVO selectFileInf(FileVO fileVO) throws Exception {
		return fileMapper.selectFileInf(fileVO);
	}

	@Override
	public int getMaxFileSN(FileVO fileVO) throws Exception {
		return fileMapper.getMaxFileSN(fileVO);
	}

	@Override
	public void deleteAllFileInf(FileVO fileVO) throws Exception {
		fileMapper.deleteCOMTNFILE(fileVO);
	}

	@Override
	public Map<String, Object> selectFileListByFileNm(FileVO fileVO) throws Exception {
		List<FileVO> result = fileMapper.selectFileListByFileNm(fileVO);
		int cnt = fileMapper.selectFileListCntByFileNm(fileVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public List<FileVO> selectImageFileList(FileVO fileVO) throws Exception {
		return fileMapper.selectImageFileList(fileVO);
	}

	@Override
	public String insertPdfFile(FileVO fileVO) throws Exception {
		String atchFileIdString = fileIdgenService.getNextStringId();
		fileVO.setAttachedFileId(atchFileIdString);
		fileVO.setDetailId("0");

		fileMapper.insertFileMaster(fileVO);
		fileMapper.insertFileDetail(fileVO);

		return atchFileIdString;

	}

}
