/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.p1ddv;


import com.mycompany.p1ddv.ProgramLoader;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Daniel Villatoro
 */
public class P1DDV {
    
    

    public static void main(String[] args) throws IOException {
        String inputFilename = "C:\\Users\\Gadyr\\Documents\\NetBeansProjects\\file.asm";
       cargarArchivos();
        
        /*
         * Generar logica de ejecución de programas utilizando el bcp
         * 
         */    
    }
    
    public static void cargarArchivos(){    
        
    int MAX_MAIN_MEMORY = 256;
    int MAX_DISK_MEMORY = 512;
    int MAX_STACK_SIZE = 10;
    Instruction[] memoriaPrincipal = new Instruction[MAX_MAIN_MEMORY];
    Object[] unidadAlmacenamiento = new Object[MAX_DISK_MEMORY];
    Stack<Integer> stack = new Stack<>();
    int posMemoriaLibre = 11;
    ProgramLoader loader = new ProgramLoader();
    String[] files = {"C:\\Users\\Gadyr\\Documents\\NetBeansProjects\\file.asm","C:\\Users\\Gadyr\\Documents\\NetBeansProjects\\file1.asm","C:\\Users\\Gadyr\\Documents\\NetBeansProjects\\file2.asm"};
    int fileIndex=0;
    while(fileIndex < 3){
        try {
            loader.loadProgram(files[fileIndex]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        List<Instruction> instrucciones = loader.instrucciones;
        int j = 0;
        for(int i=posMemoriaLibre; i<254; i++){
             if(j >= instrucciones.size()){
                posMemoriaLibre = i;
                break;
             }
            memoriaPrincipal[i] = instrucciones.get(j);
            j++;

        }
        fileIndex++;
    }

    Cpu cpu1 = new Cpu(memoriaPrincipal);
    Cpu cpu2 = new Cpu(memoriaPrincipal);  
         
    }
}
