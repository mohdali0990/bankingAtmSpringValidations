package banking_atm.Service;

import banking_atm.Model.CheckingAccount;
import banking_atm.Model.Customer;
import banking_atm.Model.SavingAccount;
import banking_atm.Repo.CheckingAccountRepo;
import banking_atm.Repo.CustomerRepo;
import banking_atm.Repo.SavingAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CheckingAccountRepo checkingAccountRepo;
    @Autowired
    private SavingAccountRepo savingAccountRepo;

    public List<Customer> getAll() {
        return customerRepo.findAll();
    }


    public Customer getId(Integer findById){
        return customerRepo.findById(findById).get();
                //.orElseThrow(() -> new ApiRequestException("Id does not exist. Please try again."));
    }

    public String newSavingAccount(String firstName, String lastname, Integer addingBalance) {

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(addingBalance);
        savingAccount.setAddorMinusBalance(addingBalance);
        savingAccount.setNewBalance(addingBalance);
        savingAccount.setStatus("active");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastname);
        customer.setStatus("active");

        customer.setSavingAccount(Arrays.asList(savingAccount));

        customerRepo.save(customer);

        return "Account Created";
    }

    public Customer newCheckingAccount(String firstName, String lastname, Integer addingBalance) {

        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(addingBalance);
        checkingAccount.setAddOrMinusBalance(addingBalance);
        checkingAccount.setNewBalance(addingBalance);
        checkingAccount.setStatus("active");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastname);
        customer.setStatus("active");

        customer.setCheckingAccount(Arrays.asList(checkingAccount));

        customerRepo.save(customer);

        return customerRepo.findByFirstNameAndLastName(firstName,lastname).get();
    }

    public String newCheckingNSavingAccount(String firstName, String lastname, Integer addingBalanceChecking, Integer addingBalanceSaving) {

        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(addingBalanceChecking);
        checkingAccount.setAddOrMinusBalance(addingBalanceChecking);
        checkingAccount.setNewBalance(addingBalanceChecking);
        checkingAccount.setStatus("active");

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(addingBalanceSaving);
        savingAccount.setAddorMinusBalance(addingBalanceSaving);
        savingAccount.setNewBalance(addingBalanceSaving);
        savingAccount.setStatus("active");


        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastname);
        customer.setStatus("active");

        customer.setCheckingAccount(Arrays.asList(checkingAccount));
        customer.setSavingAccount(Arrays.asList(savingAccount));


        customerRepo.save(customer);

        return "Accounts Created";
    }

    public String closeAccount(Integer id) {

        Customer customer = customerRepo.findById(id).get();
                //orElseThrow(() -> new ApiRequestException("Customer does not exist. Please try again."));
        customer.setStatus("inactive");

        CheckingAccount checkingAccount = checkingAccountRepo.findByCustomerId(id).get();
        checkingAccount.setBalance(0);
        checkingAccount.setAddOrMinusBalance(0);
        checkingAccount.setNewBalance(0);
        checkingAccount.setStatus("inactive");

        SavingAccount savingAccount = savingAccountRepo.findByCustomerId(id).get();
        savingAccount.setBalance(0);
        savingAccount.setAddorMinusBalance(0);
        savingAccount.setNewBalance(0);
        savingAccount.setStatus("inactive");

        customerRepo.save(customer);
        checkingAccountRepo.save(checkingAccount);
        savingAccountRepo.save(savingAccount);

        return "Accounts Closed";
    }

    public Customer findByFullName(String firstName,String lastName){
        return customerRepo.findByFirstNameAndLastName(firstName,lastName).get();
                //.orElseThrow(() -> new ApiRequestException("Name does not exist. Please check and try again."));
    }


}