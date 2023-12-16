package com.faol.locals.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(
        name = "locals",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = "owner")
public class Local {

    @Id
    @SequenceGenerator(
            name = "local_sequence",
            sequenceName = "local_sequence",
            allocationSize = 3)//shows results in 3 by 3
    @GeneratedValue(
            generator = "local_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long local_id;

    @NotBlank(message = "Add the local name")
    @Length(min = 4, max = 40)
    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @NotBlank(message = "Add the local floor")
    @Length(min = 1, max = 2)
    @Column(
            name = "floor",
            nullable = false
    )
    private String floor;

    @NotBlank(message = "Add the local code")
    @Length(max = 5)
    @Column(
            name = "code",
            nullable = false
    )
    private String code;

    @Embedded
    private Address address;

//    @OneToOne(
//            cascade = CascadeType.PERSIST,
//            fetch = FetchType.EAGER
//    )
//    @OneToOne
//    @JoinColumn(
//            name = "owner_id", //name in database
//            referencedColumnName = "owner_id"
//    )
//    @NotNull(message = "Add the owner id" )
//    @JsonIgnore //para evitar recursion infinita
//    private Owner owner;


    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER //@OneToMany defaults to LAZY
    )
    @JoinColumn(
            name = "local_id",
            referencedColumnName = "local_id"
    )
    @JsonIgnore
    private List<Transaction> transactionList;



    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER //@ManyToMany defaults to Lazy
    )
    @JoinTable(
            name = "local_customer_map", //name of the intermediate table in the DB
            joinColumns = @JoinColumn(
                    name = "local_id",
                    referencedColumnName = "local_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "customer_id",
                    referencedColumnName = "customer_id"
            )
    )
    @JsonIgnore
    private List<Customer> customerList;

}
