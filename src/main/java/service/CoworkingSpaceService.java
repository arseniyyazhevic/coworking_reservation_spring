package service;

import dao.CoworkingSpaceDAO;
import entity.CoworkingSpace;
import util.CoworkingSpaceDBUtils;

import java.util.Optional;

public class CoworkingSpaceService {

    private final CoworkingSpaceDAO coworkingSpaceDao;

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

    public static void displayAllCoworkingSpaces() {
//        ConsoleOutput.println("List of Coworking Spaces: ");
//        SortingUtil.sortById(allCoworkingSpaces).forEach((key, value) -> System.out.println(value));
        CoworkingSpaceDBUtils.readCoworkingSpaces();
    }

}

