/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * @author Jhonny Picado
 * Clase del jugador Sogeking
 */
public class Sogeking extends Jugador{
    
    //Constructor de la clase
    public Sogeking(){
        super();
        this.nombre="Sogeking";
        this.tipo ="Dios";
        iniciarItems(); //Inicializa algunos items al instanciar el objeto
    }  
    
}
