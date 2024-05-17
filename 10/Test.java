
public class Test {

    static int[] generateCode(String str, int M, int r) {

        int arr[] = new int[r + M];
        int j = 0; 
        int q = 0; 
        for (int i=0; i<arr.length; i++) {
            if (Math.pow(2, q) == (i+1)) {
                q++;
                arr[i] = 0;
            } else {
                arr[i] = Integer.parseInt(String.valueOf(str.charAt(j)));
                j++;
            }
        }
        return arr;

    }

    static int[] calculateParityBits(int arr[]) {

        int q = 0; 
        for (int i=0; i<arr.length; i++) {
            if (Math.pow(2, q) == (i+1)) {
                
                for (int j=1; j<=arr.length; j++) {
                    if (((j >> q) & 1) == 1 && j != (i + 1)) {
                        arr[i] = arr[i] ^ arr[j-1];
                    }
                }
                q++;
            }
        }

        return arr;

    }

    public static void printCode(int[] arr) {

        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");            
        }

    }
    
    public static void main(String[] args) {

        String str = "100110110";
        int M = str.length();
        
        int r = 1;
        while (Math.pow(2, r) < M + r + 1) {
            r++;
        }
        
        int code[] = generateCode(str, M, r);
        int codeCompleted[] = calculateParityBits(code);
        printCode(codeCompleted);

    }

}