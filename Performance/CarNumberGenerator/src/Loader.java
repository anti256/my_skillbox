import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        //FileOutputStream writer = new FileOutputStream("./Performance/CarNumberGenerator/res/numbers.txt");
        PrintWriter writer = new PrintWriter("./Performance/CarNumberGenerator/res/numbers.txt");

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        //int regionCode = 199;


        for (int regionCode = 1; regionCode < 100; regionCode++) {
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {

                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {

                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append('\n');


//                            if (builder.length() > 1024){
//                                writer.write(builder.toString().getBytes());
//                                //builder.delete(0,builder.length());
//                                builder = new StringBuilder();
//                            }

//                        String carNumber = firstLetter + padNumber(number, 3) +
//                            secondLetter + thirdLetter + padNumber(regionCode, 2);
//                        writer.write(carNumber.getBytes());
//                        writer.write('\n');
                        }
                    }
                }

            }
            writer.write(builder.toString());
        }
        //writer.write(builder.toString().getBytes());
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder builderPadNumber = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - builderPadNumber.toString().length();
        for (int i = 0; i < padSize; i++) {
            builderPadNumber.insert(0,'0');
        }
        return builderPadNumber.toString();
    }
}
//7-49