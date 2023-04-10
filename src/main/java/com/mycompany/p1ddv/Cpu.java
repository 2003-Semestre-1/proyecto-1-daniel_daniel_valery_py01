/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1ddv;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 *Clase que se encarga de las etapas de ejecución de un programa. Incluye las 
 * etapas de fetch y execute.
 * 
 */
public class Cpu {
    int MAX_MAIN_MEMORY = 256;
    int MAX_DISK_MEMORY = 512;
    int MAX_STACK_SIZE = 10;
    private int PC=0,AC=0;
    private Instruction IR = null;
    Instruction[] mainMemory;
    private int[] registros = {0,0,0,0};
    private final Stack<Integer> stack;
    
    private BCP bcp = null;
    
    private void ejecutarInterrup(int codigo){
        /*
        TODO Agregar logica para las interrupciones
        Conexión con la interfaz gráfica
        */
    }

    public Cpu() {
        this.stack = new Stack<>();
    }
    
    public void setMemoria(Instruction[] mainMemory){
        this.mainMemory = mainMemory;
    }
    
    public void cargarBCP(BCP bcp){
        this.bcp = bcp;
        this.PC = bcp.getPC();
        this.registros = bcp.getRegistros();
    }
    
    /**
     * Ejecuta la etapa de fetch. aumenta en 1 el PC y carga en IR la instruccion
     * a ejecutar.
     */
    public void fetch(){
        PC = PC+1;
        IR = mainMemory[PC];
    }
    
    /**
     * Ejecuta la etapa de ejecución del CPU.Ejecuta la instruccion almacenada 
     * en el IR
     * @throws java.lang.Exception
     */
    public void execute() throws Exception{
        switch (IR.getInstruc()) {
                case 1 -> AC = registros[IR.getArgumentos().get(0)-1];
                case 2 -> registros[IR.getArgumentos().get(0)-1] = AC;
                case 3 -> registros[IR.getArgumentos().get(0)-1] = registros[IR.getArgumentos().get(1)-1];
                case 4 -> registros[IR.getArgumentos().get(0)-1] = IR.getArgumentos().get(1);
                case 5 -> AC = AC-registros[IR.getArgumentos().get(0)-1];
                case 6 -> AC = AC+registros[IR.getArgumentos().get(0)-1];
                case 7 -> AC++;
                case 8 -> registros[IR.getArgumentos().get(0)-1]++;
                case 9 -> AC--;
                case 10 -> registros[IR.getArgumentos().get(0)-1]--;
                case 11 -> {
                    int temp = registros[IR.getArgumentos().get(0)-1];
                    registros[IR.getArgumentos().get(0)] = registros[IR.getArgumentos().get(1)-1];
                    registros[IR.getArgumentos().get(1)-1] = temp;
                }
                case 12 -> ejecutarInterrup(IR.getArgumentos().get(0));
                case 13 -> {
                    var newPC = PC + IR.getArgumentos().get(0);
                    if ( newPC < bcp.getBase() || newPC > bcp.getBase() + bcp.getAlcance() ){
                        throw new Exception("Error de acceso de memoria");
                    }
                    PC = newPC - 1;
                }
                case 14 -> AC = registros[IR.getArgumentos().get(0)] - registros[IR.getArgumentos().get(1)];
                case 15 -> {
                    var newPC = PC + IR.getArgumentos().get(0);
                    if ( newPC < bcp.getBase() || newPC > bcp.getBase() + bcp.getAlcance() ) {
                        if (AC == 0){
                            PC = newPC - 1;
                        }
                    }
                }
                case 16 -> {
                    var newPC = PC + IR.getArgumentos().get(0);
                    if ( newPC < bcp.getBase() || newPC > bcp.getBase() + bcp.getAlcance() ) {
                        if (AC != 0){
                            PC = newPC - 1;
                        }
                    }
                }
                case 17 -> {
                    int argumentosSize = IR.getArgumentos().size();
                    if (stack.size() + argumentosSize > MAX_STACK_SIZE){
                        throw new Exception("Stack overflow");
                    }
                    for (int param : IR.getArgumentos()){
                        stack.push(param);
                    }
                }
                case 18 -> {
                    if (stack.size() == MAX_STACK_SIZE){
                        throw new Exception("Stack overflow");
                    }
                    stack.push(registros[IR.getArgumentos().get(0)-1]);
                }
                case 19 -> {
                    if ( stack.isEmpty()){
                        throw new Exception("Error stack vacío");
                    }
                    registros[IR.getArgumentos().get(0)] = stack.pop();
                }
            }
    }

    public int getMAX_MAIN_MEMORY() {
        return MAX_MAIN_MEMORY;
    }

    public int getMAX_DISK_MEMORY() {
        return MAX_DISK_MEMORY;
    }

    public int getMAX_STACK_SIZE() {
        return MAX_STACK_SIZE;
    }

    public int getPC() {
        return PC;
    }

    public int getAC() {
        return AC;
    }

    public Instruction getIR() {
        return IR;
    }

    public Instruction[] getMainMemory() {
        return mainMemory;
    }

    public int[] getRegistros() {
        return registros;
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public BCP getBcp() {
        return bcp;
    }

    

    @Override
    public String toString() {
        return "Registros : AX:" + this.registros[0] + " BX: " +
                this.registros[1] + " CX: " + this.registros[2] + " DX: " +
                this.registros[3] + "\n" + "AC: " + this.AC;
    }
    
    
    
}
