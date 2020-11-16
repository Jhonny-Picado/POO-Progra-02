/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import java.util.List;
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
        darItem();
    }

    
    public void escuchar(){
    }
    
    public void darItem(){
        int si= (int)(Math.random()*2+1);  
        
        if (si==1){
            si= (int)(Math.random()*4+1);  
                switch(si){
                case 1:
                    this.articulos.add(new Arma());
                    break;
                case 2:
                    this.articulos.add(new Consumible());
                    break;
                case 3:
                    this.articulos.add(new Escudo());
                    break;
                case 4:
                    this.articulos.add(new Medalla());
                    break;
                default:
                    break;
            }
        }
    }
    
 
    public int RetornaEnemigo(List<Enemigo> enemigos,int[]posicion){
        int j=0;
        int []ci;
        for (int i=0; i<enemigos.size(); i++){
            ci=enemigos.get(i).getPosicion();
            if (ci[0]==posicion[0] && ci[1]==posicion[1]){
               j=i;
               break;
            }
        }
        return j;
    }
    
    
    
}

