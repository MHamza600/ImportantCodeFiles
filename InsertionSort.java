
public class InsertionSort implements SortingAlgorithm {
    
    @Override
    public void sort(Object[] list, Comparator comparator) {
        for (int i = 0; i < list.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(list[j], list[j - 1]) > 0) {
                Object temp = list[j];
                list[j] = list[j - 1];
                list[j - 1] = temp;
                j--;
            }
        }
    }
}
