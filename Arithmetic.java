package sortArithmetic;

import java.util.Arrays;

/**
 * Created by duchengyang on 2018/11/27.
 */

public class Arithmetic {

//①：插入类排序    直接插入排序  希尔排序
    public static void simpleInsertSort(int[] arr){            //直接插入排序思想  将数组中的所有元素依次跟前面已经排好的元素相比较，如果选择的元素比已排序的元素小，则交换，直到全部元素都比较过。
        int insertNote;                                                     //1.第一层循环：遍历所有未比较的元素,第一个元素默认已排好序，故从第二个开始。
        for (int i = 1; i < arr.length; i++) {                              //2.第二层循环：将本轮选择的未比较元素与已排好序的元素相比较。
            int j = i-1;
            insertNote = arr[i];                       //insertNote即为当前循环中需要排序的元素
            while (j>=0&&insertNote<arr[j]){          //当insertNote小于和它比较的元素且指针j>=0,则调换位置，且指针j往前一格
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1]=insertNote;            //当insertNote不小于和他比较的元素，则可以塞值，将insertNote值赋给当前指针j元素的后一位
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void shellSort(int[] arr){                //希尔排序思想：将待排序数组按照gap=数组的长度/2的数值进行分组，然后将每组的元素进行直接插入排序；第一轮排序完毕后，将gap数值减半，
        int gap,insertNote,j;                               // 进行第二轮循环；直到gap=1时，希尔排序退化为直接插入排序。
        for(gap=arr.length/2;gap>=1;gap/=2){                //1.第一层循环：将gap依次折半，对序列进行分组，直到gap=1
            for(int i=gap;i<arr.length;i++){                //2.第二、三层循环：也即直接插入排序所需要的两次循环。
                j=i-gap;
                insertNote = arr[i];
                while (j>=0&&insertNote<arr[j]){
                    arr[j+gap] = arr[j];
                    j-=gap;
                }
                arr[j+gap] = insertNote;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
//②选择类排序 简单选择排序  堆排序

    public static void simpleSelectSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){                //简单选择排序  从待排序序列中，找到关键字最小的元素；从余下的 N - 1 个元素中，找出关键字最小的元素，重复(1)、(2)步，直到排序结束。因此我们可以发现，简单选择排序也是通过两层循环实现。
            for (int j=i+1;j<arr.length-i;j++){         // 第一层循环：依次遍历序列当中的每一个元素
                if(arr[j]<arr[i]){                     //第二层循环：将遍历得到的当前元素依次与余下的元素进行比较，符合最小元素的条件，则交换。
                    arr[i] = arr[j];
                }
            }
        }
        //优化后
        for(int i=0;i<arr.length-1;i++){
            int k = i;
            for (int j=i+1;j<arr.length-i;j++){
                if(arr[j]<arr[i]){
                    k = j;  //记下目前找到的最小值所在的位置
                }
            }
            //本轮循环结束后，找到本轮最小的数，和arr[i]进行交换
            if(i!=k){
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;

            }
        }

        System.out.println(Arrays.toString(arr));
    }

    //堆排序
/* 首先将序列构建称为大顶堆；
（这样满足了大顶堆那条性质：位于根节点的元素一定是当前序列的最大值）
    取出当前大顶堆的根节点，将其与序列末尾元素进行交换；
  （此时：序列末尾的元素为已排序的最大值；由于交换了元素，当前位于根节点的堆并不一定满足大顶堆的性质）
    对交换后的n-1个序列元素进行调整，使其满足大顶堆的性质；
    重复2.3步骤，直至堆中只有1个元素为止*/


    //arr待调整序列 length: 序列长度 index:需要调整的结点
    public static void adjust_max_heap(int[] arr,int length,int index){
       int largest = index;
       int tmp = arr[index];
       while (true){
           int left = 2*index+1;
           int right = 2*index+2;
           if(left<length&&arr[left]>arr[index]){
               largest = left;
               tmp = arr[left];
           }else {
               largest = index;
           }
           if(right<length&&arr[right]>arr[index]&&arr[right]>tmp){
               largest = right;
           }
           if(largest!=index){
               int temp = arr[index];
               arr[index] = arr[largest];
               arr[largest] = temp;
               continue;
           }else {
               break;
           }
       }

    }
    public static void build_max_heap(int[] arr) {
        int length = arr.length;
        for (int i = 0; i <= (length - 1) / 2; i++) {
            adjust_max_heap(arr, length, i);

        }
    }
    public static void heap_sort(int[] arr) {
        build_max_heap(arr);
        int length = arr.length;
        while (length>0){
            int temp = arr[length-1];
            arr[length-1] = arr[0];
            arr[0] = temp;

            length--;
            adjust_max_heap(arr,length,0);

        }

        System.out.println(Arrays.toString(arr));
    }

//  ③ 交换类排序  冒泡排序  快速排序
//冒泡排序
/*/将序列当中的左右元素，依次比较，保证右边的元素始终大于左边的元素；
（ 第一轮结束后，序列最后一个元素一定是当前序列的最大值；）
    对序列当中剩下的n-1个元素再次执行步骤1。
    对于长度为n的序列，一共需要执行n-1轮比较
（利用while循环可以减少执行次数）*/
public static void bubbleSort(int[] arr){
    for(int i = 0;i<arr.length-1;i++){          //外层控制排序的趟数
        for(int j = 0;j<arr.length-i-1;j++){    //内层控制每一趟需要交换的次数
            if(arr[j]>arr[j+1]){
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
            }
        }
    }
    System.out.println(Arrays.toString(arr));
}
//快速排序   选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
/*
    一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有继续比较下一个，直到找到第一个比基准值小的值才交换该值和基准值。找到这个值之后，
    又从前往后开始比较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的值才交换。直到从前往后的比较索引>从后往前比较的索引，
    结束第一次循环，此时，对于基准值来说，左右两边就是有序的了。
*/
//  arr 数组  i 左边第一个数的索引值  j 右边第一个数的索引值
public static void quickSort(int[] arr,int i,int j){
    int left = i;   //左边索引值
    int right = j;  //右边索引值
    int key = arr[i];   //基准值，关键值
    while (right>left){
        //从后往前比较
        while (right>left&&arr[right]>=key){  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
            right--;
        }
        if(arr[right]<key){         //从右边开始找，找到比关键值小的值，交换左右索引值所指的数
            int temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
        }
        //从前往后比较
        while (right>left&&arr[left]<=key){ //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
            left++;
        }
        if(arr[left]>key){      //从左边开始找，找到比关键值大的值，交换左右索引值所指的数
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

    }
    System.out.println(Arrays.toString(arr));
    //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
    if(left>i){                     //左边序列。第一个索引位置到关键值索引-1
        quickSort(arr,i,left-1);
    }
    if(right<j){
        quickSort(arr,right+1,j); //右边序列。从关键值索引+1到最后一个
    }

}
//快速排序思想2：①循环完成后，先不交换，等②过程也完成后，交换left和right指针上的值。
    public static void quickSort1(int[] arr,int i,int j){
        int left = i;   //左边索引值
        int right = j;  //右边索引值
        int key = arr[i];   //基准值，关键值
        while (right>left){
            //从后往前比较
            while (right>left&&arr[right]>=key){  //①如果没有比关键值小的，比较下一个，直到有比关键值小的
                right--;
            }

            //从前往后比较
            while (right>left&&arr[left]<=key){ //②如果没有比关键值大的，比较下一个，直到有比关键值大的
                left++;
            }
            if(arr[left]>key&&arr[right]<key){      //
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }

        }
        //待left》=right时，最后将基准数arr[i]归为到arr[right]
        int temp = arr[right];
        arr[right] = arr[i];
        arr[i] = temp;

        System.out.println(Arrays.toString(arr));
        //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        if(left>i){                     //左边序列。第一个索引位置到关键值索引-1
            quickSort(arr,i,left-1);
        }
        if(right<j){
            quickSort(arr,right+1,j); //右边序列。从关键值索引+1到最后一个
        }

    }

// ④ 归并排序
/*归并排序是建立在归并操作上的一种有效的排序算法，该算法是采用分治法的一个典型的应用。它的基本操作是：将已有的子序列合并，达到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
归并排序其实要做两件事：
分解----将序列每次折半拆分
合并----将划分后的序列段两两排序合并
因此，归并排序实际上就是两个操作，拆分+合并
如何合并？
L[first...mid]为第一段，L[mid+1...last]为第二段，并且两端已经有序，现在我们要将两端合成达到L[first...last]并且也有序。
首先依次从第一段与第二段中取出元素比较，将较小的元素赋值给temp[]
重复执行上一步，当某一段赋值结束，则将另一段剩下的元素赋值给temp[]
此时将temp[]中的元素复制给L[]，则得到的L[first...last]有序
如何分解？
在这里，我们采用递归的方法，首先将待排序列分成A,B两组；然后重复对A、B序列
分组；直到分组后组内只有一个元素，此时我们认为组内所有元素有序，则分组结束。
 */
    public static void mergeSort(int[] arr){
        int[] temp = new int[arr.length];  //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        merge_sort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
    //i 头指针  j 尾指针  temp 临时数组
    private static void merge_sort(int[] arr,int i,int j,int[] temp) {
        if(i<j){
            int mid = (i+j)/2;
            //递归
            merge_sort(arr,i,mid,temp);    //左边归并排序，使得左子序列有序
            merge_sort(arr,mid+1,j,temp); //右边归并排序，使得右子序列有序
            merge(i,j,mid,arr,temp);       //将两个有序子数组合并操作

        }
    }

    private static void merge(int i,int j,int mid,int[] arr,int[] temp) {
        int a = i;      //左序列指针
        int b = mid+1;  //右序列指针
        int t = 0;      //temp的指针
        while (a<=mid&&j>=b){    //两个子序列中的元素都没有比较完的时候
            if(arr[a]<=arr[b]){
                temp[t++] = arr[a++];
            }else {
                temp[t++] = arr[b++];
            }
        }
        while (a<=mid){     //右边比较结束，将左边的剩余元素全部加入temp数组
            temp[t++] = arr[a++];
        }
        while (j>=b){       //左边比较结束，将右边剩余元素全部加入数组
            temp[t++] = arr[b++];
        }
        t = 0;      //temp构建完成，将temp中的元素全部拷贝到原数组中
        while (i<=j){
            arr[i++] = temp[t++];
        }
    }

 // ⑤ 基数排序
    /*基数排序(Radix Sort)是桶排序的扩展，它的基本思想是：将整数按位数切割成不同的数字，然后按每个位数分别比较。
具体做法是：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。*/
        private static int getMax(int[] a) {
            int max;

            max = a[0];
            for (int i = 1; i < a.length; i++)
                if (a[i] > max)
                    max = a[i];

            return max;
        }

        /*
         * 对数组按照"某个位数"进行排序(桶排序)
         *
         * 参数说明：
         *     a -- 数组
         *     exp -- 指数。对数组a按照该指数进行排序。
         *
         * 例如，对于数组a={50, 3, 542, 745, 2014, 154, 63, 616}；
         *    (01) 当exp=1表示按照"个位"对数组a进行排序
         *    (02) 当exp=10表示按照"十位"对数组a进行排序
         *    (03) 当exp=100表示按照"百位"对数组a进行排序
         *    ...
         */
        private static void countSort(int[] a, int exp) {
            //int output[a.length];    // 存储"被排序数据"的临时数组
            int[] output = new int[a.length];    // 存储"被排序数据"的临时数组
            int[] buckets = new int[10];

            // 将数据出现的次数存储在buckets[]中
            for (int i = 0; i < a.length; i++)
                buckets[ (a[i]/exp)%10 ]++;

            // 更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的位置。
            for (int i = 1; i < 10; i++)
                buckets[i] += buckets[i - 1];       //总计数值

            // 将数据存储到临时数组output[]中
            for (int i = a.length - 1; i >= 0; i--) {
                output[buckets[ (a[i]/exp)%10 ] - 1] = a[i];       //output索引值=总计数值-1
                buckets[ (a[i]/exp)%10 ]--;
            }

            // 将排序好的数据赋值给a[]
            for (int i = 0; i < a.length; i++)
                a[i] = output[i];

            output = null;
            buckets = null;
        }

        /*
         * 基数排序
         *
         * 参数说明：
         *     a -- 数组
         */
        public static void radixSort(int[] a) {
            int exp;    // 指数。当对数组按各位进行排序时，exp=1；按十位进行排序时，exp=10；...
            int max = getMax(a);    // 数组a中的最大值

            // 从个位开始，对数组a按"指数"进行排序
            for (exp = 1; max/exp > 0; exp *= 10)
                countSort(a, exp);
            System.out.println(Arrays.toString(a));
        }



    public static void main(String[] args) {
        int[] arr = {5,1,6,9,7,4,8,2,3,5};
        simpleInsertSort(arr);
        shellSort(arr);
        simpleSelectSort(arr);
        heap_sort(arr);
        bubbleSort(arr);
        quickSort(arr,0,9);
        quickSort1(arr,0,9);
        mergeSort(arr);
        radixSort(arr);

    }


}
