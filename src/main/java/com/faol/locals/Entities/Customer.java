package com.faol.locals.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "customer",
        uniqueConstraints = @UniqueConstraint(
                name = "customer_name_unique", //restriction name
                columnNames = "customer_name" //restricted column name
        )
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customer_id;

    @Length(max = 40)
    @NotBlank(message = "Add the customer's name")
    private String customer_name;

    @Length(max = 40)
    @NotBlank(message = "Add the customer's lastname")
    private String customer_lastname;

}
