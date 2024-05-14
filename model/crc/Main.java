
public class Main {
    
    static String XOR(String b1, String b2) {

        String result = "";

        for (int i=0; i<b1.length(); i++) {
            if ((b1.charAt(i) == b2.charAt(i))) {
                result += "0";
            } else {
                result += "1";
            }
        }

        return result;

    }

    static String getEffectiveBin(String bin) {
        for (int i=0; i<bin.length(); i++) {
            if (bin.charAt(i) != '0') {
                return bin.substring(i);
            }
        }
        return "";
    }

    static String formatLength(String bin, int len) {
        while (bin.length() < len) {
            bin = '0' + bin;
        }
        return bin;
    }

    static String Mod2Bin(String divident, String divisor) {

        divident = getEffectiveBin(divident);
        divisor = getEffectiveBin(divisor);

        int divisorLength = divisor.length();
        String remDivident = divident;

        String remainder = remDivident.substring(0, divisorLength);
        remDivident = remDivident.substring(divisorLength); 

        while (remDivident.length() > 0 || getEffectiveBin(remainder).length() >= divisorLength) {

            remainder = getEffectiveBin(XOR(remainder, divisor));

            while (remainder.length() < divisorLength) {

                if (remDivident.length() == 0) break;

                remainder += remDivident.charAt(0);
                remDivident = remDivident.substring(1);

            }

        }

        return getEffectiveBin(remainder).length() == 0 ? "0" : getEffectiveBin(remainder);

    }

    static String sendersSide(String data, String key) {

        String appendedData = data + new String(new char[key.length() - 1]).replace("\0", "0");

        String remainder = Mod2Bin(appendedData, key);
        remainder = formatLength(remainder, key.length() - 1);

        System.out.println("Divident  : " + appendedData);
        System.out.println("Remainder : " + remainder);
        data = data + remainder;

        return data;

    }

    static void recieversSide(String data, String key) {

        String remainder = Mod2Bin(data, key);
        if (remainder == "0") {
            System.out.println("Correct message recieved");
        } else {
            System.out.println("Error occured in data");
        }

    }

    public static void main(String[] args) {

        String data = "1010000";
        String key = "1001";

        System.out.println("Data      : " + data);
        System.out.println("Key       : " + key + "\n");

        System.out.println("Sender's Side => ");
        
        String dataSent = sendersSide(data, key);
        System.out.println("Date Sent : " + dataSent + "\n");
        
        System.out.println("Reciever's Side => ");
        recieversSide(dataSent, key);

    }

}