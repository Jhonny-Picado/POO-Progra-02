/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhonny Picado
 */
public class Enemigo extends Personaje{
    
    //Atributos de la clase
    protected int vision;
    protected int alcance;
            
    //Constructor
    public Enemigo(){        
        super();
    }

    
    public void escuchar(){
    }
    
    
    public void dejarItem(){
    }
    
    public Enemigo RetornaEnemigo(List<Enemigo> enemigos,int[]posicion){
        
        for (Enemigo enemigo:enemigos){
            
            if (enemigo.getPosicion()==posicion){
                return enemigo;
            }
        }
        return null;
    }
    
    
    
}

