//Kevin Lee
//1480757
//CMPS 12B
//Simulation.java
//Pa4

import java.util.Scanner;
import java.io.*;

public class Simulation{
    public static void main(String[] args)throws IOException{
        Scanner scOne = null;
        Scanner scTwo = null;
        PrintWriter report = null;
        PrintWriter trace = null;
        Queue Storage = new Queue();
        int i = 0;
        int m = 0;
        int n = 0;
        Queue CountStorageTwo = new Queue();
        Queue CountStorage = new Queue();

	//Check arguments
       
            if(args.length!=1){
                System.out.println("Usage: Simulation input_file");
                System.exit(1);
            }
		
            scOne = new Scanner(new File(args[0]));
            scTwo = new Scanner(new File(args[0]));
            report = new PrintWriter(new FileWriter(args[0]+".rpt"));
            trace  = new PrintWriter(new FileWriter(args[0]+".trc"));
        
	//get m and n jobs
        String[] string =scOne.nextLine().split(" ");
        m = Integer.parseInt(string[0]);

        String[] sstring =scTwo.nextLine().split(" ");
        m = Integer.parseInt(sstring[0]);

        n=m-1;
	
	//reads from the files
        while(scOne.hasNextLine()){
             Storage.enqueue(getJob(scOne));
        }

        while(scTwo.hasNextLine()){
            CountStorageTwo.enqueue(getJob(scTwo));
        }

	//Header
        trace.println("Trace file: "+args[0]+".trc");
        trace.println(m+ " Jobs:");
        trace.println(Storage);
        trace.println();

        report.println("Trace file: "+args[0]+".rpt");
        report.println(m+ " Jobs");
        report.println(Storage);
        report.println("***********************************************************");
	//from n to m 
        for(i=1;i<=n;i++){
            int time =0;
            String s;
            trace.println("*****************************");
            if (i>1){
                s= "processors:";
            }else{
                s = "processor:";
            }
            trace.println("*****************************");

            Queue[] processor = new Queue[i];

            for(int q=0;q<i;q++){
                processor[q]=new Queue();
            }
            trace.println("time="+time);
            trace.print("0: "+Storage);
            for(int f=1;f<=i;f++){
                trace.print(f+": "+processor[f-1]);
            }
            trace.println("");

            Queue finished = new Queue();
	    //process jobs
            while(finished.length()!=m){
                boolean printed=false;
		
                for(int p=0;p<i;p++){
		    //checks queue array	
                    if(processor[p].isEmpty()==false){
			
                        Job j=(Job)processor[p].peek();

                        int finishTime = j.getFinish();
			// if time finished and processor not empty
                        if(finishTime==time && processor[p].isEmpty()==false){
                            Job temp = (Job)processor[p].peek();
                            processor[p].dequeue();
                            finished.enqueue(temp);
                            Storage.enqueue(temp);
			    //gets next finish time 
                            if(processor[p].isEmpty()==false){
                                Job find =(Job)processor[p].peek();
                                find.computeFinishTime(time);
                            }
			    //print
                            if(printed==false){
                                trace.println("time="+time);
                                trace.print("0: "+Storage);
                                for(int f=1;f<=i;f++){
                                    trace.print(f+": "+processor[f-1]);
                                }
                                trace.println("");
                                printed=true;
                            }
                        }
                    }
                }

                for(int k=0;k<Storage.length();k++){

                    Job info= (Job)(Storage.peek());

                    int start=info.getArrival();
		    // start ==time remove from storage
                    if(start==time){

                        Storage.dequeue();
			//finds the lowest job line
                        int least;
                        if(processor.length>1){
                            int z=processor.length-1;
                            int lowest=processor[z].length();
                            int shortest = 0;
                            while(z>-1){
                                int current=processor[z].length();
                                if(current<lowest){
                                    lowest=current;
                                    shortest=z;
                                }
                                if(current==lowest){
                                    lowest=current;
                                    shortest=z;
                                }
                                z--;
                            }
                            least = shortest;
                        }else{
                            least=0;
                        }

                        processor[least].enqueue(info);
			//find finish time
                        if(processor[least].length()==1){
                            Job first =(Job)processor[least].peek();
                            first.computeFinishTime(time);
                        }
			//print
                        if(printed==false){
                            trace.println("time="+time);
                            trace.print("0: "+Storage);
                            for(int f=1;f<=i;f++){
                                trace.print(f+": "+processor[f-1]);
                            }
                            trace.println("");
                            printed =true;
                        }
                    }
                }

                time++;
            }
	    //report file
            int totalWait=0;
            int maxWait=0;
            double avgWait=0.0;
            while(Storage.isEmpty()==false){
                Job w = (Job)(Storage.dequeue());
                totalWait = totalWait+w.getWaitTime();
                if(w.getWaitTime()>maxWait){
                    maxWait = w.getWaitTime();
                }
            }
            avgWait=((double)totalWait/m);

		
            for(int f=i;f<=i;f++){
                if (i>1){
                    s= "processors:";
                }else{
                    s = "processor:";
                }
                report.print(f+s+": totalWait="+totalWait+", maxWait="+maxWait);
                report.println(", averageWait="+avgWait);
            }

            time=0;
            if(i<n){
		//resets
                finished.dequeueAll();
                for(int r = 1; r < CountStorage.length()+1; r++) {
        	Job clean = (Job)CountStorage.dequeue();
        	clean.resetFinishTime();
        	Storage.enqueue(clean);
        	CountStorage.enqueue(clean);
      }
                for(int w = 1; w < CountStorageTwo.length()+1; w++) {
        	Job clean = (Job)CountStorageTwo.dequeue();
        	clean.resetFinishTime();
        	Storage.enqueue(clean);
        	CountStorageTwo.enqueue(clean);
      }
                    }
                }
            
        
	//close
        scOne.close();
        scTwo.close();
        report.close();
        trace.close();
    }

    public static Job getJob(Scanner in){
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        return new Job(a,d);
    }

}

