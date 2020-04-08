package hackovid2020.back.dao;

import java.util.Date;

public class EntityObject {

	private Date createdAt;
	private Date modifiedAt;
	
	public EntityObject(Date createdAt, Date modifiedAt) {
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	
	
}
