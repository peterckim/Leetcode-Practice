public class Median {
    public static void main(String[] args) {
//        int[] array1 = new int[]{1, 2};
//        int[] array2 = new int[]{3, 4};
//        int[] testArray1 = new int[]{1, 3};
//        int[] testArray2 = new int[]{2};
//        System.out.println(findMedianSortedArrays(array1, array2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Brute Force
        // 1. Combine both Arrays
        // 2. Get Combined Array Length
        // 3. If Even
        // 4. If Odd
        int lengthOfFirstArray = nums1.length;
        int lengthOfSecondArray = nums2.length;
        int count = 0;
        int i = 0;
        int j = 0;

        int[] combinedArray = new int[lengthOfFirstArray + lengthOfSecondArray];

        while (i < lengthOfFirstArray && j < lengthOfSecondArray) {
            // 1. Compare nums1[i] with nums2[j], and Add the minimum
            if (nums1[i] == nums2[j]) {
                combinedArray[count] = nums1[i];
                i++;
            } else {
                if (nums1[i] < nums2[j]) {
                    combinedArray[count] = nums1[i];
                    i++;
                } else {
                    combinedArray[count] = nums2[j];
                    j++;
                }
            }
            count++;
        }

        // Store remaining elements of first array
        while (i < lengthOfFirstArray) {
            combinedArray[count] = nums1[i];
            count++;
            i++;
        }

        // Store remaining elements of second array
        while (j < lengthOfSecondArray) {
            combinedArray[count] = nums2[j];
            count++;
            j++;
        }

        if (combinedArray.length % 2 == 0) {
            int half = combinedArray.length / 2;
            double result = ((double)combinedArray[half] + (double)combinedArray[half - 1]) / 2;
            return result;
        } else {
            int half = (int)Math.ceil(combinedArray.length / 2);
            return combinedArray[half];
        }
    }

//    public static double FindMedianSortedArrays(int[] A, int[] B) {
//        // Final Values
//        final int lengthOfA = A.length;
//        final int lengthOfB = B.length;
//        final double lengthOfCombinedArray = (double)lengthOfA + (double)lengthOfB;
//        final int lengthOfLeftSubarray = (int) Math.ceil(lengthOfCombinedArray / 2);
//
//
//
//        int minContributedByA = lengthOfLeftSubarray - lengthOfB;
//        int maxContributedByB = lengthOfLeftSubarray - lengthOfA;
//
//        int midPoint = (minContributedByA + maxContributedByB) / 2;
//
//        int numberOfElementsContributedByB = lengthOfLeftSubarray - midPoint;
//
//        if (A[midPoint - 1] >= B[numberOfElementsContributedByB - 1]) {
//            if (A[midPoint - 1] > B[numberOfElementsContributedByB]) {
//
//            }
//        } else {
//            if (B[numberOfElementsContributedByB - 1] > A[midPoint]) {
//
//            }
//        }
//    }
}

