package banking_atm.Service;

import banking_atm.Model.SavingAccount;
import banking_atm.Repo.SavingAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SavingAccountService {

    @Autowired
    private SavingAccountRepo savingAccountRepo;

    public List <SavingAccount> allSavingAccounts(){
         return savingAccountRepo.findAll();
    }

    public SavingAccount getAccount(Integer findById){
        return savingAccountRepo.findById(findById).get();
    }

    public SavingAccount withdrawal(Integer id ,Integer minusBalance){

        SavingAccount savingAccount = savingAccountRepo.findById(id).get();
        int balance = savingAccount.getBalance();
        int newBalance = balance - minusBalance;
        savingAccount.setAddorMinusBalance(minusBalance);
        savingAccount.setBalance(balance);
        savingAccount.setNewBalance(newBalance);

        savingAccountRepo.save(savingAccount);

        return savingAccountRepo.findById(id).get();
    }

    public SavingAccount deposit(Integer addBalance, Integer id){

        SavingAccount savingAccount=savingAccountRepo.findById(id).get();
        int balance = savingAccount.getBalance();
        int newBalance = balance + addBalance;
        savingAccount.setAddorMinusBalance(addBalance);
        savingAccount.setBalance(balance);
        savingAccount.setNewBalance(newBalance);
        savingAccountRepo.save(savingAccount);

        return savingAccountRepo.findById(id).get();
    }

    public String closeAccount(Integer id){
        SavingAccount savingAccount = savingAccountRepo.findById(id).get();
        savingAccount.setAddOrMinusBalance(null);
        savingAccount.setAddOrMinusBalance(null);
        savingAccount.setNewBalance(null);
        savingAccount.setBalance(null);
        savingAccount.setStatus("inactive");
        savingAccountRepo.save(savingAccount);
          return "Account Closed";
    }
}

