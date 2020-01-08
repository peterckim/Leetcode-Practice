package sorts;

public class MergeSort {
    // Main function
    public static void main(String[] args) {

    }

    // Merge Sort Algorithm
    public static int[] mergeSort(int[] inputArray) {
        // Base Case
        if (inputArray.length < 2) {
            return inputArray;
        }

        int midValue = inputArray.length / 2;
        int[] leftArray = new int[midValue];
        int[] rightArray = new int[inputArray.length - midValue];

        // Populate left array
        for (int i = 0; i < midValue; i++) {
            leftArray[i] = inputArray[i];
        }

        // Populate right array
        for (int i = 0; i < inputArray.length - midValue; i++) {
            rightArray[i] = inputArray[i + midValue];
        }


        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, inputArray);
        return inputArray;
    }

    // Merge Arrays
    public static int[] merge(int[] leftArray, int[] rightArray, int[] outputArray) {
        // return a sorted combined arrays
        int i = 0;
        int j = 0;
        int k = 0;


        // BUG LIES HERE
        while (i < leftArray.length || j < rightArray.length) {
            if (j >= rightArray.length && i < leftArray.length) {
                outputArray[k] = leftArray[i];

                k++;
                i++;
            } else if (i >= leftArray.length && j < rightArray.length) {
                outputArray[k] = rightArray[j];

                k++;
                j++;
            } else if (leftArray[i] > rightArray[j]) {
                outputArray[k] = rightArray[j];

                k++;
                j++;
            } else {
                outputArray[k] = leftArray[i];

                k++;
                i++;
            }
        }

        return outputArray;
    }
}
