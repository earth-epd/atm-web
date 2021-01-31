package th.ac.ku.atm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BankAccountService {

    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<BankAccount> getCustomerBankAccount(int customerId) {
        String url = "http://localhost:8091/api/bankaccount/customer/" +
                customerId;
        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();

        return Arrays.asList(accounts);
    }

    public void editBankAccount(BankAccount bankAccount) {
        String url = "http://localhost:8091/api/bankaccount/" +
                bankAccount.getId();
        restTemplate.put(url, bankAccount);
    }


    public List<BankAccount> getBankAccounts() {
        String url = "http://localhost:8091/api/bankaccount/";

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);
    }


    public void openAccount(BankAccount bankAccount) {
        String url = "http://localhost:8091/api/bankaccount";

        restTemplate.postForObject(url, bankAccount, BankAccount.class);
    }

    public BankAccount getBankAccount(int id) {
        return null;
    }


//    @PostConstruct
//    public void postConstruct() {
//        this.bankAccountList = new ArrayList<>();
//    }
//
//    public List<BankAccount> getBankAccount() {
//        return new ArrayList<>(this.bankAccountList);
//    }
//
//    public BankAccount findCustomer(int id) {
//        for (BankAccount bankAccount : bankAccountList) {
//            if (bankAccount.getId() == id)
//                return bankAccount;
//        }
//        return null;
//    }
//
//    public BankAccount checkId(BankAccount inputBank){
//        BankAccount storedCustomer = findCustomer(inputBank.getId());
//        if (storedCustomer != null){
//            return storedCustomer;
//        }
//        return  null;
//    }
}
