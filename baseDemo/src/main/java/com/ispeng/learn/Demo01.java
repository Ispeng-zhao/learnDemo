package com.ispeng.learn;

public class Demo01 {

    public static void main(String[] args) {
        Student student = new Student();

        String name = student.getName() == null ? "" : student.getName();
        String name2 = student.getName() == null ? "" : student.getName();
        String name3 = student.getName() == null ? "" : student.getName();
        String name4 = student.getName() == null ? "" : student.getName();
        String name5 = student.getName() == null ? "" : student.getName();
        String name6 = student.getName() == null ? "" : student.getName();


    }

    public void St(String st){
        if (st.equals("")){
            System.out.println("");
        }
    }
}
