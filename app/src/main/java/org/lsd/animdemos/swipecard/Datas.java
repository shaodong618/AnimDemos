package org.lsd.animdemos.swipecard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaodongPC on 2017/1/25.
 */

public class Datas
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static final List<Datas> initData()
    {
        List<Datas> result = new ArrayList<>();
        int i = 10;
        while (i > 0)
        {
            Datas data = new Datas();
            data.setName(String.valueOf(i));
            result.add(data);
            i--;
        }
        return result;
    }
}
