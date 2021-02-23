import java.text.DecimalFormat;
import java.util.UUID;

/**
 * @auther izumi
 * @date 2020-08-22 09:28
 */
public class test {
    public static void main(String[] args) {
        double a= 123.123123123;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(a));
    }

}
