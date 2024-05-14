
class BinaryException extends Exception {
    public BinaryException(String errorMessage) {  
        super(errorMessage);  
    }  
}

class Test {

    static String XOR(String b1, String b2) throws BinaryException {

        String result = "";
        if (b1.length() != b2.length()) {
            throw new BinaryException("b1 and b2 must be of same length");
        } 
        
        for (int i=0; i<b1.length(); i++) {
            if ((b1.charAt(i) != '1' && b1.charAt(i) != '0') || (b2.charAt(i) != '1' && b2.charAt(i) != '0')) {
                throw new BinaryException("b1 and b2 must not have any digit other than 1 or 0");
            }
            if (b2.charAt(i) == b1.charAt(i)) {
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

    static String ModBinDiv(String divident, String divisor) {

        divident = getEffectiveBin(divident);
        divisor = getEffectiveBin(divisor);

        int divisorLength = divisor.length();
        String remainingDivident = divident;

        String dividentTemp = "";
        String quotient = "";

        dividentTemp = remainingDivident.substring(0, divisorLength);
        remainingDivident = remainingDivident.substring(divisorLength);

        while (remainingDivident.length() > 0 || getEffectiveBin(dividentTemp).length() >= divisorLength) {    

            quotient += '1';
            try {
                dividentTemp = XOR(dividentTemp, divisor);
            } catch(BinaryException be) {
                System.out.println(be.toString());
            }
            
            dividentTemp = getEffectiveBin(dividentTemp);

            int count = 0;
            while (dividentTemp.length() < divisorLength) {
                if (count > 0) {
                    quotient += '0';
                }
                
                if (remainingDivident.length() == 0)
                    break;

                dividentTemp += remainingDivident.charAt(0);
                
                remainingDivident = remainingDivident.substring(1);
                count++;
            }

        }

        // System.out.println("quotient: " + quotient);
        return getEffectiveBin(dividentTemp).length() == 0 ? "0" : getEffectiveBin(dividentTemp);

    }
    static String formatToLength(String bin, int len) {
        while (bin.length() < len) {
            bin = '0' + bin;
        }
        return bin;
    }

    static String sendersSide(String data, String key) {
        String appendedData = data + new String(new char[key.length() - 1]).replace("\0", "0");

        String remainder = ModBinDiv(appendedData, key);
        remainder = formatToLength(remainder, key.length() - 1);

        System.out.println("Divident  : " + appendedData);
        System.out.println("Remainder : " + remainder);
        data = data + remainder;

        return data;
    }

    static void recieversSide(String data, String key) {

        String remainder = ModBinDiv(data, key);
        if (remainder == "0") {
            System.out.println("Correct message recieved");
        } else {
            System.out.println("Error occured in data");
        }
    }

    public static void main(String[] args) {

        String data = "1010000";
        String key = "1001";

        // String data = "1010001";
        // String key = "101";

        // String data = "100100";
        // String key = "1101";

        System.out.println("Data      : " + data);
        System.out.println("Key       : " + key + "\n");

        System.out.println("Sender's Side => ");
        
        String dataSent = sendersSide(data, key);
        System.out.println("Date Sent : " + dataSent + "\n");
        
        System.out.println("Reciever's Side => ");
        recieversSide(dataSent, key);

    }

}