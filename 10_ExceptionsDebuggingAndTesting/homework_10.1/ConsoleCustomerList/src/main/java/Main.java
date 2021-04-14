import java.util.Scanner;

public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            try {
              String command = scanner.nextLine();
              String[] tokens = command.split("\\s+", 2);

              if ("add".equals(tokens[0])) {
                executor.addCustomer(tokens[1]);
              } else if ("list".equals(tokens[0])) {
                executor.listCustomers();
              } else if ("remove".equals(tokens[0])) {
                executor.removeCustomer(tokens[1]);
              } else if ("count".equals(tokens[0])) {
                System.out.println("There are " + executor.getCount() + " customers");
              } else if ("help".equals(tokens[0])) {
                System.out.println(helpText);
              } else {
                System.out.println(COMMAND_ERROR);
              }
            }
            catch (IllegalArgumentException ex) {
              ex.getMessage();
            }
            catch (ArrayIndexOutOfBoundsException ex){
              System.err.println("Empty argument");
            }

        }
    }
}
