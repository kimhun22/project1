package core.data;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import core.data.type.FileExtensionType;
import core.exception.HMException;
import core.exception.upload.DenyExtentionException;
import core.exception.upload.EmptyMultipartFileException;
import core.exception.upload.EmptyUploadPathException;
import core.exception.upload.NotAllowExtensionException;
import core.exception.upload.NullServletContextPathException;
import core.exception.upload.NullUploadPathException;
import core.exception.upload.SaveFailException;

public class UploadFileForUrl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileForUrl.class);
	
	private String name;
	
	private String extension;
	
	private String originalName;
	
	private Long size;
	
	private String contentType;
	
	private String url;
	
	private String path;
	
	
	private boolean useUniqueID = false;
	
	private String contextPath;
	
	private String uploadPath;
	
	private MultipartFile multipartFile;
	
	private boolean isSaved = false;
	
	private String[] allowExts;
	
	private String[] denyExts;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isUseUniqueID() {
		return useUniqueID;
	}

	public void setUseUniqueID(boolean useUniqueID) {
		this.useUniqueID = useUniqueID;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	public String[] getAllowExts() {
		String[] allowExtsCp = Arrays.copyOf(allowExts, allowExts.length);
		return allowExtsCp;
	}

	public void setAllowExts(String[] allowExts) {

		this.allowExts = new String[allowExts.length];
		
		int i = 0;
		for  ( String data : allowExts ) {
			this.allowExts[i] = data;
			i++;
		}
		
	}

	public String[] getDenyExts() {
		String[] denyExtsCp = Arrays.copyOf(denyExts, denyExts.length);
		return denyExtsCp;
	}

	public void setDenyExts(String[] denyExts) {
		
		this.denyExts = new String[denyExts.length];
		
		int i = 0;
		for  ( String data : denyExts ) {
			this.denyExts[i] = data;
			i++;
		}
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public UploadFileForUrl(){
	}
	
	public UploadFileForUrl(MultipartFile multipartFile){
		this.setMultipartFile(multipartFile);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String[] allowExts){
		this.setMultipartFile(multipartFile, allowExts);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, boolean useUniqueID){
		this.useUniqueID = useUniqueID;
		this.setMultipartFile(multipartFile);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, boolean useUniqueID, String[] allowExts){
		this.useUniqueID = useUniqueID;
		this.setMultipartFile(multipartFile, allowExts);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, boolean useUniqueID, FileExtensionType allowExts){
		this.useUniqueID = useUniqueID;
		this.setMultipartFile(multipartFile, allowExts.getValue());
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath){
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath, String[] allowExts){
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile, allowExts);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath, FileExtensionType allowExts){
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile, allowExts.getValue());
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath, boolean useUniqueID){
		this.useUniqueID = useUniqueID;
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath, boolean useUniqueID, String[] allowExts){
		this.useUniqueID = useUniqueID;
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile, allowExts);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath, boolean useUniqueID, FileExtensionType allowExts){
		this.useUniqueID = useUniqueID;
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile, allowExts.getValue());
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath, boolean useUniqueID, String[] allowExts, String[] denyExts){
		this.useUniqueID = useUniqueID;
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile, allowExts, denyExts);
	}
	
	public UploadFileForUrl(MultipartFile multipartFile, String requestPath, String uploadPath, boolean useUniqueID, FileExtensionType allowExts, FileExtensionType denyExts){
		this.useUniqueID = useUniqueID;
		this.contextPath = requestPath;
		this.uploadPath = uploadPath;
		this.setMultipartFile(multipartFile, allowExts.getValue(), denyExts.getValue());
	}
	
	public void save(){
		save(false);
	}
	
	public void save(boolean isForce){
		if(multipartFile == null){
			//throw new HMException("empty multipart file", HMErrorType.UploadEmptyMultipartFile);
			if(!isForce)
				throw new EmptyMultipartFileException();
		} else {
			if(contextPath == null){
				//throw new HMException("check requestPath", HMErrorType.UploadCheckPath);
				throw new NullServletContextPathException();
			}
			if(uploadPath == null){
				//throw new HMException("check uploadPath", HMErrorType.UploadCheckPath);
				throw new NullUploadPathException();
			}
			if(allowExts != null){
				if(!isAllowExtension(allowExts)){
					//throw new HMException(extention + " is not allow extention", HMErrorType.UploadNotAllowExtension);
					throw new NotAllowExtensionException(extension, allowExts);
				}
			}
			if(denyExts != null){
				if(isDenyExtension(denyExts)){
					//throw new HMException(extention + " is deny extention", HMErrorType.UploadDenyExtension);
					throw new DenyExtentionException(extension, denyExts);
				}
			}
			
			String realPath = FilenameUtils.normalize(contextPath + File.separator + uploadPath);
			File path = new File(realPath);
			if(!path.exists()){
				path.mkdirs();
			}
			
			File file = new File(path, this.name);
			try {
				this.multipartFile.transferTo(file);
				this.path = realPath;
				this.url = FilenameUtils.separatorsToUnix(FilenameUtils.normalize(uploadPath + File.separator + this.name));
				this.isSaved = true;

			} catch (NullPointerException ne) {
			 	LOGGER.error(ne.getMessage());
				throw new HMException(ne);
			} catch (Exception e) {
				//throw new HMException("fail save", e, HMErrorType.UploadError);
				throw new SaveFailException();
			}
		}
	}
	
	public void delete(){
		if(this.path == null){
			//throw new HMException("empty path", HMErrorType.UploadCheckPath);
			throw new EmptyUploadPathException();
		}
		
		File f = new File(FilenameUtils.normalize(this.path + File.separator + this.name));
		if(f.isFile())
			f.delete();
	}
	
	public void setMultipartFile(MultipartFile multipartFile, String[] allowExts){
		setMultipartFile(multipartFile);
		if(this.multipartFile != null){
			if(!isAllowExtension(allowExts))
				//throw new HMException("not allow extension", HMErrorType.UploadNotAllowExtension);
				throw new NotAllowExtensionException(extension, allowExts);
		}
	}
	
	public void setMultipartFile(MultipartFile multipartFile, String[] allowExts, String[] denyExts){
		setMultipartFile(multipartFile);
		if(this.multipartFile != null){
			if(!isAllowExtension(allowExts))
				//throw new HMException("not allow extension", HMErrorType.UploadNotAllowExtension);
				throw new NotAllowExtensionException(extension, allowExts);
			if(isDenyExtension(denyExts))
				//throw new HMException("deny extension", HMErrorType.UploadDenyExtension);
				throw new DenyExtentionException(extension, denyExts);
		}
	}
	
	public void setMultipartFile(MultipartFile multipartFile){
		this.multipartFile = multipartFile;
		
		if(this.multipartFile != null){
			this.extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			this.contentType = multipartFile.getContentType();
			this.originalName = multipartFile.getOriginalFilename();
			this.size = multipartFile.getSize();
			
			if(this.useUniqueID){
				this.name = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase() + "." + this.extension;
			} else {
				this.name = multipartFile.getOriginalFilename();
			}
		}
	}
	
	public void setAllowExts(String allowExts, String seperator){
		if(allowExts != null && allowExts.trim() != ""){
			this.allowExts = allowExts.trim().split("\\s*" + seperator + "\\s*");
		}
	}
	
	public void setDenyExts(String denyExts, String seperator){
		if(denyExts != null && denyExts.trim() != ""){
			this.denyExts = denyExts.trim().split("\\s*" + seperator + "\\s*");
		}
	}
	
	public boolean isAllowExtension(String[] allowExts){
		if(allowExts == null || allowExts.length == 0) return true;
		if(allowExts != null && allowExts.length > 0){
			for (String allowExt : allowExts) {
	            if (this.extension.equals(allowExt.trim().toLowerCase()) || allowExt.equals("")) {
	                return true;
	            }
	        }
		}
		return false;
	}
	
	public boolean isDenyExtension(String[] denyExts){
		if(denyExts == null || denyExts.length == 0) return false;
		if(denyExts != null && denyExts.length > 0){
			for (String denyExt : denyExts) {
	            if (this.extension.equals(denyExt.trim().toLowerCase()) && !denyExt.equals("")) {
	                return true;
	            }
	        }
		}
		return false;
	}
	
	public boolean isAllowSize(long allowSize){
		if(this.size != null){
			if(allowSize >= this.size)
				return true;
		}
		return false;
	}
}
