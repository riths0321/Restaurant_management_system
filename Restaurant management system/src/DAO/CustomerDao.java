package DAO;

import java.util.List;
import Model.Customer;
import Exception.CustomerException;

public interface CustomerDao
{
    // Read
    public Customer getCustomerDetailsById(int customerId) throws CustomerException;
    public List<Customer> getAllCustomerDetails() throws CustomerException;

    // Create
    public boolean createCustomer(Customer c1) throws CustomerException;

    // Update
    public boolean updateCustomerDetails(Customer c1) throws CustomerException;

    // Delete
    public boolean deleteCustomer(int customerId) throws CustomerException;
}
