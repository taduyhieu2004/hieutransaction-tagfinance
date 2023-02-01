package dao;

import model.TagFinance;
import model.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TransactionDAOImpl implements TransactionDAO, Search {
    private static String INSERT = "INSERT INTO exercise.transaction (created_at,title,description,amount,tag_id) " +
            "VALUES (?,?,?,?,?) ";
    private static String UPDATE = "UPDATE exercise.transaction" +
            " SET " +
            " created_at = ?" +
            "  ,title = ?" +
            "  ,description = ?" +
            "  ,amount = ?" +
            " ,tag_id = ?" +

            "  WHERE ID =?";
    private static String DELETE = "DELETE FROM exercise.transaction   WHERE ID= ? ";
    private static String SEARCH = "SELECT* FROM exercise.transaction  WHERE tag_id= ?";

    @Override
    public void insertTransaction(Transaction transaction) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
//
//            String sql = "INSERT INTO exercise.transaction (created_at,title,description,amount,tag_id) " +
//                    " VALUES ('" + transaction.getCreatedAt() + "','" + transaction.getTitle() +
//                    "','" + transaction.getDescription() + "','" + transaction.getAmount() +
//                    "','" + transaction.getTagFinance().getId() + "')";
//            st.executeUpdate(sql);
            PreparedStatement pst = connection.prepareStatement(INSERT);
            pst.setDate(1, (java.sql.Date) transaction.getCreatedAt());
            pst.setString(2, transaction.getTitle());
            pst.setString(3, transaction.getDescription());
            pst.setInt(4, transaction.getAmount());
            pst.setInt(5, transaction.getTagFinance().getId());
            pst.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Override
    public void updateTransaction(Transaction transaction, int id) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
////            Statement st =connection.prepareStatement(sql);
//            String sql = "UPDATE exercise.transaction" +
//                    " SET " +
//                    " created_at = '" + transaction.getCreatedAt() + "'," +
//                    " title = '" + transaction.getTitle() + "'," +
//                    " description = '" + transaction.getDescription() + "'," +
//                    " amount = '" + transaction.getAmount() + "'," +
//                    "tag_id = '" + transaction.getTagFinance().getId() + "'" +
//
//                    "  WHERE ID =" + id;
//            st.executeUpdate(sql);
            PreparedStatement pvt = connection.prepareStatement(UPDATE);
            pvt.setDate(1, (java.sql.Date) transaction.getCreatedAt());
            pvt.setString(2, transaction.getTitle());
            pvt.setString(3, transaction.getDescription());
            pvt.setInt(4, transaction.getAmount());
            pvt.setInt(5, transaction.getTagFinance().getId());
            pvt.setInt(6, id);
            pvt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    @Override
    public void deleteTransaction(int id) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
////            String sql = "DELETE FROM exercise.transaction  WHERE ID=" + "'" + id + "'";
//            st.executeUpdate(sql);
            PreparedStatement pvt = connection.prepareStatement(DELETE);
            pvt.setInt(1, id);
            pvt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    @Override
    public List<Transaction> getTransactionList() {
        Connection connection = null;

        List<Transaction> list = new ArrayList<>();

        try {
            connection = DataSource.getInstance().getConnection();
            Statement st = connection.createStatement();
            String sql = "SELECT*FROM exercise.transaction";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                Date cr = rs.getDate("created_at");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int amount = rs.getInt("amount");
                int tagID = rs.getInt("tag_id");
                Transaction transaction = new Transaction(id, cr, title, description, amount, new TagFinance(tagID));
                list.add(transaction);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }


    @Override
    public List<Transaction> searchTransactionByTagFinance(int id) {
        Connection connection = null;
        List<Transaction> transactionList = new ArrayList<>();
        try {
            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(SEARCH);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idTrans = rs.getInt("id");
                Date cr = rs.getDate("created_at");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int amount = rs.getInt("amount");
                int tagID = rs.getInt("tag_id");
                Transaction transaction = new Transaction(idTrans, cr, title, description, amount, new TagFinance(tagID));
                transactionList.add(transaction);
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            e.printStackTrace();
        }
        return transactionList;

    }

    @Override
    public List<Transaction> searchTransactionByTagFinanceName(String key, List<Transaction> list) {
        List<Transaction> transactionList = new ArrayList<>();
        String pattern=".*"+key+".*";
        for (Transaction transaction:list){
            if(transaction.getTagFinance().getName().matches(pattern)){
                transactionList.add(transaction);
            }
        }
        return transactionList;

    }

//    public void insertTransactiontest(Transaction transaction) {
//        Connection connection = null;
//        try {
//            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
//
//            String sql = "INSERT INTO exercise.transaction (created_at,title,description,amount,tag_id) " +
//                    " VALUES ( now() ,'" + transaction.getTitle() +
//                    "','" + transaction.getDescription() + "','" + transaction.getAmount() +
//                    "','" + transaction.getTagFinance().getId() + "')";
//            st.executeUpdate(sql);
//            connection.commit();
//
//        } catch (SQLException e) {
//            if (connection != null) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//
//            e.printStackTrace();
//        } finally {
//            if (Objects.nonNull(connection)) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
}
