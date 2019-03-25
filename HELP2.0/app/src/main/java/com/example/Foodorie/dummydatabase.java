package com.example.Foodorie;

public class dummydatabase
{
    public String[] getData(int year_buff, int month_buff, int day_buff)
    {
        String yearStr = Integer.toString(year_buff), monthStr = Integer.toString(month_buff), dayStr = Integer.toString(day_buff);
        String[] dummyList = { "Date as data placeholder: " + dayStr + " / " + monthStr + " / " + yearStr,
                "placeholder " + (Integer.toString(day_buff - 5)),
                "placeholder " + (Integer.toString(day_buff - 4)),
                "placeholder " + (Integer.toString(day_buff - 3)),
                "placeholder " + (Integer.toString(day_buff - 2)),
                "placeholder " + (Integer.toString(day_buff - 1))};

        return dummyList;
    }
}
