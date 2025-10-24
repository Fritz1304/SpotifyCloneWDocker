package musicfy.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "albums")
public class Album {
	@Id
	@JsonProperty("_id")
	private String id;
	
	private String name;
	private String desc;
	private String bgcolor;
	private String imageUrl;
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
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", desc=" + desc + ", bgcolor=" + bgcolor + ", imageUrl="
				+ imageUrl + "]";
	}
	public Album(String id, String name, String desc, String bgcolor, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.bgcolor = bgcolor;
		this.imageUrl = imageUrl;
	}
	public Album() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(bgcolor, desc, id, imageUrl, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(bgcolor, other.bgcolor) && Objects.equals(desc, other.desc)
				&& Objects.equals(id, other.id) && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(name, other.name);
	}

	
	
	
}
