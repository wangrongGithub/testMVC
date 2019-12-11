package com.wr.Test;
import java.util.*;
class node{
	List<node> adj=new LinkedList<>();
	int val;
	node(int i)
	{
		val=i;
	}
}
public class Main1 {

	public static void main(String []args)
	{
		Scanner scan=new Scanner(System.in);
		HashMap<Integer,node>map=new HashMap<>();
		int n=Integer.valueOf(scan.nextLine());
		List<node> nodes=new LinkedList<>();
		int []flags=new int[n+1];
		for(int i=1;i<=n;i++)
		{

			map.put(i,new node(i));
		}
		String str="";
		for(int i=1;i<n;i++)
		{
			str=scan.nextLine();
			String[]strs=str.split(" ");
			node left=map.get(Integer.valueOf(strs[0]));
			node right=map.get(Integer.valueOf(strs[1]));
			left.adj.add(right);
			right.adj.add(left);
		}
		//求得每一个书的个数
		List<node>ns=map.get(1).adj;
		int min=Integer.MIN_VALUE;
		flags[1]=1;
		for(node n1:ns)
		{
			int now=minNum(n1,Arrays.copyOf(flags,flags.length));
			if(min<now )
			{
				min=now+1;

			}

		}
        System.out.println(min);
	}
	static int minNum(node root,int[]shuzu )
	{
		//临街的
		List<node>nodes=root.adj;
		shuzu[root.val]=1;
		int sum=0;
		for(node n:nodes)
		{
			if(shuzu[n.val]==0)
			{//没有被便利
				int []bei= Arrays.copyOf(shuzu,shuzu.length);
				sum+=(1+minNum(n,bei ));
			}

		}
		//System.out.println(sum);
        return sum;

	}


	}
