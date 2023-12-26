package data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {

    }

    public static CardInfo getTestHolder(String cardNumber,String mount, String year, String holder, String CVC) {
        return new CardInfo(cardNumber,mount,year,holder,CVC);
    }

    public static CardInfo getCardWithValidDateAndStatusIsApproved() {
        return getTestHolder("4444 4444 4444 4441", "09", "24", "Ivan Ivanov", "555");
    }

    public static CardInfo getCardWithValidDateAndStatusIsDeclined() {
        return getTestHolder("4444 4444 4444 4442", "09", "24", "Ivan Ivanov", "555");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String holder;
        String CVC;
    }

    @Value
    public static class PaymentTransaction {
        int amount;
        String status;
    }

    @Value
    public static class CreditTransaction {
        String status;
    }

}

