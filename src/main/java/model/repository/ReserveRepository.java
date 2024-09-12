package model.repository;

import model.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
}
