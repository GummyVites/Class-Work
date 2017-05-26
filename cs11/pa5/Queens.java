//Queens.java
//Kevin Lee
//1480757
//pa5
//Finds N amount of Queens

class Queens{
    public static void main(String[] args) {
        int[] list;
        int permutation, perms, num;
        int index = 0;
        String v = "-v";

        if(args.length == 0)
            Usage();
        else if(args.length == 1) {
                try{
                    Integer.parseInt(args[0]);
                    num = Integer.parseInt(args[0]);
                    list = factorial(num);
                    perms = Pmaximum(num);
                    for(int i = 0; i < perms; i++) {
                        nextPermutation(list);
                        if(isSolution(list))
                            index++;
                    }
                    System.out.println(num + "-Queens has " + index + " solutions.");
                }
                catch(NumberFormatException e) {
                    Usage();
                }
            }
           
        
        else if(args.length == 2) {
            if(args[0].equals(v)) {
                try{
                    Integer.parseInt(args[1]);
                    num = Integer.parseInt(args[1]);
                    list = factorial(num);
                    perms = Pmaximum(num);
                    for(int j = 0; j < perms; j++) {
                        nextPermutation(list);
                        if(isSolution(list)){
                            printArray(list);
                            index++;
                        }
                    }
                    System.out.println(num + "-Queens has " + index + " solutions.");
                }
                catch(NumberFormatException e1) {
                    Usage();
                }
            }
            else
                Usage();
        }
        else if(args.length > 2)
            Usage();
    }

    static void nextPermutation(int[] x) {
        int pivot = 0, j = x.length - 1, k = x.length - 1;
        int successor = 0;

        for(int a = x.length - 1; a > 1; a--) {
            if(x[a] > x[a - 1]) {
                pivot = a - 1;
                break;
            }
        }
        if(pivot == 0) {
            reverseArray(x);
            return;
        }
        for(int b = k; b > 1; b--) {
            if(x[b] > x[pivot]){
                successor = b;
                break;
            }
        }
        swap(x, pivot, successor);
        for(int i = pivot + 1; i <= (x.length - 1 + pivot)/2; i++) {
            swap(x, i, j);
            j--;
        }
    }

    static boolean isSolution(int[] x) {
        int n = x.length - 1;
        int max, i, j; 
        boolean z = true;
        max = (n * (n-1))/2;

        if(max == 1|| max == 2)
            return false;

        for(i = n; i > 0; i--) {
            for(j = i + 1; j < x.length; j++) {
                if(Math.abs(i-j) == Math.abs(x[i] - x[j])) {
                    z = false;
                    return z;
                }
            }
        }
        return z;
    }

    static void Usage() {
        System.out.println("Usage: Queens [-v] number");
        System.out.println("Option: -v verbose output, print all solutions");
        System.exit(1);
    }

    static int[] factorial(int x) {
        int[] A = new int[x + 1];

        A[x] = x;
        for(int k = A.length - 2; k > 0; k--) {
            A[k] = x - 1;
            x -= 1;
        }
        return(A);
    }

    static int Pmaximum(int x){
        int max = 1;
        for(int i = 1; i <= x; i++){
            max *= i;
        }
        return(max);
    }

    static void printArray(int[] A) {
        System.out.print("( ");
        for(int i=1; i<A.length; i++)
            if(i == A.length - 1)
                System.out.print(A[i] + " ");
            else
                System.out.print(A[i] + ", ");
        System.out.println(")");
    }

    static void reverseArray(int[] B) {
        for(int i = 1; i <= B.length/2; i++) {
            int temp = B[i];
            B[i] = B[B.length - i];
            B[B.length - i] = temp;
        }
    }

    static void swap(int[] D, int i, int j) {
        int temp, n = D.length;
        temp = D[i];
        D[i] = D[j];
        D[j] = temp;
    }
}
