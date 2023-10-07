package com.faol.locals.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "transaction"
)
@Builder
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 20
    )
    @GeneratedValue(
            generator = "transaction_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long transactionId;

    @Column(name = "description")
    private String description;

    @Column(name = "total_amount")
    private double total_amount;

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "local_id",
            referencedColumnName = "local_id"
    )
    private Local local;

}
