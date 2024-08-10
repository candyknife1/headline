
import com.lzw.headline.util.JwtHelper;
import org.junit.Test;

/**
 * @Author: lzw
 * @Description: 密钥测试类
 * @Date: 2024/8/8 11:23
 * @Version: 1.0
 */
public class TestJwtHelper {
    @Test
    public void tsetAll() throws InterruptedException {
        String token = JwtHelper.createToken(1l);
        System.out.println("token: " + token);
        Long userId = JwtHelper.getUserId(token);
        System.out.println(JwtHelper.isExpiration(token));

        Thread.sleep(6000);
        System.out.println(JwtHelper.isExpiration(token));
    }
}
