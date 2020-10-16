package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    
    public abstract boolean create (T object) throws SQLException; 
    public abstract boolean update (T object) throws SQLException; 
    public abstract boolean delete (T object) throws SQLException; 
    public abstract T getById (int id) throws SQLException; 
    public abstract ArrayList<T> findAll() throws SQLException;
    
}