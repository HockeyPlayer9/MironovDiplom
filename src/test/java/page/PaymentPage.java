package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final SelenideElement formTitle = $$("h3.heading").findBy(Condition.text("Оплата по карте"));
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement cardNumberField = fields.get(0);
    private final SelenideElement monthField = fields.get(1);
    private final SelenideElement yearField = fields.get(2);
    private final SelenideElement holderField = fields.get(3);
    private final SelenideElement CVCField = fields.get(4);
    private final SelenideElement continueButton = $("form .button");


    public PaymentPage() {
        formTitle.shouldBe(Condition.visible);
    }

    public void payUsingPaymentGate(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getMonth());
        holderField.setValue(cardInfo.getHolder());
        CVCField.setValue(cardInfo.getCVC());
        continueButton.click();
    }
}
