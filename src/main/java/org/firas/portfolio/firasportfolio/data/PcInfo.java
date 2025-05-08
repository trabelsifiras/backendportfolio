package org.firas.portfolio.firasportfolio.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "PC_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PcInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String macAddress;
    private String pcName;
}
