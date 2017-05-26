import java.util.Scanner;
class Problem2{
    public static void main(String[] args){
        double x,y,sum;
        Scanner sc= new Scanner(System.in);
        x = sc.nextDouble();
        y= sc.nextDouble();
        sum = (Math.sqrt(x)) + (Math.sqrt(y));
        System.out.println(sum);
    }
}
