package lab3;

import java.util.HashMap;
import java.util.Map;

public class system {
    
    public class registers{
        private Map<String, Integer> registers;
        private int pc;
        private int datamemory[];

        public registers(){
            this.registers = new HashMap<>();
            this.pc = 0;
            this.datamemory = new int[8192];
            setRegisters();
        }
        
        private void setRegisters(){
            this.registers.put("$0", 0);
            this.registers.put("$v0", 0);
            this.registers.put("$v1", 0);
            this.registers.put("$a0", 0);
            this.registers.put("$a1", 0);
            this.registers.put("$a2", 0);
            this.registers.put("$a3", 0);
            this.registers.put("$t0", 0);
            this.registers.put("$t1", 0);
            this.registers.put("$t2", 0);
            this.registers.put("$t3", 0);
            this.registers.put("$t4", 0);
            this.registers.put("$t5", 0);
            this.registers.put("$t6", 0);
            this.registers.put("$t7", 0);
            this.registers.put("$s0", 0);
            this.registers.put("$s1", 0);
            this.registers.put("$s2", 0);
            this.registers.put("$s3", 0);
            this.registers.put("$s4", 0);
            this.registers.put("$s5", 0);
            this.registers.put("$s6", 0);
            this.registers.put("$s7", 0);
            this.registers.put("$t8", 0);
            this.registers.put("$t9", 0);
            this.registers.put("$sp", 0);
            this.registers.put("$ra", 0);

            
        }
        public int get_register(String name){
            return this.registers.get(name);
        }

        public void set_register(String name, int value){
            this.registers.put(name, value);
        }

        public int get_pc(){
            return this.pc;
        }

        public void set_pc(int value){
            this.pc = value;
        }

        public void execute_instruction(String inst){
            String arr[] = inst.split(" ", 4);
            String instName = arr[0];

            if (instName == "add"){
                String destination = arr[1];
                int reg1 = get_register(arr[2]);
                int reg2 = get_register(arr[3]);
                int finnal = reg1 + reg2;
                set_register(destination, finnal);
            }

            else if (instName == "addi"){
                String destination = arr[1];
                int reg1 = get_register(arr[2]);
                String immediate = arr[3];
                int reg2 = Integer.parseInt(immediate);
                int finnal = reg1 + reg2;
                set_register(destination, finnal);
            }
            
            else if (instName == "sub"){
                String destination = arr[1];
                int reg1 = get_register(arr[2]);
                int reg2 = get_register(arr[3]);
                int finnal = reg1 - reg2;
                set_register(destination, finnal);
            }

            else if (instName == "and"){
                String destination = arr[1];
                int reg1 = get_register(arr[2]);
                int reg2 = get_register(arr[3]);
                int finnal = reg1 & reg2;
                set_register(destination, finnal);
            }

            else if (instName == "or"){
                String destination = arr[1];
                int reg1 = get_register(arr[2]);
                int reg2 = get_register(arr[3]);
                int finnal = reg1 | reg2;
                set_register(destination, finnal);
            }

            else if (instName == "sll"){
                String destination = arr[1];
                int reg1 = get_register(arr[2]);
                int reg2 = Integer.parseInt(arr[3]);
                int finnal = reg1 << reg2;
                set_register(destination, finnal);
            }
            else if (instName == "slt"){
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
            else if (instName == "sw"){
                int value = get_register(arr[1]);
                String[] offset = arr[2].split("//(");
                int num = Integer.parseInt(offset[0]);
                String reg1 = offset[1].substring(1, offset[1].length() - 1);
                int memoryLoc = get_register(reg1) + num;
                datamemory[memoryLoc] = value; 
            }
            else if (instName == "lw"){
                String destination = arr[1];
                String[] offset = arr[2].split("//(");
                int num = Integer.parseInt(offset[0]);
                String reg1 = offset[1].substring(1, offset[1].length() - 1);
                int memoryLoc = get_register(reg1) + num;
                int value = datamemory[memoryLoc];
                set_register(destination, value);
            }
            else if (instName == "bne"){
                int reg1 = get_register(arr[1]);
                int reg2 = get_register(arr[2]);
                String labelname = arr[3];
                if (reg1 != reg2){
                    // set pc to label location (should we have access to label map in system?)
                }

            }
            else if (instName == "beq"){
                int reg1 = get_register(arr[1]);
                int reg2 = get_register(arr[2]);
                String labelname = arr[3];
                if (reg1 == reg2){
                    // set pc to label location (should we have access to label map in system?)
                    // set_pc(label address)
                }

            }
            else if (instName == "j"){
                String location = arr[1];
                int address = Integer.parseInt(location);
                set_pc(address);
            }
            else if (instName == "jr"){
                String location = arr[1];
                int address = get_register(location);
                set_pc(address);
            }
            else if (instName == "jal"){
                String location = arr[1];
                int address = get_register(location);
                // set pc to $ra first, then set pc
                set_register("$ra", get_pc());
                set_pc(address);
            }
            

        }


    }

}
