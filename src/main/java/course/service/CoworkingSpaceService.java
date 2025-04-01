package course.service;

import course.dao.CoworkingSpaceDAO;
import course.entity.CoworkingSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoworkingSpaceService {

    private final CoworkingSpaceDAO coworkingSpaceDao;

    @Autowired
    public CoworkingSpaceService(CoworkingSpaceDAO coworkingSpaceDao) {
        this.coworkingSpaceDao = coworkingSpaceDao;
    }

    public Optional<CoworkingSpace> getCoworkingSpaceById(Long id) {
//        Optional.ofNullable(CoworkingSpaceDBUtils.getCoworkingSpace(id))
        return Optional.ofNullable(coworkingSpaceDao.getCoworkingSpaceById(id));
    }

    public void addCoworkingSpace(CoworkingSpace coworkingSpace) {
//        CoworkingSpaceDBUtils.createCoworkingSpace(coworkingSpace);
        coworkingSpaceDao.saveCoworkingSpace(coworkingSpace);
    }

    public void updateAllInformationAboutCoworkingSpace(Long id, CoworkingSpace coworkingSpace) {
//        CoworkingSpaceDBUtils.updateCoworkingSpace(id, coworkingSpace);
//        coworkingSpaceDao.updateCoworkingSpace(id);
        coworkingSpaceDao.updateCoworkingSpace(coworkingSpace, id);
    }

    public void removeCoworkingSpace(Long id) {
//        CoworkingSpaceDBUtils.deleteCoworkingSpace(id);
        coworkingSpaceDao.deleteCoworkingSpace(id);
    }

    public void displayAllCoworkingSpaces() {
        List<CoworkingSpace> coworkingSpaces = coworkingSpaceDao.getAllCoworkingSpaces();
       System.out.printf("%-5s | %-20s | %-20s | %-15s | %-10s%n", "ID", "Name", "Type", "Price ($)", "Available");
        System.out.println("----------------------------------------------------------------------------");

        for (CoworkingSpace space : coworkingSpaces) {
            System.out.printf("%-5d | %-20s | %-20s | %-15d | %-10s%n",
                    space.getId(),
                    space.getName(),
                    space.getTypeOfWorkspaces(),
                    space.getPriceInDollars(),
                    space.isAvailabilityStatus() ? "Yes" : "No");
        }
    }

}

