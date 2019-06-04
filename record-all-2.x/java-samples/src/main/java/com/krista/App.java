package com.krista;

/**
 * Hello world!
 * @author wangdonghong
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Long a = 1234L;
        Long b = 1234L;
        // false
        System.out.println(a == b);
        // true
        System.out.println(a.equals(b));
    }
}
