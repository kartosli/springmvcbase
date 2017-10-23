package com.n5x.common.util;

import com.n5x.common.system.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;


public class AES256Encryption {
    /**
     * 密钥算法
     * java6支持56位密钥，bouncycastle支持64位
     * */
    public static final String KEY_ALGORITHM = "AES";
    /**
     * 加密/解密算法/工作模式/填充方式
     *
     * JAVA6 支持PKCS5PADDING填充方式
     * Bouncy castle支持PKCS7Padding填充方式
     * */
    public static final String CIPHER_ALGORITHM="AES/ECB/PKCS7Padding";
    /**
     *
     * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥
     * @return byte[] 二进制密钥
     * */
    public static byte[] initkey() throws Exception {
//         //实例化密钥生成器
//         Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//         KeyGenerator kg=KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
//         初始化密钥生成器，AES要求密钥长度为128位、192位、256位
//         kg.init(256);
//         kg.init(128);
//         //生成密钥
//         SecretKey secretKey=kg.generateKey();
//       //获取二进制密钥编码形式
//         return secretKey.getEncoded();
        //为了便于测试，这里我把key写死了，如果大家需要自动生成，可用上面注释掉的代码
        //默认密钥为Mobile4AESEncryp
        return new byte[] {77, 111, 98, 105, 108, 101, 52, 65, 69, 83, 69, 110, 99, 114, 121, 112};
    }

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return Key 密钥
     * */
    public static Key toKey(byte[] key) throws Exception {
        //实例化DES密钥
        //生成密钥
        SecretKey secretKey=new SecretKeySpec(key,KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 加密数据
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密后的数据
     * */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception {
        //还原密钥
        Key k=toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher= Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        //初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密数据
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密后的数据
     * */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception {
        //欢迎密钥
        Key k =toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
//         Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher= Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        //初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        //执行操作
        return cipher.doFinal(data);
    }
    public static byte[] decrypt(String data, byte[] key) throws Exception {
        return decrypt(Base64.decode(data),key);

    }
}