import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Project: seleniumDemo
 * @Author: 54540
 * @Create: 2021-04-26 13:51
 * @Desc：
 **/
public class SortDemo {
    public static void main(String[] args) {
        int[] arr = new int[6];
        arr[0]=23;
        arr[1]=43;
        arr[2]=22;
        arr[3]=11;
        arr[4]=15;
        arr[5]=66;
        //冒泡排序
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

        //列表去重
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("333");
        list.add("444");
        distinct(list);

    }
    //public static void distinct(List list){
    //    HashSet set = new HashSet(list);
    //    System.out.println(set);
    //}
    public static void distinct(List list){
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if(!listTemp.contains(list.get(i))){
                listTemp.add(list.get(i));
            }

        }
        System.out.println(listTemp);
    }
}
