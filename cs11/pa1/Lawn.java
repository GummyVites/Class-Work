//-----------------------------------------------------------
// Lawn.java
// Kevin Lee	
// 1480757
// pa1
// calculates lawn area and mowing time
//----------------------------------------
import java.util.Scanner;
class Lawn{
        public static void main( String[] args ){
                double lengthLot, widthLot, lengthHouse, widthHouse, areaLot, areaHouse, areaLawn, mowingRate, mowingTime;
                int h, m, s;
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the length and width of the lot, in feet: ");
                widthLot = sc.nextDouble();
                lengthLot = sc.nextDouble();
                System.out.println("Enter the length and width of the house, in feet: ");
                widthHouse = sc.nextDouble();
                lengthHouse = sc.nextDouble();
                areaLot = widthLot*lengthLot;
                areaHouse = lengthHouse*widthHouse;
                areaLawn = areaLot-areaHouse;
                System.out.print("The lawn area is ");
                System.out.print(areaLawn);
                System.out.println(" square feet.");
                System.out.println("Enter the mowing rate, in square feet per second: ");
                mowingRate= sc.nextDouble();
                mowingTime= areaLawn/mowingRate;
                s = (int) Math.round(mowingTime);
                m = s/60;
                s = s%60;
                h = m/60;
                m = m%60;
                System.out.println("The mowing time is "+h+" hour" +(h==1?" ":"s ") +m+ " minute" +(m==1?" ":"s ") +s+ " second" +(s==1?".":"s.") );
                
            }
        }  
