package com.wr;



import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

import java.util.regex.Pattern;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) 
     { val = x; }
     TreeNode() {
		// TODO Auto-generated constructor stub
	}
     
     
 }
 class Solution4 {

	public int movingCount(int threshold, int rows, int cols)
	{
		int i=0,j=0;
		int[][]matrix=new int[rows][cols];
		int[][]flag=new int[rows][cols];
		for(i=0;i<rows;i++)
		{
			for(j=0;j<cols;j++)
			{
				matrix[i][j]=jis(i)+jis(j);
				if(matrix[i][j]==threshold)
				{
					flag[i][j]=1;
				}
			}

		}
		//采取广度优先便利的规则啊
		int num=0;
		List<Integer[]>list=new LinkedList();
		list.add(0,new Integer[]{0,0});
		while(list!=null)
		{
			//出队列并进行访问啊
			Integer[] index=list.remove(list.size()-1);
			flag[index[0]][index[1]]=1;
			num++;
			//往上面走
			if(index[0]-1>=0&&flag[index[0]-1][index[1]]==0)
			{
				list.add(0,new Integer[]{index[0]-1,index[1]});
			}
			//往下面走
			if(index[0]+1<rows&&flag[index[0]+1][index[1]]==0)
			{
				list.add(0,new Integer[]{index[0]+1,index[1]});
			}
			//往左面走
			if(index[1]-1>=0&&flag[index[0]][index[1]-1]==0)
			{
				list.add(0,new Integer[]{index[0],index[1]-1});
			}
			//往右面走
			if(index[1]+1>=0&&flag[index[0]][index[1]+1]==0)
			{
				list.add(0,new Integer[]{index[0],index[1]+1});
			}






		}
		return num;
	}
	public int jis(int num)
	{
		int i=0;
		while(num!=0)
		{
			i+=num%10;
			num=num/10;
		}
		return i;








	}
}
 class Main{
	public static int minVal;
	public static void main6(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt()-1;//N行
		int m = sc.nextInt()-1;//M列
		int matrix[][] = new int[n+1][m+1];
		int minpos[]=new int[2];//记录矩阵中最小值的位置
		for(int i=0;i<=m;i++){
			for(int j=0;j<=n;j++){
				matrix[i][j]=sc.nextInt();
			}
		}

		getMinpos(matrix,m,n,minpos);//获取矩阵中最小值位置
		minVal=matrix[minpos[0]][minpos[1]];//初始化牛牛获得的最小价值
		System.out.println("所有分法：");
		getMaxVal(matrix,m,n,minpos);//分田过程
		System.out.println("最终牛牛获得的价值："+minVal);//输出牛牛获得的价值
	}

	private static void getMaxVal(int[][] matrix, int m, int n, int[] minpos) {
		int oldMatrix[][] = new int[m+1][n+1];
		oldMatrix=matrix;//保存原矩阵,回溯返回到这时还需要用原来的矩阵而不是被子过程改变的矩阵
		int m0 = minpos[0];
		int n0 = minpos[1];
		//最后得到4*4返回
		if(m==n&&m==3){
			for(int i=0;i<=3;i++){
				System.out.println(java.util.Arrays.toString(matrix[i]));
			}
			System.out.println();
			return;
		}
		//向上合并
		if(m0>0&&m>3){
			matrix=mergeMatrix(matrix,m,n,m0-1,n,minpos,1);
			getMaxVal(matrix,matrix.length-1,matrix[0].length-1,minpos);
		}
		matrix=oldMatrix;
		//向下合并
		if(m0<m&&m>3){
			matrix=mergeMatrix(matrix,m,n,m0,n,minpos,1);
			getMaxVal(matrix,matrix.length-1,matrix[0].length-1,minpos);
		}
		matrix=oldMatrix;
		//向左合并
		if(n0>0&&n>3){
			matrix=mergeMatrix(matrix,m,n,m,n0-1,minpos,-1);
			getMaxVal(matrix,matrix.length-1,matrix[0].length-1,minpos);
		}
		matrix=oldMatrix;
		//向右合并
		if(n0<n&&n>3){
			matrix=mergeMatrix(matrix,m,n,m,n0,minpos,-1);
			getMaxVal(matrix,matrix.length-1,matrix[0].length-1,minpos);
		}
	}

	private static int[][] mergeMatrix(int[][] matrix, int m, int n,int mergem,int mergen, int[] minpos,int flag) {
		int[][] temp=null;
		if(flag==1){//向上合并 或 向下合并
			temp = new int[m][n+1];
			for(int i=0;i<=m;i++){
				for(int j=0;j<=n;j++){
					if(i<mergem){
						temp[i][j] = matrix[i][j];
					}
					else if(i==mergem){
						temp[i][j] = matrix[i][j]+matrix[i+1][j];
					}else{
						temp[i-1][j] = matrix[i][j];
					}
				}
				if(i==mergem){
					i++;
				}
			}
		}else if(flag==-1){//向左合并 或 向右合并
			temp = new int[m+1][n];
			for(int i=0;i<=m;i++){
				for(int j=0;j<=n;j++){
					if(j<mergen){
						temp[i][j]=matrix[i][j];
					}
					else if(j==mergen){
						temp[i][j]=matrix[i][j]+matrix[i][j+1];
						j++;
					}else{
						temp[i][j-1]=matrix[i][j];
					}
				}
			}
		}
		//合并后的矩阵求其中最小的位置
		getMinpos(temp,temp.length-1,temp[0].length-1,minpos);
		return temp;
	}

	//获取矩阵最小值位置
	private static void getMinpos(int[][] matrix,int m,int n,int[] minpos) {
		int min = Integer.MAX_VALUE;
		for(int i=0;i<=m;i++){
			for(int j=0;j<=n;j++){
				if(matrix[i][j]<min){
					min=matrix[i][j];
					minpos[0]=i;
					minpos[1]=j;
				}
			}
		}
		if(minVal<min){
			minVal=min;
		}
	}

}

/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */


class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
     Point(int a, int b) { x = a; y = b; }
  }
class RandomListNode {
	int label;
	RandomListNode next = null;
	RandomListNode random = null;
	public static ArrayList<ArrayList<Integer> > FindContinuousSequence(int num)
	{
		ArrayList<ArrayList<Integer> > arrays=new ArrayList<ArrayList<Integer> >();
        /*

        */
		int num1=num;
		int i=2;
		while(num1>Math.sqrt(num))
		{
			num1=num;
			int k=num1%i;
			num1=num1/i;
			//判断是否可以构成啊\
			int flag=0;
			//当i是奇数的时候
			if(i%2!=0&&k==0)
			{
				ArrayList<Integer> array=new ArrayList<Integer>();
				int j=0;
				while(j<i)
				{
					array.add(num1+j-i/2);
					j++;
				}
				System.out.println(array);
				arrays.add(array);

			}
			//当i是偶数的时候啊；先把奇数的弄好啊
			if(i%2==0&&k==i/2)
			{
				ArrayList<Integer> array=new ArrayList<Integer>();
				int j=0;
				while(j<i)
				{
					array.add(num1+j-i/2);
					j++;

				}
				System.out.println(array);
				arrays.add(array);


			}
			i++;

		}

		return arrays;


	}
	String Serialize(TreeNode root)
	{//相当于变成一个完全二叉树的数组
		String str="";
		if(root==null)
		{
			return "";
		}
		//采用层序便利啊
		Queue<TreeNode> q=new LinkedList();
		q.offer(root);
		int val=0;
		while(!q.isEmpty())
		{
			//出队列
			TreeNode head=q.poll();
			str+=head.val;
			if(head==null)
			{
				str+="#";
				q.offer(null);
				q.offer(null);
			}
			if(head.left!=null)
			{
				q.offer(head.left);
				val=head.left.val;
			}
			else
			{
				q.offer(null);
			}

			if(head.right!=null)
			{
				q.offer(head.right);
				val=head.right.val;
			}
			else
			{
				q.offer(null);
			}





		}



		return str;






	}
	static TreeNode Deserialize(String str)
	{//恢复

		int len=str.length();
		TreeNode[] nodes=new TreeNode[str.length()];
		//有孩子节点的个数是
		int i=0;
		for(int j=0;j<len;j++)
			nodes[j]=new TreeNode(0);
		nodes[0].val=Integer.valueOf(str.substring(0,1));

		while(i<len/2)
		{//弄到第几个了
			if(!str.substring(i*2+1,i*2+2).equals("#"))
			{
				nodes[i*2+1].val=Integer.valueOf(str.substring(i*2+1,i*2+2));
				nodes[i].left=nodes[i*2+1];
			}
			else
			{
				//nodes[i*2+1]=null;
				nodes[i].left=null;
			}
			if(!str.substring(i*2+2,i*2+3).equals("#"))
			{
				nodes[i*2+2].val=Integer.valueOf(str.substring(i*2+2,i*2+3));
				nodes[i].right=nodes[i*2+1];
			}
			else
			{
				//nodes[i*2+2]=null;
				nodes[i].left=null;
			}
			i++;
		}



		return nodes[0];
	}
	RandomListNode(int label) {
		this.label = label;
	}
//使用归并排序的思想啊
	public static  int InversePairs(int [] array)
	{

      int num=InversePairs(array,0,array.length);
      System.out.println(num%1000000007);


		return num;

	}



	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2)
	{
		if(pHead1==null||pHead2==null)
		{return null;}
		Stack<ListNode> s1=new Stack(),s2=new Stack();
		while(pHead1!=null)
		{
			s1.push(pHead1);
		}
		while(pHead2!=null)
		{
			s1.push(pHead2);
		}
        /*
        使用一个记录上一次的
        */
		ListNode l1=null,l2=null;
		while(!s1.isEmpty()&&!s2.isEmpty())
		{
			if(s1.peek()!=s2.peek())
			{
				break;
			}
			l1=s1.pop();
			l2=s2.pop();

		}
		return l1;
	}

	static	int  InversePairs(int [] array,int begin,int end)
	{
		if(end-begin<=1)
		{
			return 0;

		}
		int left=InversePairs( array, begin,(begin+end)/2);
		int right=InversePairs( array,(begin+end)/2, end);
		//两个有序序列的合并啊
        int sum=0;
        int i=begin;
		//排序啊
		int j=(begin+end)/2;
		int [] array1=new int[end-begin];
		int k=0;
		//进行归并啊
		while(i<(begin+end)/2&&j<end)
		{
			if(array[i]>array[j])
			{
				//改变啊
				while(j<end&&array[i]>array[j])
				{
					array1[k++] = array[j];
					j++;
				}

			}
			else
			{/*
			把剩下的都加入到这个里面啊
			*/
				while(i<(begin+end)/2&&array[i]<=array[j])
				{
					array1[k++]=array[i++];//不改变啊sum啊
					sum= sum%1000000007+(j-(begin+end)/2);//比左边部分剩下的都小啊

				}

			}

		}
     if(i<(begin+end)/2)
    {
    	while(i<(begin+end)/2) {

			if (array[i]>array[end-1]) {
				sum =sum%1000000007+ (end - (begin + end) / 2);
			}
			array1[k++] = array[i++];
		}
    }
    if(j<end)
   {
	  while(j<end)
	  {
		  array1[k++]=array[j++];
	  }
   }

		for( i=0;i<array1.length;i++)
		{
			array[begin+i]=array1[i];

		}

		return sum%1000000007+left%1000000007+right%1000000007;

	}













	public String toString()
	{
	return (" "+label) ;
	}
}







 class Solution1 {


	public boolean VerifySquenceOfBST(int [] sequence)
	{


		return VerifySquenceOfBST(sequence,0,sequence.length-1) ;


	}
	 public static TreeNode Convert(TreeNode pRootOfTree)
	 {//二叉搜索树就是二叉排序树啊；pre:左子树的最大值；next:右子树的最小值；其实就是中序便利的改进啊
		 if(pRootOfTree==null)
		 {
			 return null;

		 }
		 if(pRootOfTree.right==null&&pRootOfTree.left==null)
		 {
			 //pRootOfTree.left=pRootOfTree;
			 return pRootOfTree;

		 }
		 //假设改变成循环双向链表啊，返回的是链表头啊;使用头节点的left存储尾巴节点啊
		 TreeNode left=Convert( pRootOfTree.left),rootRight=pRootOfTree.right;
		 if(left!=null)
		 {
			 //pRootOfTree.right=left.left;
			 TreeNode Tnode=left;
			 while(Tnode.right!=null) {
				 Tnode=Tnode.right;
			 }

			 pRootOfTree.left=Tnode;//让尾巴节点
			 Tnode.right=pRootOfTree;//让跟节点的左边指向尾节点
			 pRootOfTree.right=null;
		 }
		 else
		 {
			 left=pRootOfTree;
		 }
		 //假设改变成循环双向链表啊，返回的是链表头啊
		 if(rootRight!=null)
		 {
			 TreeNode right=Convert( rootRight);
			 right.left=pRootOfTree;//指向根节点
			 pRootOfTree.right=right;//指向右边的第一个节点
		 }
		 return left;
	 }


	 public static ArrayList<String> Permutation(String str)
	 {
		 ArrayList<String> list=new ArrayList<String>();
		 //
		 Set<String> set=new TreeSet();
		 if(str==null||str=="")
		 {
			 list.add("");
			 return list;
		 }
		 if(str.length()==1)
		 {
			 list.add(str);
			 return list;
		 }
		 for(int i=0;i<str.length();i++)
		 {
			 String headStr=str.substring(i,i+1);
			 String sourceStr=str.substring(0,i)+str.substring(i+1,str.length());
			 ArrayList<String>  subList=Permutation( sourceStr);
			 for(String destStr:subList)
			 {
				 set.add(headStr+destStr);
			 }
		 }
		 for(String str1:set)
		 {
		 	list.add(str1);
		 }
		 return list;
	 }

	 public int MoreThanHalfNum_Solution(int [] array)
	 {
		 //折半查找啊；用map实现
		 Map<Integer,Integer> map=new HashMap();
		 for(int i=0;i<array.length;i++)
		 {
			 if(map.get(array[i])==null)
			 {
				 map.put(array[i],0);
			 }
			 else
			 {
				 map.put(array[i],map.get(array[i])+1);

			 }
		 }
		 int bound=array.length/2;
		 Set<Integer>set=map.keySet();
		 for(Integer data: set)
		 {
			 if(map.get(data)>bound)
			 {
				 return data;
			 }
		 }

		 return 0;


	 }
	public boolean VerifySquenceOfBST(int [] sequence,int start,int end)
	{/*
    二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，
    或者是具有下列性质的二叉树： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
    若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
    */
		//中序便利肯定是有序的，左边比它小，右边比它大
		int key=sequence[start];
		int flag=0,j=0;
		if(start>=end)
		{
			return true;
		}
		for(int i=start+1;i<=end;i++)
		{
			if(flag==0&&key<sequence[i])
			{//key比这个小
				j++;

			}
			else if(flag==1&&key<sequence[i])
			{
				return false;
			}
			else if(flag==0&&key>sequence[i])
			{
				flag=1;
			}

		}
		//判断左右子树满足不
		boolean left=VerifySquenceOfBST(sequence,start+1,start+j);
		boolean right=VerifySquenceOfBST(sequence,start+j+1,end);
		if(left==true&&right==true)
		{
			return true;
		}

		return false;


	}
	public static RandomListNode Clone(RandomListNode pHead)
	{
		//复杂链表的复制啊
		List<RandomListNode> listRandom=new ArrayList();
		HashMap<RandomListNode,RandomListNode> map=new LinkedHashMap();
        /*
        使用hashMap存储对应的关系啊
        */
		RandomListNode head=pHead,newHead=null,p=null;

		while(pHead!=null)
		{
			RandomListNode node=new RandomListNode(pHead.label);
			map.put(pHead,node);
			node.next=null;
			if(newHead==null)
			{
				newHead=node;
				p=node;

			}
			p.next=node;
			p=node;
			pHead=pHead.next;



		}
		p=newHead;
		while(head!=null)
		{
			p.random=map.get(head.random);
			p=p.next;
			head=head.next;


		}

		return newHead;
	}
public static void test()
{
	TreeNode root10=new TreeNode(10);
	TreeNode node5=new TreeNode(5);
	TreeNode node4=new TreeNode(4);
	TreeNode node7=new TreeNode(7);
	TreeNode node12=new TreeNode(12);
	root10.left=node5;
	root10.right=node12;
	node5.left=node4;
	node5.right=node7;

//	TreeNode node1=node.left,node2=node.left.left;
	System.out.println(Permutation("aasassa"));


	//TreeNode node2=new TreeNode(2);
	//TreeNode node4=new TreeNode(4);
	//TreeNode node_7=new TreeNode(7);
	//TreeNode _node9=new TreeNode(9);
	//TreeNode _node2=new TreeNode(2);
	//root2.right=_node2;
	//root2.left=_node9;
	RandomListNode pHead=new RandomListNode(9) ;
	RandomListNode n3=new RandomListNode(3) ;
	RandomListNode n7=new RandomListNode(7) ;
	RandomListNode n8=new RandomListNode(8) ;
	pHead.next=n3;pHead.random=n8;

	n3.next=n7;n3.random=pHead;

	n7.next=n8;n7.random=n3;
	n8.next=null;n8.random=n7;

	pHead.label=9;



}

	public static  boolean HasSubtree(TreeNode root1,TreeNode root2)
	{
		//判断两颗树的便利结果是否一致可以判断；或者在便利root1的时候去遍历root2
		//去看前序遍历
		if(root2==null)
		{return false;}
		java.util.LinkedList<TreeNode> list1=new java.util.LinkedList();
		java.util.LinkedList<TreeNode> list2=new java.util.LinkedList();
		TreeNode p1=root1;
		TreeNode p2=root2;
		list1.add(p1);
		list2.add(p2);
		while(list1.size()>0)
		{
			int flag=0;
			if(list1.get(list1.size()-1).val==list2.get(list2.size()-1).val)
			{//进行判断啊
				java.util.LinkedList<TreeNode> list11=new java.util.LinkedList();
				java.util.LinkedList<TreeNode> list22=new java.util.LinkedList();
				list11.add(list1.get(list1.size()-1));
				list22.add(list2.get(list2.size()-1));

				while(list22.size()>0)
				{
					p1=list11.remove((list11.size()-1));
					p2=list22.remove((list22.size()-1));
					if(p1.right!=null&&p2.right!=null&&p1.right.val==p2.right.val)
					{
						list11.add(p1.right);
						list22.add(p2.right);
					}
					else if(p2.right==null)
					{

					}
					else
					{
						flag=1;
						break;//跳出当前循环
					}
					if(p1.left!=null&&p2.left!=null&&p1.left.val==p2.left.val)
					{
						list11.add(p1.left);
						list22.add(p2.left);
					}
					else if(p2.left==null)
					{

					}
					else
					{
						flag=1;
						break;//跳出当前循环
					}

				}
				if(flag==0)
				{return true;}


			}
			{//遍历list1
				TreeNode root=  list1.remove(list1.size()-1);
				if(root.right!=null)
				{list1.add(root.right);}
				if(root.left!=null)
				{list1.add(root.left);}
			}

		}


		return false;
	}
}
class strCompareto implements  Comparator<String>{


	@Override
	public int compare(String o1/*32*/, String o2/*321*/)
	{
		//针对字符串的比较
		String str=o1+o2;//32321
		String str1=o2+o1;//32132
		if(str.compareTo(str1)>0)
		{//str>str1

			return  1;
		}
		else
		{//str<str1
			return -1;
		}
	}
}
public class binary {

	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
 
		TreeNode t = new TreeNode();
		return	search(0,pre.length-1, 0, in.length-1, pre, in,t);	
		 
	    }


	public String PrintMinNumber(int [] numbers)
	{
		List<String>  strs=numToStr(numbers);
		Collections.sort(strs, new strCompareto() {
		});
		String result="";
		for(String str:strs)
		{
			result+=str;



		}
		return result;
	}
	public List<String>  numToStr(int [] numbers)
	{
		List<String>strs=new ArrayList();
		int i=0;
		for(int num:numbers)
		{
			String str="";
			while(num!=0)
			{
				str=num%10+str;
				num/=10;
			}
			strs.add(str);



		}

		return strs;

	}




	 public TreeNode search(int la,int ra,int lb,int rb,int[] pre,int[] in,TreeNode t) {
		 if(la>ra||lb>rb)
			 return null;
			 
	//	TreeNode t= new TreeNode(pre[la]);	 
		 t.val = pre[la];
			 for(int j=lb;j<=rb;j++) {
				if(in[j]==pre[la]) {
					//t.left还没有数值
				t.left=	search(la+1,la+j-lb, lb, j-1, pre, in,t.left);
				t.right=search(j-lb+la+1, ra, j+1, rb, pre, in,t.right);
			
				} 
			 }
		 
		 return t;	 
	 }
	 
	 public void print(TreeNode t) {
		 if(t!=null) {
			 System.out.println("����ڵ�"+t.val);
			 print(t.left);
			 print(t.right);
			 
		 }
	 }

	public static void main3(String[] args) {
		//使用三维数组存放

		String str=new String("dd");
		String str1=new String("dd");
		String str3=new String("dd,ff,gg,dd,ff,jj");
		Double i1=new Double(12345.0);
		Double i2=new Double(12345.0);
		String[] strs=str3.split(",");
		System.out.println(i1.hashCode()+"  "+i2.hashCode());
		System.out.println(str.hashCode()+"  "+str1.hashCode()+"  "+str==str1);
		for(int i=0;i<strs.length;i++)
			System.out.println("strs"+strs[i].hashCode());


	}


	public static void main1(String[]args)

	{
		TreeNode root=Deserialize("8,6,10,5,7,9,11");
		//System.out.println("  "+root.val+"  "+root.left.val+"  "+root.right.val);

		//System.out.println("str  "+KthNode(root, 8));
		ListNode head=new ListNode((int)(Math.random()*20)),p=head;
		for(int i=0;i<8;i++)
		{
			ListNode q=new ListNode((int)(Math.random()*20));
			p.next = q;
			p=q;
		}
		head=insertionSortList(head);
		while(head!=null) {
			System.out.println("head.val "+head.val);
			head=head.next;
		}


		//System.out.println(evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
		//System.out.println("str  "+Serialize( root));

/*
* (0,9),(138,429),(115,359),(115,359),(-30,-102),(230,709),(-150,-686),(-135,-613),(-60,-248),(-161,-481),(207,639),(23,79),(-230,-691),(-115,-341),(92,289),(60,336),(-105,-467),(135,701),(-90,-394),(-184,-551),(150,774)
* */
		//System.out.println(maxPoints1(new Point[]{new Point(0,0),new Point(-1,-1),new Point(2,2)}));
		//RandomListNode.Deserialize("861057911");
//Solution1.test();

	}
	static TreeNode KthNode(TreeNode pRoot, int k)
	{   //二叉排序树的中序便利是有序的啊
		//用栈实现中序号便利啊
		if(pRoot==null)
		{
			return null;
		}
		Stack<TreeNode> nodes= new Stack() ;
		nodes.push(pRoot);
		int num=0;
		while(!nodes.isEmpty())
		{//一个节点的左边子树访问完了才访问这个节点，先一直往左边走到底下
			TreeNode node=nodes.peek();
			while(node!=null&&node.left!=null)
			{
				nodes.push(node.left);
				node=node.left;
			}
			TreeNode nodeLeft=nodes.pop();
			//弹出来空指针
			if(nodeLeft==null)
			{
				if(!nodes.isEmpty()) {
					nodeLeft = nodes.pop();
				}
			}

			if(nodeLeft!=null) {
				System.out.println(nodeLeft.val);
				nodes.push(nodeLeft.right);
				num++;
				if (num == k) {
					return nodeLeft;
				}
			}


		}
		return null;
	}
	/**
	 * Definition for binary tree
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	/*
	*
	* ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	* ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
	* */






	public static int maxPoints1(Point[] points)
	{//可以用双重for循环
		int row=points.length;
		int max=2;
		if(points==null)
		{
			return 0;
		}
		if(points.length<=2)
		{
			return points.length;
		}

		for(int i=0;i<row;i++)
		{
			for(int j=i+1;j<row;j++)
			{//判断points i与points j
				int x=points[j].x-points[i].x;
				int y=points[j].y-points[i].y;
				int num=2;
				for(int k=0;k<row;k++)
				{
					if(k==i||k==j)
					{
						continue;
					}
					int x2=points[k].x-points[i].x;
					int y2=points[k].y-points[i].y;
					if(x==0)
					{
						if(x==x2)
						num++;
						continue;
					}
					if(y==0)
					{
						if(y==y2)
						num++;
						continue;
					}
					if(x*y2==x2*y)
					{
						num++;
						System.out.println(x+" "+y+" "+x2+" "+y2);
					}
				}
				System.out.println(num);
				if(max<num)
				{
					max=num;
				}
			}

		}

		return max;
	}
	public static ListNode sortList(ListNode head)
	{//算法的时间复杂度
		List<ListNode> list=new ArrayList<>();
		while(head!=null)
		{
			list.add(head);
			head=head.next;
		}
		ListNode [] nodes=new ListNode[list.size()];
		System.out.println(list);
		//使用归并排序
		for(int i=0;i<list.size();i++)
		{
			nodes[i]=list.get(i);
		}

		merge(nodes,0,list.size());
		for( int i=0;i<list.size()-1;i++)
		{
			System.out.println(nodes[i].val);
			nodes[i].next = nodes[i + 1];
		}
		nodes[list.size()-1].next=null;
		return nodes[0];
	}
	static void merge(ListNode[] nodes,int left,int right)
	{
		if(right-left<=1)
		{
			return;
		}
		merge(nodes,left,(left+right)/2);
		merge(nodes,(left+right)/2,right);
		int i=left;
		int j=(left+right)/2;
		int k=0;
		ListNode[] nodes1=new ListNode[right-left] ;
		while(i<(left+right)/2&&j<right)
		{
			if(nodes[i].val<=nodes[j].val)
			{
				nodes1[k++]=nodes[i++];
			}
			else
			{
				nodes1[k++]=nodes[j++];
			}

		}
		while(j<right)
			nodes1[k++]=nodes[j++];
		while(i<(left+right)/2)
			nodes1[k++]=nodes[i++];
		i=0;
		while(i<k)
		{

			nodes[left+i]=nodes1[i];i++;
		}
	}
	public static ListNode insertionSortList(ListNode head)
	{
		ListNode newHead=null,p=head;
		while(p!=null)
		{

			if(newHead==null)
			{
				newHead=p;
				p=p.next;
				newHead.next=null;

			}
			else
			{
				ListNode newHead1=newHead;
                if(newHead.val >= p.val)
				{   //记录head的下一个
					newHead1=p;
					p=p.next;
					//
					newHead1.next=newHead;

					newHead=newHead1;

					continue;
				}

				while(newHead1!=null&&newHead1.next!=null)
				{
					if (newHead1.next.val >= p.val) {
						break;
					}
					newHead1 = newHead1.next;
				}
				ListNode q=p.next;
				p.next=newHead1.next;
				newHead1.next=p;
				p=q;
			}
		}

		return newHead;

	}



	public static int evalRPN(String[] tokens)
	{
		if(tokens==null||tokens.length==0)
	    {
		return 0;
		}
		Stack<String> datas=new Stack();
		Stack<String> chars=new Stack();
		String expr = "";
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Object result = null;
		Pattern digit = Pattern.compile("\\d+");
		Pattern digit1 = Pattern.compile("-\\d+");
		datas.push(tokens[0]);
		int i=1;
		while(!chars.isEmpty()|| i<tokens.length)
		{
			if(chars.isEmpty())
			{
				if(digit.matcher(tokens[i]).matches())
				{
					datas.push(tokens[i++]);

				}
				else if(digit1.matcher(tokens[i]).matches())
				{
					datas.push("("+tokens[i++]+")");
				}
				else {
					chars.push(tokens[i++]);
				}
			}
			else
			{
				expr="";
				String d2=datas.pop();;
				String d1=datas.pop();;
				expr+=d1;
				expr+=chars.pop();
				expr+=d2;
				try {
					result = engine.eval(expr);
					double d=Double.valueOf(result.toString());
					if(d<=0.0&&i<tokens.length)
					{
						datas.push("("+(int)d+")");
					}
					else
					{
						datas.push((int)d+"");
					}
					System.out.println("expr  "+ expr+"  "+result);

				} catch (ScriptException e) {
					e.printStackTrace();
				}
			}


		}

double d=Double.valueOf(datas.pop());

		return (int)d;
	}

	public static int evalRPN1(String[] tokens)
	{
		if(tokens==null||tokens.length==0)
		{
			return 0;
		}
		Stack<Double> datas=new Stack();
		Stack<String> chars=new Stack();
		String expr = "";
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Object result = null;
		Pattern digit = Pattern.compile("\\d+");
		Pattern digit1 = Pattern.compile("-\\d+");
		datas.push(Double.valueOf(tokens[0]));
		int i=1;
		while(!chars.isEmpty()|| i<tokens.length)
		{
			if(chars.isEmpty())
			{
					switch(tokens[i])
					{
						case "+":

						case "-":

						case "*":

						case "/":
							chars.push(tokens[i++]);
							break;
						default:
							datas.push(Double.valueOf(tokens[i++]));
							break;



					}

			}
			else
			{
				double d=0;
				Double d2=datas.pop();
				Double d1=datas.pop();
				String str=chars.pop();
			switch(str)
			{
				case "+":
					d=d1+d2;
					break;

				case "-":
					d=d1-d2;
					break;
				case "*":
					d=d1*d2;
					break;
				case "/":
					d=d1/d2;
					break;
				default:
					break;



			}
				datas.push(d);





			}


		}

		double d=Double.valueOf(datas.pop());

		return (int)d;
	}









		public int run(TreeNode root)
		{
			if(root==null)
			{
				return 0;

			}
			/*
			* 采用前序便利啊
			* */
			Stack<TreeNode> nodes=new Stack();
			Stack<Integer> depths=new Stack();
			nodes.push(root);
			depths.push(1);
			int min=99999;
			while(!nodes.isEmpty())
			{
				TreeNode node=nodes.pop();
				int depth=depths.pop();
				if(node.left==null&&node.right==null)
				{
                   if(min>depth)
				   {
					   min=depth;
				   }
				   continue;
				}
				if(node.right!=null)
				{
					nodes.push(node.right);
					depths.push(depth+1);

				}
				if(node.left!=null)
				{
					nodes.push(node.left);
					depths.push(depth+1);

				}



			}







return min;


		}

	static String Serialize(TreeNode root)
	{//相当于变成一个完全二叉树的数组；对于每一个节点，去记录它的左右孩子的情况啊
		String str="";
		if(root==null)
		{
			return "";
		}
		//采用前序便利啊
		List<Integer> list=new ArrayList();
		Map<Integer,Integer> map=new LinkedHashMap();
		Stack <TreeNode> q=new Stack();
		Stack <Integer> indexes=new Stack();
		indexes.push(0);
		q.push(root);
		int index=0,max=0;
		while(!q.isEmpty())
		{
			//出队列
			TreeNode head=q.pop();

			 index=indexes.pop();
			 if(index>max)
			 {
			 	max=index;
			 }
			map.put(index,head.val);

			if(head.right!=null)
			{
				q.push(head.right);
				indexes.push(2*index+2);
			}
			if(head.left!=null)
			{
				q.push(head.left);
				indexes.push(2*index+1);
			}
		}

		String []strs=new String[max+1];
		Set<Integer> set=map.keySet();
		for(Integer val:set)
		{
			strs[val]=String.valueOf(map.get(val));
		}
		int j=0;
		for(j=0;j<strs.length;j++)
		{
			if(strs[j]!=null)
			{
				str+=strs[j]+",";
			}
			else
			{
				str+="#,";
			}
		}

		return str.substring(0,str.length()-1);
	}
	static	TreeNode Deserialize(String str)
	{//恢复
		if(str.length()<=0)
		{
			return null;

		}
		String[] strs=str.split(",");
		int len=strs.length;
		TreeNode[] nodes=new TreeNode[strs.length];
		for(int i=0;i<strs.length;i++)
		{
			if(strs[i].equals("#"))
			{
				nodes[i]=null;
			}
			else
			{
				nodes[i]=new TreeNode(Integer.valueOf(strs[i]));
			}
		}
		int i=0;
		while(i<len/2)
		{
			if(nodes[i]!=null)
			{
				if(2 * i + 1<nodes.length) {
					nodes[i].left = nodes[2 * i + 1];
				}
				if(2 * i + 2<nodes.length) {
					nodes[i].right = nodes[2 * i + 2];
				}
			}
			i++;


		}




		return nodes[0];
	}
}
