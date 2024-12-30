import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class EnDeCrypted {

    private ArrayList<Character> alphabetArrayList = new ArrayList<>();
    private String pathDataFile;
    private String pathEncryptedFile;
    private String pathDecryptedFile;

    private int nextSymbolIndex;
    private char nextSymbol;
    private int key;


    public EnDeCrypted() {
        this.pathDataFile = "resources/data.txt";
        this.pathDecryptedFile = "decrypted/resultDecrypted.txt";
        this.pathEncryptedFile = "encrypted/resultEncrypted.txt";
        completionAlphabetArrayList();
    }


    public String getPathDecryptedFile() {
        return this.pathDecryptedFile;
    }

    public int getAlphabetArrayListSize() {
        return alphabetArrayList.size();
    }


    private void completionAlphabetArrayList() {
        for (char symbol = 'A'; symbol <= 'Z'; symbol++) {
            alphabetArrayList.add(symbol);
        }
        for (char symbol = 'a'; symbol <= 'z'; symbol++) {
            alphabetArrayList.add(symbol);
        }
        for (char symbol = 'А'; symbol <= 'я'; symbol++) {
            alphabetArrayList.add(symbol);
        }

        alphabetArrayList.add('.');
        alphabetArrayList.add(',');
        alphabetArrayList.add('-');
        alphabetArrayList.add(':');
        alphabetArrayList.add(';');
        alphabetArrayList.add('!');
    }


    public void toEncrypted(int key) {
        this.key = key;
        crypted(pathDataFile, pathEncryptedFile);
    }

/*    public void toEncrypted(String key) {
        byte[] bytes = key.getBytes();
        this.key=0;
        for (int a : bytes) {
            this.key += a;
        }
        System.out.println(this.key);
        crypted(pathDataFile, pathEncryptedFile);
    }*/


    public void toDecrypted(int key) {
        this.key = key * (-1);
        crypted(pathEncryptedFile, pathDecryptedFile);
    }

   /* public void toDecrypted(String key) {
        byte[] bytes = key.getBytes();
        this.key=0;
        for (int a : bytes) {
            this.key += a;
        }

        this.key *= (-1);
        System.out.println(this.key);
        crypted(pathDataFile, pathEncryptedFile);
    }*/


    private void crypted(String pathTextBefore, String pathTextAtfer) {

        String textBefore = "";
        String textAfter = "";

        try {
            textBefore = Files.readString(Path.of(pathTextBefore));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < textBefore.length(); i++) {

            if (alphabetArrayList.contains(textBefore.charAt(i))) {

                searchNextIndexSymbol(i, textBefore);
                nextSymbol = alphabetArrayList.get(this.nextSymbolIndex);
                textAfter += nextSymbol;

            } else {
                textAfter += textBefore.charAt(i);
            }

        }

        System.out.printf(Messages.RESULT_MESSAGE, pathTextAtfer, textAfter);
        writeTextToFile(pathTextAtfer, textAfter);

    }


    private void searchNextIndexSymbol(int i, String textBefore) {

        this.nextSymbolIndex = alphabetArrayList.indexOf(textBefore.charAt(i)) + key;

        if (nextSymbolIndex >= alphabetArrayList.size()) {
            while (nextSymbolIndex >= alphabetArrayList.size()) {
                nextSymbolIndex = nextSymbolIndex - alphabetArrayList.size();
            }
        }

        if (nextSymbolIndex < 0) {
            while (nextSymbolIndex < 0) {
                nextSymbolIndex = nextSymbolIndex + alphabetArrayList.size();
            }
        }
    }


    private void writeTextToFile(String pathTextAfter, String textAfter) {

        try {
            Files.writeString(Path.of(pathTextAfter), textAfter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
