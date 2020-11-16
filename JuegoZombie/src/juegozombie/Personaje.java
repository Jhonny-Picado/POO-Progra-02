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
    List<Items> articulos = new ArrayList<>();      //Lista de items de cada personaje

    //Constructor de la clase
    public Personaje(){
        this.muerto=false;
        this.posicion=new int[2];
    }
    
    //Metodos getters y setters de los atributos
    public int getAtaque(){
        return this.ataque;
    }
    
    public void setAtaque(Jugador jugador,Items item){
        int numero=item.getPoder();
        Sogeking sog= new Sogeking();
        
        if ("Sogeking".equals(jugador.getNombre()) && "Arma Corta".equals(item.getTipo()))
            numero=sog.DuplicarAtaqueCorto(jugador,numero);
        this.ataque=numero;
    }
    
    //Metodo utilizado para restarle la vida al personaje
    public void RecibirDano(int golpe){
        this.vida-=golpe;
        if (vida<=0){
            vida=0;
            this.muerto=true;
        }
    }
    
    //Metodo para modificar el atributo booleano de muerto
    //se valida despues y se usan otros metodos en el mapa
    public boolean Muerto(){
        return this.muerto;
    }
    
    //Metodo a usar para incrementar la vida, ya sea por una habilidad o un item
    public void setVida(Personaje jugador,int incremento){
        Tanjiro tan=(Tanjiro)jugador;
        
        if (vida<100){
            if ("Tanjiro".equals(jugador.getNombre()))
                vida+=tan.DuplicarCuracion(incremento);
            else
            vida+=incremento;
            
            if (vida>100)
                vida=100;
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
    
    //Metodo que retorna la posicion en la cual se encuentra el jugador en el tablero
    public int [] getPosicion(){
        return this.posicion;
    }
    
    //Metodo que retorna la lista de items de cada personaje 
    public List<Items> getItems(){
        return this.articulos;
    }
    
    ///Modifica la posicion del usuario en el tablero
    public void setPosicion(int fila,int columna){
        this.posicion[0]=fila;
        this.posicion[1]=columna;
    }
    
    //Metodo protegido que inicia los items de los jugadores
    protected void iniciarItems(){
    
    //Le doy dos items de cada tipo a cada jugador
    for (int i=0; i<2; i++){
        Consumible con =new Consumible();
        this.articulos.add(con);
        
        Medalla med = new Medalla();
        this.articulos.add(med);
        
        Escudo esc= new Escudo();
        this.articulos.add(esc);
        }
        CortoAlcance cor =new CortoAlcance();
        this.articulos.add(cor);
    }
}
