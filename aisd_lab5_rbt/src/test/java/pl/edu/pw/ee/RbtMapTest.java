package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RbtMapTest {
// TODO

    @Test
    public void put_Should_CorrectlyPutAFewElements_AscendingOrder(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.setValue(new Integer(1).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(2).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(3).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(4).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(5).hashCode(), new Integer(1));

    }

    @Test
    public void put_Should_CorrectlyPutAFewElements_DescendingOrder(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.setValue(new Integer(5).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(4).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(3).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(2).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(1).hashCode(), new Integer(1));

    }

    @Test
    public void put_Should_CorrectlyPutAFewElements_TheSameElement(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.setValue(new Integer(5).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(5).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(5).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(5).hashCode(), new Integer(1));
        rbtMap.setValue(new Integer(5).hashCode(), new Integer(1));

    }

    @Test (expected = IllegalArgumentException.class)
    public void put_Should_CatchException_NullValue(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.setValue(new Integer(5).hashCode(),null);

        //then
        assert false;
    }

    @Test (expected = IllegalArgumentException.class)
    public void put_Should_CatchException_NullKey(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.setValue(null,new Integer(5).hashCode());

        //then
        assert false;
    }

    @Test (expected = IllegalArgumentException.class)
    public void get_Should_CatchException_NullKey(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.getValue(null);

        //then
        assert false;
    }

    @Test
    public void put_ShouldCorrectly_RotateLeft(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.setValue(3,3);
        rbtMap.setValue(4,4);
        rbtMap.setValue(1,1);
        rbtMap.setValue(2,2);

        //then
//        System.out.println(TestSupportiveClass.getLeftRotate(3));
//        System.out.println(TestSupportiveClass.getLeftRotate(4));
//        System.out.println(TestSupportiveClass.getLeftRotate(1));
//        System.out.println(TestSupportiveClass.getLeftRotate(2));
    }
    
    @Test
    public void get_ShouldCorrectlyGetAFewValues(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();

        //when
        rbtMap.setValue(3,3);
        rbtMap.setValue(4,4);
        rbtMap.setValue(1,1);
        rbtMap.setValue(2,2);
        
        //then
        assertEquals(rbtMap.getValue(3), 3,0.1);
        assertEquals(rbtMap.getValue(3), 3,0.1);
        assertEquals(rbtMap.getValue(1), 1,0.1);
        assertEquals(rbtMap.getValue(4), 4,0.1);
    }
    
    @Test
    public void put_get_ShouldCorrecltyPutAndGet_MoreElementsInOrder(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();
        for( int i =0; i< 100; i++){
            rbtMap.setValue(i,i);
        }
        
        //when
         for( int i =0; i< 100; i++){
            rbtMap.getValue(i);
        }
    }
    
    @Test
    public void put_get_ShouldCorrecltyPutAndGet_MoreElementsNotInOrder(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();
        for( int i =100; i>=1; i--){
            rbtMap.setValue(i,i);
        }
        
        //when
        for( int i =100; i>=1; i--){
            rbtMap.setValue(i,i);
        }
    }
    
        @Test
    public void put_get_ShouldCorrecltyPutAndGet_MoreElementsInRandomOrder(){
        //given
        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();
        for( int i =100; i>=1; i--){
            rbtMap.setValue(i,i);
        }
        
        //when
        for( int i =100; i>=1; i--){
            rbtMap.setValue(i,i);
        }
    }
    
//    @Test
//    public void put_NullValue(){
//        //given
//        RbtMap<Integer,Integer> rbtMap = new RbtMap<>();
//        
//        //then
//        rbtMap.getValue(null);
//    }


}


