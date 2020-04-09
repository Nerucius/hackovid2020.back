package hackovid2020.back.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hackovid2020.back.dao.support.FileType;

@Entity(name="file")
public class File extends EntityObject {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fileId;
	
	private String name;
	
	private FileType fileType;
	
	private String url;
	
	@ManyToOne
	@JoinColumn(name="shop_id", nullable=true)
	private Shop shop;
	
	
	private File(String name, FileType fileType, String url, Shop shop, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.name = name;
		this.fileType = fileType;
		this.url = url;
		this.shop = shop;
	}
	
	public static File createFile(String name, FileType fileType, String url, Shop shop, Date createdAt, Date modifiedAt) {
		return new File(name, fileType, url, shop, createdAt, modifiedAt);
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	
	
}
