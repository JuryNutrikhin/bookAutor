import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        /**
         * В папке csv лежат два csv-файла: список книг и список авторов. В папке images лежат обложки книг. В файле с книгами есть поле author_id и image_path. Первое поле обозначает идентификатор автора, второе – название файла картинки(которая лежит в папке images).
         * Задание
         * Создайте Map<Integer, String> authors. Ключ – id автора, значение – его имя.
         * Прочитайте весь файл author.csv, записав каждую пару id-name в наш Map authors.
         * Прочитайте каждую строчку файла book.csv и для каждой строки сделайте следующее:
         * Считайте название книги (столбец title)
         * Считайте поле author_id
         * Из Map authors найдите имя автора по этому author_id
         * Считайте название файла-обложки книги (поле image_path)
         * Считайте картинку с этим названием (image_path), которая лежит в папке images в массив byte[]
         * Запишите этот массив байт в новую картинку. Картинка должна лежать в папке result/img. Картинка должна иметь название вида
         * «Имя автора – название книги». Например для первой строки из файла books.csv картинка будет иметь название «Фрир О. – UK для начинающих».
         *
         * Дополнительное задание
         *
         * Найдите самую дорогую книгу.  (поле price отвечает за цену)
         * Найдите самую дешевую книгу.
         * Запишите в текстовый файл результаты. Файл должен лежать в папке result
         */

        Map<String, String> authors = new TreeMap<>();
        String dirFile = "images/";
        double maxPrice = 0;
        double minPrice = 0;
        double price = 0;
        String[] maxPriceArray = null;
        String[] minPriceArray = null;


        try (FileReader fileReader = new FileReader("csv/author.csv");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String s = null;
            int i = 0;

            while ((s = bufferedReader.readLine()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                // String s1 = s.replace("\"", " ");
                String[] strArray = s.split(",");
                // int key = Integer.parseInt(strArray[0]);
                String key = strArray[0];

                String value = strArray[1];
                authors.put(key, value);
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

//        for(Map.Entry<String,String> entry: authors.entrySet()){
//            System.out.println(entry.getKey()+" : "+ entry.getValue());
//               }
        try (FileReader fileReader = new FileReader("csv/book.csv");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String s = null;
            int i = 0;
            while ((s = bufferedReader.readLine()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                String s1 = s.replace("\"", " ");
                String[] strArray2;
                strArray2 = s1.split(",");
                String[] errStrarray = s1.split(",");

                String id = strArray2[0];
                String title = strArray2[1];
                try {
                    if(strArray2.length==6)
                        price = Double.parseDouble(strArray2[2]);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    price = Double.parseDouble(errStrarray[3]);
                }

                String amount = strArray2[3];
                String image_path = strArray2[4];
                String author_id = strArray2[5];
                if (errStrarray.length == 7) {
                    image_path = errStrarray[5];
                    author_id = errStrarray[6];
                }
                //..........................................
                //поиск мин и макс цены
                if (price > maxPrice) {
                    maxPrice = price;
                    maxPriceArray = s.split(",");
                }
                if (minPrice == 0) {
                    minPrice = price;
                } else {
                    if (price < minPrice)
                        minPrice = price;
                    minPriceArray = s.split(",");
                }


                //..............................................

                String titeleImage = "";
                //    System.out.println(id + " " + title + " " + image_path + " " + author_id);

                for (Map.Entry<String, String> entry : authors.entrySet()) {
                    if (entry.getKey().equals(author_id)) {
                        titeleImage = "result/" + entry.getValue() + " - " + title + ".jpg";

                    }
                }
                String inputFile = dirFile + image_path;
                byte[] bytes = null;

                try (FileInputStream inputStream = new FileInputStream(inputFile);
                     FileOutputStream fileOutputStream = new FileOutputStream(titeleImage)) {

                    bytes = inputStream.readAllBytes();
                    fileOutputStream.write(bytes);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //         * «Имя автора – название книги». Например для первой строки из файла books.csv картинка будет иметь название «Фрир О. – UK для начинающих».

//        System.out.println("минимальная цена :"+ Arrays.toString(minPriceArray));
//        System.out.println("Максимальная  цена :"+Arrays.toString(maxPriceArray));

        try (FileWriter fileWriter = new FileWriter("result/ file.txt")) {
            fileWriter.write("Книга с минимальной ценой = "+minPrice+" -> " + Arrays.toString(minPriceArray) +
                    "\nКнига с максимальной ценой = "+maxPrice+" -> " + Arrays.toString(maxPriceArray));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
}