package th.ac.ku.atm.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import th.ac.ku.atm.data.CustomerRepository;
import th.ac.ku.atm.model.Customer;
import org.mindrot.jbcrypt.BCrypt;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

//    private List<Customer> customerList;
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

//    @PostConstruct
//    public void postConstruct() {
//        this.customerList = new ArrayList<>();
//    }

    public void createCustomer(Customer customer) {
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);
        repository.save(customer);
    }

    public List<Customer> getCustomers() {
//        return new ArrayList<>(this.customerList);
        return repository.findAll();
    }

    public Customer findCustomer(int id) {
//        for (Customer customer : customerList) {
//            if (customer.getId() == id)
//                return customer;
//        }
//        return null;
        // jdbc***
//        try {
//            return repository.findById(id);
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
        // jpa***
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }


    }

    public Customer checkPin(Customer inputCustomer) {
        // 1. หา customer ที่มี id ตรงกับพารามิเตอร์
        Customer storedCustomer = findCustomer(inputCustomer.getId());

        // 2. ถ้ามี id ตรง ให้เช็ค pin ว่าตรงกันไหม โดยใช้ฟังก์ชันเกี่ยวกับ hash
        if (storedCustomer != null) {
            String hashPin = storedCustomer.getPin();

            if (BCrypt.checkpw(inputCustomer.getPin(), hashPin))
                return storedCustomer;
        }
        // 3. ถ้าไม่ตรง ต้องคืนค่า null
        return null;
    }




    private String hash(String pin) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(pin, salt);
    }




}
