package musicfy.dto;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import musicfy.model.Album;

public class AlbumListResponse {
	
	
	
	private boolean success;
	private List<Album> albums;
	
	public AlbumListResponse(boolean success, List albums) {
		this.success = success;
		this.albums = albums;
	}

	public AlbumListResponse() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "AlbumListResponse [success=" + success + ", albums=" + albums + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(albums, success);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumListResponse other = (AlbumListResponse) obj;
		return Objects.equals(albums, other.albums) && success == other.success;
	}
	
	

}
