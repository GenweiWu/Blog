package com.njust.test.kemu2;

class StaticTestC
{
    public static void main(String[] args)
    {
        FatherC person = new ChildC();
        System.out.println(person.id);
        person.printId();
    }
}

class FatherC
{
    public int id = 34;
    
    public void printId()
    {
        System.out.println(id);
    }
}

class ChildC extends FatherC
{
    public int id = 35;
    
    @Override
    public void printId()
    {
        System.out.println(id);
    }
}
