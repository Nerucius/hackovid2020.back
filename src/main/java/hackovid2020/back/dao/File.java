package hackovid2020.back.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import hackovid2020.back.dao.support.FileType;

@Entity(name="file")
public class File extends EntityObject {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fileId;
	
	private String name;
	
	private FileType fileType;
	
	private String url;
	
	
	private File(String name, FileType fileType, String url, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.name = name;
		this.fileType = fileType;
		this.url = url;
	}
	
	public static File createFile(String name, FileType fileType, String url, Date createdAt, Date modifiedAt) {
		return new File(name, fileType, url, createdAt, modifiedAt);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getFileId() {
		return fileId;
	}
}
