/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhonny Picado
 * Clase padre de la cual herendan tanto los enemigos como los jugadores
 */
public class Personaje {
    
    //Atributos de la clase padre
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected boolean muerto;
    protected String nombre;
    List<Items> articulos = new ArrayList<>();
    
    //Constructor de la clase
    Personaje(){
        this.vida=100;
        this.defensa=100;
        this.muerto=false;
    }
    
    
    //Metodo que recibe de parametro un numero, suma el ataque del personaje
    //y se usara despues para restar la defensa, vida y demás 
    public int Atacar(int ataque){
        this.ataque=ataque;
        return ataque;
    }
    
    //Metodo utilizado para restarle la vida al personaje
    public void RecibirDano(int golpe){
        this.vida-=golpe;    
    }
    
    //Metodo para modificar el atributo booleano de muerto
    //se valida despues y se usan otros metodos en el mapa
    public void Matar(){
        if (vida<1){
            this.muerto=true;
        }
    }
    
    //Metodo a usar para incrementar la vida, ya sea por una habilidad o un item
    public void IncrementarVida(int incremento){
        vida+=incremento;
    }

    public void setDefensa(int valor){
        this.defensa=valor;
    }
    
    public int getDefensa(){
        return this.defensa;
    }
    
    //Metodo utilizado para agregar items a la lista de cada personaje
    public void AgregarItem(Items item){
        this.articulos.add(item);
    }
}
