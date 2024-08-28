package DAO;

import java.util.List;
import Exception.TableException;
import Model.Table;

public interface TableDao
{

    // Read
    public Table getTableById(int tableId) throws TableException;
    public List<Table> getAllTables() throws TableException;
    public List<Table> getAvailableTables() throws TableException;

    // Create
    public boolean createTable(Table table) throws TableException;

    // Update
    public boolean updateTable(Table table) throws TableException;

    // Delete
    public boolean deleteTable(int tableId) throws TableException;
}

