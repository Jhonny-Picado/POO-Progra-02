/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 *
 * @author Usuario
 */
public class Tanjiro extends Jugador{
    
    
    public Tanjiro(){
        super();
        this.nombre="Tanjiro";
        this.tipo = "Espadachín";
        iniciarItems();     //Metodo que le añade algunos items en su inicializacion
    }
}
