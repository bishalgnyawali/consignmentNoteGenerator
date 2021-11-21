package test.example;
import com.google.gson.Gson;
import java.lang.Math;
import java.io.Reader; 
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * next Consignment Note Number Generator
 */
public final class App {
    
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("freight.json"));
            // convert JSON string to ConsignmentValues object
            ConsignmentValues consignment = gson.fromJson(reader,ConsignmentValues.class);
            reader.close();
            // print Consignment object
            if(checkRange(consignment.rangeStart,consignment.rangeEnd,consignment.lastUsedIndex+1)){
                String prefix=courierFixMatch(consignment.carrierName);
                if(prefix!="No match"){
                    int mulValueFirst=checkSumCalculate(consignment.lastUsedIndex+1,consignment.digits,1);
                    int mulValueSecond=checkSumCalculate(consignment.lastUsedIndex+1,consignment.digits,2);
                    int checkSum=100-(mulValueFirst+mulValueSecond);
                    // return (100-(mulValueFirst+mulValueSecond));
                    String accountNumber=getAccountNumber(consignment.accountNumber);
                    String consignmentPadded=paddIndex((consignment.lastUsedIndex+1)+String.valueOf(checkSum));
                    System.out.println(prefix+accountNumber+consignmentPadded);
                    
                }else{
                    System.out.println("Error:No Courier Service match code found");
                    
                }
                
            }else{
                System.out.println("Error:Invalid Range for the Consignment Index");
                
            }
            
            
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Gson gson = new GsonBuilder().create();
        // System.out.println("Hello Bishal");
        
    }
    /**
     * 
     * @param courierName
     * @return returns short value for courierName like FMCC, if match found else returns No match
     */
    public static String courierFixMatch(String courierName){
        switch (courierName){
            case "FreightmateCourierCo":
                return "FMCC";
                
            default:
                return "No match";
                
        }
    
    }
    
    /**
     * 
     * @param lastUsedIndex from json
     * @param lengthOfNumber from digits key value
     * @param digitPositionToStart for calculating checkSum, we need two parameters according to question start at position 1 and at position 2,So it calculates two values based on position.
     * @return
     */    
    public static int checkSumCalculate(int lastUsedIndex,int lengthOfNumber,int digitPositionToStart){
        int sum=0;
        if(digitPositionToStart==2){
            lastUsedIndex=lastUsedIndex/10;
        }
        for(int i=1;i<=Math.ceil(((double)lengthOfNumber/2));i++){
            sum=lastUsedIndex%10+sum;
            lastUsedIndex=lastUsedIndex/100;
            // modNumber=modNumber*100;
        }
        
        if(digitPositionToStart==1){
            return sum*3;
        }else{
            return sum*7;
        }
        
    }
    /***
     * 
     * @param lowerRange from json 
     * @param upperRange from json value
     * @param rangeValue from json next Consignment Index.
     * @return true if next consignment index is within range else false
     */
    private static boolean checkRange(int lowerRange,int upperRange,int rangeValue){
        if(rangeValue>=lowerRange&&rangeValue<=upperRange){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 
     * @param account from json accountNumber key. 
     * @return new string reversing the mid substring.
     */
    private static String getAccountNumber(String account){
        String newAccountValue=account.substring((account.length()/2))+account.substring(0,account.length()/2);
        return newAccountValue;
    }
    /**
     * assumption string to be padded with 0 is of total length 10
     * @param valueTopad
     * @return padded string
     */
    private static String paddIndex(String valueTopad){

        String newIndex=String.valueOf(valueTopad);
        newIndex=String.format("%010d", Integer.parseInt(newIndex));

        return newIndex;
    }
}




