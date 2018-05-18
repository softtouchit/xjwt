package com.softtouchit.jwt;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Test;

public class TestJWT {

    @Test
    public void testTOken() throws Exception {
        long now = 1465216929929L;
        JWT jwt = new JWT("xld3dde", "jmzPKOCI+BsUTehdFGpOurjUtaiPLRBpT61sTVka5ms=");
        String data = "f/////////8CAAAAAAAABNI=.y7lEPixVX1i41fhIXV2MMg==.g9FXBolu2ablNJabit0LWw8+n5j+xheW1ETpPXyz+V0=";
        System.out.println(jwt.verifyAndDecrypt(data,  now));
        
        String json = jwt.verifyAndDecrypt(data,  now);
        
        ByteBuffer payload = ByteBuffer.allocate(json.length()).order(ByteOrder.BIG_ENDIAN);
        payload.put(json.getBytes()).flip();
        
        
        ByteBuffer token = ByteBuffer.allocate(1024);
        jwt.encryptAndSign(payload, token, now + 1000);
        
        String nt = new String(token.array(),token.arrayOffset(), token.remaining());
        System.out.println(nt);
        
        String json2 = jwt.verifyAndDecrypt(nt, now);
        
        assertEquals(json,json2);
        
    }
    
}
