import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.security.Key;
import java.util.*;

public class TheLuckyOne {
    private static final String INFILENAME = "C:\\workspace\\TC10_2TheLuckyOne\\input\\submitInputG.txt";
    private static String OUTFILENAME = "C:\\workspace\\TC10_2TheLuckyOne\\output\\outputChallenge2.txt";
    private static Scanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws FileNotFoundException {
        in = new Scanner(new FileReader(INFILENAME));
        out = new PrintWriter(OUTFILENAME);
        int cases = Integer.parseInt(in.nextLine());

        for(int i=0 ; i<cases;i++){
            int matches = Integer.parseInt((in.nextLine()));
            HashMap winnersHash = new HashMap<Integer, ArrayList<Integer>>();
            for(int j= 0; j<matches;j++){
                String line = in.nextLine();
                if(line != null)
                    bestPlayer(line,winnersHash);
            }
            //Integer max_key = (Integer) Collections.max(matchHashMap.entrySet(), Map.Entry.comparingByValue()).getKey();
            for (Object name : winnersHash.keySet()) {
                //System.out.println(name);
                out.println("Case #" + (i+1) + ": " + name);
            }

        }
        in.close();
        out.close();
    }
    private static void bestPlayer(String line,HashMap winnersHash) {
        String[] splitedLine = line.split("\\s+");

        Integer  winner = Integer.parseInt(splitedLine[2]);
        Integer keyA = Integer.parseInt(splitedLine[0]);
        Integer keyB = Integer.parseInt(splitedLine[1]);

        if(winnersHash.size() == 0){
            if(winner == 1)
                winnersHash.put(keyA, new ArrayList((Arrays.asList(keyB))));
            else
                winnersHash.put(keyB, new ArrayList((Arrays.asList(keyA))));
        }else{
            if(winner == 0){//GANA B
                //Si el que gana es el mejor ya se añade el perdedor a su lista de perdedores
                if(winnersHash.containsKey(keyB) == Boolean.TRUE){
                    ArrayList arrayKeyB = (ArrayList) winnersHash.get(keyB);
                    arrayKeyB.add(keyA);
                    winnersHash.replace(keyA, arrayKeyB);
               }else{
                    //No es el KEY, hay que ver si esta en la lista de peores de los mejores.
                    if(winnersHash.values()!= null){
                        Integer key = (Integer)winnersHash.keySet().stream().findFirst().get();
                        ArrayList listaPeores = (ArrayList) winnersHash.values().stream().findFirst().get();
                        if(listaPeores.contains(keyB) == Boolean.TRUE){
                        //Si esta en la lista añadimos al que ha perdido en la lista del mejor
                            listaPeores.add(keyA);
                            winnersHash.replace(key, listaPeores);


                        }else{
                         //hay que añadirlo como nuevos campeones a revisar.
                            System.out.println("veremos si entra aqui");
                        }
                    }
                }
                //Winner es 1 GANA A
            }else{
                //Si el que gana es el mejor ya se añade el perdedor a su lista de perdedores
                if(winnersHash.containsKey(keyA) == Boolean.TRUE){
                    ArrayList arrayKeyA = (ArrayList) winnersHash.get(keyA);
                    arrayKeyA.add(keyB);
                    winnersHash.replace(keyB, arrayKeyA);
                }else{
                    //No es el KEY, hay que ver si esta en la lista de peores de los mejores.
                    if(winnersHash.values()!= null) {
                        Integer key = (Integer)winnersHash.keySet().stream().findFirst().get();
                        ArrayList listaPeores = (ArrayList) winnersHash.values().stream().findFirst().get();
                        if(listaPeores.contains(keyA) == Boolean.TRUE){
                            //Si esta en la lista añadimos al que ha perdido en la lista del mejor
                            listaPeores.add(keyB);
                            winnersHash.replace(key, listaPeores);
                        } else {
                            //hay que añadirlo como nuevos campeones a revisar.
                            System.out.println("veremos si entra aqui");
                      }
                    }
                }
           }
        }
       }

}
