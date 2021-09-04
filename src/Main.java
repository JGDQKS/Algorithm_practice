import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "abbdsncd";
        System.out.println("输入操作次数");
        int times = scanner.nextInt();
        findNear(s,times);

    }

    public static void findNear(String s, int times) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < times; i++) {
            System.out.println("opt?");
            int opt = scanner.nextInt();
            if (opt == 1) {
                System.out.println("输入增加的字母");
                String add = scanner.nextLine();
                StringBuilder stringBuilder = new StringBuilder(s);
                stringBuilder.append(add);
            } else if (opt == 2) {
                System.out.println("输入查询的位数");
                int index = scanner.nextInt();
                int M = (s.length() - 1) / 2;
                System.out.println(findPos(s, 0, index, s.length() - 1));

            } else {
                opt = scanner.nextInt();
            }

        }
    }

    private static int findPos(String s,int L, int index, int R) {
        int p1 = 0;
        int p2 = 0;
        int ans = 0;
        for (int i = 0; i < index; i++) {

            p1 = s.indexOf(i) == s.indexOf(index)?i:-1;

        }
        for (int i = R; i > index  ; i--) {
            p2 = s.indexOf(i) == s.indexOf(index)?i:-1;

        }
        ans = Math.min((p2-index),(index-1));
        return ans;
    }


}
