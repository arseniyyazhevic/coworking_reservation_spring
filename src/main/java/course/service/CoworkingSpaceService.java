package course.service;

import course.entity.CoworkingSpaceEntity;
import course.repository.CoworkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoworkingSpaceService {

    private final CoworkingSpaceRepository coworkingSpaceRepository;

    @Autowired
    public CoworkingSpaceService(CoworkingSpaceRepository coworkingSpaceRepository) {
        this.coworkingSpaceRepository = coworkingSpaceRepository;
    }

    @Cacheable(value = "coworkingSpaces", key = "#id")
    public Optional<CoworkingSpaceEntity> getCoworkingSpaceById(Long id) {
        return coworkingSpaceRepository.findById(id);
    }

    @CachePut(value = "coworkingSpaces", key = "#coworkingSpaceEntity.id")
    public void addCoworkingSpace(CoworkingSpaceEntity coworkingSpaceEntity) {
        coworkingSpaceRepository.save(coworkingSpaceEntity);
    }


    @CachePut(value = "coworkingSpaces", key = "#coworkingSpaceEntity.id")
    public void updateAllInformationAboutCoworkingSpace(CoworkingSpaceEntity coworkingSpaceEntity) {
        coworkingSpaceRepository.save(coworkingSpaceEntity);
    }

    @CacheEvict(value = "coworkingSpaces", key = "#id")
    public void removeCoworkingSpace(Long id) {
        coworkingSpaceRepository.delete(getCoworkingSpaceById(id).orElseThrow());
    }

    @Cacheable(value = "allCoworkingSpaces")
    public List<CoworkingSpaceEntity> getAllCoworkingSpaces() {
        return coworkingSpaceRepository.findAll();
    }

}

