package course.entity;

import course.enums.TypeOfWorkspaces;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "coworking_spaces")
public class CoworkingSpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_workspace")
    private TypeOfWorkspaces typeOfWorkspaces;

    @Column(name = "price_in_dollars")
    private Integer priceInDollars;

    @Column(name = "availability_status")
    private boolean availabilityStatus;


    public CoworkingSpaceEntity(String name, TypeOfWorkspaces typeOfWorkspace, int priceDollars, boolean availabilityStatus) {
    }
}
