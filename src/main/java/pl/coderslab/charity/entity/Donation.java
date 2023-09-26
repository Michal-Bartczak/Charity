package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    @Size(min = 1)
    @ManyToMany
    @JoinTable(name = "donation_category",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @NotNull
    @ManyToOne
    private Institution institution;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotNull
    private LocalTime pickUpTime;
    private String pickUpComment;
}
