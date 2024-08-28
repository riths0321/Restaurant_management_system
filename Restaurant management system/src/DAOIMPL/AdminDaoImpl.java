package DAOIMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AdminDao;
import Exception.AdminNotFoundException;
import Exception.InvalidLoginException;
import Model.Admin;
import Utility.DBConnection;

public class AdminDaoImpl implements AdminDao
{
      private Connection connection;
      Admin admin;
      PreparedStatement ppst;
      ResultSet rs;
      public AdminDaoImpl()
      {this.connection = DBConnection.getConnection();}


      //AdminId
     @Override
        public Admin getAdminById(int adminId) throws AdminNotFoundException {
            String sql = "SELECT * FROM admin WHERE admin_id=?";
            try(PreparedStatement ppst = connection.prepareStatement(sql)){
                ppst.setInt(1,adminId);
                try( ResultSet rs = ppst.executeQuery()){
                    if (rs.next()){
                        Admin admin = new Admin();
                        admin.setAdminId(rs.getInt("admin_id"));
                        admin.setUsername(rs.getString("username"));
                        admin.setPassword(rs.getString("password"));
                        return admin;
                    }  else{
                        throw new AdminNotFoundException("Admin with id "+adminId+" not found.");
                    }
                }
            }
            catch (SQLException e){
                e.printStackTrace();
                throw new AdminNotFoundException();
            }
        }

      //All Admin
      @Override
      public List<Admin> getAllAdmin()  throws AdminNotFoundException
      {
          List<Admin> admins = new ArrayList<>();
          String sql ="SELECT * FROM admin";
          try(PreparedStatement ppst = connection.prepareStatement(sql);
              ResultSet rs = ppst.executeQuery())
          {
              while(rs.next())
              {
                  Admin admin = new Admin();
                  admin.setAdminId(rs.getInt("admin_id"));
                  admin.setUsername(rs.getString("username"));
                  admin.setPassword(rs.getString("password"));
                  admins.add(admin);
              }
          }
          catch (SQLException e)
          {
              e.printStackTrace();
              throw new AdminNotFoundException("Admin not found");
          }
          return admins;
      }


    //Add Admin
    @Override
    public void addAdmin(Admin admin) throws InvalidLoginException
    {
        String sql = "INSERT INTO admin(admin_id,username,password) VALUES(?,?,?)";
        try(PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1,admin.getAdminId());
            ppst.setString(2,admin.getUsername());
            ppst.setString(3,admin.getPassword());
            ppst.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new InvalidLoginException("Cannot add admin invalid credentials.");
        }
    }


    //Update Admin
    @Override
    public void updateAdmin(Admin admin)  throws AdminNotFoundException
    {
        String sql = "UPDATE admin SET username=?,password=? WHERE admin_id=?";
        try(PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setString(1,admin.getUsername());
            ppst.setString(2,admin.getPassword());
            ppst.setInt(3,admin.getAdminId());
            ppst.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new AdminNotFoundException("Can't update cause admin not found.");
        }
    }


    //Delete Admin
    @Override
    public void deleteAdmin(int adminId) throws AdminNotFoundException
    {
        String sql = "DELETE FROM admin WHERE admin_id=?";
        try(PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setInt(1,adminId);
            int rowsAffected = ppst.executeUpdate();
            if (rowsAffected == 0)
            {
                throw new AdminNotFoundException("Can't update because admin not found.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new AdminNotFoundException("Unable to delete admin because admin not found.");

        }
    }



    //Admin Credentials
    @Override
    public Admin validateAdminCredentials(String username, String password) throws InvalidLoginException
    {
        String sql = "SELECT * FROM admin WHERE username=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql)) {
            ppst.setString(1, username);
            ResultSet rs = ppst.executeQuery();
            if (rs.next())
            {
                        Admin admin = new Admin();
                        admin.setAdminId(rs.getInt("admin_id"));
                        admin.setUsername(rs.getString("username"));
                        admin.setPassword(rs.getString("password"));
                        return admin;
                 }
                 else
                 {
                        throw new InvalidLoginException("Invalid username or password.");
                 }
            }
            catch (SQLException e)
           {
            e.printStackTrace();
            throw new InvalidLoginException("Invalid admin credentials");
           }
    }



    //Admin Password
    @Override
    public void changeAdminPassword(int adminId, String password) throws InvalidLoginException
    {
        String sql = "UPDATE admin SET password=? WHERE admin_id=?";
        try(PreparedStatement ppst = connection.prepareStatement(sql))
        {
            ppst.setString(1,password);
            ppst.setInt(2,adminId);
            ppst.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new InvalidLoginException("Can't change the password.");
        }
    }
    public int getAdminCount()
    {
        String sql = "select count(*) from admin";
        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())
        {
            if (rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

}