package cn.cmw.pass.utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Cheng
 * @create 2019-10-22 20:37
 **/
public class CipherUtil {
    /**
     * DES算法
     */
    private static final String ALGORITHM_DES = "SHA1PRNG";
    /**
     * base64字符集 0..63
     */
    static private char[] alphabet =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
                    .toCharArray();
    /**
     * 初始化base64字符集表
     */
    static private byte[] codes = new byte[256];


    static {
        for (int i = 0; i < 256; i++) {
            codes[i] = -1;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            codes[i] = (byte) (i - 'A');
        }
        for (int i = 'a'; i <= 'z'; i++) {
            codes[i] = (byte) (26 + i - 'a');
        }
        for (int i = '0'; i <= '9'; i++) {
            codes[i] = (byte) (52 + i - '0');
        }
        codes['+'] = 62;
        codes['/'] = 63;
    }

    /**
     * 生成DES密钥
     *
     * @param seed 密钥种子
     * @return 返回base64编码的密钥字符串
     */
    public static SecretKey generateDESKey(String seed) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            SecureRandom random = SecureRandom.getInstance(ALGORITHM_DES);
            random.setSeed(seed.getBytes(Charset.forName("UTF-8")));
            kg.init(random);
            SecretKey secretKey = kg.generateKey();
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES加密
     *
     * @param data 要加密的数据
     * @param secretKey  密钥
     * @return 返回加密后的数据(经过base64编码)
     */
    public static String DESEncrypt(String data, SecretKey secretKey) {
        return DESCipher(data, secretKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * DES解密
     *
     * @param data 要解密的数据
     * @param secretKey  密钥
     * @return 返回解密后的数据
     */
    public static String DESDecrypt(String data, SecretKey secretKey) {
        return DESCipher(data, secretKey, Cipher.DECRYPT_MODE);
    }

    /**
     * DES的加密解密
     *
     * @param data 要加密或解密的数据
     * @param secretKey  密钥
     * @param mode 加密或解密模式
     * @return 返回加密或解密的数据
     */
    private static String DESCipher(String data, SecretKey secretKey, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(mode, secretKey);
            return mode == Cipher.DECRYPT_MODE ? new String(cipher.doFinal(base64StrToByteArray(data)),"UTF-8") : byteArrayToBase64Str(cipher.doFinal(data.getBytes(Charset.forName("UTF-8"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将base64码转换成字节数组
     *
     * @param base64Str base64码
     * @return 返回转换后的字节数组
     */
    public static byte[] base64StrToByteArray(String base64Str) {
        char[] dataArr = new char[base64Str.length()];
        base64Str.getChars(0, base64Str.length(), dataArr, 0);
        return decode(dataArr);
    }

    /**
     * 将字节数组通过base64转码
     *
     * @param byteArray 字节数组
     * @return 返回转码后的字符串
     */
    public static String byteArrayToBase64Str(byte byteArray[]) {
        return new String(encode(byteArray));
    }

    /**
     * 将一个字节数组转换成base64的字符数组
     *
     * @param data 字节数组
     * @return base64字符数组
     */
    private static char[] encode(byte[] data) {
        char[] out = new char[((data.length + 2) / 3) * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = alphabet[val & 0x3F];
        }
        return out;
    }

    /**
     * 将一个base64字符数组解码成一个字节数组
     *
     * @param data base64字符数组
     * @return 返回解码以后的字节数组
     */
    private static byte[] decode(char[] data) {
        int len = ((data.length + 3) / 4) * 3;
        if (data.length > 0 && data[data.length - 1] == '=') {
            --len;
        }
        if (data.length > 1 && data[data.length - 2] == '=') {
            --len;
        }
        byte[] out = new byte[len];
        int shift = 0;
        int accum = 0;
        int index = 0;
        for (int ix = 0; ix < data.length; ix++) {
            int value = codes[data[ix] & 0xFF];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte) ((accum >> shift) & 0xff);
                }
            }
        }
        if (index != out.length) {
            throw new Error("miscalculated data length!");
        }
        return out;
    }

    /**
     * 将base64编码后的密钥字符串转换成密钥对象
     *
     * @param key       密钥字符串
     * @param algorithm 加密算法
     * @return 返回密钥对象
     */
    private static Key toKey(String key, String algorithm) {
        SecretKey secretKey = new SecretKeySpec(base64StrToByteArray(key), algorithm);
        return secretKey;
    }


}
