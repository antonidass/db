import java.sql.*;

public class Main {


    public static int AverageAgeOwner(String ownerName) throws SQLException {
        String query = "SELECT AVG(age) FROM CompanyOwner WHERE owner_name = ?";
        int avg = 0;

        try {
            Connection connection = DriverManager.getConnection("jdbc:default:connection:");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ownerName);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            avg = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avg;
    }


    public static void main(String[] args) {

    }
}
