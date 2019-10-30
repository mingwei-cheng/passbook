package cn.cmw.pass;

import cn.cmw.pass.utils.CipherUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class PassApplicationTests {

    @Test
    void contextLoads() {
        String s3 = DigestUtils.md5DigestAsHex("sunsh".getBytes());
        System.out.println(s3);
//        String s1 = CipherUtil.generateDESKey("620af81a-f4a0-11e9-bc62-06e8b9a73d06");
//        String s = CipherUtil.DESEncrypt("cmw96920123", s1);
//        System.out.println(s);
//        String s2 = CipherUtil.DESDecrypt("bMIydiCnBdffKTSzTLzXNQ==", s1);
//        System.out.println(s2);
    }

}
