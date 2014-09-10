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
        try
        {
            connection = getConnection();

            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM LOAN_EXTENSION");
            stmt.execute("DELETE FROM LOAN");
            stmt.execute("DELETE FROM LOAN_REQUEST");
            stmt.execute("DELETE FROM CUSTOMER");
            stmt.execute("DELETE FROM REQUEST_IP");
            stmt.execute("DELETE FROM BANK_PARAMS");
           /*
            connection.setAutoCommit(false);
            stmt.execute("SET REFERENTIAL_INTEGRITY FALSE");
            connection.commit();
            try {
                for (String tableName : getDatabaseMetaData(connection)) {
                    PreparedStatement preparedStatement = connection
                            .prepareStatement("DELETE FROM " + tableName);
                    System.out.println(preparedStatement.toString());
                    preparedStatement.executeUpdate();
                }
                connection.commit();

                stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");
                connection.commit();*/
            }
        catch (SQLException e)
            {
                System.out.println("Deleted All Rows In  Table Error. ");
                e.printStackTrace();
                throw new DBException(e);
            }
        try
        {
            connection.close();
        } catch (Throwable e)
        {
            System.out.println("Exception while deleting data from DB.");
            e.printStackTrace();
            throw new DBException(e);
        } finally
        {
            closeConnection(connection);
        }
    }

    private List<String> getDatabaseMetaData(Connection connection) {
        List<String> tableList = new ArrayList<String>();
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = metaData.getTables(null, null, "%", types);
            while (rs.next())
                tableList.add(rs.getString("TABLE_NAME"));

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tableList;
    }

}
