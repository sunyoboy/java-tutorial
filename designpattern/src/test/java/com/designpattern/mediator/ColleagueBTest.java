package com.designpattern.mediator;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* ColleagueB Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 11, 2016</pre> 
* @version 1.0 
*/ 
public class ColleagueBTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: doSomething() 
* 
*/ 
@Test
public void testDoSomething() throws Exception { 
//TODO: Test goes here...
    IMediator mediator = new ConcreteMediator();

    ColleagueA talkColleague = new ColleagueA(mediator);

    ColleagueB fightColleague = new ColleagueB(mediator);

    talkColleague.doSomething();

    fightColleague.doSomething();
} 


} 
