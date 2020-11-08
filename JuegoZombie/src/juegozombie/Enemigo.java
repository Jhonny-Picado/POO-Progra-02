/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 *
 * @author Chris
 */
public class Enemigo extends Personaje{
    
    //Atributos de la clase
    protected int vision;
    protected int sonido;
    protected String tipo;
    
    //Constructor
    Enemigo(){        
        super();
        this.vision=1;
        this.sonido=0;
    }
    
    //Para subir la vision en el tablero
    public void AumentarVision(){
        this.vision+=1;
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
    public void set(int valor){
        this.sonido+=valor;
    }
}

