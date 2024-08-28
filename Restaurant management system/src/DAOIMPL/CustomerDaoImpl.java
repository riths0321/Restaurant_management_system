package DAOIMPL;

import DAO.CustomerDao;
import Exception.CustomerException;
import Model.Customer;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao
{
    private Connection connection;

    public CustomerDaoImpl()
    {
        this.connection = DBConnection.getConnection();
    }

    //Get Customer Details By ID
    @Override
    public Customer getCustomerDetailsById(int customerId) throws CustomerException
    {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, customerId);
            try (ResultSet rs = ppst.executeQuery())
            {
                if (rs.next())
                {
                    return new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("phone_number")
                    );
                }
                else
                {
                    throw new CustomerException("Customer with ID: " + customerId + " not found.");
                }
            }
        }
        catch (SQLException e)
        {
            throw new CustomerException("Error retrieving customer by ID: " + e.getMessage());
        }
    }

    //Get All Customer Details
    @Override
    public List<Customer> getAllCustomerDetails() throws CustomerException
    {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (PreparedStatement ppst = connection.prepareStatement(sql);
             ResultSet rs = ppst.executeQuery())
        {
            while (rs.next())
            {
                customers.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number")
                ));
            }
        }
        catch (SQLException e)
        {
            throw new CustomerException("Error retrieving all customers: " + e.getMessage());
        }
        return customers;
    }

    //Create A New Customer
    @Override
    public boolean createCustomer(Customer c1) throws CustomerException
    {
        String sql = "INSERT INTO customers (customer_id, name, email, password, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, c1.getCustomerId());
            ppst.setString(2, c1.getName());
            ppst.setString(3, c1.getEmail());
            ppst.setString(4, c1.getPassword());
            ppst.setString(5, c1.getPhoneNumber());

            int rowsAffected = ppst.executeUpdate();
            if (rowsAffected > 0)
            {
                return true;
            }
            else
            {
                throw new CustomerException("Failed to create customer.");
            }
        }
        catch (SQLException e)
        {
            throw new CustomerException("Error creating customer: " + e.getMessage());
        }
    }

    //Update Customer Details
    @Override
    public boolean updateCustomerDetails(Customer c1) throws CustomerException
    {
        String sql = "UPDATE customers SET name = ?, email = ?, password = ?, phone_number = ? WHERE customer_id = ?";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setString(1, c1.getName());
            ppst.setString(2, c1.getEmail());
            ppst.setString(3, c1.getPassword());
            ppst.setString(4, c1.getPhoneNumber());
            ppst.setInt(5, c1.getCustomerId());

            int rowsAffected = ppst.executeUpdate();
            if (rowsAffected > 0)
            {
                return true;
            }
            else
            {
                throw new CustomerException("Customer with ID: " + c1.getCustomerId() + " not found.");
            }
        }
        catch (SQLException e)
        {
            throw new CustomerException("Error updating customer details: " + e.getMessage());
        }
    }

    //Delete Customer
    @Override
    public boolean deleteCustomer(int customerId) throws CustomerException
    {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1, customerId);

            int rowsAffected = ppst.executeUpdate();
            if (rowsAffected > 0)
            {
                return true;
            }
            else
            {
                throw new CustomerException("Customer with ID: " + customerId + " not found.");
            }
        }
        catch (SQLException e)
        {
            throw new CustomerException("Error deleting customer: " + e.getMessage());
        }
    }
}