import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;    
import java.time.format.DateTimeFormatter;  

/**
 *
 * @author HRC
 */
public class Berkeley {
    
    public static String time_formated(int a,int b){
        int time=Math.abs(a-b);
        int hour=(int)(time/3600);
        time=time%3600;
        int min=(int)(time/60);
        time=time%60;
        int sec=(int)time;
        String tm;
        tm=String.valueOf(hour)+":"+String.valueOf(min)+":"+String.valueOf(sec);
    return tm;
    }
    
    public static String sign(int z){
        if(z>0){
            return "+";
        }
        else if(z<0){
            return "-";
        }
        else{
            return "";
        }
    }
    
    public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
    //Master time fetching(uses local system time)
      String a=String.valueOf(java.time.LocalTime.now());
      String[] b=a.split(":");
        int i=0;
        int master[] = new int[3];
      for(String w:b){
            master[i]=(int)Math.round(Double.valueOf(w));
            i++; 
        }
      System.out.println("Master's Time-->"+master[0]+":"+master[1]+":"+master[2]);
 
      int mast_sec=master[0]*3600+master[1]*60+master[2];
    //Master time fetching ends
      System.out.println("Master's time in seconds:"+mast_sec);
      
      
    //Slave time collecting
      System.out.print("Enter no. of slaves:");
      
      int nslaves=sc.nextInt();
      int slaves[][] = new int[10][10];
   
      System.out.println("Give time of slaves in HH:MM:SS format");
      for(i=0;i<nslaves;i++){
          System.out.print("Slave"+(i+1)+"-->");
        String[] c=sc.next().split(":");
        int k=0;
        for(String x:c){
            slaves[i][k]=(int)Math.round(Double.valueOf(x));  
            k++;
        }
      }
      
      //printing slave values
      for(i=0;i<nslaves;i++){
      System.out.println("Slave "+(i+1)+"--> "+slaves[i][0]+":"+slaves[i][1]+":"+slaves[i][2]);
      }
      
      //Converting slave times in seconds
      int slave_sec[] = new int[nslaves];
      
      for(i=0;i<nslaves;i++){
      slave_sec[i]=slaves[i][0]*3600+slaves[i][1]*60+slaves[i][2];
      System.out.println("Slave"+(i+1)+"'s time in seconds: "+slave_sec[i]);
      }
 
      //Difference in slave and master
      int diff_sec[] = new int[nslaves];
    
      for(i=0;i<nslaves;i++){
      diff_sec[i]=mast_sec-slave_sec[i];
      System.out.println("Slave-"+(i+1)+"&Master"+" difference in seconds: "+diff_sec[i]);
      }
      
     //sum of differnces and average
      int sum=0;
      for(i=0;i<nslaves;i++){
      sum=sum+diff_sec[i];
      }
      System.out.println("Sum of differences: "+sum);
      
      //average
      int avg=(int)(sum/(nslaves+1));
      System.out.println("Avg is: "+avg+" seconds OR "+sign(avg)+time_formated(avg,0));
      
     
      System.out.println("Time After Adjustments");

      //Master adjusts self timer:
      String new_time="";
      new_time=time_formated(mast_sec,avg);
      System.out.println("Master-->"+new_time+"("+sign(-avg)+time_formated(Math.abs(avg),0)+" w.r.t original)");
      
      //avg-diff is sent to slave by master
      int to_send=0;
      String sgn="";
      for(i=0;i<nslaves;i++){
          to_send=avg-diff_sec[i];
          sgn=sign(-to_send);
           System.out.println("Slave"+(i+1)+": "+new_time+"("+sgn+time_formated(Math.abs(to_send),0)+")");
      }
    }
}
