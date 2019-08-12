import java.util.Scanner;

/**
 *
 */
public class Test {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.println("input:");
        int a = sc.nextInt();
        int result = reverse(a);
        System.out.println("output:\n"+result);

    }

    /**
     *  x % 10 把x原来的值除10然后取余数
     *  x/=10 等价于 x=x/10 就是先把x原本的值和10的值进行除法运算后，取整数在赋值给x。
     * @param x
     * @return
     */
    public static int reverse(int x){
        if(x<=Integer.MIN_VALUE || x>=Integer.MAX_VALUE)
            return 0;
        int res = 0;
        int flag = x < 0 ? -1 : 1;
        x = Math.abs(x);
        while(x>0){
            res = res * 10 + x % 10;
            x=x/10;
        }

        return flag*res;
    }
}
