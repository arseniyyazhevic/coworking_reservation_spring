package course.service;

import course.dao.CoworkingSpaceDAO;
import course.entity.CoworkingSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CoworkingSpaceServiceTest {
    private CoworkingSpaceService coworkingSpaceService;

    @BeforeEach
    public void prepareTestInstance() {
        coworkingSpaceService = new CoworkingSpaceService(new CoworkingSpaceDAO());
    }

    @Test
    void getCoworkingSpaceById_notFoundInMap_emptyOptional() {

        Optional<CoworkingSpace> coworkingSpace = coworkingSpaceService.getCoworkingSpaceById(100L);

        assertFalse(coworkingSpace.isPresent());
    }

    @Test
    void getCoworkingSpaceById_FoundInMap_coworkingSpaceFromFIle(){
        Optional<CoworkingSpace> booking = coworkingSpaceService.getCoworkingSpaceById(1L);

        assertTrue(booking.isPresent());
    }

}
