package musicfy.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "songs")
public class Song {

	@Id
	@JsonProperty("_id")
	private String id;
	private String name;
	private String desc;
	private String album;
	private String image;
	private String file;
	private String duration;
		
	
	public Song() {
		super();
	}
	
	public Song(String id, String name, String desc, String album, String image, String file, String duration) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.album = album;
		this.image = image;
		this.file = file;
		this.duration = duration;
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
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", desc=" + desc + ", album=" + album + ", image=" + image
				+ ", file=" + file + ", duration=" + duration + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(album, desc, duration, file, id, image, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(album, other.album) && Objects.equals(desc, other.desc)
				&& Objects.equals(duration, other.duration) && Objects.equals(file, other.file)
				&& Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name);
	}
	
	
	
}
