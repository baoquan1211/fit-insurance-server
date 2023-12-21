package com.fit.health_insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;
    private String name;
    private String type;
    private boolean isActive;
}
