package com.myself.utils.linux;


import sun.plugin.javascript.navig.LinkArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CmdProcess {
    public static void main(String[] args) {

        readFile();

    }

    public static void readFile(){
        try
        {
            // read file content from file
            StringBuffer sb = new StringBuffer("");

            FileReader reader = new FileReader("C:\\Users\\Lenovo\\Desktop\\dataprovider-1.0\\result.log.2021-03-26.26.log");
            BufferedReader br = new BufferedReader(reader);

            String str = null;
            List<Double> doubleList = new ArrayList<>();
            while ((str = br.readLine()) != null )
            {
                if (str.contains("com.uxsino.bms.io.strategyfilter.DataFilter:queueData()")){
                    String substring = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
                    String m = substring.substring(0, substring.indexOf('m'));
                    double v = Double.parseDouble(m);
                    doubleList.add(v);
                }
            }
            Map<Double, Long> map = doubleList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

            double result = 0;
            double num = 0 ;
            for (Map.Entry<Double, Long> ele : map.entrySet()) {
                result += ele.getKey() * ele.getValue();
                num += ele.getValue();
            }

            System.out.println(result/num);
            br.close();
            reader.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
