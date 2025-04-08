package course.service;

import course.repository.CoworkingSpaceRepository;
import course.entity.CoworkingSpace;
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
    public Optional<CoworkingSpace> getCoworkingSpaceById(Long id) {
        return coworkingSpaceRepository.findById(id);
    }

    @CachePut(value = "coworkingSpaces", key = "#coworkingSpace.id")
    public void addCoworkingSpace(CoworkingSpace coworkingSpace) {
        coworkingSpaceRepository.save(coworkingSpace);
    }


    @CachePut(value = "coworkingSpaces", key = "#coworkingSpace.id")
    public void updateAllInformationAboutCoworkingSpace(CoworkingSpace coworkingSpace) {
        coworkingSpaceRepository.save(coworkingSpace);
    }

    @CacheEvict(value = "coworkingSpaces", key = "#id")
    public void removeCoworkingSpace(Long id) {
        coworkingSpaceRepository.delete(getCoworkingSpaceById(id).orElseThrow());
    }

    @Cacheable(value = "allCoworkingSpaces")
    public List<CoworkingSpace> getAllCoworkingSpaces() {
        return coworkingSpaceRepository.findAll();
    }

}

