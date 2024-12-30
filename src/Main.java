import java.util.Scanner;

public class Main {

    public static EnDeCrypted enDeCrypted = new EnDeCrypted();
    public static MasterKey masterKey = new MasterKey();

    public static void main(String[] args) {
        Scanner scannerCommand = new Scanner(System.in);
        Scanner scannerKey = new Scanner(System.in);
        String commandUser;


        System.out.println(Messages.START_MESSAGE);

        do {

            System.out.println(Messages.MY_SKILL_MESSAGE);
            commandUser = scannerCommand.nextLine();

            switch (commandUser.toLowerCase()) {


                case "1" -> {

                    System.out.printf(Messages.INPUT_KEY_MESSAGE, "(закодирование)");
                    enDeCrypted.toEncrypted(scannerKey.nextInt());

                }
                case "2" -> {

                    System.out.printf(Messages.INPUT_KEY_MESSAGE, "(декодирование)");
                    enDeCrypted.toDecrypted(scannerKey.nextInt());


                }
                case "3" -> {
                    masterKey.sortedItOutKey();
                }
                case "exit" -> System.out.println(Messages.END_MESSAGE);


                default -> System.out.println(Messages.ERROR_NOT_COMMAND_MESSAGE);
            }

        } while (!commandUser.equalsIgnoreCase("exit"));

    }


}