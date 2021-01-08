package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    List<BankAccount> bankAccountList;

    @PostConstruct
    public void postConstruct() {
        this.bankAccountList = new ArrayList<>();
    }

    public List<BankAccount> getBankAccount() {
        return new ArrayList<>(this.bankAccountList);
    }

    public BankAccount findCustomer(int id) {
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getId() == id)
                return bankAccount;
        }
        return null;
    }

    public BankAccount checkId(BankAccount inputBank){
        BankAccount storedCustomer = findCustomer(inputBank.getId());
        if (storedCustomer != null){
            return storedCustomer;
        }
        return  null;
    }
}
