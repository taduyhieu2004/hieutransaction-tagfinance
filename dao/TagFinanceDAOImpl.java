package dao;

import model.TagFinance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TagFinanceDAOImpl implements TagFinanceDAO {
    private static String SQLINSERT = "INSERT INTO  exercise.tag_finance (name,description)  VALUES (?,?)";
    private static String SELECTALL = "SELECT*FROM exercise.tag_finance";
    private static String UPDATE = "UPDATE exercise.tag_finance" +
            " SET " +
            " name =? " +
            " ,description = ?" +

            "  WHERE ID =?";
    private static String DELETE = "DELETE FROM exercise.tag_finance  WHERE ID= ?";

    @Override
    public void insertTagFinance(TagFinance tagFinance) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
//            String insert="INSERT INTO  exercise.tag_finance (name,description)"+"  VALUES (?, ?)";
//            String sql = "INSERT INTO exercise.tag_finance (name,description) " +
//                    " VALUES ('" + tagFinance.getName() + "','" + tagFinance.getDescription() + "')";
            PreparedStatement pst = connection.prepareStatement(SQLINSERT);
            pst.setString(1, tagFinance.getName());
            pst.setString(2, tagFinance.getDescription());


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
        }
    }

    @Override
    public List<TagFinance> getTagFinanceList() {
        List<TagFinance> list = new ArrayList<>();

        try {
            Connection connection = DataSource.getInstance().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECTALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                TagFinance tagFinance = new TagFinance(id, name, description);
                list.add(tagFinance);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateTagFinance(TagFinance tagFinance, int id) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
////            Statement st =connection.prepareStatement(sql);
//            String sql = "UPDATE exercise.tag_finance" +
//                    " SET " +
//                    " name = '" + tagFinance.getName() + "'," +
//                    " description = '" + tagFinance.getDescription() + "'" +
//
//                    "  WHERE ID =" + id;
            System.out.println(UPDATE);
            PreparedStatement pst = connection.prepareStatement(UPDATE);
            pst.setString(1, tagFinance.getName());
            pst.setString(2, tagFinance.getDescription());
            pst.setInt(3, id);
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
        }
    }

    @Override
    public void deleteTagFinance(int id) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
//            Statement st = connection.createStatement();
////            Statement st =connection.prepareStatement(sql);
//            String sql = "DELETE FROM exercise.tag_finance  WHERE ID=" + "'" + id + "'";
//            st.executeUpdate(sql);
            PreparedStatement pst = connection.prepareStatement(DELETE);
            pst.setInt(1, id);
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


}
