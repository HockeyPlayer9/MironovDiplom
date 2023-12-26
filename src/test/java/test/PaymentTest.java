package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.getTransactionFromPaymentGate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    static final String MySQLUrl = "jdbc:mysql://localhost:3306/app";

    static void teardownMySQLDB() {
        SQLHelper.cleanDatabase(MySQLUrl);
    }

    @Test
    void successPayment(){
        var mainPage = open("http://localhost:8080/", MainPage.class);
        var paymentGateForm = mainPage.choosePaymentGate();
        paymentGateForm.payUsingPaymentGate(DataHelper.getCardWithValidDateAndStatusIsApproved());
        mainPage.verifySuccessNotificationVisibility();
        DataHelper.PaymentTransaction transaction = getTransactionFromPaymentGate(MySQLUrl);
        int expectedAmount = 45000;
        String expectedStatus = "APPROVED";
        int actualAmount = transaction.getAmount();
        String actualStatus = transaction.getStatus();
        assertEquals(expectedAmount, actualAmount);
        assertEquals(expectedStatus, actualStatus);
        teardownMySQLDB();

    }
}
