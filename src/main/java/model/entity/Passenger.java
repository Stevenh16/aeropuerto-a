package model.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="passengers")
public class Passenger{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="lastname")
    private String lastname;
    @Column(name="address")
    private String address;
    @Column(name="cell_phone")
    private String cellphone;
    @Column(name="email")
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reserve_id")
    private Reserve reserve;
}
