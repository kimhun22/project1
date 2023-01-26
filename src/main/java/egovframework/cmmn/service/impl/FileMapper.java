package egovframework.cmmn.service.impl;

import java.util.List;

import egovframework.cmmn.service.FileVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @author mjkim
 *
 */
@Mapper("fileMapper")
public interface FileMapper {

	public List<FileVO> selectFileList(FileVO fileVO) throws Exception;
	
	public void insertFileMaster(FileVO fileVO) throws Exception;

	public FileVO selectFileMaster(FileVO fileVO) throws Exception;
	
	public void insertFileDetail(FileVO fileVO) throws Exception;
	
	public void deleteFileDetail(FileVO fileVO) throws Exception;
	
	public int getMaxFileSN(FileVO fileVO) throws Exception;
	
	public FileVO selectFileInf(FileVO fileVO) throws Exception;
	
	public void deleteCOMTNFILE(FileVO fileVO) throws Exception;
	
	public List<FileVO> selectFileListByFileNm(FileVO fileVO) throws Exception;
	
	public int selectFileListCntByFileNm(FileVO fileVO) throws Exception;
	
	public List<FileVO> selectImageFileList(FileVO fileVO) throws Exception;
}
