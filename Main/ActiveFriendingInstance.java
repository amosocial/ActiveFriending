package aaai_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ActiveFriendingInstance {
	public ActiveFriendingNetwork network;
	public int s,t;
	public ArrayList<Integer> cfriends;
	public Integer[] nodes_by_outdegree; 
	public Integer[] nodes_by_indegree; 
	public String name;
	
	public ActiveFriendingInstance(String path, int vertexNum, String name)
	{
		network=new ActiveFriendingNetwork(path, "LT" , vertexNum);
		cfriends=new ArrayList<Integer>();
		nodes_by_outdegree=new Integer[network.vertexNum];
		nodes_by_indegree=new Integer[network.vertexNum];
		
		sort_nodes_by_outdegree();
		sort_nodes_by_indegree();
		set_s_t_shift(0,0);
		
		this.name=name;
	}
	
	
	public void get_rrsets(ArrayList<ArrayList<Integer>> rrsets, int size)
	{
		/*
		network.get_rrsets_p(rrsets, size);
		for (int i=0;i<rrsets.size();i++) 
		{
	    	if(rrsets.get(i).size()==0)
	    	{
	    		rrsets.remove(i);
	    		i--;
	    	}
	    	
	    }*/
		network.get_rrsets(rrsets, size);
	}
	
	public void get_rrsets(ArrayList<ArrayList<Integer>> rrsets, int size, Random rnd, ArrayList<Integer> cfriends)
	{

		network.get_rrsets(rrsets, size, rnd, cfriends);
	}
	
	public void get_rrsets_multiThread(ArrayList<ArrayList<Integer>> rrsets, int size, int poolnum)
	{
		ExecutorService pool = Executors.newFixedThreadPool(poolnum);
		ArrayList<ArrayList<ArrayList<Integer>>> rrsetss=new ArrayList<ArrayList<ArrayList<Integer>>>();

	    Set<Future<Double>> set = new HashSet<Future<Double>>();
	    for (int i=0;i<poolnum;i++) {
	    	ArrayList<ArrayList<Integer>> temp=new ArrayList<ArrayList<Integer>>();
	    	rrsetss.add(temp);
	    	Callable<Double> callable = new gmis1Callable(network, rrsetss.get(i), size/poolnum);
	    	Future<Double> future = pool.submit(callable);
	    	set.add(future);
	    }
	    Double influence=0.0;
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
	    for (int i=0;i<poolnum;i++) {
	    	for(int j=0;j<rrsetss.get(i).size();j++)
	    	{
	    		rrsets.add(rrsetss.get(i).get(j));
	    	}
	    }
	    if(rrsets.size()!=size)
	    {
	    	//throw new ArithmeticException("rrsets.size()!=size "+rrsets.size()+" "+size); 
	    }
	}
	
	
	public static class gmis1Callable implements Callable<Double> 
	{
		private 
		ActiveFriendingNetwork network;
		ArrayList<ArrayList<Integer>> rrsets; 
		int sub_times;
		Random rnd;
		
		public gmis1Callable(ActiveFriendingNetwork network, ArrayList<ArrayList<Integer>> rrsets, int sub_times) {
		this.network = network;
		this.rrsets = rrsets;
		this.sub_times=sub_times;
		this.rnd = new java.util.Random();
		
		}
		public Double call() {
			network.get_rrsets(rrsets, sub_times, rnd);
			return 0.0;
		}
	}
	
	
	public double test_probability(ArrayList<Integer> invitation_set, int times)
	{
		double result=0;
		for(int i=0; i<times;i++)
		{
			result=result+network.spreadOnce_invitation_set(invitation_set);
			//System.out.println(i);
		}
		return result/times;
	}
	
	public double test_probability_rr(ArrayList<Integer> invitation_set, double p_max)
	{
		int rr_size= (int) (2*(Math.log(2*3000))*(1+0.1)/(0.1*0.1*p_max*p_max));
		System.out.println(rr_size);
		ArrayList<ArrayList<Integer>> rrsets=new ArrayList<ArrayList<Integer>>();
		boolean[] vector_invitation_set=new boolean[network.vertexNum];
		for(int i=0;i<network.vertexNum; i++)
		{
			vector_invitation_set[i]=false;
		}
		for(int i=0; i<invitation_set.size(); i++)
		{
			vector_invitation_set[invitation_set.get(i)]=true;
		}
		this.get_rrsets(rrsets, rr_size);
		double result=0;
		for(int i=0; i<rrsets.size(); i++)
		{
			boolean ifsubset=true;
			if(rrsets.get(i).size()==0)
			{
				ifsubset=false;
			}
			else
			{
				for(int j=0; j<rrsets.get(i).size(); j++)
				{
					if(!vector_invitation_set[rrsets.get(i).get(j)])
					{
						ifsubset=false;
						break;
					}
				}
			}
			if(ifsubset)
			{
				result++;
			}
			
		}
		return result/(double) rr_size;
	}
	
	public void set_s_t_shift(int s_shift, int t_shift)
	{
		this.s=nodes_by_outdegree[s_shift];
		cfriends.clear();
		set_cfriends();
		
		for(int i=0;i<network.vertexNum;i++)
		{
			
			if(!cfriends.contains(this.nodes_by_indegree[i+t_shift]))
			{
				t=this.nodes_by_indegree[i+t_shift];
				break;
			}
		}
		network.set_t_c(t, cfriends);
	}
	
	public void set_s_t_index(int s_index, int t_index)
	{
		this.s=s_index;
		cfriends.clear();
		set_cfriends();
		this.t=t_index;
		network.set_t_c(t, cfriends);
	}
	
	public void get_neighbors(int root, int round, ArrayList<Integer> neighbors, int limit)
	{
		ArrayList<Integer> reach_nodes=new ArrayList<Integer>();
		ArrayList<Integer> new_nodes=new ArrayList<Integer>();
		new_nodes.add(root);
		for(int i=0;i<round;i++)
		{
			ArrayList<Integer> tnew_nodes=new ArrayList<Integer>();
			if(i<round-1)
			{
				for(int j=0;j<new_nodes.size();j++)
				{
					//System.out.println(new_nodes.size()+" "+j);
					int t_index=new_nodes.get(j);
					for(int k=0;k<network.neighbor.get(t_index).size();k++)
					{
						int n_index=network.neighbor.get(t_index).get(k);
						if(!reach_nodes.contains(n_index))
						{
							reach_nodes.add(n_index);
							tnew_nodes.add(n_index);
						}
					}
				}
				new_nodes.clear();
				for(int j=0;j<tnew_nodes.size();j++)
				{
					new_nodes.add(tnew_nodes.get(j));
				}
				tnew_nodes.clear();
			}
			else
			{
				for(int j=0;j<new_nodes.size();j++)
				{
					int t_index=new_nodes.get(j);
					for(int k=0;k<network.neighbor.get(t_index).size();k++)
					{
						int n_index=network.neighbor.get(t_index).get(k);
						if(!reach_nodes.contains(n_index))
						{
							reach_nodes.add(n_index);
							neighbors.add(n_index);
							if(neighbors.size()>limit)
							{
								return;
							}
						}
					}
				}
			}
		}
	}
	
	public int get_neighbors_byread(ArrayList<Integer> neighbors, int limit, int round)
	{
		File singleFile=new File("data/"+name+"_neighbors"+round+".txt");
		String inString = "";
		int t=-1;
		try {
            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
            while((inString = reader.readLine())!= null){
            	if(t==-1)
            	{
            		t=Integer.parseInt(inString);
            	}
            	else
            	{
            		if(neighbors.size()<limit)
            		{
            			neighbors.add(Integer.parseInt(inString));
            		}
            		else
            		{
            			reader.close();
            			return t;
            		}
            	}
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
		return t;
		
	}
	
	public void get_pairs_byread(ArrayList<Integer> initiators, ArrayList<Integer> targets, int limit, int round)
	{
		String path="data/"+name+"_random_neighbors_"+round+".txt";
		File singleFile=new File(path);
		String inString = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(singleFile));
            while((inString = reader.readLine())!= null){
            	String[] tempString = null;
    			tempString=inString.split(" ");
    			
        		if(initiators.size()<limit)
        		{
        			initiators.add(Integer.parseInt(tempString[0]));
        			targets.add(Integer.parseInt(tempString[1]));
        		}
        		else
        		{
        			break;

        		}
 
            	//System.out.println(inString);
            	//System.out.println(inString);
            	//String[] tempString = null;
    			//tempString=inString.split(" ");
    			//int node_1=Integer.parseInt(tempString[0])-1; 
    			//int node_2=Integer.parseInt(tempString[1])-1; 
    			
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("data/"+name+"_random_neighbors_"+round+".txt"+" The path of data is incorrect.");
        } catch (IOException ex) {
            System.out.println("Error in reading data.");
        }
		
	}
	private void set_cfriends()
	{
		
		cfriends.add(s);
		for(int i=0; i<network.neighbor.get(s).size();i++)
		{
			cfriends.add(network.neighbor.get(s).get(i));
		}
			
	}
	
	
	
	private void sort_nodes_by_outdegree()
	{
		
		
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		for(int i=0;i<network.vertexNum;i++)
		{
			mymap.put(i, network.outDegree[i]);
		}
		//System.out.println(mymap.size());
		Map<Integer, Integer> sortedMap = sortByValue(mymap);
		
		sortedMap.keySet().toArray(nodes_by_outdegree);
	}
	
	private void sort_nodes_by_indegree()
	{
		
		
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		for(int i=0;i<network.vertexNum;i++)
		{
			mymap.put(i, network.inDegree[i]);
		}
		//System.out.println(mymap.size());
		Map<Integer, Integer> sortedMap = sortByValue(mymap);
		
		sortedMap.keySet().toArray(nodes_by_indegree);
	}
	
	
	public Map<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Integer, Integer>> list =
                new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String wiki="data/wiki.txt";
		ActiveFriendingInstance myinstance=new ActiveFriendingInstance(wiki,8300,"wiki");
		for(int i=0;i<100;i++)
		{
			
		}
		
		ArrayList<ArrayList<Integer>> rrsets=new ArrayList<ArrayList<Integer>> ();
		myinstance.get_rrsets(rrsets, 1000);
		for(int i=0;i<1000;i++)
		{
			String temp="";
			for(int j=0;j<rrsets.get(i).size();j++)
			{
				temp=temp+" "+rrsets.get(i).get(j);
			}
			System.out.println(temp);
		}
		
		
	}
}
