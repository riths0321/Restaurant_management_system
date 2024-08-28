package DAO;

import java.util.List;
import Exception.AdminNotFoundException;
import Exception.InvalidLoginException;
import Model.Admin;

public interface AdminDao
{

    Admin getAdminById(int adminId) throws AdminNotFoundException;
    List<Admin> getAllAdmin() throws AdminNotFoundException;
    void addAdmin(Admin admin) throws AdminNotFoundException, InvalidLoginException;
    void updateAdmin(Admin admin) throws AdminNotFoundException;
    void deleteAdmin(int adminId) throws AdminNotFoundException;
    Admin validateAdminCredentials(String username, String password) throws InvalidLoginException;
    void changeAdminPassword(int adminId,String password) throws InvalidLoginException;

    int getAdminCount();
}