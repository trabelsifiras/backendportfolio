package org.firas.portfolio.firasportfolio.data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "visitor_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;
    private String city;
    private String region;
    private String country;
    private String org;
    private String timezone;
}