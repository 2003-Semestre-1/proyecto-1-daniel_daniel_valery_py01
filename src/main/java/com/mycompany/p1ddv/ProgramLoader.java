package com.mycompany.p1ddv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author 
 */
public class ProgramLoader {
    private Lexer lexer;
    private Parser parser;
    List<Instruction> instrucciones;
    //private Instruction instruction;

    public ProgramLoader() {
        this.lexer = new Lexer();
        this.parser = new Parser();
        //this.instruction= new Instruction();
    }
    
    public BCP loadProgram(String fileName) throws Exception{
        if(!fileName.endsWith(".asm")){
            throw new Exception("Input file has to be .asm");
        }
        try {
            List<String> programLines =  Files.readAllLines(
                    Paths.get(fileName));
            List<Token> tokens = lexer.tokenize(programLines);
            instrucciones = parser.parse(tokens);
            
            /*
            TODO Agregar almacenamiento de las instrucciónes y el procesamiento
            en la memoria principal y la unidad de almacenamiento.
            */
            
            /* FIXME fix base value to actual value */
            int base = 0;
            
            BCP bcp = new BCP(base, instrucciones.size());
            /*
            TODO Asignar al bcp información faltante como CPU asignado
            Entre otros
            */
            
            return bcp;
        } catch (IOException ex) {
            throw new Exception("Error reading file");
        }
    }
    
    
}
