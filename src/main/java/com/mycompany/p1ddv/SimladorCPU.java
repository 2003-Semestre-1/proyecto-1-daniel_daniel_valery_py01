/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.p1ddv;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author
 */
public class SimladorCPU {
    
    public static void main(String[] args) throws IOException {
        String inputFilename = "";
        
        
        int MAX_MAIN_MEMORY = 256;
        int MAX_DISK_MEMORY = 512;
        int MAX_STACK_SIZE = 10;
        Instruction[] memoriaPrincipal = new Instruction[MAX_MAIN_MEMORY];
        Object[] unidadAlmacenamiento = new Object[MAX_DISK_MEMORY];
        Stack<Integer> stack = new Stack<>();
        
        
        
                
        ProgramLoader loader = new ProgramLoader();
        
        
        try {
            
            loader.loadProgram(inputFilename);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
    
        Cpu cpu1 = new Cpu(memoriaPrincipal);
        Cpu cpu2 = new Cpu(memoriaPrincipal);
        
        /*
         * Generar logica de ejecución de programas utilizando el bcp
         * 
         */
        
        
        
        
        
    }
}
