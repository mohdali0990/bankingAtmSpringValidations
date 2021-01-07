package banking_atm.Controller;

import banking_atm.Model.Customer;
import banking_atm.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value ="/all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping(value ="/id/{id}")
    public Customer getId(@PathVariable("id")@NotNull(message="{fieldempty}")Integer findById) {
        return customerService.getId(findById);
    }

    @PostMapping(value ="/newsavingaccount")
    public String newSavingAccount(@RequestParam("firstname")@NotEmpty(message="{firstname.notempty}") String firstName, @RequestParam("lastname")@NotEmpty(message="{lastname.notempty}")String lastname, @RequestParam("addbalance")@NotNull(message = "{field.notempty}") Integer addingBalance){

        return customerService.newSavingAccount(firstName, lastname, addingBalance);
    }

    @PostMapping(value ="/newcheckingaccount")
    public Customer newCheckingAccount(@RequestParam("firstname")@NotEmpty(message = "{validation.firstname.notempty}") String firstName, @RequestParam("lastname")String lastname, @RequestParam("addbalance")Integer addingBalance){

        return customerService.newCheckingAccount(firstName, lastname, addingBalance);
    }

    @PostMapping(value ="/newcheckingnsavingaccount")
    public String newCheckingNSavingAccount(@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastname,@RequestParam("addBalancechecking")Integer addingBalanceChecking,@RequestParam("addBalancesaving")Integer addingBalanceSaving){

        return customerService.newCheckingNSavingAccount(firstName, lastname, addingBalanceChecking, addingBalanceSaving);
    }

    @PutMapping(value = "/closeaccount")
    public String closeAccounts(@RequestParam("id") Integer id){
        return customerService.closeAccount(id);
    }

    @GetMapping(value= "/findbyname")
    public Customer findByName(@RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName){
        return customerService.findByFullName(firstName,lastName);
    }

}
