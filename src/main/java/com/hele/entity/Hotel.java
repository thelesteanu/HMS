package com.hele.entity;

import com.hele.entity.User;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity(name = "hotel")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "hotel_name", nullable = false, length = 50)
    private String hotelName;

    @NotEmpty
    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "employee_number")
    private Long employeeNumber;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "earnings")
    private Long earnings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User ownerId;

    @OneToMany(mappedBy = "hotelId", cascade = CascadeType.ALL)
    private List<User> users;
}
