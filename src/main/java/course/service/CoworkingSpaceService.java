package course.service;

import course.repository.CoworkingSpaceRepository;
import course.entity.CoworkingSpace;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<CoworkingSpace> getCoworkingSpaceById(Long id) {
        return coworkingSpaceRepository.findById(id);
    }

    public void addCoworkingSpace(CoworkingSpace coworkingSpace) {
        coworkingSpaceRepository.save(coworkingSpace);
    }

    public void updateAllInformationAboutCoworkingSpace( CoworkingSpace coworkingSpace) {
        coworkingSpaceRepository.save(coworkingSpace);
    }

    public void removeCoworkingSpace(Long id) {
        coworkingSpaceRepository.delete(getCoworkingSpaceById(id).orElseThrow());
    }

    public List<CoworkingSpace> getAllCoworkingSpaces() {
        return coworkingSpaceRepository.findAll();
    }

}

