package musicfy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import musicfy.model.Album;


public interface AlbumRepository extends MongoRepository<Album, String>{
	

}
