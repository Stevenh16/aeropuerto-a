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
public class Passenger extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reserve_id")
    private Reserve reserve;
}
