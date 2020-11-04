/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * @author Jhonny Picado
 * Clase que da medallas en el juego, se usa para subir experiencia y nivel del jugador
 */
public class Medalla extends Items{
    
    //Listas para dar valores a algunos atributos
    private final String nombresM[]={"Lita","Pequeñina","Suertuda","Debil","Facil","Asombrosa","Fuerte","Comun","Celestial"};
    private final String categorias[]={"Oro","Plata","Bronce"};
    private final  String categoria;
    
    //Constructor de la clase
    Medalla(){
        super();
        this.nombre= nombresM[(int) (Math.random()*9+ 0)];
        this.categoria=categorias[(int) (Math.random()*2+0)];
        this.poder= (int)(Math.random()*12+5); 
    }
 
    public String getCategoria(){
        return this.categoria;
    }
    
}
