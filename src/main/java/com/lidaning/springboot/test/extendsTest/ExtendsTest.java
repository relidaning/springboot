package com.lidaning.springboot.test.extendsTest;

public class ExtendsTest {

    public static void main(String[] args) {
        D d = new D();
        d.ifoo();
    }
}

class A{
    void foo(){
        System.out.println(" a.foo ");
    }
}

class B extends A{

    void foo(){
        System.out.println(" b.foo ");
    }

}

class C extends A{

    void foo(){
        System.out.println(" c.foo ");
    }

}

interface Ib{
    void ifoo();
}

interface Ic{
    void ifoo();
}

class D extends B implements Ib, Ic{

    @Override
    public void ifoo() {
        System.out.println("d.ifoo");
    }
}
