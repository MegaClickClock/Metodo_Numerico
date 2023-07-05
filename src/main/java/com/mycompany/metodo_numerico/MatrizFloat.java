/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.metodo_numerico;

import static java.awt.image.ImageObserver.HEIGHT;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author examp
 */
public class MatrizFloat extends JTable {
    final float nulo = (float) -9999.00;
    private int fila, columna;
    private float [][]matriz;
    private float []solucion = new float[3];
    MatrizFloat matActual = this;        

    public MatrizFloat(int fila, int columna, float[][] matriz) {
        this.fila = fila;
        this.columna = columna;
        this.matriz = matriz;
    }
    
    public MatrizFloat(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.matriz = new float[fila][columna];
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

    public float[][] getMatriz() {
        return this.matriz;
    }
    
    public float getDato(int fila, int columna){
        return this.matriz[fila][columna];
    } 
    
    public void setDato(int fila, int columna, float dato){
        this.matriz[fila][columna] = dato;
    } 
    
    public void setMatriz(JTable inputMatriz) {
        this.matriz = new float [inputMatriz.getRowCount()][inputMatriz.getColumnCount()];
        for (int i = 0; i < inputMatriz.getRowCount(); i++) {
            for (int j = 0; j < inputMatriz.getColumnCount(); j++) {                
                matriz[i][j] = Integer.parseInt( inputMatriz.getValueAt(i, j).toString());
            }
        }
    }   
    
    private void inicializar(){
        for(int f=0; f<fila; f++){
            for(int c=0; c<columna; c++){
                
                matriz[f][c] = nulo;
            }
        }
    }
    
    private void setSolucion(float x1, float x2, float x3){
        this.solucion[0] = x1;
        this.solucion[1] = x2;
        this.solucion[2] = x3;
    }
    
    public float[] getSolucion(){
        return solucion;
    }
    
    private void ponerColumnasACeros(int filaPivote){
        for(int c=0; c<=filaPivote; c++){
            for(int f=0; f<this.getFila(); f++){
               if(f!=c){
                   this.setDato(f, c, 0);
               } 
            }
        }
    }
    
    private MatrizFloat pasarNuevaMatriz(int filapivote){
        MatrizFloat mat = new MatrizFloat(this.getFila(), this.getColumna());
        mat.inicializar();
        System.out.println("Nueva matriz : " + filapivote);
        // Aqui copiamos el pivote desde la posicion(0,0) hasta la posicion(filapivote, filapivote)
        for(int f=0; f<this.getFila(); f++){
            for(int c=0; c<this.getColumna(); c++){ 
                if ((f<filapivote) && (f==c)){
                    mat.setDato(f, f, this.getDato(filapivote, filapivote));
                }
            }
        }
        //poner ceros arriba y abajo del pivote...
        mat.ponerColumnasACeros(filapivote);    
        
        for(int f=0; f<this.getFila(); f++){
            for(int c=0; c<this.getColumna(); c++){                
                if (f==filapivote){
                    mat.setDato(f, c, this.getDato(f, c));
                } 
                System.out.print(mat.getDato(f, c) + ", ");
            }
            System.out.println("");
        }
        return mat;
    }
        
    private void mostrarDatos(){
        for(int f=0; f<this.fila; f++){
            for(int c=0; c<this.columna; c++){ 
                System.out.print(getDato(f, c) + ", ");
            }
            System.out.println("");
        }
    }
    
    private float calcularDeterminante(float pivote, int factor){
        float det;
        float dPrincipal = this.getDato(0, 0) * this.getDato(1, 1);
        float dSecundaria = this.getDato(1, 0) * this.getDato(0, 1);
        det = ((dPrincipal - dSecundaria) / pivote) * factor;
        return det;
    }
    
    private void dividirPorEscalar(float escalar){
        for(int f=0; f<this.fila; f++){
            for(int c=0; c<this.columna; c++){ 
                this.setDato(f, c, this.getDato(f, c)/escalar);
            }
        }
    }
    
    public void calcularDatosRestantes(MatrizFloat m, int posFilPivote, float pivote){
        // Posicion del pivote actual de la matriz determinante en m1[posFilPivote][posFilPivote]
        // pivote es el factor pivote de la anterior matriz...
        // factor para multiplicar el resultado del determinante
        System.out.println("El nuevo pivote es : " + m.getDato(posFilPivote, posFilPivote));
        int factor; 
        for(int f=0; f<m.getFila(); f++){
            for (int c=0; c<this.getColumna(); c++) {
                if(this.getDato(f, c)==nulo){ 
                    System.out.println("dato vacio en " + f + ", " + c);
                    if((f-posFilPivote)>0)
                        factor=1;
                    else
                        factor=-1;
                    MatrizFloat matDet = new MatrizFloat(2, 2);
                    // poniendo datos a la matriz para calcular su determinante
                    if(factor==1){
                        matDet.setDato(0, 0, m.getDato(posFilPivote, posFilPivote));
                        matDet.setDato(0, 1, m.getDato(posFilPivote, c));                        
                        matDet.setDato(1, 0, m.getDato(f, posFilPivote));
                        matDet.setDato(1, 1, m.getDato(f, c));                        
                    } else {
                        matDet.setDato(0, 0, m.getDato(f, posFilPivote));
                        matDet.setDato(0, 1, m.getDato(f, c));                        
                        matDet.setDato(1, 0, m.getDato(posFilPivote, posFilPivote));
                        matDet.setDato(1, 1, m.getDato(posFilPivote, c));
                    }   
                    float datoCalculado = matDet.calcularDeterminante(pivote, factor);
                    matDet.mostrarDatos();
                    System.out.println("El determinante es : " + datoCalculado);                        
                    this.setDato(f, c, datoCalculado);
                }
            }
        }        
    }
    
    public void metodoMontante(){       
       ArrayList<Float> pivotes = new ArrayList<>();
       // El primer pivote siempre es 1...
       pivotes.add((float) 1);
       //pivotes.add(1);
       
       for(int i=1; i<=3; i++){
            MatrizFloat matTransformada = matActual.pasarNuevaMatriz(i-1);
            float pivote = pivotes.get(pivotes.size()-1);
            matTransformada.calcularDatosRestantes(matActual, i-1, pivote);
            pivotes.add(matActual.getDato(i-1, i-1));
            matActual = matTransformada;
       }
       // Si la diagonal principal no son unos...
       // se tiene que llevar la diagonal principal a la unidad (mat identidad)
        if(matActual.getDato(0, 0) != 1){
            matActual.dividirPorEscalar(matActual.getDato(0, 0));            
        }
        this.setSolucion(matActual.getDato(0, 3), matActual.getDato(1, 3), matActual.getDato(2, 3));
        System.out.println("SOLUCION : ");
        matActual.mostrarDatos();
        this.mostrarDatos();        
    }    
    
    public void mostrarResultado(JTable matrizResultado){
        for(int f=0; f<this.fila; f++){
            for(int c=0; c<this.columna; c++){ 
                //System.out.print(getDato(f, c) + ", ");
                matrizResultado.setValueAt(matActual.getDato(f, c), f, c);
            }
            //System.out.println("");
        }                               
    }
}
