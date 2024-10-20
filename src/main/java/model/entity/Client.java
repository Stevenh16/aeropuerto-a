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
public class Client extends User {
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Reserve> reserves;
}
