/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * Clase padre de las armas
 * @author Jhonny Picado 
 */
public class Arma extends Items{
    
    //Atributos de las armas
    protected int nivel;
    protected int ruido; 
    protected int alcance;
    
    //Constructor por defecto
    public Arma(){ 
    }
    
    //Setters de los atributos
    public int nivel(){
        return this.nivel;
    }
    
    public int getRuido(){
        return this.ruido;
    }
    
    public int getalcance(){
        return this.alcance;
    }
}
