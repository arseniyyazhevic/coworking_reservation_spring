package service;

import dao.CoworkingSpaceDao;
import entity.CoworkingSpace;
import ui.ConsoleOutput;
import util.CoworkingSpaceDBUtils;
import util.DBUtils;
import util.FileUtils;
import util.sortingUtils.SortingUtil;

import java.util.HashMap;
import java.util.Optional;

public class CoworkingSpaceService {

    private final CoworkingSpaceDao coworkingSpaceDao;

    public CoworkingSpaceService(CoworkingSpaceDao coworkingSpaceDao) {
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

