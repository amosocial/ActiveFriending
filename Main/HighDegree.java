package aaai_1;

import java.util.ArrayList;

public class HighDegree {
	public static void run(ActiveFriendingInstance myinstance, ArrayList<Integer> result, ArrayList<Integer> appear_node_list, int size)
	{
		/*
		result.add(myinstance.t);
		System.out.println(appear_node_list.size()+" "+size);
		Integer[] temp=new Integer[appear_node_list.size()];
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		for(int i=0; i<appear_node_list.size(); i++)
		{
			mymap.put(appear_node_list.get(i), myinstance.nodes_by_outdegree[appear_node_list.get(i)]);
		}
		//System.out.println(mymap.size());
		Map<Integer, Integer> sortedMap = myinstance.sortByValue(mymap);
		sortedMap.keySet().toArray(temp);
		
		
		for(int i=0;i<temp.length ;i++)
		{
			if(result.size()==size)
			{
				return;
			}
			else if(!result.contains(temp[i]))
			{
				result.add(temp[i]);
			}
		}*/
		
		result.add(myinstance.t);
		for(int i=0; i<myinstance.network.vertexNum; i++)
		{
			int node=myinstance.nodes_by_outdegree[i];
			if(result.size()==size)
			{
				return;
			}
			else if(!result.contains(node))
			{
				result.add(node);
			}
		}
	}
	
	public static void run(ActiveFriendingInstance myinstance, ArrayList<Integer> result, int size)
	{
		/*
		result.add(myinstance.t);
		System.out.println(appear_node_list.size()+" "+size);
		Integer[] temp=new Integer[appear_node_list.size()];
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		for(int i=0; i<appear_node_list.size(); i++)
		{
			mymap.put(appear_node_list.get(i), myinstance.nodes_by_outdegree[appear_node_list.get(i)]);
		}
		//System.out.println(mymap.size());
		Map<Integer, Integer> sortedMap = myinstance.sortByValue(mymap);
		sortedMap.keySet().toArray(temp);
		
		
		for(int i=0;i<temp.length ;i++)
		{
			if(result.size()==size)
			{
				return;
			}
			else if(!result.contains(temp[i]))
			{
				result.add(temp[i]);
			}
		}*/
		
		result.add(myinstance.t);
		for(int i=0; i<myinstance.network.vertexNum; i++)
		{
			int node=myinstance.nodes_by_outdegree[i];
			if(result.size()==size)
			{
				return;
			}
			else //if(!result.contains(node))
			{
				result.add(node);
			}
		}
	}
}
