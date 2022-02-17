package Divide_and_Conquer.scr;

public class SortingAndCounting {
    public static int countInversions(int[] array) {
        return sortInversions(array, 0, array.length-1);
    }
    
    static int sortInversions(int[] array, int low, int high) {
    int inversions = 0;
    int mid;
        if(high > low){
        mid = (low+high)/2;
        inversions = sortInversions(array, low, mid);
        inversions += sortInversions(array, mid+1, high);
        inversions += mergeInversions(array, low, mid+1, high);
        }
        return inversions;
    }
    
    static int mergeInversions(int[] arr, int low, int mid, int high) {
        int inversions = 0;
        int[] temp = new int[arr.length];
        int i = low;
        int j = mid;
        int k = low;
        while((i <= mid-1) && j <= high){
        if(arr[i] <= arr[j]){
            temp[k++] = arr[i++];
        }else{
            temp[k++] = arr[j++];
            inversions = inversions + (mid-i); 
        }
        }
        while(i <= mid-1){
        temp[k++] = arr[i++];
        }
        while(j <= high){
        temp[k++] = arr[j++];
        }
        for(i = low; i <= high; i++){
        arr[i] = temp[i];
        }
        return inversions;
    }
}
