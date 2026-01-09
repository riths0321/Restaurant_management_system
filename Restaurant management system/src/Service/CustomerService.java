package Service;

import DAO.CustomerDao;
import DAOIMPL.CustomerDaoImpl;
import Exception.CustomerException;
import Exception.InvalidLoginException;
import Model.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDaoImpl();
    }

    // Authenticate customer
    public boolean authenticateCustomer(String email, String password) throws InvalidLoginException {
        try {
            List<Customer> customers = customerDao.getAllCustomerDetails();
            for (Customer customer : customers) {
                if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                    return true;
                }
            }
            throw new InvalidLoginException("Invalid email or password!");
        } catch (CustomerException e) {
            throw new InvalidLoginException("Authentication error: " + e.getMessage());
        }
    }

    // Get customer by ID
    public Customer getCustomerById(int customerId) throws CustomerException {
        return customerDao.getCustomerDetailsById(customerId);
    }

    // Get all customers
    public List<Customer> getAllCustomers() throws CustomerException {
        return customerDao.getAllCustomerDetails();
    }

    // Create customer
    public void Customer(Customer customer) throws CustomerException, InvalidLoginException {
        customerDao.createCustomer(customer);
    }

    // Update customer details
    public void updateCustomerDetails(Customer customer) throws CustomerException {
        customerDao.updateCustomerDetails(customer);
    }

    // Delete customer
    public void deleteCustomer(int customerId) throws CustomerException {
        customerDao.deleteCustomer(customerId);
    }

    // Change customer password
    public void changeCustomerPassword(int customerId, String newPassword) throws InvalidLoginException {
        try {
            Customer customer = customerDao.getCustomerDetailsById(customerId);
            customer.setPassword(newPassword);
            customerDao.updateCustomerDetails(customer);
        } catch (CustomerException e) {
            throw new InvalidLoginException("Error changing password: " + e.getMessage());
        }
    }

    // Get customer count
    public int getCustomerCount() {
        try {
            return getAllCustomers().size();
        } catch (CustomerException e) {
            return 0;
        }
    }
}
