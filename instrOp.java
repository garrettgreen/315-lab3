

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class instrOp {
    
    private HashMap<String, Integer> registers;
    private HashMap<String, Integer> labelMap;
    private String instruction;
    private int pc;
    private int datamemory[];

    public instrOp(String instruction, HashMap<String, Integer> labelMap, HashMap<String, Integer> registers){
        this.labelMap = labelMap;
        this.instruction = instruction;
        this.registers = registers;
        this.pc = 0;
        this.datamemory = new int[8192];
    }

    public int get_register(String name){
        return registers.get(name);
    }

    public void set_register(String name, int value){
        registers.put(name, value);
    }

    public int get_pc(){
        return this.pc;
    }

    public void set_pc(int value){
        this.pc = value;
    }

    public HashMap<String, Integer> execute_instruction(){
        String arr[] = instruction.trim().split("\\s+");
        String instName = arr[0];
        for (String line : arr){
            System.out.println(line);
        }

        System.out.println("This is the instr going to be compelte: " + arr[0]);

        if (instName.equals("add")){
            String destination = arr[1];
            int reg1 = get_register(arr[2]);
            int reg2 = get_register(arr[3]);
            int finnal = reg1 + reg2;
            set_register(destination, finnal);
        }

        else if (instName.equals("addi")){
            String destination = arr[1];
            int reg1 = get_register(arr[2]);
            String immediate = arr[3];
            int intimm = Integer.parseInt(immediate);
            int finnal = reg1 + intimm;
            set_register(destination, finnal);
        }
        
        else if (instName.equals("sub")){
            String destination = arr[1];
            int reg1 = get_register(arr[2]);
            int reg2 = get_register(arr[3]);
            int finnal = reg1 - reg2;
            set_register(destination, finnal);
        }

        else if (instName.equals("and")){
            String destination = arr[1];
            int reg1 = get_register(arr[2]);
            int reg2 = get_register(arr[3]);
            int finnal = reg1 & reg2;
            set_register(destination, finnal);
        }

        else if (instName.equals("or")){
            String destination = arr[1];
            int reg1 = get_register(arr[2]);
            int reg2 = get_register(arr[3]);
            int finnal = reg1 | reg2;
            set_register(destination, finnal);
        }

        else if (instName.equals("sll")){
            String destination = arr[1];
            int reg1 = get_register(arr[2]);
            int reg2 = Integer.parseInt(arr[3]);
            int finnal = reg1 << reg2;
            set_register(destination, finnal);
        }
        else if (instName.equals("slt")){
            String destination = arr[1];
            int reg1 = get_register(arr[2]);
            int reg2 = get_register(arr[3]);
            int finnal;
            if (reg1 < reg2){
                finnal = 1;
            }
            else{
                finnal = 0;
            }
            set_register(destination, finnal);
        }
        else if (instName.equals("sw")){
            int value = get_register(arr[1]);
            String[] offset = arr[2].split("//(");
            int num = Integer.parseInt(offset[0]);
            String reg1 = offset[1].substring(1, offset[1].length() - 1);
            int memoryLoc = get_register(reg1) + num;
            datamemory[memoryLoc] = value; 
        }
        else if (instName.equals("lw")){
            String destination = arr[1];
            String[] offset = arr[2].split("//(");
            int num = Integer.parseInt(offset[0]);
            String reg1 = offset[1].substring(1, offset[1].length() - 1);
            int memoryLoc = get_register(reg1) + num;
            int value = datamemory[memoryLoc];
            set_register(destination, value);
        }
        else if (instName.equals("bne")){
            int reg1 = get_register(arr[1]);
            int reg2 = get_register(arr[2]);
            String labelname = arr[3];
            if (reg1 != reg2){
                // set pc to label location (should we have access to label map in system?)
            }

        }
        else if (instName.equals("beq")){
            int reg1 = get_register(arr[1]);
            int reg2 = get_register(arr[2]);
            String labelname = arr[3];
            if (reg1 == reg2){
                // set pc to label location (should we have access to label map in system?)
                // set_pc(label address)
            }

        }
        else if (instName.equals("j")){
            String location = arr[1];
            int address = Integer.parseInt(location);
            set_pc(address);
        }
        else if (instName.equals("jr")){
            String location = arr[1];
            int address = get_register(location);
            set_pc(address);
        }
        else if (instName.equals("jal")){
            String location = arr[1];
            int address = get_register(location);
            // set pc to $ra first, then set pc
            set_register("$ra", get_pc());
            set_pc(address);
        }
        
        return registers;

    }


}
