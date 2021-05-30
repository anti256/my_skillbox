import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Movements {

    private ArrayList<Operations> listOperations;//список операций
    private TreeSet<String> hsFirm = new TreeSet<>(String::compareToIgnoreCase);//список фирм

    public Movements(String pathMovementsCsv) {
        listOperations = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            for (int i = 1; i < lines.size(); i++) {//цикл с первого т.к. нулевая строка это строка заголовков
                listOperations.add(new Operations(lines.get(i)));
                hsFirm.add(listOperations.get(i-1).getFirm());
            }
            //hsFirm.stream().forEach(s->System.out.println(s));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getExpenceSumFirm(){
        String result = "";
        Iterator<String> itr = hsFirm.iterator();
        while (itr.hasNext()){
         String def = itr.next();
         double summ = listOperations.stream().
             filter(a->a.getFirm().equals(def)).
             mapToDouble(Operations::getExpense).sum();
         if (summ == 0) {continue;}
            result = result + def + "\t\t\t" + String.valueOf(summ) + " руб." +
             System.lineSeparator();
        }
       return result;
    }

    public String getIncomeSumFirm(){
        String result = "";
        Iterator<String> itr = hsFirm.iterator();
        while (itr.hasNext()){
            String def = itr.next();
            double summ = listOperations.stream().
                filter(a->a.getFirm().equals(def)).
                mapToDouble(Operations::getIncome).sum();
            if (summ == 0) {continue;}
            result = result + def + "\t\t\t" + String.valueOf(summ) + " руб." +
                System.lineSeparator();
        }
        return result;
    }

    public double getExpenseSum() {
        return listOperations.stream().mapToDouble(Operations::getExpense).sum();
    }

    public double getIncomeSum() {
        return listOperations.stream().mapToDouble(Operations::getIncome).sum();
    }
}
