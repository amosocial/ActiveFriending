package aaai_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.sound.midi.MidiDevice.Info;

public class Start_taskMulti {
	
	public static boolean SHOW=false;
	public static double EPSILON;
	public static double P_MAX_EPSILON;
	public static double N;
	public static int POOLNUM;
	public static int ROUND;
	public static PrintWriter writer;
	public static int INITIATOR_NUM;
	public static int PAIR_NUM, LINE_NUM;
	public static int COUNT=0;
	public static double LOWER_BOUND;
	public static int TARGET;
	public static String NAME="";
	public static NumberFormat FORMATTER; 
	public static double global_sum_1,global_sum_2,global_sum_3,global_count;
	//public static ArrayList<Integer> ALL_NODE;
	public static void wiki_new()
	{
		String path="data/wiki.txt";
		int vNum=8300;
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,vNum,"wiki");
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	
	public static void exp1(String name, int vnum)
	{
		String path="data/"+name+".txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, vnum, name);
		println("dataset imported");
		ArrayList<String> lines_info=new ArrayList<String>();
		ArrayList<String> mpuresults=new ArrayList<String>();
		get_linesinfo(name,LINE_NUM,lines_info);
		get_MpUresult(name,LINE_NUM,mpuresults);
		for(int i=0; i<LINE_NUM; i++)
		{
			String[] tempString=mpuresults.get(i).split(" ");
			if(tempString[0].length()!=0)
			{
				System.out.print(i+" ");
				collect_result_print_one(lines_info.get(i), mpuresults.get(i), myinstance);
			}
			
		}
		
		
		/*
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);*/
	}
	
	public static void exp2(String name, int vnum)
	{
		String path="data/"+name+".txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, vnum, name);
		println("dataset imported");
		ArrayList<String> lines_info=new ArrayList<String>();
		ArrayList<String> mpuresults=new ArrayList<String>();
		get_linesinfo(name,LINE_NUM,lines_info);
		get_MpUresult(name,LINE_NUM,mpuresults);
		for(int i=0; i<LINE_NUM; i++)
		{
			//System.out.println(i+" ");
			String[] tempString=mpuresults.get(i).split(" ");
			if(tempString[0].length()!=0)
			{
				//System.out.print(i+" ");
				exp2_one(lines_info.get(i), mpuresults.get(i), myinstance);
			}
			
		}
		
		
		/*
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);*/
	}
	
	public static void exp3(String name, int vnum)
	{
		String path="data/"+name+".txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, vnum, name);
		println("dataset imported");
		ArrayList<String> lines_info=new ArrayList<String>();
		ArrayList<String> mpuresults=new ArrayList<String>();
		get_linesinfo(name,LINE_NUM,lines_info);
		get_MpUresult(name,LINE_NUM,mpuresults);
		global_sum_1=0;
		global_sum_2=0;
		global_sum_3=0;
		global_count=0;
		for(int i=0; i<LINE_NUM; i++)
		{
			//System.out.println(i+" ");
			String[] tempString=mpuresults.get(i).split(" ");
			if(tempString[0].length()!=0)
			{
				//System.out.print(i+" ");
				exp3_one(lines_info.get(i), mpuresults.get(i), myinstance);
			}
			
		}
		System.out.println(global_sum_1/global_count+" "+global_sum_2/global_count+" "+global_sum_3/global_count);
		
		/*
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);*/
	}
	
	public static void exp4(String name, int vnum)
	{
		String path="data/"+name+".txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, vnum, name);
		println("dataset imported");
		ArrayList<String> lines_info=new ArrayList<String>();
		ArrayList<String> mpuresults=new ArrayList<String>();
		get_linesinfo4(name,LINE_NUM,lines_info);
		get_MpUresult4(name,LINE_NUM,mpuresults);
		global_sum_1=0;
		global_sum_2=0;
		global_sum_3=0;
		global_count=0;
		for(int i=0; i<LINE_NUM; i++)
		{
			//System.out.println(i+" ");
			String[] tempString=mpuresults.get(i).split(" ");
			if(tempString[0].length()!=0)
			{
				//System.out.print(i+" ");
				exp4_one(lines_info.get(i), mpuresults.get(i), myinstance);
			}
			
		}
		System.out.println(global_sum_1/global_count+" "+global_sum_2/global_count+" "+global_sum_3/global_count);
		
		/*
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);*/
	}
	
	public static void exp4_one(String line_info, String mpuresult, ActiveFriendingInstance myinstance)
	{
		//System.out.println(line_info);
		//System.out.println(mpuresult);
		Random rnd=new java.util.Random();
		int s,t;
		String p_max,alpha;
		String[] tempString=line_info.split(" ");
		s=Integer.parseInt(tempString[1]);
		t=Integer.parseInt(tempString[3]);
		p_max=tempString[9];
		alpha=tempString[5];
		if(!alpha.equals("0.001"))
		{
			//return;
		}
		
		
		ArrayList<Integer> cfriends=new ArrayList<Integer>();
		cfriends.add(s);
		int L = Integer.parseInt(tempString[7]);
		double beta=0;
		for(int i=0; i<myinstance.network.neighbor.get(s).size();i++)
		{
			cfriends.add(myinstance.network.neighbor.get(s).get(i));
		}
		myinstance.set_s_t_index(s, t);
		ArrayList<Integer> inv_set=new ArrayList<Integer>();
		tempString=mpuresult.split(" ");
		if(tempString[0].length()==0)
		{
			return;
		}
		/*
		for(int j=1;j<10;j++)
		{
			int size=tempString.length*j/10;
			for(int i=0;i<size;i++)
			{
				inv_set.add(Integer.parseInt(tempString[i]));
			}
			double aaai_prob=get_prob_monte(myinstance,inv_set,rnd,cfriends);
			System.out.print("p_max "+p_max+" ");
			System.out.println((j/10.0)+" aaai_prob "+FORMATTER.format(aaai_prob));
			
		}*/
		//int size=tempString.length*j/10;
		for(int i=0;i<tempString.length;i++)
		{
			inv_set.add(Integer.parseInt(tempString[i]));
		}
		double aaai_prob=get_prob_monte(myinstance,inv_set,rnd,cfriends);
		System.out.print("p_max "+p_max+" ");
		System.out.println("aaai_prob "+FORMATTER.format(aaai_prob)+" "+L);
		
		
		
		//ArrayList<Integer> hdegree=new ArrayList<Integer>();
		//HighDegree.run(myinstance, hdegree, inv_set.size());
		//double degree_prob=get_prob_monte(myinstance, hdegree, rnd, cfriends);
		//System.out.print(" degree_prob "+FORMATTER.format(degree_prob));
		
		//ArrayList<Integer> sp=new ArrayList<Integer>();
		//SP.run(myinstance, sp, inv_set.size());
		//double sp_prob=get_prob_monte(myinstance, sp, rnd, cfriends);
		//System.out.println(" sp_prob "+FORMATTER.format(sp_prob));
	}
	
	public static void get_linesinfo(String name, int linem, ArrayList<String> lines_info)
	{
		String path="data/"+name+"_result/"+ROUND+"/input.txt";
		File singleFile=new File(path);
		String inString = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
            while((inString = reader.readLine())!= null){
            	if(lines_info.size()<linem)
            	{
            		lines_info.add(inString);
            	}
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(path+" The path of data is incorrect.");
        } catch (IOException ex) {
            System.out.println("Error in reading data.");
        }
	}
	
	public static void get_linesinfo4(String name, int linem, ArrayList<String> lines_info)
	{
		String path="data/"+name+"_result/"+ROUND+"/exp4_input.txt";
		File singleFile=new File(path);
		String inString = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
            while((inString = reader.readLine())!= null){
            	if(lines_info.size()<linem)
            	{
            		lines_info.add(inString);
            	}
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(path+" The path of data is incorrect.");
        } catch (IOException ex) {
            System.out.println("Error in reading data.");
        }
	}
	
	public static void get_MpUresult(String name, int linem, ArrayList<String> mpuresults)
	{
		String path="data/"+name+"_result/"+ROUND+"/mpu.txt";
		File singleFile=new File(path);
		String inString = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
            while((inString = reader.readLine())!= null){
            	if(mpuresults.size()<linem)
            	{
            		mpuresults.add(inString);
            	}
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(path+" The path of data is incorrect.");
        } catch (IOException ex) {
            System.out.println("Error in reading data.");
        }
	}
	
	public static void get_MpUresult4(String name, int linem, ArrayList<String> mpuresults)
	{
		String path="data/"+name+"_result/"+ROUND+"/exp4_mpu.txt";
		File singleFile=new File(path);
		String inString = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
            while((inString = reader.readLine())!= null){
            	if(mpuresults.size()<linem)
            	{
            		mpuresults.add(inString);
            	}
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(path+" The path of data is incorrect.");
        } catch (IOException ex) {
            System.out.println("Error in reading data.");
        }
	}
	
	public static void collect_result_print_one(String line_info, String mpuresult, ActiveFriendingInstance myinstance)
	{
		//System.out.println(line_info);
		//System.out.println(mpuresult);
		Random rnd=new java.util.Random();
		int s,t;
		String p_max,alpha;
		String[] tempString=line_info.split(" ");
		s=Integer.parseInt(tempString[1]);
		t=Integer.parseInt(tempString[3]);
		p_max=tempString[9];
		alpha=tempString[5];
		System.out.print(" p_max "+p_max+" alpha "+alpha);
		
		ArrayList<Integer> cfriends=new ArrayList<Integer>();
		cfriends.add(s);
		int L = 0;
		double beta=0;
		for(int i=0; i<myinstance.network.neighbor.get(s).size();i++)
		{
			cfriends.add(myinstance.network.neighbor.get(s).get(i));
		}
		myinstance.set_s_t_index(s, t);
		ArrayList<Integer> inv_set=new ArrayList<Integer>();
		tempString=mpuresult.split(" ");
		if(tempString[0].length()==0)
		{
			return;
		}
		for(int i=0;i<tempString.length;i++)
		{
			inv_set.add(Integer.parseInt(tempString[i]));
		}
		double aaai_prob=get_prob_monte(myinstance,inv_set,rnd,cfriends);
		System.out.print(" aaai_prob "+FORMATTER.format(aaai_prob));
		
		
		ArrayList<Integer> hdegree=new ArrayList<Integer>();
		HighDegree.run(myinstance, hdegree, inv_set.size());
		double degree_prob=get_prob_monte(myinstance, hdegree, rnd, cfriends);
		System.out.print(" degree_prob "+FORMATTER.format(degree_prob));
		
		ArrayList<Integer> sp=new ArrayList<Integer>();
		SP.run(myinstance, sp, inv_set.size());
		double sp_prob=get_prob_monte(myinstance, sp, rnd, cfriends);
		System.out.println(" sp_prob "+FORMATTER.format(sp_prob));
	}
	
	public static void exp2_one(String line_info, String mpuresult, ActiveFriendingInstance myinstance)
	{
		//System.out.println(line_info);
		//System.out.println(mpuresult);
		Random rnd=new java.util.Random();
		int s,t;
		//String p_max,alpha;
		String[] tempString=line_info.split(" ");
		s=Integer.parseInt(tempString[1]);
		t=Integer.parseInt(tempString[3]);
		//p_max=tempString[9];
		String alpha=tempString[5];
		if(!alpha.equals("0.100"))
		{
			return;
		}
		//System.out.print(" p_max "+p_max+" alpha "+alpha);
		ArrayList<Integer> cfriends=new ArrayList<Integer>();
		cfriends.add(s);
		int L = 0;
		//double beta=0;
		for(int i=0; i<myinstance.network.neighbor.get(s).size();i++)
		{
			cfriends.add(myinstance.network.neighbor.get(s).get(i));
		}
		myinstance.set_s_t_index(s, t);
		ArrayList<Integer> inv_set=new ArrayList<Integer>();
		tempString=mpuresult.split(" ");
		for(int i=0;i<tempString.length;i++)
		{
			inv_set.add(Integer.parseInt(tempString[i]));
		}
		double aaai_prob=get_prob_monte(myinstance,inv_set,rnd,cfriends);
		//System.out.print(" aaai_prob "+FORMATTER.format(aaai_prob));
		int rest=myinstance.network.vertexNum-inv_set.size();
		int k=20;
		double c_prob;
		for(int i=0;i<k+1;i++)
		{
			int size=inv_set.size()+rest*(i)/k;
			//int size=inv_set.size()*(i+1);
			//System.out.print(i);
			System.out.print("aaai_prob "+FORMATTER.format(aaai_prob)+" "+inv_set.size());
			/*
			ArrayList<Integer> hdegree=new ArrayList<Integer>();
			HighDegree.run(myinstance, hdegree, size);
			System.out.print(" done");
			double degree_prob=get_prob_monte(myinstance, hdegree, rnd, cfriends);
			System.out.print(" degree_prob "+FORMATTER.format(degree_prob)+" "+ size);
			if(Math.abs(degree_prob-aaai_prob)<0.001)
			{
				
				System.out.println();
				break;
			}*/
				
			
			ArrayList<Integer> sp=new ArrayList<Integer>();
			SP.run(myinstance, sp, size);
			double sp_prob=get_prob_monte(myinstance, sp, rnd, cfriends);
			System.out.print(" sp_prob "+FORMATTER.format(sp_prob)+" "+ size);
			if(Math.abs(sp_prob-aaai_prob)<0.005 || Math.abs(sp_prob-aaai_prob)<aaai_prob*0.01 || sp_prob>aaai_prob )
			{
				System.out.println();
				break;
			}
			System.out.println();
		}
	}
	
	public static void exp3_one(String line_info, String mpuresult, ActiveFriendingInstance myinstance)
	{
		//System.out.println(line_info);
		//System.out.println(mpuresult);
		Random rnd=new java.util.Random();
		int s,t;
		String p_max;
		String[] tempString=line_info.split(" ");
		s=Integer.parseInt(tempString[1]);
		t=Integer.parseInt(tempString[3]);
		p_max=tempString[9];
		String alpha=tempString[5];
		if(!alpha.equals("0.100"))
		{
			return;
		}
		//System.out.print(" p_max "+p_max+" alpha "+alpha);
		ArrayList<Integer> cfriends=new ArrayList<Integer>();
		cfriends.add(s);
		int L = 0;
		//double beta=0;
		for(int i=0; i<myinstance.network.neighbor.get(s).size();i++)
		{
			cfriends.add(myinstance.network.neighbor.get(s).get(i));
		}
		myinstance.set_s_t_index(s, t);
		
		double[] result_1=new double[2];
		//System.out.println("get_pmax...");
		result_1=get_pmax(myinstance, 0.01, cfriends);
		tempString=mpuresult.split(" ");
		global_sum_1=global_sum_1+result_1[1];
		global_sum_2=global_sum_2+tempString.length;
		global_sum_3=global_sum_3+result_1[1]/tempString.length;
		global_count++;
		System.out.println((int)result_1[1]+" "+tempString.length+" "+p_max);
	}
	public static void hepth_new()
	{
		String path="data/HepTh.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,27770,"hepth");	
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	
	public static void epinions_new()
	{
		String path="data/epinions.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 75890, "epinions");
		
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	
	public static void facebook_new()
	{
		String path="data/facebook.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 4039, "facebook");
		
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	
	public static void hepph_new()
	{
		String path="data/hepph.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,35000,"hepph");	
		println("dataset imported");

			
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	
	public static void youtube_new()
	{
		String path="data/youtube.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 1157900, "youtube");
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	
	public static void gplus_new()
	{

		String path="data/gplus.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,107614,"gplus");		
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	
	public static void pokec_new()
	{

		String pokec="data/pokec.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(pokec,1632803,"pokec");	
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		ArrayList<Integer> targets=new ArrayList<Integer> ();	
		println("reading neighbors....");
		myinstance.get_pairs_byread(initiators, targets, PAIR_NUM, ROUND);
		println("run running....");	
		run_get_L(myinstance, initiators, targets);
	}
	/*
	public static void wiki()
	{
		String path="data/wiki.txt";
		int vNum=8300;
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,vNum,"wiki");
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		println("reading neighbors....");
		TARGET=myinstance.get_neighbors_byread(initiators, INITIATOR_NUM, ROUND);
		println("run running....");	
		//run_get_L(myinstance, initiators);
	}
	
	
	
	public static void hepth()
	{
		String path="data/HepTh.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,27770,"hepth");	
		println("dataset imported");
		ArrayList<Integer> initiators=new ArrayList<Integer> ();
		println("reading neighbors....");
		int t=myinstance.get_neighbors_byread(initiators, INITIATOR_NUM, ROUND);
		println("run running....");
		run(myinstance, initiators, t);
	}
	
	public static void youtube()
	{
		String path="data/youtube.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 1157900, "youtube");
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();		
		println("reading neighbors....");
		int t=myinstance.get_neighbors_byread(initiators, INITIATOR_NUM, ROUND);
		println("run running....");
		run(myinstance, initiators, t);
	}
	
	public static void gplus()
	{

		String path="data/gplus.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,107614,"gplus");		
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();
		println("reading neighbors....");
		int t=myinstance.get_neighbors_byread(initiators, INITIATOR_NUM, ROUND);
		println("run running....");
		run(myinstance, initiators, t);
	}
	
	public static void pokec()
	{

		String pokec="data/pokec.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(pokec,1632803,"pokec");
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();
		println("reading neighbors....");
		int t=myinstance.get_neighbors_byread(initiators, INITIATOR_NUM, ROUND);
		println("run running....");
		run(myinstance, initiators, t);
	}
	
	public static void epinions()
	{

		String path="data/epinions.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 75890,"epinions");
		println("dataset imported");		
		ArrayList<Integer> initiators=new ArrayList<Integer> ();	
		println("reading neighbors....");
		int t=myinstance.get_neighbors_byread(initiators, INITIATOR_NUM, ROUND);
		println("run running....");	
		run(myinstance, initiators, t);
	}*/
	
	private static double[] get_pmax(ActiveFriendingInstance myinstance, double epsilon0, ArrayList<Integer> cfriends)
	{
		boolean record[]=new boolean[myinstance.network.vertexNum];
		for(int i=0;i<myinstance.network.vertexNum;i++)
		{
			record[i]=false;
		}
		
		double gamma=1+(4*(Math.E-2)*(1+epsilon0)*Math.log(N*2))/(epsilon0*epsilon0);
		double i=0;
		double j=0;
		while(j <= gamma)
		{
			i=i+100;
			ArrayList<ArrayList<Integer>>  rrsets=new ArrayList<ArrayList<Integer>>() ;
			myinstance.get_rrsets(rrsets, 100, new java.util.Random(), cfriends);
			//System.out.println(rrsets.size());
			j=j+rrsets.size();
			if(gamma/i<0.01)
				break;
			
			for(int k=0;k<rrsets.size();k++)
			{
				//String temp="";
				for(int l=0;l<rrsets.get(k).size();l++)
				{
					record[rrsets.get(k).get(l)]=true;
					//temp=temp+" "+rrsets.get(k).get(l);
				}
				//if(rrsets.get(k).size()>1){
				//	System.out.println(temp);
				//}
				
			}
			
		}
		//System.out.println(j+" "+i);
		int count=0;
		for(int k=0;k<myinstance.network.vertexNum;k++)
		{
			if(record[k])
				count++;
		}
		double[] result=new double[2];
		result[0]=gamma/i;
		result[1]=count;
		return result;
	}
	
	private static double get_pmax_lowerbound(ActiveFriendingInstance myinstance)
	{
		int l=(int) (Math.log(2*N)*(2+P_MAX_EPSILON)/(LOWER_BOUND*P_MAX_EPSILON*P_MAX_EPSILON));
		ArrayList<ArrayList<Integer>>  rrsets=new ArrayList<ArrayList<Integer>>() ;
		myinstance.get_rrsets_multiThread(rrsets, l, POOLNUM);
		return ((double )rrsets.size())/((double)l);
	}
	
	private static double get_prob_monte(ActiveFriendingInstance myinstance, ArrayList<Integer> invitation, Random rnd, ArrayList<Integer> cfriends)
	{
		double gamma=1+(4*(Math.E-2)*(1+P_MAX_EPSILON)*Math.log(N*2))/(P_MAX_EPSILON*P_MAX_EPSILON);
		double i=0;
		double j=0;
		while(j <= gamma)
		{
			i=i+100;
			//System.out.println(i);
			ArrayList<ArrayList<Integer>>  rrsets=new ArrayList<ArrayList<Integer>>() ;
			myinstance.get_rrsets(rrsets, 100, rnd, cfriends);
			
			for(int l=0; l<rrsets.size(); l++)
			{
				boolean sign=true;
				for(int k=0;k<rrsets.get(l).size();k++)
				{
					if(!invitation.contains(rrsets.get(l).get(k)))
					{
						sign=false;
						break;
					}
				}
				if(sign)
				{
					j=j+1;
				}
			}
			if(gamma/i<0.005)
				return 0.0;
		}
		return gamma/i;
	}
	/*
	private static void run(ActiveFriendingInstance myinstance,	ArrayList<Integer> initiators, int t)
	{
		//double alpha=0.8;
		//int minues=0;
		double[][] avg=new double[5][8];
		double[] p_max=new double[INITIATOR_NUM];
		//ArrayList<Integer> skip_list=new ArrayList<Integer>();
		double temppp=0.0;
		for(int i=0;i<INITIATOR_NUM;i++)
		{
			myinstance.set_s_t_index(initiators.get(i), t);
			p_max[i]=get_pmax_lowerbound(myinstance);
			//System.out.println(p_max[i]);
			temppp+=p_max[i];
			if(i % 100 ==0)
			{
				System.out.println(i+" "+p_max[i]);
			}
		}
		println("temp "+temppp/INITIATOR_NUM);
		println("get_pmax GET.... ");
	
		ExecutorService pool = Executors.newFixedThreadPool(POOLNUM);
		ArrayList<ArrayList<ArrayList<Double>>> result=new ArrayList<ArrayList<ArrayList<Double>>>();
		for(int i=0;i<INITIATOR_NUM;i++)
		{
			ArrayList<ArrayList<Double>> temp=new ArrayList<ArrayList<Double>>();
			for(int j=0; j<5 ;j++)
			{
				ArrayList<Double> tempp=new ArrayList<Double>();
				temp.add(tempp);
			}
			result.add(temp);
		}
		
		
	    Set<Future<Double>> set = new HashSet<Future<Double>>();
	    for (int i=0; i<INITIATOR_NUM; i++) {
	    	Callable<Double> callable = new gmis1Callable(myinstance, p_max[i], result.get(i), initiators.get(i));
	    	Future<Double> future = pool.submit(callable);
	    	set.add(future);
	    }
	    
	    Double influence=0.0;;
	    for (Future<Double> future : set) {
	    	try {
				influence += future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				System.out.println("fail to get result");
				//e.printStackTrace();
			}
	    }
	    pool.shutdown();
	    
	    for(int i=0; i<INITIATOR_NUM; i++)
		{
			for(int j=0; j<5 ;j++)
			{
				for(int k=0; k<8; k++)
				{
					avg[j][k]+=result.get(i).get(j).get(k);
				}
			}
		}
	    
	    //for(int j=0; j<5 ;j++)
		//{
		//	for(int k=0; k<8; k++)
		//	{
		//		avg[j][k]=avg[j][k]/INITIATOR_NUM;
		//	}
		//}		
		
	    print("alpha ");
	    print("avg_max_size ");
	    print("avg_max_prob ");
	    print("avg_aaai_invitation_size ");
	    print("avg_aaai_invitation_prob ");
	    print("avg_hd_invitation_size ");
	    print("avg_hd_invitation_prob ");
	    print("avg_sp_invitation_size ");
	    println("avg_sp_invitation_prob");

		for(int j=0;j<5;j++)
		{
			System.out.print(0.5+0.1*j+" ");
			for(int i=0;i<8;i++)
			{
				avg[j][i]=avg[j][i]/(INITIATOR_NUM);
				print(avg[j][i]+" ");
			}
			println();
		}
	}*/
	
	private static void run_get_L(ActiveFriendingInstance myinstance, ArrayList<Integer> initiators, ArrayList<Integer> targets)
	{
		//double alpha=0.8;
		//int minues=0;
		//double[][] avg=new double[5][8];
		//double[] p_max=new double[INITIATOR_NUM];
		//ArrayList<Integer> skip_list=new ArrayList<Integer>();
		//double temppp=0.0;
		//for(int i=0;i<INITIATOR_NUM;i++)
		//{
		//	myinstance.set_s_t_index(initiators.get(i), t);
			//p_max[i]=get_pmax_lowerbound(myinstance);
			//System.out.println(p_max[i]);
		//	temppp+=p_max[i];
		//	if(i % 100 ==0)
		//	{
		//		System.out.println(i+" "+p_max[i]);
		//	}
		//}
		//println("temp "+temppp/INITIATOR_NUM);
		//println("get_pmax GET.... ");
	
		ExecutorService pool = Executors.newFixedThreadPool(POOLNUM);
		/*
		ArrayList<ArrayList<ArrayList<Double>>> result=new ArrayList<ArrayList<ArrayList<Double>>>();
		for(int i=0;i<PAIR_NUM;i++)
		{
			ArrayList<ArrayList<Double>> temp=new ArrayList<ArrayList<Double>>();
			for(int j=0; j<5 ;j++)
			{
				ArrayList<Double> tempp=new ArrayList<Double>();
				temp.add(tempp);
			}
			result.add(temp);
		}*/
		
		
	    Set<Future<Double>> set = new HashSet<Future<Double>>();
	    for (int i=0; i<PAIR_NUM; i++) {
	    	Callable<Double> callable = new gmis1Callable(myinstance, initiators.get(i),targets.get(i),i);
	    	Future<Double> future = pool.submit(callable);
	    	set.add(future);
	    }
	    
	    Double influence=0.0;;
	    for (Future<Double> future : set) {
	    	try {
				influence += future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				System.out.println("fail to get result");
				//e.printStackTrace();
			}
	    }
	    pool.shutdown();
	}
	
	private static int get_L(double alpha, int n)
	{
		double epsilon0=0;
		double epsilon1=0;
		double epsilon2=0;
		double current[]=new double[3];
		double c_value=Double.MAX_VALUE;
		
		for(int i0=0;i0<20;i0++)
		{
			for(int i1=0;i1<20;i1++)
			{
				epsilon0=0.05*i0;
				epsilon1=0.05*i1;
				epsilon2=(alpha*(1-epsilon1)+EPSILON-alpha)/(1-epsilon0);
				double l1=((2+epsilon1)*Math.log(N/2))/(1+epsilon0);
				double l2=(2/(1-epsilon0)+epsilon2)*(n*Math.log(2)+Math.log(N))/(epsilon2*epsilon2);
				if(Math.max(l1, l2)<c_value && epsilon2>0)
				{
					current[0]=epsilon0;
					current[1]=epsilon1;
					current[2]=epsilon2;
					c_value=Math.max(l1, l2);
				}
			}
		}
		return (int)c_value;
		
	}
	
	private static double[] get_L_beta(double alpha, int n)
	{
		double epsilon0=P_MAX_EPSILON;
		double epsilon1=0;
		double current[]=new double[3];
		double c_value=Double.MAX_VALUE;
		double c_beta=-1;
		double c_epsilon0=-1;
		//EPSILON=0.9*alpha;
		//EPSILON=0.1;
		
		//epsilon1=P_MAX_EPSILON/n;
		//for(int i=1;i<20;i++)
		//{
			epsilon0=0.05;
			epsilon1=(EPSILON)/((2*alpha+2-EPSILON)*(1+epsilon0));
			double l=(n*Math.log(2)+Math.log(N)+Math.log(2))*(2+epsilon1*(1-epsilon0))/(epsilon1*epsilon1*(1-epsilon0)*(1-epsilon0));
			double beta=(alpha-epsilon1*(1+epsilon0))/(1+epsilon1*(1+epsilon0));
			if(l<c_value && beta>0)
			{
				c_value=l;
				c_beta=beta;
				c_epsilon0=epsilon0;
			}
			
		//}
		
		
		double[] result=new double[3];
		result[0]=c_value;
		result[1]=c_beta;
		result[2]=c_epsilon0;
		return result;
		
	}
	
	public static class gmis1Callable implements Callable<Double> 
	{
		private 
		ActiveFriendingInstance myinstance;
		ArrayList<ArrayList<Double>> result;
		double p_max;
		Random rnd;
		int s,t,pairnum;
		
		public gmis1Callable(ActiveFriendingInstance myinstance, int s, int t,int pairnum) {
		this.myinstance = myinstance;
		this.p_max = -1;
		//this.result=result;
		this.rnd = new java.util.Random();
		this.s=s;
		this.t=t;
		this.pairnum=pairnum;
		}
		/*
		public Double call() {
			
			ArrayList<Integer> cfriends=new ArrayList<Integer>();
			cfriends.add(s);
			int L = 0;
			for(int i=0; i<myinstance.network.neighbor.get(s).size();i++)
			{
				cfriends.add(myinstance.network.neighbor.get(s).get(i));
			}
			for(int j=0;j<5;j++)
			{
				ArrayList<Integer> appear_node_list=new ArrayList<Integer>();
				double alpha=0.5+0.1*j;
				
				L=(int) (get_L(alpha, myinstance.network.vertexNum)/p_max);
				//System.out.println("L "+L);
				ArrayList<Integer> aaai_result=new ArrayList<Integer>();
				
				if(SHOW)
				{
					System.out.println("aaai running.... ");
				}
				
				AAAI.run(myinstance, alpha, L, aaai_result, appear_node_list, rnd, cfriends);

				result.get(j).add(p_max);
				//System.out.println(p_max);
				result.get(j).add((double) appear_node_list.size());
				
				//System.out.println(record[1][i]);
				
				if(SHOW)
				{
					System.out.println("test_probability");
				}

				result.get(j).add(get_prob_monte(myinstance, aaai_result, rnd, cfriends));
				result.get(j).add((double) aaai_result.size());
				//System.out.println("HighDegree.run");
				
				if(SHOW)
				{
					System.out.println("HighDegree running.... ");
				}
				ArrayList<Integer> hd_result=new ArrayList<Integer>();
				HighDegree.run(myinstance, hd_result, appear_node_list, aaai_result.size());
				
				if(SHOW)
				{
					System.out.println("test_probability");
				}
				result.get(j).add(get_prob_monte(myinstance, hd_result, rnd, cfriends));
				result.get(j).add((double) hd_result.size());
				
				if(SHOW)
				{
					System.out.println("SP running");
				}
				ArrayList<Integer> sp_result=new ArrayList<Integer>();
				SP.run(myinstance, sp_result, appear_node_list, aaai_result.size());
				
				if(SHOW)
				{
					System.out.println("test_probability");
				}
				result.get(j).add(get_prob_monte(myinstance, sp_result, rnd, cfriends));
				result.get(j).add((double) sp_result.size());
			}
			COUNT++;
			if(COUNT % 10 ==0)
			{
				System.out.println(COUNT+" "+L);
			}
			return 0.0;
		}*/
		
		private void read_inv(int pairnum, int j, ArrayList<Integer> invitation_set)
		{
			String path="data/"+myinstance.name+"_result/"+ROUND+"/"+pairnum+"_"+j+".txt";
			File singleFile=new File(path);
			String inString = "";
			try {
	            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
	            while((inString = reader.readLine())!= null){
	            	String[] tempString = null;
	    			tempString=inString.split(" ");
	    			for(int i=0;i<tempString.length;i++)
	    			{
	    				invitation_set.add(Integer.parseInt(tempString[i]));
	    			}
	    			
	        		
	            }
	            reader.close();
	        } catch (FileNotFoundException ex) {
	            System.out.println(path+" The path of data is incorrect.");
	        } catch (IOException ex) {
	            System.out.println("Error in reading data.");
	        }
		}
		
		public Double call_1() {
			
			ArrayList<Integer> cfriends=new ArrayList<Integer>();
			cfriends.add(s);
			int L = 0;
			double beta=0;
			for(int i=0; i<myinstance.network.neighbor.get(s).size();i++)
			{
				cfriends.add(myinstance.network.neighbor.get(s).get(i));
			}
			myinstance.set_s_t_index(s, t);
			double[] result_1=new double[2];
			//System.out.println("get_pmax...");
			result_1=get_pmax(myinstance, 0.05, cfriends);
			
			//ArrayList<Integer> all=new ArrayList<Integer>();
			//for(int i=0;i<myinstance.network.vertexNum;i++)
			//{
			//	all.add(i);
			//}
			//System.out.println(myinstance.test_probability(all, 10000));
			
			for(int j=0;j<5;j++)
			{
				//ArrayList<Integer> appear_node_list=new ArrayList<Integer>();
				double alpha=0.5+0.1*j;
				double[] result=new double[3];
				//System.out.println("get_L_beta...");
				result=get_L_beta(alpha, (int)result_1[1]);
				//System.out.println("epsilon0 "+result[2]);
				L= (int) (result[0]/result_1[0]);
				beta=result[1];
				ArrayList<Integer> inv_set=new ArrayList<Integer>();
				read_inv(pairnum, j, inv_set);
				double aaai_prob=0;
				aaai_prob=get_prob_monte(myinstance,inv_set,rnd,cfriends);
				System.out.print("s "+s+" t "+t+" alpha "+alpha+" L "+L+" pmax "+FORMATTER.format(result_1[0])+" max_size "+(int)result_1[1]);
				System.out.println(" beta "+FORMATTER.format(beta)+" aaai_prob "+FORMATTER.format(aaai_prob)+" aaai_size "+inv_set.size());
				
				ArrayList<Integer> hdegree=new ArrayList<Integer>();
				ArrayList<Integer> all=new ArrayList<Integer>();
				for(int i=0;i<myinstance.network.vertexNum;i++)
				{
					all.add(i);
				}
				HighDegree.run(myinstance, hdegree, all, inv_set.size());
				double degree_prob=get_prob_monte(myinstance, hdegree, rnd, cfriends);
				System.out.println(" degree_prob "+FORMATTER.format(degree_prob));
			}
			return 0.0;
		}
		
		public Double call() {
			
			ArrayList<Integer> cfriends=new ArrayList<Integer>();
			cfriends.add(s);
			int L = 0;
			double beta=0;
			for(int i=0; i<myinstance.network.neighbor.get(s).size();i++)
			{
				cfriends.add(myinstance.network.neighbor.get(s).get(i));
			}
			myinstance.set_s_t_index(s, t);
			double[] result_1=new double[2];
			//System.out.println("get_pmax...");
			result_1=get_pmax(myinstance, 0.05, cfriends);
			if(result_1[0]<=0.01)
			{
				return 0.0;
			}
			
			//ArrayList<Integer> all=new ArrayList<Integer>();
			//for(int i=0;i<myinstance.network.vertexNum;i++)
			//{
			//	all.add(i);
			//}
			//System.out.println(myinstance.test_probability(all, 10000));
			double[] alpha={1.0, 1.0, 1.0};
			double[] interval={0.9,0.8,0.7};
			int[] Ns={100,10000,100000};
			for(int j=0;j<1;j++)
			{
				//ArrayList<Integer> appear_node_list=new ArrayList<Integer>();
				//double alpha=0.0+0.1*j;
				EPSILON=interval[j];
				//N=Ns[j];
				double[] result=new double[3];
				//System.out.println("get_L_beta...");
				result=get_L_beta(alpha[j], (int)result_1[1]);
				//System.out.println("epsilon0 "+result[2]);
				L= (int) (result[0]/result_1[0]);
				beta=result[1];
				//ArrayList<Integer> inv_set=new ArrayList<Integer>();
				//read_inv(pairnum, j, inv_set);
				//double aaai_prob=0;
				//aaai_prob=get_prob_monte(myinstance,inv_set,rnd,cfriends);
				//if(result_1[0]>0.00)
				//{
				double[] ratio={0.1, 0.2, 0.5};
				for(int i=0;i<3;i++)
				{
					System.out.print("s "+s+" t "+t+" alpha "+FORMATTER.format(ratio[i])+" L "+(int)(L*ratio[i])+" pmax "+FORMATTER.format(result_1[0]));
					//System.out.println(" beta "+FORMATTER.format(get_prob_monte(myinstance, ALL_NODE, rnd, cfriends)));
					System.out.println(" beta "+FORMATTER.format(beta));
				}

				//}
				
				/*
				ArrayList<Integer> hdegree=new ArrayList<Integer>();
				ArrayList<Integer> all=new ArrayList<Integer>();
				for(int i=0;i<myinstance.network.vertexNum;i++)
				{
					all.add(i);
				}
				HighDegree.run(myinstance, hdegree, all, (int)result_1[1]);
				double degree_prob=get_prob_monte(myinstance, hdegree, rnd, cfriends);
				System.out.println(" degree_prob "+FORMATTER.format(degree_prob));
				*/
			}
			return 0.0;
		}
	}
	
	private static void print(String string)
	{
		System.out.print(string);
		writer.print(string);
		
	}
	
	private static void println(String 
			
			string)
	{
		System.out.println(string);
		writer.println(string);
		
	}
	
	private static void println()
	{
		System.out.println();
		writer.println();
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		POOLNUM=1;
		N=30000;
		EPSILON=0.1;
		ROUND=3;
		//STEP=50000;
		//INITIATOR_NUM=500;
		PAIR_NUM=300;
		LINE_NUM=16;
		P_MAX_EPSILON=0.1;
		LOWER_BOUND=0.01;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String logname=(dateFormat.format(date).replace(":", "-")).replace("/", "=");

		 //2016/11/16 12:08:43
		writer = new PrintWriter("data/result/log/"+logname+".txt");
		println(logname);
		
		FORMATTER = new DecimalFormat("#0.000"); 
		
		//SHOW=true;
		println("poolnum "+POOLNUM);
		println("N "+N);
		println("epsilon "+EPSILON);
		println("P_MAX_EPSILON "+P_MAX_EPSILON);
		println("round "+ROUND);
		//System.out.println("step "+STEP);
		println("INITIATOR_NUM "+INITIATOR_NUM);
		//wiki();
		//hepth();
		//youtube();
		//gplus();
		//hepth();
		//epinions();
		//wiki_new();
		//exp4("wiki", 8300);
		//exp1("HepTh", 27770);
		//exp2("youtube", 1157900);
		//exp1("youtube", 1157900);
		//exp4("HepTh", 27770);
		exp4("wiki", 8300);
		//exp4("hepph", 35000);
		//hepph_new();
		//epinions_new();
		//facebook_new();
		//youtube_new();
		//hepth_new();
		//gplus_new();
		//pokec_new();
		writer.close();
	}
	

}
