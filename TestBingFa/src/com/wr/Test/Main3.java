package com.wr.Test;
public class Main3 {

    public static void main(String[] args)
    {
        int m=5,n=13;
        int [][]arr=new int[m+1][n+1];
        for(int i=1;i<m+1;i++)
        {
            for(int j=i;j<n+1;j++)
            {
                int bottom=1>(j-6*(i-1))?1:(j-6*(i-1));
                int top=6<(j-i+1)?6:(j-i+1);
                if(i==1&&bottom<=top)
                {
                    arr[i][j]=1;
                    continue;
                }
                if(i==j)
                {
                    arr[i][j]=1;
                    continue;
                }

                for(int k=j-bottom;k>=j-top;k--)
                {
                    arr[i][j]+=arr[i-1][k];
                }
            }
        }
        for(int i=1;i<m+1;i++)
        {
            for(int j=1;j<n+1;j++)
            {
                System.out.print(arr[i][j]+"     ");
            }
            System.out.println();
        }

    }
}
