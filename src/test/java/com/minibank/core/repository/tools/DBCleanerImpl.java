package com.minibank.core.repository.tools;

import com.minibank.core.repository.DBException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class DBCleanerImpl extends JDBCProvider
    implements DBCleaner
{
    @Override
    public void clear() throws DBException
    {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            //connection.prepareStatement("SET SQL_SAFE_UPDATES=0;").execute();
            //Statement stmt = connection.createStatement();
            //stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            connection.commit();
            try {
                for (String tableName : getDatabaseMetaData(connection)) {
                    PreparedStatement preparedStatement = connection
                            .prepareStatement("DELETE FROM " + tableName);
                    System.out.println(preparedStatement.toString());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
               // stmt.execute("SET FOREIGN_KEY_CHECKS=1");
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Deleted All Rows In  Table Error. ");
                e.printStackTrace();
                throw new DBException(e);
            }
            connection.close();
        } catch (Throwable e) {
            System.out.println("Exception while deleting data from DB.");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private List<String> getDatabaseMetaData(Connection connection) {
        List<String> tableList = new ArrayList<String>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = metaData.getTables(null, null, "%", types);
            while (rs.next())
                tableList.add(rs.getString("TABLE_NAME"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

}
