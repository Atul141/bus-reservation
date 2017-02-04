package Models;



public class Payment {

    private String cardNumber;
    private String getCardName;
    private int year;
    private int month;
    private long cvvNumber;
    private String  cardType;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(long cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
