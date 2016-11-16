/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: CTest.java
 * Author:   DD240
 * Date:     2015-11-9 下午6:52:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.junit.tdd;


import org.junit.Assert;
import org.junit.Test;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class DTest {
    
/*    @Test
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        Assert.assertEquals(10, product.amount);
        product = five.times(3);
        Assert.assertEquals(15, product.amount);
    }*/
    
    /*    @Test
    public void testMultiplication1() {
        Dollar five = new Dollar(5);
        five.times(2);
        Assert.assertEquals(10, five.amount);
    }
    */
    
/*    @Test
    public void testEquality() {
        Assert.assertTrue(new Dollar(5).equals(new Dollar(5)));
        Assert.assertFalse(new Dollar(5).equals(new Dollar(6)));
        Assert.assertTrue(new Franc(5).equals(new Franc(6)));
        Assert.assertFalse(new Franc(5).equals(new Franc(6)));
        Assert.assertFalse(new Franc(5).equals(new Dollar(5)));
    }*/

    @Test
    public void testEquality() {
        Assert.assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        Assert.assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        Assert.assertTrue(Money.franc(5).equals(Money.franc(6)));
        Assert.assertFalse(Money.franc(5).equals(Money.franc(6)));
        Assert.assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }    
    
    @Test
    public void testFrancMultiplication() {
/*        Franc five = new Franc(5);
        // Dollar product = five.times(2);
        Assert.assertEquals(new Franc(10), five.times(2));
        // product = five.times(3);
        Assert.assertEquals(new Franc(15), five.times(3));*/
    }        

/*    @Test
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        // Dollar product = five.times(2);
        Assert.assertEquals(new Dollar(10), five.times(2));
        // product = five.times(3);
        Assert.assertEquals(new Dollar(15), five.times(3));
    }
    */

    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        // Dollar product = five.times(2);
        Assert.assertEquals(Money.dollar(10), five.times(2));
        // product = five.times(3);
        Assert.assertEquals(Money.dollar(15), five.times(3));
    }
    
    @Test
    public void testCurrency() {
        Assert.assertEquals("USD", Money.dollar(1).currency());
        Assert.assertEquals("CHF", Money.franc(1).currency());
    }
        
    
/*@Test
    public void testMultiplication3() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        Assert.assertEquals(new Dollar(10), product);
        product = five.times(3);
        Assert.assertEquals(new Dollar(15), product);
    }*/

   

}

abstract class Money {
    protected int amount;
    protected String currency;
    
    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount && getClass().equals(money.getClass());
        // return true;
    }
    
    static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }
    
    static Money franc(int amount) {
        // return new Franc(amount);
        return new Franc(amount, "CHF");
    }
    
    abstract Money times(int mulitplier);
    // abstract String currency();
    String currency() {
        return currency;
    }
    
    public String toString() {
        return amount + "" + currency;
    }
} 

class Dollar extends Money {
    // v1 int amount = 10;
    // private int amount;
/*    private String currency;
    String currency() {
        return "USD";
    }*/
    
    
    Dollar(int amount, String currency) {
        super(amount, currency);
        /*this.amount = amount;
        currency = "USD";*/
    }
    
    
    
/*        public boolean equals(Object object) {
        Dollar dollar = (Dollar) object;
        return amount == dollar.amount;
        // return true;
    }*/
    
/*    public void times(int multiplier) {
        amount *= multiplier;
    }*/
    
    public Money times(int multiplier) {
        return new Dollar(amount * multiplier, currency);
        // return new Dollar(amount * multiplier, "USD");
        // return Money.dollar(amount * multiplier);
        // return new Dollar(amount * multiplier);
        // return null;
    }
    

    
}

class Franc extends Money {
    // v1 int amount = 10;
    // private int amount;
    
/*    private String currency;
    String currency() {
        return "CHF";
    }*/
    
/*    Franc(int amount) {
        this.amount = amount;
        this.currency = "CHF";
    }*/
    
    Franc(int amount, String currency) {
        super(amount, currency);
        /*this.amount = amount;
        this.currency = "CHF";*/
    }
    
/*        public boolean equals(Object object) {
        Franc dollar = (Franc) object;
        return amount == dollar.amount;
        // return true;
    }*/
    
/*    public void times(int multiplier) {
        amount *= multiplier;
    }*/
    
    @Override
    public Money times(int multiplier) {
        // return Money.franc(amount * multiplier);
        // return new Franc(amount * multiplier, "CHF");
        return new Franc(amount * multiplier, currency);
        // return null;
    }    

    

}


