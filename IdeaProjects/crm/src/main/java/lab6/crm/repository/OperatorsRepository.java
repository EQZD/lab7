package lab6.crm.repository;

import lab6.crm.entity.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorsRepository extends JpaRepository<Operators, Long> {
}
