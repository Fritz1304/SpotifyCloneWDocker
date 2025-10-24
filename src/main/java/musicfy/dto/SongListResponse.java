package musicfy.dto;

import java.util.List;

import musicfy.model.Song;

public class SongListResponse {
	
	private Boolean success;
	private List<Song> songs;
	
	
	public SongListResponse() {
		super();
	}

	public SongListResponse(Boolean success, List<Song> songs) {
		super();
		this.success = success;
		this.songs = songs;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "SongListResponse [success=" + success + ", songs=" + songs + "]";
	}
	
	

}
