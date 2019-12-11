package Core;

import java.util.List;

public class CoreComputing {
    public static double getAvg(List<String> list, int begin, int end)
    {//[,)
        int i=begin;
        Double sum=0.0;
        for(i=begin;i<end;i++)
        {
            String str=list.get(i);
           Double d=Double.valueOf(str);
            sum+=d*10;

        }
        return sum/10/(end-begin);

    }
    public static double getMax(List<String> list, int begin, int end)
    {//[,)
        int i=begin;
        Double Max=Double.valueOf(list.get(0));
        for(i=begin;i<end;i++)
        {
            String str=list.get(i);
            Double d=Double.valueOf(str);
            if(Max<d)
            {
                Max=d;
            }

        }
        return Max;

    }
    public static double getMin(List<String> list, int begin, int end)
    {//[,)
        int i=begin;
        Double Min=Double.valueOf(list.get(0));
        for(i=begin;i<end;i++)
        {
            String str=list.get(i);
            Double d=Double.valueOf(str);
            if(Min>d)
            {
                Min=d;
            }

        }
        return Min;

    }
    public static double getStandardDeviation(List<String> list, int begin, int end,Double avg)
    {//[,)
        int i=begin;
        Double sum=0.0;
        for(i=begin;i<end;i++)
        {
            String str=list.get(i);
            Double d=Double.valueOf(str);
         //   sum+=d*10;
            sum+=(d-avg)*(d-avg);
        }
        return Math.sqrt(sum/(end-begin));
    }


}
