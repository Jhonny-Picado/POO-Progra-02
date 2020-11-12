/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * @author Jhonny Picado
 * Clase que hereda de Items y genera los consumibles del juego
 */
public class Consumible extends Items {
    
    //Listas para dar valores a algunos atributos
    private final String nombresC[]={"Paleta", "Fresquito", "Elixir", "Agua", "Frijoli","Ramen","Tamal","Cafe","Zake","Semillas"};
    
    //COnstructor de la clase
    Consumible(){
        super();
        this.nombre= nombresC[(int) (Math.random()*9+ 0)];
        this.poder= (int)(Math.random()*12+5); 
        this.tipo="Consumible";
    }
    
}
