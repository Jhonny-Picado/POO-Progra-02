/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 *@author Jhonny Picado
 * Clase que hereda de personaje y a su vez es padre de los jugadores
 */
public class Jugador extends Personaje{
    
    //Atributos de la clase
    protected int nivel;
    protected int experiencia;
    protected String tipo;
    
    //Constructor
    Jugador(){        
        super();
        this.nivel=1;
        this.experiencia=0;
    }
    
    //Metodo utilizado para subir el nivel del jugador
    public void SubirNivel(){
        this.nivel+=1;
    }
    
    
    /*
    //Metodo utilizado para usar un item
    public int UsarItem(int posicion){
        
       return items.get(posicion);
    }
    
    public int UsarHabilidad(){
        return
    }*/
    
    //Metodo utilizado para modificar la experiencia del jugador
    public void setExperiencia(int valor){
        this.experiencia+=valor;
    }
}
