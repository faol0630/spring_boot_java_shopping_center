package com.faol.locals.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(
        name = "owner",
        uniqueConstraints = @UniqueConstraint(
                name = "owner_name_unique",
                columnNames = "owner_name"
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long owner_id;

    @Length(max = 40)
    @NotBlank(message = "Add the owner name")
    private String owner_name;

    @Length(max = 40)
    @NotBlank(message = "Add the owner lastname")
    private String owner_lastname;

    //bidirectionality
//    @OneToOne(
//            mappedBy = "owner",
//            fetch = FetchType.EAGER
//    )
    @OneToOne
    private Local local;
}
