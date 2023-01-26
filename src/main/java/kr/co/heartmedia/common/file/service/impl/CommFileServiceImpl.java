package kr.co.heartmedia.common.file.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import core.util.FileUtil;
import core.util.PropertiesUtil;
import core.util.StringUtil;
import kr.co.heartmedia.common.file.mapper.CommFileMapper;
import kr.co.heartmedia.common.file.service.CommFileService;
import kr.co.heartmedia.vo.TnAtchFileTbVO;
import kr.co.heartmedia.vo.extend.TnAtchFileTbExVO;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;


@Service
public class CommFileServiceImpl implements CommFileService {

	@Resource
	private CommFileMapper commFileMapper;

	@Autowired
	private HttpSession session;


	@Override
	public List<TnAtchFileTbVO> selectFileList(TnAtchFileTbVO tnAtchFileTbVO) throws Exception {

		return commFileMapper.selectFileList(tnAtchFileTbVO);

	}

	@Override
	public TnAtchFileTbVO selectFile(TnAtchFileTbVO tnAtchFileTbVO) throws Exception {

		return commFileMapper.selectFile(tnAtchFileTbVO);

	}

	@Override
	public boolean isAccessFile(TnAtchFileTbVO tnAtchFileTbVO) throws Exception {

//		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
//		// 관리자 일 경우
//		if  ( loginInfo.isSuperAdmin() || loginInfo.isAdmin() )  {
//			// 모든 파일 접근 가능
//			return true;
//		}
//
//		// 파일 등록자 회원 정보 조회
//		TnUserTbVO regUserInfo = commFileMapper.selectFileRegUserInfo(raAtchFileTbVO);
//
//		// 등록자 회원 정보가 없을 경우
//		else if  ( StringUtil.isEmpty(regUserInfo) )  {
//			// 파일 접근 불가능
//			return false;
//		}
//		// 관리자가 등록한 게시물 일 경우
//		if  ( regUserInfo.isSuperAdmin() || regUserInfo.isAdmin() )  {
//			// 파일 접근 가능
//			return true;
//		}
//		// 기관 책임자 이고, 등록자와 같은 기관 일 경우
//		if  ( loginInfo.isRspnberUser() && regUserInfo.getInsttCode().equals(loginInfo.getInsttCode()) )  {
//			// 파일 접근 가능
//			return true;
//		}
//		// 기관 사용자 이고, 등록자 회원 NO 와 같을 경우
//		if  ( loginInfo.isUser() && regUserInfo.getUserNo().equals(loginInfo.getUserNo()) )  {
//			// 파일 접근 가능
//			return true;
//		}
//		// 해당 사항이 없을 경우
//		else  {
			// 모든 파일 접근 불가능
			return false;
//		}

	}

	@Override
	public Map<String, Object> crudFiles(String allowFileType, String boardType, Integer fileNo, String delSn, int maxFileUploadCount, List<MultipartFile> multiRequest, String hmSiteCode) throws Exception {

		String errorMessage = null;
		String[] allowFileTypeArr = null;
		boolean fileRegistYn = true;
		int fileCnt = 0;

		LinkedHashMap<Integer, MultipartFile> files = new LinkedHashMap<Integer, MultipartFile>();
		// 첨부파일이 있을 경우
		if  ( null != multiRequest && multiRequest.size() > 0 && StringUtil.isNotEmpty(multiRequest.get(0).getOriginalFilename()))  {
			if  ( StringUtils.isNotEmpty(allowFileType) )  {
				allowFileTypeArr = StringUtils.split(allowFileType.toUpperCase(), ",");
			}

			int fileKey = 1;
			for  ( MultipartFile file : multiRequest )  {
				boolean isAllow = false;
				// 확장자 검증
				if  ( allowFileTypeArr != null && allowFileTypeArr.length > 0 )  {
					for  ( String type : allowFileTypeArr )  {
						if  ( StringUtils.equalsIgnoreCase(type, StringUtils.substringAfterLast(FilenameUtils.getName(file.getOriginalFilename()), ".").toUpperCase()) )  {
							isAllow = true;
							break;
						}
					}
				} else {
					isAllow = true;
				}

				// 업로드 불가능한 확장자 일 경우
				if  ( !isAllow )  {
					String extn = StringUtils.substringAfterLast(FilenameUtils.getName(file.getOriginalFilename()), ".");
					errorMessage = extn + " 확장자는 업로드를 할 수 없습니다.";
					fileRegistYn = false;
					break;
				}

				files.put(fileKey, file);
				fileCnt++;
				fileKey++;
			}
		}

		// 파일 처리가 가능한 경우
		if  ( fileRegistYn )  {
			TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");

			// 파일 no 가 있을 경우
			if  ( StringUtil.isNotEmpty(fileNo) )  {
				// 파일 목록 중 삭제하는 파일이 있을 경우
				if  ( StringUtils.isNotEmpty(delSn) )  {
					String[] deleteSnArr = delSn.split(",");
					for  ( String sn : deleteSnArr )  {
						TnAtchFileTbVO tnAtchFileTbVO = new TnAtchFileTbVO();
						tnAtchFileTbVO.setFileNo(fileNo);
						tnAtchFileTbVO.setFileSn(Integer.parseInt(sn));
						if  ( null != loginInfo )
							tnAtchFileTbVO.setUpdateUserId(loginInfo.getLoginId());

						// 파일 삭제 처리
						commFileMapper.deleteFile(tnAtchFileTbVO);
					}
				}

				fileCnt += commFileMapper.selectFileCnt(fileNo);
			}

			// 최대 업로드 가능 파일 개수를 초과했을 경우
			if  ( fileCnt > maxFileUploadCount )  {
				errorMessage = "파일 업로드 최대갯수를 초과 했습니다.\n" + "(최대갯수 : " + maxFileUploadCount + ")";
				fileRegistYn = false;
			}

			if  ( fileRegistYn && null != multiRequest && multiRequest.size() > 0 )  {
				List<TnAtchFileTbVO> uploadFileList = new ArrayList<TnAtchFileTbVO>();

				String keyStr = boardType + "_";   // ex) "JOBNEWS_"
				int fileKey = 0;
				String storePathString = "";
				if  ("".equals(boardType) || boardType == null)
					storePathString = PropertiesUtil.getProperty("globals.upload.path");
				else
					storePathString = PropertiesUtil.getProperty("globals.upload.path") + File.separator + boardType;

				File saveFolder = new File(new FileUtil().filePathBlackList(storePathString));

				if  ( !saveFolder.exists() || saveFolder.isFile() )  {
					saveFolder.mkdirs();
				}

				Iterator<Entry<Integer, MultipartFile>> itr = files.entrySet().iterator();
				String filePath = "";
				while (itr.hasNext()) {
					Entry<Integer, MultipartFile> entry = itr.next();
					fileKey++;

					MultipartFile file = entry.getValue();
					String orginFileName = file.getOriginalFilename();
					// 원 파일명이 없는 경우 건너뜀 (첨부가 되지 않은 input file type)
					if  ( "".equals(orginFileName) )  {
						continue;
					}

					int index = orginFileName.lastIndexOf(".");
					String fileExt = orginFileName.substring(index + 1);
//					String newName = keyStr + new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.KOREAN).format(System.currentTimeMillis()) + fileKey + "." + fileExt;
					String newName = keyStr + new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.KOREAN).format(System.currentTimeMillis()) + fileKey;
					long size = file.getSize();
					filePath = storePathString + File.separator + newName;
					file.transferTo(new File(filePath));

					TnAtchFileTbVO tnAtchFileTbVO = new TnAtchFileTbVO();
					tnAtchFileTbVO.setFileNo(fileNo);
					tnAtchFileTbVO.setFileExtsn(fileExt);
					tnAtchFileTbVO.setFilePath(storePathString);
					tnAtchFileTbVO.setFileSize(size);

					// 본래 파일 이름에 특수문자를 공백으로 치환
					String FileNameCheck = orginFileName.substring(0, index);
					if  ( !FileNameCheck.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|\\s|\\(|\\)|[-]|_]*") )  {
						FileNameCheck = FileNameCheck.replaceAll("[!@#$%`'&*\\{\\}\\^\\[\\]\\\\,.:+;?/]", "_");
						tnAtchFileTbVO.setOrginlFileNm(FileNameCheck + "." + fileExt);
					} else {
						tnAtchFileTbVO.setOrginlFileNm(orginFileName);
					}

					tnAtchFileTbVO.setFileNm(newName);
					if  ( null != loginInfo )
						tnAtchFileTbVO.setRegistUserId(loginInfo.getLoginId());

					uploadFileList.add(tnAtchFileTbVO);
				}

				if  ( uploadFileList.size() != 0 )  {
					Iterator<?> iter = uploadFileList.iterator();
					while  ( iter.hasNext() )  {
						TnAtchFileTbVO tnAtchFileTbVO = (TnAtchFileTbVO) iter.next();

						tnAtchFileTbVO.setHmSiteCode(hmSiteCode);
						if  ( null != loginInfo )  {
							tnAtchFileTbVO.setRegistUserId(loginInfo.getLoginId());
							tnAtchFileTbVO.setUpdateUserId(loginInfo.getLoginId());
						}

						if  ( StringUtil.isEmpty(fileNo) )  {
							commFileMapper.insertFileSelectKeyFileNo(tnAtchFileTbVO);

							fileNo = tnAtchFileTbVO.getFileNo();
						}
						else  {
							tnAtchFileTbVO.setFileNo(fileNo);

							commFileMapper.insertFileSelectKeyFileSn(tnAtchFileTbVO);
						}
					}
				}

				fileRegistYn = true;
			}
			else if  ( fileCnt == 0 )  {
				fileNo = null;
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("bool", fileRegistYn);
		resultMap.put("errorMsg", errorMessage);
		resultMap.put("fileNo", fileNo);

		return resultMap;

	}

	@Override
	public Map<String, Object> copyFiles(String allowFileType, String boardType, Integer fileNo, String delSn, int maxFileUploadCount, List<MultipartFile> multiRequest) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String errorMessage = null;
		String[] allowFileTypeArr = null;
		boolean fileRegistYn = true;
		int fileCnt = 0;

		LinkedHashMap<Integer, MultipartFile> files = new LinkedHashMap<Integer, MultipartFile>();
		// 첨부파일이 있을 경우
		if  ( null != multiRequest && multiRequest.size() > 0 )  {
			if  ( StringUtils.isNotEmpty(allowFileType) )  {
				allowFileTypeArr = StringUtils.split(allowFileType.toUpperCase(), ",");
			}

			int fileKey = 1;
			for  ( MultipartFile file : multiRequest )  {
				boolean isAllow = false;
				// 확장자 검증
				if  ( allowFileTypeArr != null && allowFileTypeArr.length > 0 )  {
					for  ( String type : allowFileTypeArr )  {
						if  ( StringUtils.equalsIgnoreCase(type, StringUtils.substringAfterLast(FilenameUtils.getName(file.getOriginalFilename()), ".").toUpperCase()) )  {
							isAllow = true;
							break;
						}
					}
				} else {
					isAllow = true;
				}

				// 업로드 불가능한 확장자 일 경우
				if  ( !isAllow )  {
					String extn = StringUtils.substringAfterLast(FilenameUtils.getName(file.getOriginalFilename()), ".");
					errorMessage = extn + " 확장자는 업로드를 할 수 없습니다.";
					fileRegistYn = false;
					break;
				}

				files.put(fileKey, file);
				fileCnt++;
				fileKey++;
			}
		}

		// 파일 처리가 가능한 경우
		if  ( fileRegistYn )  {
			TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");

			// 파일 no 가 있을 경우
			if  ( StringUtil.isNotEmpty(fileNo) )  {
				TnAtchFileTbExVO tnAtchFileTbExVO = new TnAtchFileTbExVO();
				tnAtchFileTbExVO.setCopyFileNo(fileNo);
				if  ( null != loginInfo )  {
					tnAtchFileTbExVO.setRegistUserId(loginInfo.getLoginId());
					tnAtchFileTbExVO.setUpdateUserId(loginInfo.getLoginId());
				}
				// 파일 정보 복사 등록 처리
				int resultCopy = commFileMapper.insertCopyFile(tnAtchFileTbExVO);
				if  ( resultCopy > 0 )  {
					fileNo = tnAtchFileTbExVO.getFileNo();
				}
				else  {
					resultMap.put("bool", false);
					resultMap.put("errorMsg", "파일 정보 복사에 실패하였습니다.");
					resultMap.put("fileNo", fileNo);

					return resultMap;
				}

				// 파일 목록 중 삭제하는 파일이 있을 경우
				if  ( StringUtils.isNotEmpty(delSn) )  {
					String[] deleteSnArr = delSn.split(",");
					for  ( String sn : deleteSnArr )  {
						TnAtchFileTbVO tnAtchFileTbVO = new TnAtchFileTbVO();
						tnAtchFileTbVO.setFileNo(fileNo);
						tnAtchFileTbVO.setFileSn(Integer.parseInt(sn));
						if  ( null != loginInfo )
							tnAtchFileTbVO.setUpdateUserId(loginInfo.getLoginId());

						// 파일 삭제 처리
						commFileMapper.deleteFile(tnAtchFileTbVO);
					}
				}

				fileCnt += commFileMapper.selectFileCnt(fileNo);
			}

			// 최대 업로드 가능 파일 개수를 초과했을 경우
			if  ( fileCnt > maxFileUploadCount )  {
				errorMessage = "파일 업로드 최대갯수를 초과 했습니다.\n" + "(최대갯수 : " + maxFileUploadCount + ")";
				fileRegistYn = false;
			}

			if  ( fileRegistYn && null != multiRequest && multiRequest.size() > 0 )  {
				List<TnAtchFileTbVO> uploadFileList = new ArrayList<TnAtchFileTbVO>();

				String keyStr = boardType + "_";   // ex) "JOBNEWS_"
				int fileKey = 0;
				String storePathString = "";
				if  ("".equals(boardType) || boardType == null)
					storePathString = PropertiesUtil.getProperty("globals.upload.path");
				else
					storePathString = PropertiesUtil.getProperty("globals.upload.path") + File.separator + boardType;

				File saveFolder = new File(new FileUtil().filePathBlackList(storePathString));

				if  ( !saveFolder.exists() || saveFolder.isFile() )  {
					saveFolder.mkdirs();
				}

				Iterator<Entry<Integer, MultipartFile>> itr = files.entrySet().iterator();
				String filePath = "";
				while (itr.hasNext()) {
					Entry<Integer, MultipartFile> entry = itr.next();
					fileKey++;

					MultipartFile file = entry.getValue();
					String orginFileName = file.getOriginalFilename();
					// 원 파일명이 없는 경우 건너뜀 (첨부가 되지 않은 input file type)
					if  ( "".equals(orginFileName) )  {
						continue;
					}

					int index = orginFileName.lastIndexOf(".");
					String fileExt = orginFileName.substring(index + 1);
//					String newName = keyStr + new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.KOREAN).format(System.currentTimeMillis()) + fileKey + "." + fileExt;
					String newName = keyStr + new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.KOREAN).format(System.currentTimeMillis()) + fileKey;
					long size = file.getSize();
					filePath = storePathString + File.separator + newName;
					file.transferTo(new File(filePath));

					TnAtchFileTbVO tnAtchFileTbVO = new TnAtchFileTbVO();
					tnAtchFileTbVO.setFileNo(fileNo);
					tnAtchFileTbVO.setFileExtsn(fileExt);
					tnAtchFileTbVO.setFilePath(storePathString);
					tnAtchFileTbVO.setFileSize(size);

					// 본래 파일 이름에 특수문자를 공백으로 치환
					String FileNameCheck = orginFileName.substring(0, index);
					if  ( !FileNameCheck.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|\\s|\\(|\\)|[-]|_]*") )  {
						FileNameCheck = FileNameCheck.replaceAll("[!@#$%`'&*\\{\\}\\^\\[\\]\\\\,.:+;?/]", "_");
						tnAtchFileTbVO.setOrginlFileNm(FileNameCheck + "." + fileExt);
					} else {
						tnAtchFileTbVO.setOrginlFileNm(orginFileName);
					}

					tnAtchFileTbVO.setFileNm(newName);
					if  ( null != loginInfo )
						tnAtchFileTbVO.setRegistUserId(loginInfo.getLoginId());

					uploadFileList.add(tnAtchFileTbVO);
				}

				if  ( uploadFileList.size() != 0 )  {
					Iterator<?> iter = uploadFileList.iterator();
					while  ( iter.hasNext() )  {
						TnAtchFileTbVO tnAtchFileTbVO = (TnAtchFileTbVO) iter.next();

						if  ( null != loginInfo )  {
							tnAtchFileTbVO.setRegistUserId(loginInfo.getLoginId());
							tnAtchFileTbVO.setUpdateUserId(loginInfo.getLoginId());
						}

						if  ( StringUtil.isEmpty(fileNo) )  {
							commFileMapper.insertFileSelectKeyFileNo(tnAtchFileTbVO);

							fileNo = tnAtchFileTbVO.getFileNo();
						}
						else  {
							tnAtchFileTbVO.setFileNo(fileNo);

							commFileMapper.insertFileSelectKeyFileSn(tnAtchFileTbVO);
						}
					}
				}

				fileRegistYn = true;
			}
			else if  ( fileCnt == 0 )  {
				fileNo = null;
			}
		}

		resultMap.put("bool", fileRegistYn);
		resultMap.put("errorMsg", errorMessage);
		resultMap.put("fileNo", fileNo);

		return resultMap;

	}

	@Override
	public List<MultipartFile> makeUploadFile(List<MultipartFile> files) throws Exception {

		List<MultipartFile> result = new ArrayList<MultipartFile>();

		for  ( MultipartFile file : files )  {
			if(!file.isEmpty() && file.getSize() > 0) {
				result.add(file);
			}

		}
		return result;
	}

}
