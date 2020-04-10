package hackovid2020.back.dto.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.support.FileType;

import java.util.Calendar;

public class FileRequest {
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private FileType fileType;
	
	@JsonProperty
	private String url;

	@JsonCreator
	private FileRequest(String name, FileType fileType, String url) {
		this.name = name;
		this.fileType = fileType;
		this.url = url;
	}
	
	public File toFile() {
		return File.createFile(name, fileType, url, null,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
	}

	public String getName() {
		return name;
	}

	public FileType getFileType() {
		return fileType;
	}

	public String getUrl() {
		return url;
	}
	
	
}
