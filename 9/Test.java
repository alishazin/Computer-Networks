
public class Test {

    static int getMaxSum(int arr[], int k) {

        if (k > arr.length) {
            System.out.println("k must be less than array size");
            return -1;
        }

        int maxSum = 0;
        int windowSum = 0;
        int start = 0;
        int n = arr.length; 

        for (int i=0; i<n; i++) {

            windowSum += arr[i];

            if ((i - start + 1) == k) {
                maxSum = Math.max(windowSum, maxSum);
                windowSum -= arr[start];
                start += 1;
            }

        }

        return maxSum;
    }

    public static void main(String[] args) {

        // int k = 2;
        // int a[] = {100, 200, 300, 100};

        int k = 3;
        int a[] = {16, 12, 9, 19, 69, 8};

        System.out.println("Max Sum: " + getMaxSum(a, k));

    } 

}
