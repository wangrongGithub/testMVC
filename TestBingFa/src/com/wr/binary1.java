package com.wr;





public class binary1 {
	public int minNumberInRotateArray(int [] array) {
		if(array.length<=0)
			return 0;

		//特点是左边比它大，右边比它大
		for(int i=1;i<array.length;i++)
		{
			if((i+1>=array.length||array[i]<array[i+1])&&array[i]<array[i-1])
			{
				return array[i];

			}

		}
		return array[0];

	}

	public static ListNode ReverseList(ListNode head) {
		ListNode p,q,r;
		if(head==null)
		{
			return null;

		}
		p=head;
		q=head.next;
		r=q;
		p.next=null;
		//System.out.println("输出"+r.val);
		while(r!=null) {

			q=r;
			r=r.next;
			q.next=p;
			p=q;


		//	r=q.next;
			//q.next = p;
		//	p=q;
		//	q=r;
			// System.out.println("输出"+r.val);
		}

		return p;
	}
	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
 
		TreeNode t = new TreeNode();
		return	search(0,pre.length-1, 0, in.length-1, pre, in,t);	
		 
	    }	
	
	 public TreeNode search(int la,int ra,int lb,int rb,int[] pre,int[] in,TreeNode t) {
		 if(la>ra||lb>rb)
			 return null;
			 
	//	TreeNode t= new TreeNode(pre[la]);	 
		 t.val = pre[la];
		 TreeNode left=new TreeNode();
		 TreeNode right=new TreeNode();
		 /*
		 * 直接给左右节点new
		 * */
		 t.left=left;
		 t.right=right;
			 for(int j=lb;j<=rb;j++) {
				if(in[j]==pre[la]) {
					//t.left肯定是null

				t.left=	search(la+1,la+j-lb, lb, j-1, pre, in,left);
				t.right=search(j-lb+la+1, ra, j+1, rb, pre, in,right);
			
				} 
			 }
		 
		 return t;	 
	 }
	 
	 public void print(TreeNode t) {
		 if(t!=null) {
			 System.out.println("节点"+t.val);
			 print(t.left);
			 print(t.right);
			 
		 }
	 }

public static void main(String[] args) {
	binary1 b = new binary1();
	int[] pre= {1,2,4,7,3,5,6,8};
	int[] in = {4,7,2,1,5,3,8,6};
	
	TreeNode t= b.reConstructBinaryTree(pre,in);
	 b.print(t);
	
	
}

}
