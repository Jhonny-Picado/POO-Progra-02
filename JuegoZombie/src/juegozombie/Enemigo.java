/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import java.util.List;
/**
* Clase padre de todos los enemigos del juego
 * @author Jhonny Picado
 */
public class Enemigo extends Personaje{
    
    //Atributos de la clase
    protected int vision;
    protected int escucha;
    
    //Constructor 
    public Enemigo(){        
        super();
        darItem();
        usoVision=false;
    }
    
    //Método que brinda un item para cade enemigo, envia un random para ver si darle item y otro para ver de que tipo le da
    public void darItem(){
        int si= (int)(Math.random()*2+1);  
        
        if (si==1){
            si=(int)(Math.random()*6+0);  
                switch(si){
                case 0:
                    this.articulos.add(new Consumible());
                    break;
                case 1:
                    this.articulos.add(new Escudo());
                    break;
                case 2:
                    this.articulos.add(new Medalla());
                    break;
                case 3:
                    this.articulos.add(new LargoAlcance());
                    break;
                default:
                    this.articulos.add(new CortoAlcance());
                    break;
            }
        }
    }
    
    
    //Método que retorna la posicion donde se encuentra el enemigo
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
    
    
    //Método que retorna la visión del enemigo
    public int getVisison(){
        return this.vision;
    }
    
    //Metodo que retorna el alcance de escucha de cada enemigo
    public int getEscucha(){
        return this.escucha;
    }
}

