package musicfy.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import musicfy.dto.AlbumListResponse;
import musicfy.dto.AlbumRequest;
import musicfy.model.Album;
import musicfy.repository.AlbumRepository;

@Service
public class AlbumService {
	
	private final AlbumRepository albumRepository;
	private final Cloudinary cloudinary;
	
	public AlbumRepository getAlbumRepository() {
		return albumRepository;
	}



	public Cloudinary getCloudinary() {
		return cloudinary;
	}


	public AlbumService(AlbumRepository albumRepository, Cloudinary cloudinary) {
		super();
		this.albumRepository = albumRepository;
		this.cloudinary = cloudinary;
		}
	
	
	public AlbumListResponse getAllAlbums(){	
		return new AlbumListResponse(true, albumRepository.findAll());
	}
	
	//Here we need the album id, when de id come just check if the album w this id exists
	// if it exist call albumRepository to delete it from the dataBase and return true.
	
	public boolean removeAlbum(String id) {
		Album existingAlbum = albumRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Album not found"));
		albumRepository.delete(existingAlbum);
		return true;
	}
	
	public Album addAlbum(AlbumRequest albumRequest) throws IOException {
		Map<String, Object> imageUploadResult = cloudinary.uploader().upload(albumRequest.getImageFile().getBytes(),ObjectUtils.asMap("resource_type","image"));
		
		Album newAlbum = new Album();
		
		newAlbum.setName(albumRequest.getName());
		newAlbum.setDesc(albumRequest.getDesc());
		newAlbum.setBgcolor(albumRequest.getBgColor());
		newAlbum.setImageUrl(imageUploadResult.get("secure_url").toString());
		
		return albumRepository.save(newAlbum);
	}
	
	
	

}
