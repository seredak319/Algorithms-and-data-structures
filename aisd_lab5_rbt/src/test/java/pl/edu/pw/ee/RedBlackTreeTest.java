package pl.edu.pw.ee;

import static jdk.internal.org.jline.utils.Colors.s;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class RedBlackTreeTest {
	// TODO
    
    
    @Test
    public void rbt_GetInOrder(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //when
        rbt.put(1, 1);
        rbt.put(2, 2);
        rbt.put(6, 6);
        rbt.put(7, 7);
        rbt.put(5, 5);
        
        //then
        if(rbt.getInOrder() == null){
            assert false;
        }
        System.out.println(rbt.getInOrder());
    }
    
        
    @Test
    public void rbt_GetPreOrder(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //when
        rbt.put(1, 1);
        rbt.put(2, 2);
        rbt.put(6, 6);
        rbt.put(7, 7);
        rbt.put(5, 5);
        
        //then
        if(rbt.getPreOrder() == null){
            assert false;
        }
        System.out.println("Wynik:"+rbt.getPreOrder());
    }
    
    @Test
    public void rbt_GetPostOrder(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //when
        rbt.put(1, 1);
        rbt.put(2, 2);
        rbt.put(6, 6);
        rbt.put(7, 7);
        rbt.put(5, 5);
        
        //then
        if(rbt.getPostOrder() == null){
            assert false;
        }
        System.out.println("Wynik:"+rbt.getPostOrder());
    }
    
    @Test
    public void rbt_GetOrder_Null(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //then
        String s1 = rbt.getPostOrder();  
        String s2 = rbt.getInOrder();
        String s3 = rbt.getPreOrder();
    }
    
    @Test
    public void rbt_DeleteMaxValue(){
         //given
        RedBlackTree rbt = new RedBlackTree();

        //when
        rbt.put(2, 2);
        rbt.put(6, 6);
        rbt.put(7, 7);
        rbt.put(5, 5);
        
        //then
        rbt.deleteMax();
        rbt.deleteMax();
        rbt.deleteMax();
        rbt.deleteMax();
        assertNull(rbt.get(7));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void rbt_ValidateParams(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //then
        rbt.put(null, null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void rbt_ValidateParams_1(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //then
        rbt.put(1, null);
    }
    
        @Test (expected = IllegalArgumentException.class)
    public void rbt_ValidateParams_2(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //then
        rbt.put(null, 1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void rbt_ValidateKey(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //when
        rbt.put(1, 1);
        
        //then
        rbt.get(null);
    }
    
    
    
    @Test
    public void rvt_DeleteMaxValue_Null(){
        //given
        RedBlackTree rbt = new RedBlackTree();
        
        //then
        rbt.deleteMax();
    }
    
    @Test
    public void rbt_DeleteMinValue(){
         //given
        RedBlackTree rbt = new RedBlackTree();

        //when
        rbt.put(2, 2);
        rbt.put(6, 6);
        rbt.put(7, 7);
        rbt.put(5, 5);
        
        //then
        System.out.println("WYNIK@@: " + rbt.getInOrder());
        //rbt.deleteMin();
        System.out.println("WYNIK@@: " + rbt.getInOrder());
        //assertNull(rbt.get(2));
        
    }
    
    
}
