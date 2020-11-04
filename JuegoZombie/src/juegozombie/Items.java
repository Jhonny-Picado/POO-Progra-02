/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * @author Jhonny Picado
 * Clase padre de los items del juego
 */
public class Items {  
    
    //Atributos de la clase
    protected String nombre;
    protected int poder;
    protected int contador=0;
    
    //Constructor por defecto
    Items(){
    }
    
    //Metodo utilizado para usar el item 
    public int UsarItem(){
        return this.poder;
    }
    
}
