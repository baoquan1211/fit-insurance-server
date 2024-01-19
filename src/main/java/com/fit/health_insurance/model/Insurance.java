package com.fit.health_insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer minFeePerYear;
    private boolean isActive;
    private String logo;
    private Integer totalPayPerYear;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "insurance")
    private List<InsuranceBenefit> benefits;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="insurance_type_id")
    private InsuranceType insuranceType;
}
