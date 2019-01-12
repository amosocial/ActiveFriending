package aaai_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Tool {
	public static void to_gnuplot(String path)
	{
		File singleFile=new File(path);
		//ArrayList<String> opt_size=new ArrayList<String>();
		//ArrayList<String> rba_size=new ArrayList<String>();
		//ArrayList<String> opt_prob=new ArrayList<String>();
		//ArrayList<String> rba_prob=new ArrayList<String>();
		String inString = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
            while((inString = reader.readLine())!= null){
            	//System.out.println(inString);
            	//System.out.println(inString);
            	//String[] tempString = null;
    			//tempString=inString.split(" ");
    			//int node_1=Integer.parseInt(tempString[0])-1; 
    			//int node_2=Integer.parseInt(tempString[1])-1; 
    			
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" The path of data is incorrect.");
        } catch (IOException ex) {
            System.out.println("Error in reading data.");
        }
		System.out.println("loaded");
	}

	public static void get_neighbors() throws FileNotFoundException
	{
		String youtube="data/youtube.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(youtube, 1157900, youtube);
		
		//String path="data/wiki.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 8300, "wiki");
		
		//String path="data/HepTh.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,27770,"hepth");
		
		//String path="data/gplus.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 107614, "gplus");
		
		//String pokec="data/pokec.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(pokec,1632803,"pokec");
		
		int round=2;
		//int neighbornum=10;
		
		System.out.println("dataset imported");
		int t=myinstance.nodes_by_indegree[0];
		ArrayList<Integer> initiators=new ArrayList<Integer>();
		ArrayList<Integer> current=new ArrayList<Integer>();
		ArrayList<Integer> passed=new ArrayList<Integer>();
		passed.add(t);
		current.add(t);
		for(int i=0;i<round;i++)
		{
			if(i==round-1)
			{
				for(int j=0;j<current.size();j++)
				{
					int index=current.get(j);
					for(int k=0;k<myinstance.network.neighbor_reverse.get(index).size();k++)
					{
						int indexx=myinstance.network.neighbor_reverse.get(index).get(k);
						if(!passed.contains(indexx) && !initiators.contains(indexx))
						{
							initiators.add(indexx);
						}
					}
				}
			}
			else
			{
				ArrayList<Integer> temp=new ArrayList<Integer>();
				for(int j=0;j<current.size();j++)
				{
					int index=current.get(j);
					for(int k=0;k<myinstance.network.neighbor_reverse.get(index).size();k++)
					{
						int indexx=myinstance.network.neighbor_reverse.get(index).get(k);
						if(!passed.contains(indexx) && !temp.contains(indexx))
						{
							temp.add(indexx);
							passed.add(indexx);
						}
					}
				}
				current.clear();
				current=new ArrayList<Integer>(temp);
			}	
		}
		
		PrintWriter writer = new PrintWriter("data/"+myinstance.name+"_neighbors"+round+".txt");
		writer.println(t);
		for(int i=0; i<initiators.size() ;i++)
		{
			writer.println(initiators.get(i));
		}
		writer.close();
		
		
		
	}
	
	public static void get_random_pairs() throws FileNotFoundException
	{
		//String path="data/epinions.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 75890, "epinions");
		
		String path="data/facebook.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 4039, "facebook");
		
		//String youtube="data/youtube.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(youtube, 1157900, "youtube");
		
		//String path="data/wiki.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 8300, "wiki");
		
		//String path="data/HepTh.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,27770,"hepth");
		
		//String hepph="data/hepph.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(hepph, 35000, "hepph");
		
		//String path="data/gplus.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 107614, "gplus");
		
		//String path="data/gplus.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 107614, "gplus");
		
		//String pokec="data/pokec.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(pokec,1632803,"pokec");
		System.out.println("dataset imported");
		
		int round=3;
		int pairnum=1000;
		int cnum=0;
		ArrayList<Integer> current_s=new ArrayList<Integer>();
		ArrayList<Integer> current_t=new ArrayList<Integer>();
		while(cnum<pairnum)
		{
			int s=(int) (Math.random()*myinstance.network.vertexNum);
			//System.out.println(t);
			if(!current_s.contains(s))
			{
				
				//ArrayList<Integer> initiators=new ArrayList<Integer>();
				ArrayList<Integer> current=new ArrayList<Integer>();
				ArrayList<Integer> passed=new ArrayList<Integer>();
				passed.add(s);
				current.add(s);
				boolean back=false;
				for(int i=0;i<round;i++)
				{
					if(i==round-1)
					{
						for(int j=0;j<current.size();j++)
						{
							int index=current.get(j);
							for(int k=0;k<myinstance.network.neighbor.get(index).size();k++)
							{
								int indexx=myinstance.network.neighbor.get(index).get(k);
								if(!passed.contains(indexx) && myinstance.network.neighbor_reverse.get(indexx).size()<4)
								{
									current_s.add(s);
									current_t.add(indexx);
									cnum++;
									System.out.println(cnum);
									back=true;
									
								}
								if(back)
									break;
							}
							if(back)
								break;
						}

					}
					else
					{
						if(round ==1)
						{
							ArrayList<Integer> temp=new ArrayList<Integer>();
							for(int j=0;j<current.size();j++)
							{
								int index=current.get(j);
								for(int k=0;k<myinstance.network.neighbor.get(index).size();k++)
								{
									int indexx=myinstance.network.neighbor.get(index).get(k);
									if(!passed.contains(indexx) && !temp.contains(indexx) && myinstance.network.neighbor_reverse.get(indexx).size()<4)
									{
										temp.add(indexx);
										passed.add(indexx);

									}
								}
							}
							current.clear();
							current=new ArrayList<Integer>(temp);
						}
						else
						{
							ArrayList<Integer> temp=new ArrayList<Integer>();
							for(int j=0;j<current.size();j++)
							{
								int index=current.get(j);
								for(int k=0;k<myinstance.network.neighbor.get(index).size();k++)
								{
									int indexx=myinstance.network.neighbor.get(index).get(k);
									if(!passed.contains(indexx) && !temp.contains(indexx))
									{
										temp.add(indexx);
										passed.add(indexx);

									}
								}
							}
							current.clear();
							current=new ArrayList<Integer>(temp);
						}
						
					}	
				}
			}
		}
		
		
		PrintWriter writer = new PrintWriter("data/"+myinstance.name+"_random_neighbors_"+round+".txt");
		for(int i=0; i<current_t.size() ;i++)
		{

				writer.println(current_s.get(i)+" "+current_t.get(i));
		}
		writer.close();
		
		
		
	}
	
	public static void get_random_pairs_fast() throws FileNotFoundException
	{
		//String youtube="data/youtube.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(youtube, 1157900, "youtube");
		
		//String path="data/wiki.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 8300, "wiki");
		
		//String path="data/HepTh.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path,27770,"hepth");
		
		//String hepph="data/hepph.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(hepph, 35000, "hepph");
		
		String path="data/epinions.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 75890, "epinions");
		
		//String path="data/gplus.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(path, 107614, "gplus");
		
		//String pokec="data/pokec.txt";
		//ActiveFriendingInstance myinstance=new ActiveFriendingInstance(pokec,1632803,"pokec");
		System.out.println("dataset imported");
		
		int round=3;
		int pairnum=1000;
		int cnum=0;
		ArrayList<Integer> current_s=new ArrayList<Integer>();
		ArrayList<Integer> current_t=new ArrayList<Integer>();
		while(cnum<pairnum)
		{
			int s=(int) (Math.random()*myinstance.network.vertexNum);
			int s_1=(int) (Math.random()*myinstance.network.neighbor.get(s).size());
			int s_2=(int) (Math.random()*myinstance.network.neighbor.get(s_1).size());
			int t=(int) (Math.random()*myinstance.network.neighbor.get(s_2).size());
			if(!current_s.contains(s) || current_t.get(current_s.indexOf(s))!=t)
			{
				current_s.add(s);
				current_t.add(t);
				cnum++;
				System.out.println(cnum);
			}
			//System.out.println(t);
		}
		
		
		PrintWriter writer = new PrintWriter("data/"+myinstance.name+"_random_neighbors_"+round+".txt");
		for(int i=0; i<current_t.size() ;i++)
		{

				writer.println(current_s.get(i)+" "+current_t.get(i));
		}
		writer.close();
	}
	
	public static void changefile_index (String name, int round, int node_num) throws FileNotFoundException
	{
		ArrayList<String> results=new ArrayList<String>();
		for(int j=0;j<node_num;j++)
		{
			for(int i=0;i<5;i++)
			{
				String path="data/"+name+"_result/"+round+"/"+j+"_"+i+".txt";
				File singleFile=new File(path);
				String inString = "";
				try {
		            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
		            while((inString = reader.readLine())!= null){
		            	results.add(inString);
		            }
		            reader.close();
		        } catch (FileNotFoundException ex) {
		            System.out.println(path+" The path of data is incorrect.");
		        } catch (IOException ex) {
		            System.out.println("Error in reading data.");
		        }
			}
		}
		
		PrintWriter writer;
		writer = new PrintWriter("data/"+name+"_result/"+round+"/result.txt");

		for(int i=0; i<results.size() ;i++)
		{
			writer.println(results.get(i));
		}
		writer.close();
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tool.get_random_pairs();
		//Tool.changefile_index("wiki", 3, 60);
	}

}
