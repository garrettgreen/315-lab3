// Garrett Green
// Logan Schwarz
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
public class emulate {


    public static void readASM(String fname){
        File infile = new File(fname);
        if (!infile.isFile()) {
            System.out.println(fname + " is not a file!");
            return;
        }
        StringBuilder output = new StringBuilder();
        HashMap<String, Integer> labelMap = new HashMap<>();
        int lineCount = 0;

        // first pass to find labels, save name and line number into table 
        try {
            Scanner scanner = new Scanner(infile);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim(); // removes whitespace

                if (line.startsWith("#")){
                    continue;
                }

                int colonIndex = line.indexOf(":"); // looks for colon

                // extracts the data from before the colon and then puts it into hashmap with lineCount and label
                if (colonIndex >= 0) {
                    String label = line.substring(0, colonIndex);
                    labelMap.put(label, lineCount);
                    lineCount++;
                } else {
                    int commentIndex = line.indexOf("#");
                    if (commentIndex >= 0) {
                        line = line.split("#")[0].trim(); // remove any text after the comment
                    }
                    if (!line.isEmpty() || line.startsWith("#")) { // check if line is not empty
                        lineCount++;
                    }
                }
            }
            scanner.close();    
        }
        catch (FileNotFoundException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        int datamemory[] = new int[8192];

        if (args.length == 2) {
            // interactive
            Scanner scanner = new Scanner(System.in);
            System.out.print("mips> ");
            char userInput = scanner.next().charAt(0);

        
            if (userInput == 'h'){
                // help
            }
            if (userInput == 'd'){
                // dump registers
            }
            if (userInput == 's'){
                // single step through instructions
            }
            if (userInput == 'r'){
                // run whole program
            }
            if (userInput == 'c'){
                // clear registers, memory (pc == 0)
            }
            if (userInput == 'q'){
                // exit program 
            }
        }
        else if (args.length == 3){
            // script
            String filename = args[2];
            File script = new File(filename);

            if (!script.isFile() || !script.exists()){
                System.out.print("Unable to open given file\n");
                return;
            }
        }
        else{
            System.out.println("Incorrect arguments passed!");
            return;
        }
        
}