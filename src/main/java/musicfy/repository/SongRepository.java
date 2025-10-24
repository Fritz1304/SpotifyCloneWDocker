package musicfy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import musicfy.model.Song;

public interface SongRepository extends MongoRepository<Song, String>{
	
}
