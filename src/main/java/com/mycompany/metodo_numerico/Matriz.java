/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodo_numerico;
import javax.swing.JTable;

/**
 *
 * @author Familiar
 */
public class Matriz extends JTable {
    private int fila, columna;
    private int [][]matriz;

    public Matriz(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.matriz = new int[this.fila][this.columna];
    }   

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    /*public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    } */
    
    public void setMatriz(int fila, int columna) {
        this.matriz = new int[fila] [columna];
    }    
    
    public void cargarMatrizRandom(){
        
    }
}
