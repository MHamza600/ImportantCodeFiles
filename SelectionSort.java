
public class SelectionSort implements SortingAlgorithm{

    @Override
    public void sort(Object[] list, Comparator comparator) {
       for (int i = 0; i < list.length - 1; i++) {
           int selectedIndex = i;
           for (int j = i + 1; j < list.length; j++) {
               if (comparator.compare(list[j], list[selectedIndex]) > 0) {
                   selectedIndex = j;
               } 
           }
           Object temp = list[i];
           list[i] = list[selectedIndex];
           list[selectedIndex] = temp;
       }
    }
}
