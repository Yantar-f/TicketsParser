import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
        String date = "12.07.02";

        System.out.println(format.parse(date));
    }
}
