package course.service;

import course.entity.CoworkingSpaceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CoworkingSpaceEntityServiceTest {
    private CoworkingSpaceService coworkingSpaceService;

    @BeforeEach
//    public void prepareTestInstance() {
//        coworkingSpaceService = new CoworkingSpaceService(new CoworkingSpaceRepository());
//    }

    @Test
    void getCoworkingSpaceById_notFoundInMap_emptyOptional() {

        Optional<CoworkingSpaceEntity> coworkingSpace = coworkingSpaceService.getCoworkingSpaceById(100L);

        assertFalse(coworkingSpace.isPresent());
    }

    @Test
    void getCoworkingSpaceById_FoundInMap_coworkingSpaceFromFIle(){
        Optional<CoworkingSpaceEntity> booking = coworkingSpaceService.getCoworkingSpaceById(1L);

        assertTrue(booking.isPresent());
    }

}
