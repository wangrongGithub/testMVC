package cn.wr.SpringBoot;
import java.util.*;

public class Main
{
    public static void main(String[]args)
    {
        int[] nums1=new int[]{1,2,6,7};
        int[] nums2=new int[]{3,4,5,8};
        double d=findMedianSortedArrays2(nums1,nums2);
        System.out.println(d);
    }
    public static double  findMedianSortedArrays2(int[] A,int B[])
    {
        //1.必须满足i+j=m+n-i-j||m+n-i-j+1;前面的始终比后面的大1
        //2.A[i-1]<B[j];A[i]>B[j-1]; 保证右边部分的比左边的元素大;
        //首先保证左右两边的分配均匀的问题啊
        int m=A.length,n=B.length;
        if(m>n)//保证搜索的范围比较小的啊
        {//是偶数啊
            int []C=null;
            C=A;
            A=B;
            B=C;
            m=A.length;
            n=B.length;
        }
        int i=0,j=0;
        if(m%2!=0&&n%2!=0)
        {//两个都是偶数啊;让m多一个,n少一个啊
            i=m/2;
            j=n/2+1;
        }
        else
        {//两个都是偶数啊
            i=m/2;
            j=n/2;
        }
        //在i中进行搜索的啊；0-->i-1;i---->m-1;i是比较小的那个长度啊
        while(i>=0&&i<m)
        {
            //说明A[i]比较小的啊j--;
            if(B[j-1]>A[i])
            {//i++时候;j--
                i++;
                j--;
            }
            else if(B[j] <  A[i-1]) //说明A[i]比较大的啊j--;
            {
                i--;
                j++;
            }
            else {//进行返回的啊
                if ((m + n) % 2 == 0)//说明是偶数
                {//左边最大；右边最小啊
                    int leftMax = A[i - 1] > B[j - 1] ? A[i - 1] : B[j - 1];
                    int rightMin = A[i] < B[j] ? A[i] : B[j];
                    return (leftMax + rightMin) / 2.0;

                }
                else//说明是
                {
                    //返回右边的最小数值
                    return A[i] < B[j] ? A[i] : B[j];

                }



            }


        }

       return 0.0;

    }



    //寻找刚好去掉左边多少个元素，去掉右边多少个元素
    public static double  findMedianSortedArrays(int[] A,int B[]) {
        int m=A.length;
        int n=B.length;
        if(m>n) {//保证m<n 意思就是B的长度大于等于A的长度
            int[] temp=A;A=B; B=temp;
            int tmp=m;m=n;n=tmp;
        }
        int iMin=0,iMax=m,halfLen=(n+m+1)/2;
        while(iMin<=iMax) {
            int i=(iMin+iMax)/2;
            int j=halfLen-i;
            if(i<iMax&&B[j-1]>A[i]) {
                iMin=iMin+1;
            }
            else if(i>iMin&&A[i-1]>B[j]) {
                iMax=iMax-1;
            }
            else {
                int maxLeft=0;
                if(i==0) {maxLeft=B[j-1];}
                else if(j==0) {maxLeft=A[i-1];}
                else {maxLeft=Math.max(A[i-1], B[j-1]);}
                if((m+n)%2==1) {return maxLeft;}
                int minRight=0;
                if(i==m) {minRight=B[j];
                }else if(j==n) {minRight=A[i];}
                else {minRight=Math.min(B[j], A[i]);}
                return (maxLeft+minRight)/2.0;
            }
        }
        return 0.0;
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2)
    {
        int left1=0,left2=0,right1=nums1.length-1,right2=nums2.length-1;
        int mid1=0,mid2=0;
        if(nums1==null)
        {
            int len=nums2.length;
            if(nums2.length%2==0)
            {
                return (nums2[len/2]+nums2[len/2-1])/2;
            }
        }
        if(nums2==null)
        {
            int len=nums1.length;
            if(nums1.length%2==0)
            {
                return (nums1[len/2]+nums1[len/2-1])/2;
            }
        }

        if(nums1.length!=0&&nums2.length==1)
        {
            int index1=findIndex(nums1,left1,right1,nums2[0]);
            int leftIndex=(nums1.length+nums2.length-1)/2;
            int rightIndex=(nums1.length+nums2.length)/2;
            int B[]=new int[nums1.length+nums2.length];
            int flag=0;
            for(int i=0;i<nums1.length;i++)
            {
                if(flag==0 && nums2[0]< nums1[i])
                {
                    flag=1;
                    B[i]=nums2[0];
                    i--;

                }
                else if(flag==0 && nums2[0]>= nums1[i])
                {
                    B[i]=nums1[i];
                }
                else
                {
                    B[i+1]=nums1[i];

                }
            }
            if(flag==0)
            {
                B[B.length-1]=nums2[0];
            }
            return ((double)B[leftIndex]+B[rightIndex])/2;
        }
        if(nums2.length!=0&&nums1.length==1)
        {
            int index1=findIndex(nums2,left2,right2,nums1[0]);
            int leftIndex=(nums1.length+nums2.length-1)/2;
            int rightIndex=(nums1.length+nums2.length)/2;
            int B[]=new int[nums1.length+nums2.length];
            int flag=0;
            for(int i=0;i<nums2.length;i++)
            {
                if(flag==0 && nums1[0]< nums2[i])
                {
                    flag=1;
                    B[i]=nums1[0];
                    i--;

                }
                else if(flag==0 && nums1[0]>= nums2[i])
                {
                    B[i]=nums2[i];
                }
                else
                {
                    B[i+1]=nums2[i];

                }
            }
            if(flag==0)
            {
                B[B.length-1]=nums1[0];
            }

            return ((double)B[leftIndex]+B[rightIndex])/2;

        }



        if(nums1.length!=0&&nums2.length==0)
        {
            int rightIndex=nums1.length/2;
            int leftIndex=(nums1.length-1)/2;
            return ((double) nums1[rightIndex]+nums1[leftIndex])/2;
        }
        if(nums2.length!=0&&nums1.length==0)
        {
            int rightIndex=nums2.length/2;
            int leftIndex=(nums2.length-1)/2;
            return ((double) nums2[rightIndex]+nums2[leftIndex])/2;
        }
        int len1=right1-left1+1;
        int len2=right2-left2+1;
        if(len1==1&&len2==1)
        {
            return ((double) nums1[left1]+nums2[left2])/2;
        }
        //左边去掉的个数啊
        int leftOutMax=(len1+len2-1)/2;
        int rightOutMax=(len1+len2-1)/2;
        int leftOut=0;//记录左边扔掉了多少个啊
        int rightOut=0;//记录左边扔掉了多少个啊
        //假设所有的区间都是左边闭合右边开启的;总会有一个数组先一步走到1的啊
        while(len1>2&&len2>2)
        {
            //小的部分扔左边啊
            mid1=(left1+right1)/2;mid2=(left2+right2)/2;
            if(nums1[mid1]>nums2[mid2])
            {//扔掉的数字是左边的
                leftOut+=mid2-left2;
                rightOut+=right1-mid1;
                right1=mid1;
                left2=mid2;
                //左边去掉的啊
            }
            else
            {//扔掉的数字是左边的
                leftOut+=mid1-left1;
                rightOut+=right2-mid2;
                right2=mid2;
                left1=mid1;
            }
            len1=right1-left1+1;
            len2=right2-left2+1;
            System.out.println(len1+"*******"+len2);
            System.out.println(left1+"*******"+right1);
            System.out.println(left2+"*******"+right2);
        }
        System.out.println(len1+"-----------"+len2);
        System.out.println(left1+"-----------"+right1);
        System.out.println(left2+"----------"+right2);
        System.out.println(leftOut+"----------"+rightOut);
        System.out.println(leftOutMax+"----------"+rightOutMax);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        int[]nums=null;
        leftOutMax=leftOutMax-leftOut;
        rightOutMax=rightOutMax-rightOut;
        if(len1==2)
        {//在2数组中给一号数组找插入位置啊
            int index1=findIndex(nums2, left2,right2,nums1[left1]);
            int index2=findIndex(nums2, left2,right2,nums1[right1]);

            return findData(nums2,left2,right2,leftOutMax,rightOutMax,index1,index2,nums1[left1],nums1[right1]);
        }
        else
        {
            //返回插入位置啊
            int index1=findIndex(nums1, left1,right1,nums2[left2]);
            int index2=findIndex(nums1, left1,right1,nums2[right2]);
            System.out.println(index1+" 88888888888 "+index2);
            return findData(nums1,left1,right1,leftOutMax,rightOutMax,index1,index2,nums2[left2],nums2[right2]);
        }

    }
    public static double findData(int[]A,int left,int right,int leftOut,int rightOut,int index1,int index2,int key1,int key2)
    {
        //先把index1加上啊
        //index1一定小于index2啊
        int leftGap=index1-left;//表示的是index1的左边有多少个数字啊
        int rightGap=right-index2+1;//表示的是index2的右边有多少个数字啊
        int leftData=0;
        int rightData=0;
        System.out.println(leftGap+"///"+rightGap+"///"+leftOut+"//"+rightOut);
        if(leftOut>leftGap)//左边应该扔掉的数字个数啊
        {//判断一下right的啊
            int leftIndex=left+leftOut-1;//将left1加入进来啊
            //刚好那个位置到index2d的位置
            if(index2==leftIndex)
            {
                leftData=key2;
            }
            //key1和key2都在左边啊
            else if(index2<leftIndex)
            {
                leftData=A[left+leftOut-2];
            }
            else
            {
                leftData=A[left+leftOut-1];
            }

        }
        else if(leftOut<leftGap)
        {
            leftData=A[left+leftOut];
        }
        else
        {
            leftData=key1;
        }

        System.out.println(leftData);
        if(rightOut>rightGap)
        {
            //刚好那个位置到index1的位置
            int rightIndex=right-rightOut+1;
            if(rightIndex+1==index1)
            {
                rightData=key1;
            }
            else if(rightIndex+1<index1)
            {
                rightData=A[right-rightOut+2];
            }
            else
            {
                rightData=A[right-rightOut+1];
            }
        }
        else if(rightOut<rightGap)
        {
            rightData=A[right-rightOut];
        }
        else
        {
            rightData=key2;
        }
        return ((double) leftData+rightData)/2;
    }


   public static int findIndex(int []A,int low,int high,int key)
    {
        for(int i=low;i<=high;i++)
        {
            if(key<A[i])
            {
                return i;//插入到i位置啊
            }

        }
        return high+1;//插入到high位置啊
    }

    public static void main5(String[]args)
    {
        String str=new Scanner(System.in).nextLine();
        if(str.length()>12||str.length()<4)
        {
            System.out.println("");
            return ;
        }
        List<String> list=digui(str,4);
        System.out.println(list);

    }
    static List<String> digui(String ips,int cishu)
    {
        List<String> list=new ArrayList<String>();
        if(cishu==1)
        {
            if(ips.length()<=3&&ips.length()>0)
            {
                int value = Integer.valueOf(ips);
                if (value <=255) {
                    list.add("" + value);
                }
            }

            return list;
        }
        for(int i=1;i<=3;i++)
        {
            if(ips.length()<i)
            {
                break;
            }
            int value=Integer.valueOf(ips.substring(0,i));
            if(value<=255)
            {
                List<String> subResult=digui(ips.substring(i,ips.length()),cishu-1);
                for(String str:subResult)
                {
                    list.add(value+"."+str);
                }
            }
        }
        return list;
    }



    public static void main4(String[]args)
    {
        Scanner scanner=new Scanner(System.in);
        int m=Integer.valueOf(scanner.nextLine());
        String[]subStrs=new String[m];
        Map<String,Integer>subStrMap=new HashMap<String, Integer>();
        Set<Integer>set=new TreeSet();
        int minLen=Integer.MAX_VALUE;
        for(int i=0;i<m;i++)
        {
            subStrs[i]=scanner.nextLine();//会读进去一个换行
            int len=subStrs[i].length();
            subStrMap.put(subStrs[i],i);
            set.add(len);
            if(len<minLen)
            {
                minLen=len;
            }
        }
        System.out.println(set);
        String str=scanner.nextLine();
        int []dp=new int[str.length()+1];
        int [][]flags=new int[str.length()+1][m];
        //flag是1表示放进去了啊

        for(int i=minLen;i<=str.length();i++)
        {
            dp[i]=dp[i-1];
            for(int j:set)
            {
                if(j>i)
                {
                    continue;
                }
                if(dp[i]<dp[i-j]+1  &&  subStrMap.get(str.substring(i-j,i))!=null)
                {
                    dp[i]=dp[i-j]+1;
                }
            }
        }
        System.out.println(dp[str.length()]);
    }









    public static void main1(String[]args)
    {
        int[] A=new int[]{3,2,7,4,5,10,8,9};
        TarverseMid(createTree( A));
        List<List<Boolean>>  list=last(createTree( A));
        Rest r=new Rest();
        getHigh(createTree( A), r);
        System.out.println(r.max);
    }
   static class Rest{
        int high;
        int max;
    }
    public static int  getHigh(TreeNode root,Rest r)
   {
        if(root==null)
        {
            return 0;
        }
        int left=getHigh( root.left, r);
        int right=getHigh( root.right, r);
        r.high=left+1>right+1?left+1:right+1;
        if(r.max<left+right)
        {
            r.max=left+right;
        }
        return  r.high;
}

    public static List<List<Boolean>> last(TreeNode root)
    {
        List<List<Boolean>> result=new ArrayList<List<Boolean>>();

        Stack<TreeNode>stack=new Stack<TreeNode>();
        Stack<Integer>flags=new Stack<Integer>();
        stack.push(root);
        flags.push(0);
        while(!stack.isEmpty())
        {
            TreeNode top=stack.peek();
            int flag=flags.peek();
            if(flag==0)
            {
                if(top.left!=null)
                {
                    stack.push(top.left);
                    flags.pop();
                    flags.push(flag+1);
                    flags.push(0);
                }
                else
                {
                    flags.pop();
                    flags.push(flag+1);
                }

            }
            else if(flag==1)
            {
                if(top.right!=null)
                {
                    stack.push(top.right);
                    flags.pop();
                    flags.push(flag+1);
                    flags.push(0);
                }
                else
                {
                    flags.pop();
                    flags.push(flag+1);
                }
            }
            else
            {
               //进行访问啊 stack.get()
                if(top.left==null&&top.right==null)
                {
                    List<Boolean> rst = new ArrayList<Boolean>();
                    int size = stack.size();
                    rst.add(false);//表示的是最远的啊
                    for (int i = 1; i < size; i++) {
                        //true表示右边，false表示左边
                        if (stack.get(i-1).left==stack.get(i))
                        {
                            rst.add(false);
                        }
                        else
                        {
                            rst.add(true);
                        }

                    }
                    result.add(rst);
                }
                stack.pop();
                flags.pop();


            }
        }

return result;
    }

    public static void TarverseMid(TreeNode root)
    {
        if(root==null)
        {
            return;
        }
        TarverseMid(root.left);
        System.out.println(root.val);
        TarverseMid(root.right);
    }
    public static TreeNode createTree(int[] A)
    {//1-N
        int len=A.length;
        TreeNode root=null,p=null;
        for(int i=0;i<len;i++)
        {
            if(root==null)
            {
                root=new TreeNode(A[i]);
            }
            else
            {//寻找插入位置并且插入
                p=root;
                while(true)
                {
                    if(A[i]>p.val)
                    {
                        if(p.right==null)
                        {
                            p.right=new TreeNode(A[i]);
                            break;
                        }
                        p=p.right;
                    }
                    else
                    {
                        if(p.left==null)
                        {
                            p.left=new TreeNode(A[i]);
                            break;
                        }
                        p=p.left;
                    }

                }

            }

        }

     return root;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val)
    {
        this.val=val;
    }
}
