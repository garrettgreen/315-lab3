package lab3;

import java.util.HashMap;
import java.util.Map;

public class system {
    
    public class registers{
        private Map<String, Integer> registers;
        private int pc;

        public registers(){
            this.registers = new HashMap<>();
            this.pc = 0;
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
    }

}
