package com.example.Foodorie;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class foodorieData {

    ArrayList textOutput = new ArrayList();

    public ArrayList getData(int year_buff, int month_buff, int day_buff, Context context_buff)
    {
        File rootDir = context_buff.getFilesDir();
        String path = "foodData/" + Integer.toString(year_buff) + "/" + Integer.toString(month_buff);
        File pathDir = new File(rootDir, path);

        String filename = Integer.toString(day_buff) + ".txt";
        File file = new File(pathDir, filename);

        textOutput.clear();
        if(pathDir.exists() && pathDir.isDirectory())
        {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    textOutput.add(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(textOutput.isEmpty())
        {
            textOutput.add("");
        }

        return textOutput;
    }

    public void saveData(int year_buff, int month_buff, int day_buff, String input_buff, Context context_buff)
    {
        File rootDir = context_buff.getFilesDir();
        String path = "foodData/" + Integer.toString(year_buff) + "/" + Integer.toString(month_buff);
        File pathDir = new File(rootDir, path);

        StringBuilder text = new StringBuilder();
        String input = input_buff + "\n";
        String filename = Integer.toString(day_buff) + ".txt";
        File file = new File(pathDir, filename);
        try
        {
            if(pathDir.exists() && pathDir.isDirectory())
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null)
                {
                    text.append(line);
                    text.append("\n");
                }
                br.close();
            }

            pathDir.mkdirs();
            FileOutputStream filestream = new FileOutputStream(file);
            filestream.write(text.toString().getBytes());
            filestream.write(input.getBytes());
            filestream.close();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
