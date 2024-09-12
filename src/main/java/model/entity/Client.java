package model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Reserve> reserves;
}
