package com.lidaning.springboot.test.stringtest;

public class Stringtest {

    public static void main(String[] args) {
        String a = "aaa";
        String b = "bbb";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        a = b;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        final int[] arrays = {1, 2, 3};
        System.out.println(arrays.hashCode());
        arrays[2] = 4;
        System.out.println(arrays.hashCode());

        String t = new String("aaa");
        String newt = Stringtest.appendStr(t);
        System.out.println(t);

        StringBuffer tb = new StringBuffer("aaa");
        StringBuffer newtb = Stringtest.appendSb(tb);
        System.out.println(tb);

        FClass f = new FClass();
        f.setAge(18);
        FClass nf = Stringtest.newFc(f);
        System.out.println(f);

        System.out.println();
        t="bbb";
        System.out.println(t=="bbb");

    }

    public static String appendStr(String a){
        a+="bbb";
        return a;
    }

    public static StringBuffer appendSb(StringBuffer a){
        return a.append("bbb");
    }

    public static FClass newFc(FClass a){
        a.setAge(9);
        return a;
    }
}

final class FClass{
    private int age;
    public void setAge(int age){
        this.age = age;
    }
    public String toString(){
        return String.valueOf(age);
    }
}
