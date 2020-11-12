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
    protected int [] posicion;
    protected boolean muerto;
    protected String nombre;
    List<Items> articulos = new ArrayList<>();

    //Constructor de la clase
    public Personaje(){
        this.vida=100;
        this.defensa=100;
        this.muerto=false;
        this.posicion=new int[2];
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
    public void Muerto(){
        if (vida==0){
            this.muerto=true;
        }
    }
    
    //Metodo a usar para incrementar la vida, ya sea por una habilidad o un item
    public void setVida(int incremento){
        if (vida<100){
            vida+=incremento;
            if (vida<0)
                vida=0;
        }
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
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getVida(){
        return this.vida;
    }
    
    public void setPosicion(int fila,int columna){
        this.posicion[0]=fila;
        this.posicion[1]=columna;
    }
    
    public int [] getPosicion(){
        return this.posicion;
    }
    
    public List<Items> getItems(){
        return this.articulos;
    }
    
    
    //Metodo protegido que inicia los items de los jugadores
    protected void iniciarItems(){
        
        for (int i=0; i<2; i++){
        Consumible con =new Consumible();
        this.articulos.add(con);
        }
       Medalla med = new Medalla();
       this.articulos.add(med);
    }
}
