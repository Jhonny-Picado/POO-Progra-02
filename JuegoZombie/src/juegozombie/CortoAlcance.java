/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * Clase que instancia las armas de corto alcance
 * @author Jhonny Picado
 */
public class CortoAlcance extends Arma{
    
    //Listas para dar valores a algunos atributos
    private final String nombresE[]={"Arco","Mina","Cuchillo","Machete","Extintor","Shuriken","Piedra","Guantes","Manopla","Aguja"};
    
    //Constructor de la clase
    public CortoAlcance(){
        super();
        this.nombre= nombresE[(int) (Math.random()*9+0)];
        this.poder= (int)(Math.random()*15+3); 
        this.ruido= (int)(Math.random()*3+1); 
        this.tipo="Arma Corta";
    } 
}
