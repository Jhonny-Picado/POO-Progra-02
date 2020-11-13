/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 *
 * @author Jhonny Picado
 */
public class Saitama extends Jugador{
    
    public Saitama(){
        super();
        this.nombre = "Saitama";
        this.tipo = "Super heroe";
        iniciarItems(); //Inicializa algunos items al instanciar el juego
    }
    
    //Metodo de la segunda habilidad de este jugador, le duplica el incremento de experiencia si ya posee la habilidad
    public int DuplicarExperiencia(int numero){
        if (this.habilidad2){
            numero*=2;
        }
        return numero;
    }  
    
    //La primera habilidad se implemento en el controlador, porque no era necesario hacer un metodo
}
