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
//        Optional.ofNullable(CoworkingSpaceDBUtils.getCoworkingSpace(id))
        return Optional.ofNullable(coworkingSpaceRepository.getCoworkingSpaceById(id));
    }

    public void addCoworkingSpace(CoworkingSpace coworkingSpace) {
//        CoworkingSpaceDBUtils.createCoworkingSpace(coworkingSpace);
        coworkingSpaceRepository.saveCoworkingSpace(coworkingSpace);
    }

    public void updateAllInformationAboutCoworkingSpace(Long id, CoworkingSpace coworkingSpace) {
//        CoworkingSpaceDBUtils.updateCoworkingSpace(id, coworkingSpace);
//        coworkingSpaceDao.updateCoworkingSpace(id);
        coworkingSpaceRepository.updateCoworkingSpace(coworkingSpace, id);
    }

    public void removeCoworkingSpace(Long id) {
//        CoworkingSpaceDBUtils.deleteCoworkingSpace(id);
        coworkingSpaceRepository.deleteCoworkingSpace(id);
    }

    public List<CoworkingSpace> getAllCoworkingSpaces() {
        return coworkingSpaceRepository.getAllCoworkingSpaces();
    }

}

