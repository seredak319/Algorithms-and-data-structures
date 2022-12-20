package pl.edu.pw.ee;

import org.junit.Assert;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LongestCommonSubsequenceTest 
{
    
    @Test
    public void LCS_test(){
        LongestCommonSubsequence d = new LongestCommonSubsequence("WATYKAŃSKI", "FRANCISZKAŃSKA");
        String s = d.findLCS();
        System.out.println(s);
        
        Assert.assertEquals("KSNAK", s);
    }

    
}
