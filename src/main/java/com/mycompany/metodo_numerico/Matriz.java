/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodo_numerico;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Matriz extends JTable {

    private int fila, columna, dato;
    private int[][] matriz;

    public Matriz() {
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

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    /*public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    } */
    public void setMatriz(int fila, int columna) {
        this.matriz = new int[fila][columna];
    }

    public void matrizAleatoria(JTable matriz) {        
        for (int i = 0; i < getFila(); i++) {
            for (int j = 0; j < getColumna(); j++) {
                int numeroAleatorio = (int) (Math.random() * 10 + 1);
                matriz.setValueAt(numeroAleatorio, i, j);
            }
        }        
    }
}
