package data;

import data.DataHelper;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {};

    private static Connection getConn(String urlDB) throws SQLException {
        return DriverManager.getConnection(urlDB, "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.PaymentTransaction getTransactionFromPaymentGate(String urlDB) {
        var codeSQL = "SELECT amount, status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn(urlDB);
        String SQLResponse = runner.query(conn, codeSQL, new ScalarHandler<>());
        int amount = Integer.parseInt(SQLResponse.split(",")[0]);
        String status = SQLResponse.split(",")[1];
        return new DataHelper.PaymentTransaction(amount, status);
    }

    @SneakyThrows
    public static DataHelper.CreditTransaction getTransactionFromCreditGate(String urlDB) {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn(urlDB);
        String status = runner.query(conn, codeSQL, new ScalarHandler<>());
        return new DataHelper.CreditTransaction(status);
    }

    @SneakyThrows
    public static void cleanDatabase(String urlDB) {
        var conn = getConn(urlDB);
        runner.execute(conn, "DELETE FROM credit_request_entity");
        runner.execute(conn, "DELETE FROM order_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
    }

}

