package DAOIMPL;

import DAO.TableDao;
import Exception.TableException;
import Model.Table;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableDaoImpl implements TableDao
{
    private Connection connection;
    PreparedStatement ppst = null;
    ResultSet rs = null;
    private int id;
    private int capacity;
    private String  status;

    public TableDaoImpl() {
        this.connection = DBConnection.getConnection();
    }

    // 1. Get Table by ID
    @Override
    public Table getTableById(int tableId) throws TableException {
        String sql = "SELECT * FROM tables WHERE table_id=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql)) {
            ppst.setInt(1, tableId);
            rs = ppst.executeQuery();
            if (rs.next()) {
                return extractTableFromResultSet(rs);
            } else {
                throw new TableException("Table with ID: " + tableId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TableException(e.getMessage());
        }
    }

    // 2. Get all Tables
    @Override
    public List<Table> getAllTables() throws TableException {
        List<Table> tables = new ArrayList<>();
        String sql = "SELECT * FROM tables";
        try (PreparedStatement ppst = connection.prepareStatement(sql)) {
            rs = ppst.executeQuery();
            while (rs.next()) {
                tables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TableException(e.getMessage());
        }
        return tables;
    }

    // 3. Get available Tables
    @Override
    public List<Table> getAvailableTables() throws TableException {
        List<Table> availableTables = new ArrayList<>();
        String sql = "SELECT * FROM tables WHERE status = 'available'";
        try (PreparedStatement ppst = connection.prepareStatement(sql)) {
            rs = ppst.executeQuery();
            while (rs.next()) {
                availableTables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TableException(e.getMessage());
        }
        return availableTables;
    }

    // 4. Create Table
    @Override
    public boolean createTable(Table table) throws TableException {
        String sql = "INSERT INTO tables (table_id, capacity, status) VALUES (?, ?, ?)";
        try (PreparedStatement ppst = connection.prepareStatement(sql)) {
            ppst.setInt(1, table.getTableId());
            ppst.setInt(2, table.getCapacity());
            ppst.setString(3, table.getStatus());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TableException(e.getMessage());
        }
    }

    // 5. Update Table
    @Override
    public boolean updateTable(Table table) throws TableException {
        String sql = "UPDATE tables SET capacity=?, status=? WHERE table_id=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql)) {
            ppst.setInt(1, table.getCapacity());
            ppst.setString(2, table.getStatus());
            ppst.setInt(3, table.getTableId());
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TableException(e.getMessage());
        }
    }

    // 6. Delete Table
    @Override
    public boolean deleteTable(int tableId) throws TableException {
        String sql = "DELETE FROM tables WHERE table_id=?";
        try (PreparedStatement ppst = connection.prepareStatement(sql)) {
            ppst.setInt(1, tableId);
            int rowsAffected = ppst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TableException("Error deleting table: " + e.getMessage());
        }
    }

    // Helper method to extract Table from ResultSet
    private Table extractTableFromResultSet(ResultSet rs) throws SQLException {
        Table table = new Table(id, capacity, status);
        table.setTableId(rs.getInt("table_id"));
        table.setCapacity(rs.getInt("capacity"));
        table.setStatus(rs.getString("status"));
        return table;
    }
}
