package OrderSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    public static <T> void sort(List<T> list, Comparator<T> comp) {
        List<T> temp = new ArrayList<>(list);
        mergeSort(list, temp, 0, list.size() - 1, comp);
    }

    private static <T> void mergeSort(List<T> list, List<T> temp,
                                     int left, int right,
                                     Comparator<T> comp) {

        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(list, temp, left, mid, comp);
        mergeSort(list, temp, mid + 1, right, comp);

        merge(list, temp, left, mid, right, comp);
    }

    private static <T> void merge(List<T> list, List<T> temp,
                                 int left, int mid, int right,
                                 Comparator<T> comp) {

        for (int i = left; i <= right; i++) {
            temp.set(i, list.get(i));
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (comp.compare(temp.get(i), temp.get(j)) <= 0) {
                list.set(k++, temp.get(i++));
            } else {
                list.set(k++, temp.get(j++));
            }
        }

        while (i <= mid) {
            list.set(k++, temp.get(i++));
        }
    }
}
