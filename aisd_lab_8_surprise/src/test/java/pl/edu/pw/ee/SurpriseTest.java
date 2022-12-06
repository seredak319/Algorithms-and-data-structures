package pl.edu.pw.ee;

import org.junit.Test;

public class SurpriseTest {
    
    @Test
    public void test(){
        //given
        Surprise s = new Surprise();
        int [] plansza = {-3,1,0,7,-1,-10,0,1,3,10,-10,-20,0,-1,-1,3,7,2,-8,8,0,0,1,1,0,-4,6,5};
        
        //when
        int res = s.countMaxSum(plansza);
        
        //then
        System.out.println(res);
        assert true;
        
    }
    
        @Test
    public void test2(){
        //given
        Surprise s = new Surprise();
        int [] plansza = {3,-2,-1,-1,-1,-2,-4,-10,-10,-10,-8,10,3,-2,-1,-1,-1,-2,-4,-10,-10,-10,-8,10};
        
        //when
        int res = s.countMaxSum(plansza);
        
        //then
        System.out.println(res);
        assert true;
        
    }
    
    

}
