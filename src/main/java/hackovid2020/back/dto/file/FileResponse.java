package hackovid2020.back.dto.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.support.FileType;

public class FileResponse {
	
	@JsonProperty
	private Long fileId;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private FileType fileType;
	
	@JsonProperty
	private String url;
	
	@JsonCreator
	private FileResponse(Long fileId, String name, FileType fileType, String url) {
		this.fileId = fileId;
		this.name = name;
		this.fileType = fileType;
		this.url = url;
	}
	
	public static FileResponse ofFile(File file) {
		return new FileResponse(file.getFileId(), file.getName(), file.getFileType(), file.getUrl());
	}

}
