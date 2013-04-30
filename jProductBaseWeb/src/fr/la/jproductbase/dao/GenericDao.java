package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GenericDao {

	protected void handleDAOException(Exception e) {
		throw new RuntimeException(e);
	}
	
    protected void close(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException ignore) { }
        rs = null;
    }

    protected void close(Statement s)
    {
        try
        {
            if(s != null)
                s.close();
        }
        catch(SQLException ignore) { }
        s = null;
    }
	
    protected void close(Connection c)
    {
        try
        {
            if(c != null)
                c.close();
        }
        catch(SQLException ignore) { }
        c = null;
    }
}
