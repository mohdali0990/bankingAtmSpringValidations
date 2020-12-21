package banking_atm.Controller;

import banking_atm.Model.CheckingAccount;
import banking_atm.Service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checking")
public class CheckingAccountController {

    @Autowired
    private CheckingService checkingService;

    @GetMapping(value = "/all")
    public List<CheckingAccount> allCheckingAccounts() {
        return checkingService.allCheckingAccounts();
    }

    @GetMapping(value="/getAccount/{id}")
    public CheckingAccount getAccount(@PathVariable("id")Integer id){
        return checkingService.getAccount(id);
    }

    @PutMapping(value="/withdrawal")
    public CheckingAccount withdrawal(@RequestParam("minusBalance")Integer minusBalance,@RequestParam("id") Integer id){
        return checkingService.withdrawal(minusBalance,id);
    }

    @PutMapping(value="/deposit")
    public CheckingAccount deposit(@RequestParam("addBalance")Integer addBalance,@RequestParam("id") Integer id){
        return checkingService.deposit(addBalance, id);
    }

    @PutMapping(value = "/closeaccount")
    public String closeAccount(@RequestParam("id") Integer id){
        return checkingService.closeAccount(id);
    }

}
