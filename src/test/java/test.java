import com.jfinal.kit.HttpKit;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
/**
 * Created by Administrator on 2016/7/22.
 */
public class test {

    //
    public static void main(String[] args) {


        DateTime dateTime = new DateTime();
        System.out.println(dateTime.toDateTime().getMillis());
        System.out.println(dateTime.toDateTime().plus(2).getMillis());
        Map<String,String> map = new HashMap<>();
        map.put("Cookie","583149697-1467364527-%7C1469175798");

        String result = HttpKit.get("http://www.yiyu.com/profile",null,map);
        System.out.println(result);
        System.out.println(result);
    }
}
