import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) {
        byte[] bytes = null;


        try (FileInputStream fileInputStream = new FileInputStream("1.jpg")) {
                bytes = fileInputStream.readAllBytes();
       } catch (IOException ex) {
            ex.getMessage();
        }

        try(FileOutputStream fileOutputStream =new FileOutputStream("result/333.jpg")){
            fileOutputStream.write(bytes);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
