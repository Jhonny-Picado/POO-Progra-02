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
        this.habilidad1=true;
        iniciarItems(); //Inicializa algunos items al instanciar el juego
    }
    
    //Metodo de la segunda habilidad de este jugador, le duplica el incremento de experiencia si ya posee la habilidad
    public int DuplicarExperiencia(int numero){
        if (this.habilidad2){
            numero=numero*2;
        }
        return numero;
    }  
    
    //La primera habilidad se implemento en el controlador, porque no era necesario hacer un metodo
    
    
    public String[] Nombrehabs(){
        String[] nombres= new String[3];
        
        if (this.habilidad1)
            nombres[0]="Moverse libre";
        if (this.habilidad2)
            nombres [1]="Duplicar Experiencia";
        if (this.habilidad3)
            nombres[2]="Esquivar";
        return nombres;
    }
        
        
}
