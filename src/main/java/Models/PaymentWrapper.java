package Models;


import java.util.List;

public class PaymentWrapper {

    private String cardNumber;
    private String getCardName;
    private List<Integer> year;
    private List<Integer> month;
    private long cvvNumber;
    private List<String > cardType;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getGetCardName() {
        return getCardName;
    }

    public void setGetCardName(String getCardName) {
        this.getCardName = getCardName;
    }

    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }

    public List<Integer> getMonth() {
        return month;
    }

    public void setMonth(List<Integer> month) {
        this.month = month;
    }

    public long getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(long cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public List<String> getCardType() {
        return cardType;
    }

    public void setCardType(List<String> cardType) {
        this.cardType = cardType;
    }
}
