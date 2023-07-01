/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodo_numerico;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Matriz extends JTable {

    int dato;
    private int fila, columna;
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

    /*public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    } */
    public void setMatriz(int fila, int columna) {
        this.matriz = new int[fila][columna];
    }

    public void matrizAleatoria(JTable matriz) {
        if (matriz.getColumnCount() == 0) {
            JOptionPane.showMessageDialog(null, "configure la matriz primero");
        } else {
            for (int i = 0; i < this.fila; i++) {
                for (int j = 0; j < this.columna; j++) {
                    int numeroAleatorio = (int) (Math.random() * 10 + 1);
                    matriz.setValueAt(numeroAleatorio, i, j);
                }
            }
        }
    }
}
