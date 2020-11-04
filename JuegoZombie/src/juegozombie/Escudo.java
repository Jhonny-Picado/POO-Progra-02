/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * @author Jhonny Picado
 * Clase que genera los escudos del juego
 */
public class Escudo extends Items {
    
    //Listas para dar valores a algunos atributos
    private final String nombresE[]={"Bob","Rigo","Manu","Saul","Gogo","Fito","Chepe","Majo","Teo","ZaKo"};  //No sabía que poner XD
    private final String materiales[]={"Acero","Fibras","Madera","Plastico"};
    private final String material; 
    
    //Constructor de la clase
    Escudo(){
        super();
        this.nombre= nombresE[(int) (Math.random()*9+ 0)];
        this.material=materiales[(int) (Math.random()*3+0)];
        this.poder= (int)(Math.random()*12+5); 
    }
 
    public String getMaterial(){
        return this.material;
    }
}