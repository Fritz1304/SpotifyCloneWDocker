package musicfy.dto;

import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class AlbumRequest {
	private String id;
	private String name;
	private String desc;
	private String bgColor;
	private MultipartFile imageFile;
	
	public AlbumRequest(String id, String name, String desc, String bgColor, MultipartFile imageFile) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.bgColor = bgColor;
		this.imageFile = imageFile;
	}

	public AlbumRequest() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bgColor, desc, id, imageFile, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumRequest other = (AlbumRequest) obj;
		return Objects.equals(bgColor, other.bgColor) && Objects.equals(desc, other.desc)
				&& Objects.equals(id, other.id) && Objects.equals(imageFile, other.imageFile)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "AlbumRequest [id=" + id + ", name=" + name + ", desc=" + desc + ", bgColor=" + bgColor + ", imageFile="
				+ imageFile + "]";
	}
	
	
	
	
	
}
