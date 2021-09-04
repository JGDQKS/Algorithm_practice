import javax.sound.sampled.Line;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

public class Class01_10 {
    static int[] arr = {1, 3, 9, 8, 5, 5};


    public static void main(String[] args) {
        heapSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }

    }

    // 体系班01 三种基本排序，概率问题
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int f1() {//把 1-5 等概率转换成 0-1等概率
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);
        ans = ans < 3 ? 0 : 1;
        return ans;
    }

    public static int f2() { //0-1等概率转成0-6等概率
        int ans = 0;
        do {
            ans = (f1() << 2) + (f1() << 1) + (f1());
        } while (ans == 7);
        return ans;
    }

    public static int f3() { //0-6等概率返回加1就是1-7
        return f2() + 1;
    }

    private static void inserSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int cur = i;
            while (cur - 1 >= 0 && arr[cur] < arr[cur - 1]) {
                swap(arr, cur, cur - 1);
                cur--;
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                min = arr[j] > arr[min] ? min : j;
            }
            swap(arr, i, min);
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ N-1
        // 0 ~ N-2
        // 0 ~ N-3
        int N = arr.length;
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }

        }
    }

    public static int findNearLeft(int[] arr, int num) {
        inserSort(arr);
        int L = 0;
        int R = arr.length - 1;
        int index = 0;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= num) {
                R = mid - 1;
                index = mid;
            } else {
                L = mid + 1;
                index = mid;
            }
        }
        return index;
    }

    // 体系班02 异或
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void EvenTimeOddTimes(int[] arr) {
        int length = arr.length;
        int ero = 0;
        int getA = 0;
        for (int value : arr) {
            ero = ero ^ value;
        }

        int getOne = ero & (-ero);
        for (int value : arr) {
            if ((getOne & value) != 0) {
                getA = getA ^ value;
            }

        }
        System.out.println(getA + " " + (getA ^ ero));

    }

    public static int OnlyKMTimes(int[] arr, int k, int m) {
        int[] temp = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 31; i++) {
                temp[i] += (num >> i) & 1;

            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (temp[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    // 体系班03 单双链表，栈和队列
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    public static Node ReverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = head.next;
        }
        return pre;

    }

    public static DoubleNode ReverseDoubleNodeList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode cur = null;
        while (head != null) {
            cur = head.next;
            head.next = pre;
            head.last = cur;
            pre = head;
            head = cur;
        }
        return pre;
    }

    public static Node removeValue(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        Node cur = head;
        Node pre = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static int processC3_1(int[] arr, int L, int R) { //递归找最大
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) << 1);
        int leftMax = processC3_1(arr, L, mid);
        int RightMax = processC3_1(arr, mid + 1, R);
        return Math.max(leftMax, RightMax);
    }

    //体系班04 归并排序
    public static void processC4_1(int[] arr, int L, int R) {//递归方法实现归并排序
        // 请把arr[L..R]排有序
        // l...r N
        // T(N) = 2 * T(N / 2) + O(N)
        // O(N * logN)
        if (L == R) {
            return;
        }
        int M = L + ((R + L) >> 1);
        processC4_1(arr, L, M);
        processC4_1(arr, M + 1, R);
        mergeC4_1(arr, L, M, R);
    }

    public static void mergeC4_1(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

    }

    public static void mergeC4_2(int[] arr) {//非递归方法实现归并排序
        if (arr == null || arr.length < 2) {
            return;
        }
        int stepLength = 1;
        int N = arr.length;
        while (stepLength < N) {
            int L = 0;
            while (L < N) {
                if (stepLength >= N - L) {
                    break;
                }
                int M = L + stepLength - 1;
                int R = M + Math.min(stepLength, N - M - 1);
                mergeC4_1(arr, L, M, R);
                L = R + 1;
            }
            if (stepLength > (N >> 1)) {
                break;
            }
            stepLength <<= 1;
        }
    }

    public static int processC4_2(int[] arr, int l, int r) { //小和问题
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return
                processC4_2(arr, l, mid)
                        +
                        processC4_2(arr, mid + 1, r)
                        +
                        mergeC4_3(arr, l, mid, r);
    }

    public static int mergeC4_3(int[] arr, int L, int m, int R) {//小和问题
        int[] help = new int[arr.length];
        int p1 = L;
        int p2 = m + 1;
        int res = 0;
        int i = 0;
        while (p1 <= m && p2 <= R) {
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    public static int mergeC4_4(int[] arr, int L, int m, int r) {
        // [L....M]   [M+1....R]

        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = m + 1;
        for (int i = L; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - m - 1;
        }


        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public static int processC4_3(int[] arr, int L, int R) {//逆序对
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return processC4_3(arr, L, M) + processC4_3(arr, M + 1, R) + mergeC4_5(arr, L, M, R);
    }

    private static int mergeC4_5(int[] arr, int L, int M, int R) {//逆序对
        int[] help = new int[R - L + 1];
        int p1 = M;
        int p2 = R;
        int res = 0;
        int i = help.length - 1;
        while (p1 >= L && p2 >= M + 1) {
            res = arr[p1] > arr[p2] ? (p2 - M) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 > M) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + 1] = help[i];
        }
        return res;
    }

    //体系班05 归并附加题与快速排序
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return processC5_1(sum, 0, nums.length - 1, lower, upper);
    }

    public static int processC5_1(long[] sum, int L, int R, int lower, int upper) {
        int M = L + ((R - L) >> 1);
        return processC5_1(sum, L, M, lower, upper) + processC5_1(sum, M + 1, R, lower, upper) + mergeC5_01(sum, L, M, R, lower, upper);
    }

    private static int mergeC5_01(long[] sum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = R;
        for (int i = M = 1; i < R + 1; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowL <= M && windowL < min) {
                windowL++;
            }
            while (windowR <= R && windowR <= max) {
                windowR++;
            }
            ans += windowR - windowL;
        }
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = sum[p1] <= arr[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= M) {
            help[i++] = sum[p1++];

        }
        while (p2 <= R) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }
        return ans;
    }

    public static int[] netherlandsFlag1(int[] arr, int L, int R) {
        int lessScope = L - 1;
        int moreScope = R;
        int index = L;
        int valu = arr[R];

        while (index < moreScope) {
            if (arr[index] < arr[R]) {
                swap(arr, index, lessScope + 1);
                index++;
                lessScope++;
            } else if (arr[index] == arr[R]) {
                index++;
            } else {
                swap(arr, index, moreScope - 1);
                moreScope--;
            }

        }
        swap(arr, moreScope - 1, R);
        System.out.println(lessScope + 1);
        System.out.println(moreScope);

        return new int[]{lessScope + 1, moreScope};

    }

    public static void quickSort1(int[] arr) { //快排1.0
        if (null == arr) {
            return;
        }
        processC5_2(arr, 0, arr.length - 1);
    }

    private static void processC5_2(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] ints = netherlandsFlag1(arr, L, R);
        processC5_2(arr, L, ints[0] - 1);
        processC5_2(arr, ints[1] + 1, R);

    }

    public static class Op { //快排3.0非递归 用栈实现
        public int l;
        private int r;

        public Op(int left, int right) {
            this.l = left;
            this.r = right;
        }
    }

    public static void quickSort2(int[] arr) {
        if (null == arr) {
            return;
        }
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equlArea = netherlandsFlag1(arr, 0, N - 1);
        int eL = equlArea[0];
        int eR = equlArea[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, eL - 1));
        stack.push(new Op(eR + 1, N - 1));
        while (!stack.isEmpty()) {
            Op pop = stack.pop();
            if (pop.l < pop.r) {
                swap(arr, pop.r - pop.l + 1, pop.r);
                int[] ints = netherlandsFlag1(arr, pop.l, pop.r);
                eL = ints[0];
                eR = ints[1];
                stack.push(new Op(pop.l, eL - 1));
                stack.push(new Op(eR + 1, pop.r));
            }
        }
    }

    //体系班06 手写堆和堆排序
    public static void heapInsert(int[] arr, int index) {

        while (arr[(index - 1) / 2] < arr[index]) {
            int dad = (index - 1) / 2;
            swap(arr, dad, index);
            index = dad;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int Lson = index * 2 + 1;
        while (Lson < heapSize) {
            int largest = Lson + 1 < heapSize && arr[Lson + 1] > arr[Lson] ? Lson + 1 : Lson;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            Lson = index * 2 + 1;
        }
    }

    public static void heapSort(int[] arr) {
        if (null == arr) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            heapInsert(arr, i);

        }
        int heapSize = arr.length;
        swap(arr, 0, heapSize - 1);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }


    }

    //体系班07 加强堆
    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line> {


        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int maxCover(int[][] m) { //1
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && lines[i].start >= heap.peek()) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());

        }
        return max;
    }

    public static class Customer {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int v, int b, int o) {
            id = v;
            buy = b;
            enterTime = 0;
        }
    }

    public List<List<Integer>> givePrice(int[] arr, boolean[] op, int k) {//给前k名用户颁奖
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> winners = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrNot = op[i];
            if (!buyOrNot && !map.containsKey(id)) {
                ans.add(getCurAns(winners));
                continue;
            }
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            Customer c = map.get(id);
            if (buyOrNot) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                map.remove(id);
            }
            if (!cands.contains(id) && !winners.contains(id)) {
                if (winners.size() < k) {
                    c.enterTime = i;
                    winners.add(c);
                } else {
                    c.enterTime = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(winners);
            cands.sort(new candsComparator());
            winners.sort(new WinnerComparator());
            move(cands, winners, k, i);
            ans.add(getCurAns(winners));

        }
        return ans;
    }

    private void move(ArrayList<Customer> cands, ArrayList<Customer> winners, int k, int i) {
        if (cands.isEmpty()) {
            return;
        }
        if (winners.size() < k) {
            Customer c = cands.get(0);
            c.enterTime = i;
            winners.add(c);
        } else {
            if (cands.get(0).buy > winners.get(0).buy) {
                Customer winnerBad = winners.get(0);
                Customer CandGood = cands.get(0);
                winners.remove(0);
                Customer newWinner = CandGood;
                cands.remove(0);
                newWinner.enterTime = i;
                winnerBad.enterTime = i;
                winners.add(newWinner);
                cands.add(winnerBad);
            }
        }
    }

    public static class candsComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy == o2.buy ? (o1.enterTime - o2.enterTime) : (o2.buy - o1.buy);
        }
    }

    public static class WinnerComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy == o2.buy ? (o1.enterTime - o2.enterTime) : o1.buy - o2.buy;
        }
    }

    private void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<>();
        for (Customer customer : arr) {
            if (customer.buy != 0) {
                noZero.add(customer);
            }


        }
        arr.clear();
        for (Customer customer : noZero) {
            arr.add(customer);
        }
    }

    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }

    //体系版08 前缀树和两种不基于比较的排序
    public static void RedixSort(int[] arr,int digit){//基数排序
        final int redix = 10;
        int[] help = new int[arr.length];
        int[] count = new int[redix];
        int L = 0;
        int R = arr.length-1;
        for (int i = 1; i < digit; i++) {
            for (int j = 0; j < arr.length; j++) {
                int times = getDigit(arr[j],i);
                count[times]++;
            }
            for (int j = 1; j < redix; j++) {
                count[j] = count[j] + count[j-1];
            }
            for (int j = R; j > L; j--) {
                int times = getDigit(arr[j],digit);
                help[count[times] -1] = arr[i];
                count[times] --;
            }
            for (int j = 0; j < arr.length; j++) {
                arr[i] = help[i];
            }


        }

    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static void countSort(int[] arr){//计数排序
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int[] bucket = new int[max+1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[arr[i]]++;

        }
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0){
                arr[j] = i;
                bucket[i] --;
                j++;
            }

        }

    }






}
