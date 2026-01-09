package Service;

import DAO.TableDao;
import DAOIMPL.TableDaoImpl;
import Exception.TableException;
import Model.Table;

import java.util.List;
import java.util.stream.Collectors;

public class TableService {
    private final TableDao tableDao;

    public TableService() {
        this.tableDao = new TableDaoImpl();
    }

    // Create table
    public void creatTable(Table table) throws TableException {
        tableDao.createTable(table);
    }

    // Get table by ID
    public Table getTableById(int tableId) throws TableException {
        return tableDao.getTableById(tableId);
    }

    // Get all tables
    public List<Table> getAllTables() throws TableException {
        return tableDao.getAllTables();
    }

    // Get available tables
    public List<Table> getAvailableTables() throws TableException {
        return tableDao.getAvailableTables();
    }

    // Get tables by capacity
    public List<Table> getTablesByCapacity(int capacity) throws TableException {
        return getAllTables().stream()
                .filter(table -> table.getCapacity() == capacity)
                .collect(Collectors.toList());
    }

    // Update table
    public void updateTable(Table table) throws TableException {
        tableDao.updateTable(table);
    }

    // Update table status
    public void updateTable(int tableId, String status) throws TableException {
        Table table = getTableById(tableId);
        table.setStatus(status);
        tableDao.updateTable(table);
    }

    // Delete table
    public void deleteTable(int tableId) throws TableException {
        tableDao.deleteTable(tableId);
    }
}
