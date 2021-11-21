package test.example;

public class ConsignmentValues {
    public String carrierName;
    public String accountNumber;
    public int digits;
    public int rangeStart;
    public int rangeEnd;
    public int lastUsedIndex;

    
    
    public ConsignmentValues() {
    }

    public ConsignmentValues(String carrierName, String accountNumber, int digits,int lastUsedIndex,int rangeStart,int rangeEnd) {
        this.carrierName = carrierName;
        this.accountNumber = accountNumber;
        this.digits = digits;
        this.lastUsedIndex=lastUsedIndex;
        this.rangeStart=rangeStart;
        this.rangeEnd=rangeEnd;
    }

}
