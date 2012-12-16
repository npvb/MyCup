
import Clases.*;
import java.io.FileReader;

/**
 *
 * @author Paulette
 */
public class Main {
    public static void main(String[] args) {
       try {
            MyParser p = new MyParser(new Yylex(new FileReader("C:\\Users\\Paulette\\MyCup\\MyCup-Generated\\src\\test.txt")));
            p.Parse();
            
    } catch (Exception e) {
      e.printStackTrace();
    }
    }

}
