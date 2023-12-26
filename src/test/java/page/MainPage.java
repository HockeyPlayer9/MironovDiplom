package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final ElementsCollection buttons = $$(".button");
    private final SelenideElement paymentButton = buttons.get(0);
    private final SelenideElement creditButton = buttons.get(1);
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");

    public void verifyErrorNotificationVisibility() {
        errorNotification.shouldBe(Condition.visible);
    }

    public void verifySuccessNotificationVisibility() {
        successNotification.shouldBe(Condition.visible);
    }

    public PaymentPage choosePaymentGate() {
        paymentButton.click();
        return new PaymentPage();
    }

    public CreditPage chooseCreditGate() {
        creditButton.click();
        return new CreditPage();
    }
}
