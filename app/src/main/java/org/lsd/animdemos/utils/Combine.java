package org.lsd.animdemos.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 取组合数据
 * 例如：[1, 2, 3]
 * 取两个结果是：12, 13, 23
 *
 * 参考： http://blog.csdn.net/fanfan199312/article/details/62422244
 */

public class Combine<T>
{
    private List<T> tmpArr = new ArrayList<>();
    private List<List<T>> result = new ArrayList<>();

    //    public static void main(String[] args) {
    //        TestCombine<String> t = new TestCombine<String>();
    //        String [] com = {"1","2","3","4", "5", "6"};
    //        int k = 3;
    //        if(k > com.length || com.length <= 0){
    //            return ;
    //        }
    //        t.combine(0 ,k ,Arrays.asList(com));
    //        System.out.println(t.result.toString());
    //    }

    public List<List<T>> getResult()
    {
        return result;
    }

    public void combine(int index, int k, List<T> aar)
    {
        if (k == 1)
        {
            for (int i = index; i < aar.size(); i++)
            {
                tmpArr.add(aar.get(i));
                List<T> tmp = new ArrayList<>();
                for (T t : tmpArr)
                {
                    tmp.add(t);
                }
                result.add(tmp);
                tmpArr.remove(aar.get(i));
            }
        }
        else if (k > 1)
        {
            for (int i = index; i <= aar.size() - k; i++)
            {
                tmpArr.add(aar.get(i));
                combine(i + 1, k - 1, aar);
                tmpArr.remove(aar.get(i));
            }
        }
        else
        {
            return;
        }
    }
}
