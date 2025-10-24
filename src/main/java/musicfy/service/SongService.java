package musicfy.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import musicfy.dto.SongListResponse;
import musicfy.dto.SongRequest;
import musicfy.model.Song;
import musicfy.repository.SongRepository;

@Service
public class SongService {

	private final SongRepository songRepository;
	private final Cloudinary cloudinary;
	
	public SongService(SongRepository songRepository, Cloudinary cloudinary) {
		super();
		this.songRepository = songRepository;
		this.cloudinary = cloudinary;
	}
	
	public Song addSong(SongRequest request) throws IOException {
		
		Map<String, Object> audioUploadResult = cloudinary.uploader().upload(request.getAudioFile().getBytes(), ObjectUtils.asMap("resource_type","video"));
		Map<String, Object> imageUploadResult = cloudinary.uploader().upload(request.getImageFile().getBytes(), ObjectUtils.asMap("resource_type","image"));
		
		Double durationSeconds = (Double)audioUploadResult.get("duration");
		
		String duration = formatDuration(durationSeconds);
		
		Song newSong = new Song();
		
		newSong.setId(request.getId());
		newSong.setName(request.getName());
		newSong.setDesc(request.getDesc());
		newSong.setAlbum(request.getAlbum());
		newSong.setImage(imageUploadResult.get("secure_url").toString());
		newSong.setFile(audioUploadResult.get("secure_url").toString());
		newSong.setDuration(duration);
		
		return songRepository.save(newSong);
	} 
	
	
	private String formatDuration(Double durationSeconds) {
		
		if(durationSeconds == null) {
			return "0:00";
		}
		
		int minutes = (int)(durationSeconds / 60);
		int seconds = (int)(durationSeconds % 60);
		
		return String.format("%d:%02d", minutes, seconds);
	}




	public SongListResponse getAllSongs() {
		return new SongListResponse(true, songRepository.findAll());
	}
	
	
	public Boolean removeSong(String id) {
		Song existingSong = songRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Song not found"));
		songRepository.delete(existingSong);
		return true;
	}
}
