package hackovid2020.back.dao;

import hackovid2020.back.dao.support.FileType;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity(name="file")
public class File extends EntityObject {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fileId;
	
	private String name;
	
	private FileType fileType;
	
	private String url;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="shop_shop_id", nullable=true)
	private Shop shop;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="product_product_id", nullable=true)
	private Product product;
	
	private File(String name, FileType fileType, String url, Shop shop, Product product, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.name = name;
		this.fileType = fileType;
		this.url = url;
		this.shop = shop;
	}
	
	public static File createFile(String name, FileType fileType, String url, Shop shop, Product product, Date createdAt, Date modifiedAt) {
		return new File(name, fileType, url, shop, product, createdAt, modifiedAt);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
