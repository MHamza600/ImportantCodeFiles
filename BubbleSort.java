
public class BubbleSort implements SortingAlgorithm {

    @Override
    public void sort(Object[] list, Comparator comparator) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - 1; j++) {
                if (comparator.compare(list[j + 1], list[j]) > 0) {
                    Object temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }
}