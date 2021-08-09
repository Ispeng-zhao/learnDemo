package com.myself.utils.linux;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CmdProcess {
    public static void main(String[] args) throws IOException {

        setText("2222","C:\\Users\\Lenovo\\Desktop\\dataprovider-1.0\\dataprovider-1.0\\btr_process.conf");

    }
    private static void setText(String ens,String path) {//修改
        String str = "";
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该行前面的内容
            while ( (str = br.readLine()) != null) {

                if (str.contains("pktminerr") && str.contains("command")){
                    str = str.replaceAll("\\s{1,}", " ");
                    String[] s1 = str.split(" ");
                    String ens1 = s1[4];
                    String ens0 = s1[2];
                    ens0 = ens0.replace(ens1, ens);
                    String str1 = s1[0] + " " + s1[1] + " " + ens0  + " " + s1[3] + " " + ens + " " + s1[5]+ " " + s1[6]+ " " + s1[7]+ " " + s1[8]+ " " + s1[9]+ " " + s1[10]+ " " + s1[11]+ " " + s1[12]+ " " + s1[13];
                    if (str1.length() < str.length()){
                        for (int i = 0; i < str.length() - str1.length(); i++) {
                            str1  = str1 + " ";
                        }
                    }
                    buf = buf.append(str1);
                }else{
                    buf = buf.append(str);
                }

                buf = buf.append(System.getProperty("line.separator"));
            }

            br.close();
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void readSnoopFile(String ens) throws IOException {
        RandomAccessFile file =  new RandomAccessFile("C:\\Users\\Lenovo\\Desktop\\dataprovider-1.0\\dataprovider-1.0\\btr_process.conf","rw");
        String s = file.readLine();
        String str = null;
        // 记住上一次的偏移量
        long lastPoint = 0;
        while ((str = file.readLine()) != null ){
            long pointer = file.getFilePointer();
            if (str.contains("pktminerr") && str.contains("command")){
                file.seek(lastPoint);
                str = str.replaceAll("\\s{1,}", " ");
                String[] s1 = str.split(" ");
                String ens1 = s1[4];
                String ens0 = s1[2];
                ens0 = ens0.replace(ens1, ens);
                String str1 = s1[0] + " " + s1[1] + " " + ens0  + " " + s1[3] + " " + ens + " " + s1[5]+ " " + s1[6]+ " " + s1[7]+ " " + s1[8]+ " " + s1[9]+ " " + s1[10]+ " " + s1[11]+ " " + s1[12]+ " " + s1[13];
                if (str1.length() < str.length()){
                    for (int i = 0; i < str.length() - str1.length(); i++) {
                        str1  = str1 + " ";
                    }
                }
                file.writeBytes(str1);
            }
            lastPoint = pointer;
        }
        file.close();

        System.out.println("");
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
