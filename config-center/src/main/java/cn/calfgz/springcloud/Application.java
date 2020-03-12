package cn.calfgz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-12 14:10
 *
 * 如果连接git报com.jcraft.jsch.JSchException: invalid privatekey错，
 * 请检查sshkey文件id_rsa的第一行是不是BEGIN OPENSSH PRIVATE KEY
 * 据了解jsch只支持较低版本的openssh，即以BEGIN RSA PRIVATE KEY开头的密钥
 * BEGIN OPENSSH PRIVATE KEY 开头的密钥是高级版本，jsch不支持。
 *
 * 解决此问题的方法是重新生成旧版RSA格式的密钥。
 *
 * 解决方法（一）：
 *
 * 使用 ssh-keygen -m PEM -t rsa -b 4096 来生成
 * -m 参数指定密钥的格式，PEM（也就是RSA格式）是之前使用的旧格式
 * -b：指定密钥长度；
 * -e：读取openssh的私钥或者公钥文件；
 * -C：添加注释；
 * -f：指定用来保存密钥的文件名；
 * -i：读取未加密的ssh-v2兼容的私钥/公钥文件，然后在标准输出设备上显示openssh兼容的私钥/公钥；
 * -l：显示公钥文件的指纹数据；
 * -N：提供一个新密语；
 * -P：提供（旧）密语；
 * -q：静默模式；
 * -t：指定要创建的密钥类型
 *
 * 解决方法（二）：
 * 将密钥转换为PuTTy ppk格式并返回来解决
 * tempkey的内容：
 * ```
 * -----BEGIN OPENSSH PRIVATE KEY-----
 * b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAA...
 * -----END OPENSSH PRIVATE KEY-----
 * ```
 * 转换为ppk格式：
 * `puttygen tempkey -o tempkey.ppk`
 *
 * 转换回openssh格式：
 * `puttygen tempkey.ppk -O private-openssh -o tempkey.oldformat`
 * tempkey.oldformat的内容：
 *
 * ```
 * -----BEGIN RSA PRIVATE KEY-----
 * MIIJJwIBAAKCAgEAzmmS5aA0....
 * -----END RSA PRIVATE KEY-----
 * ```
 *
 */
@SpringBootApplication
@EnableConfigServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
