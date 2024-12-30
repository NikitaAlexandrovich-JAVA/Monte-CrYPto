import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MasterKey extends EnDeCrypted {
    String verifiableText;
    List<String> stringListText;

    public void sortedItOutKey() {
        Scanner dialog = new Scanner(System.in);

     /*Для перебора Key берем интервал чисел от -алфавит.size до +алфавит.size.
       Интервал чисел от Integer.MIN_VALUE до Integer.MAX_VALUE не имеет смысла так,
       как для шифра Цезаря нам необходимо обязательно знать количество  символов в алфавите
       и их порядок, а соответсвенно любой ключ приводится в диапазон значений (+-алфавит.size)
       В шифре Цезаря Ключи могут повторяться!!!
        */

        for (int key = -getAlphabetArrayListSize(); key <= getAlphabetArrayListSize(); key++) {
            toDecrypted(key);
            //System.out.println(key);

            if (checkDecryptedFile()) {

               // System.out.printf(Messages.RESULT_MESSAGE, " ", verifiableText);
                System.out.println(Messages.DECRYPTED_IS_SUCCESSFUL_QUESTION);

                if (dialog.nextBoolean()) {
                    System.out.println("Ключ:" + key);
                    break;
                }

            }
            ;
        }
    }

    /* Логика метода:
       Метод проверяет первые 1000 символов раскодированного текста.
       В правильно отформатированном тексте после символа "," всегда идет символ пробела (кроме переноса)
    */
    private boolean checkDecryptedFile() {

        boolean check = false;

        try {
            verifiableText = Files.readString(Path.of(getPathDecryptedFile()));
            for (int i = 0; i <= 1000; i++) {

                if (verifiableText.charAt(i) == ',' && i < 999) {
                    if (verifiableText.charAt(i + 1) == ' ') {
                        check = true;
                    } else {
                        break;
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return check;
    }

    private boolean checkDecryptedFile2() {
        boolean check = true;
        try {
            stringListText = Files.readAllLines(Path.of(getPathDecryptedFile()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= stringListText.size() / 2; i++) {

            for (int j = 0; j < stringListText.get(i).length(); j++) {

                if (stringListText.get(i).charAt(j) == ',' && j < stringListText.get(i).length() - 1) {
                    if (stringListText.get(i).charAt(j+1) == '\t' || stringListText.get(i).charAt(j+1) == '\n') {
                        check = true;
                    } else {
                        check = false;
                        break;

                    }
                }
            }  if(!check){
                break;
            }


        }
        return check;
    }
}



