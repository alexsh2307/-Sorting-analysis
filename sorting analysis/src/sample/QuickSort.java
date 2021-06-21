package sample;

public class QuickSort {
    private int[] array;
    private int low, high;

    public QuickSort(int[] array) {
        this.array = array;
    }

    public long quickSortRight()
    {
        long start = System.currentTimeMillis();
        low = 0;
        high = array.length-1;
        while (true){
            if (array.length == 0)
                break;

            if (low >= high)
                break;

            int middle = high;;
            int opora = array[middle];

            int i = low, j = high;

            while (i <= j) {
                while (array[i] < opora) {
                    i++;
                }

                while (array[j] > opora) {
                    j--;
                }

                if (i <= j) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) {
                high = j;
            } else {
                if (high > i) {
                    low = i;
                } else {
                    break;
                }
            }
        }
        return System.currentTimeMillis() - start;
    }

    public long quickSortLeft() {
        long start = System.currentTimeMillis();
        low = 0;
        high = array.length-1;
        while (true){
            if (array.length == 0)
                break;

            if (low >= high)
                break;

            int middle = low;;
            int opora = array[middle];

            int i = low, j = high;

            while (i <= j) {
                while (array[i] < opora) {
                    i++;
                }

                while (array[j] > opora) {
                    j--;
                }

                if (i <= j) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) {
                high = j;
            } else {
                if (high > i) {
                    low = i;
                } else {
                    break;
                }
            }
        }
        return System.currentTimeMillis() - start;
    }

    public String quickSortR(int low, int high) {
        String str = "";
        if (low < high) {
            int partitionIndex = partition(low, high);
            for (int I = 0; I < array.length; I++) {
                str += array[I] + " ";
            }
            str += "\n\n";
            str += quickSortR(low, partitionIndex-1);
            str += quickSortR(partitionIndex+1, high);
        }
        for (int I = 0; I < array.length; I++) {
            str += array[I] + " ";
        }
        str += "\n\n";
        return str;
    }

    private int partition(int begin, int end) {
        int pivot = array[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;

                int swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }

        int swapTemp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;

        return i+1;
    }

    public String quickSortL(int low, int high) {
        String str = "";
        int pivot;
        int l_hold = low;
        int r_hold = high;
        pivot = array[low];
        while (low < high)
        {
            while ((array[high] >= pivot) && (low < high))
                high--;
            if (low != high)
            {
                array[low] = array[high];
                low++;
            }
            while ((array[low] <= pivot) && (low < high))
                low++;
            if (low != high)
            {
                array[high] = array[low];
                high--;
            }
        }
        array[low] = pivot;
        pivot = low;
        low = l_hold;
        high = r_hold;
        for (int I = 0; I < array.length; I++) {
            str += array[I] + " ";
        }
        str += "\n\n";
        if (low < pivot)
            str += quickSortL(low, pivot - 1);
        if (high > pivot)
            str += quickSortL(pivot + 1, high);
        return str;
    }
}
