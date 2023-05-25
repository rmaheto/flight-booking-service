package com.codemaniac.bookingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "airlines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;
    private String airlineCode;
    private String name;
    private LocalDate foundedYear;
    private String website;
    @OneToMany(mappedBy = "airline")
    @JsonIgnore
    private List<Flight> flights;
    @Embedded
    private Audit audit;

}
