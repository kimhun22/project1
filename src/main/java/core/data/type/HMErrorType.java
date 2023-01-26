package core.data.type;

@Deprecated
public enum HMErrorType {
	UploadError(9100),
	UploadEmptyMultipartFile(9101),
	UploadCheckPath(9102),
	UploadNotAllowExtension(9111),
	UploadDenyExtension(9112);
	
	private final int value;
	
	private HMErrorType(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
}
