import java.io.*;

public class Test {
    public static void main(String[] args) {
        byte[] bytes = null;

        String i = "2145620.jpg";
        String fileName = "images/" + i;

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {

                bytes = fileInputStream.readAllBytes();

        } catch (IOException ex) {
            ex.getMessage();
        }

        try(FileOutputStream fileOutputStream =new FileOutputStream("result/222.jpg")){
            fileOutputStream.write(bytes);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }


        //https://itstepru.sharepoint.com/sites/Java211/_layouts/15/stream.aspx?id=%2Fsites%2FJava211%2FShared%20Documents%2FGeneral%2FRecordings%2FSudoku%20solver%2E%20%D0%9F%D0%BE%D1%82%D0%BE%D0%BA%D0%B8%20%D0%B2%D0%B2%D0%BE%D0%B4%D0%B0%20%D0%B2%D1%8B%D0%B2%D0%BE%D0%B4%D0%B0%2E%2D20221214%5F203400%2D%D0%97%D0%B0%D0%BF%D0%B8%D1%81%D1%8C%20%D1%81%D0%BE%D0%B1%D1%80%D0%B0%D0%BD%D0%B8%D1%8F%2Emp4
    //время 2:26:44
    }
}
