package data;

public class SQLConstants {
    public static class TagFinanceSQL {
        public static String INSERT_TAGFINANCE = "INSERT INTO  exercise.tag_finance (name,description)  VALUES (?,?)";
        public static String SELECTALL_TAGFINANCE = "SELECT*FROM exercise.tag_finance";
        public static String UPDATE_TAGFINANCE = "UPDATE exercise.tag_finance" +
                " SET " +
                " name =? " +
                " ,description = ?" +

                "  WHERE ID =?";
        public static String DELETE_TAGFINANCE = "DELETE FROM exercise.tag_finance  WHERE ID= ?";

    }

    public static class TransactionSQL {
        public static String INSERT_TRANSACTION = "INSERT INTO exercise.transaction (created_at,title,description,amount,tag_id) " +
                "VALUES (?,?,?,?,?) ";
        public static String UPDATE_TRANSACTION = "UPDATE exercise.transaction" +
                " SET " +
                " created_at = ?" +
                "  ,title = ?" +
                "  ,description = ?" +
                "  ,amount = ?" +
                " ,tag_id = ?" +

                "  WHERE ID =?";
        public static String DELETE_TRANSACTION = "DELETE FROM exercise.transaction   WHERE ID= ? ";
        public static String SEARCH_TRANSACTION = "SELECT* FROM exercise.transaction  WHERE tag_id= ?";
        public static String SEARCH_TRANSACTION_BY_NAME = "SELECT * FROM exercise.transaction join exercise.tag_finance\n" +
                " ON exercise.transaction.tag_id = exercise.tag_finance.id \n" +
                " where name like ?;";
        public static String GET_TRANSACTION = "SELECT * FROM exercise.transaction join exercise.tag_finance\n" +
                " ON exercise.transaction.tag_id = exercise.tag_finance.id;";
    }
}
