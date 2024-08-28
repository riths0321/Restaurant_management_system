package Service;

import java.util.List;
import DAO.AdminDao;
import DAOIMPL.AdminDaoImpl;
import Exception.AdminNotFoundException;
import Exception.InvalidLoginException;
import Model.Admin;

public class AdminService
{
    private final AdminDao adminDao;

    public AdminService()
    {
        this.adminDao = new AdminDaoImpl();
    }

    //Admin Authentication
    public boolean authenticateAdmin(String username, String password) throws InvalidLoginException
    {
        try
        {
            Admin admin = adminDao.validateAdminCredentials(username, password);
            if (admin!=null)
            {
                return true;
            }else
            {
                throw new InvalidLoginException("Invalid username or password!");

            }
        }
        catch (InvalidLoginException e)
        {
            System.out.println("Authentication failed: "+e.getMessage());
            return false; // Return false if credentials are invalid
        }
    }

    // Get Admin by ID
    public Admin getAdminById(int adminId) throws AdminNotFoundException
    {
        return adminDao.getAdminById(adminId);
    }

    // Get all Admins
    public List<Admin> getAllAdmins() throws AdminNotFoundException
    {
        return adminDao.getAllAdmin();
    }

    // Add a new Admin
    public void addAdmin(Admin admin) throws InvalidLoginException, AdminNotFoundException
    {
        adminDao.addAdmin(admin);
    }

    // Update existing Admin
    public void updateAdmin(Admin admin) throws AdminNotFoundException
    {
        adminDao.updateAdmin(admin);
    }

    // Delete an Admin
    public void deleteAdmin(int adminId) throws AdminNotFoundException
    {
        adminDao.deleteAdmin(adminId);
    }

    // Change Admin password
    public void changeAdminPassword(int adminId, String newPassword) throws InvalidLoginException
    {
        adminDao.changeAdminPassword(adminId, newPassword);
    }

    public int getAdminCount()
    {
        return adminDao.getAdminCount();
    }
}