import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {

        int[] occurencyArray=new int[201];

        double median=0.0;
        int notificationSent=0;

        for(int day=0; day<expenditure.length; day++){
            if(day>=d){
                median=getMedian(occurencyArray, d);
                System.out.println(expenditure[day]+">="+median*2+"="+(expenditure[day]>=median*2));
                if(expenditure[day]>=median*2)
                    notificationSent++;
                occurencyArray[expenditure[day-d]]--;    

            }    
            occurencyArray[expenditure[day]]++;


        }
        return notificationSent;
    }

    private static double getMedian(int[] occurencyArray, int trailingDay){

        int stepper=0;
        int actualIndex=0;
        int medianPartOne=0;
        int medianPartTwo=0;

        while(actualIndex<trailingDay/2+1 && stepper<occurencyArray.length){
            actualIndex+=occurencyArray[stepper];
            medianPartOne=stepper;
            stepper++;
        }

        if(trailingDay%2==0 && actualIndex>trailingDay/2+1){ 
            while(occurencyArray[stepper]==0 && stepper<occurencyArray.length){
                stepper--;
            }    
            medianPartTwo=stepper;
            System.out.println((medianPartOne+medianPartTwo)/2.0);
            return (medianPartOne+medianPartTwo)/2.0;
        }
        return (double)medianPartOne;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

