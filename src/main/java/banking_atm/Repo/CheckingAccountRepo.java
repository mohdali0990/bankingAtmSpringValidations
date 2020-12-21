package banking_atm.Repo;

import banking_atm.Model.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CheckingAccountRepo extends JpaRepository<CheckingAccount,Integer> {

    public Optional<CheckingAccount> findByCustomerId(Integer customerId);

}
