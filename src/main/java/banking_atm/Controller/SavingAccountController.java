package banking_atm.Controller;

import banking_atm.Model.SavingAccount;
import banking_atm.Service.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/savingaccount")
public class SavingAccountController {

    @Autowired
    private SavingAccountService savingAccountService;

    @GetMapping(value = "/all")
    public List<SavingAccount> getAll(){
        return savingAccountService.allSavingAccounts();
    }

    @GetMapping(value = "/getAccount/{id}")
    public SavingAccount getId(@PathVariable("id")Integer findById){
        return savingAccountService.getAccount(findById);
    }

    @PutMapping(value ="/withdrawal")
    public SavingAccount withdrawal(@RequestParam("id")Integer id , @RequestParam("minusbalance") Integer minusBalance){
        return savingAccountService.withdrawal(id, minusBalance);
    }

    @PutMapping(value="/deposit")
    public SavingAccount deposit (@RequestParam("addBalance")Integer addBalance,@RequestParam("id") Integer id) {
        return savingAccountService.deposit(addBalance, id);
    }

    @PutMapping(value = "/closeaccount")
    public String closeAccount(@RequestParam("id") Integer id){
       return savingAccountService.closeAccount(id);
    }
}
