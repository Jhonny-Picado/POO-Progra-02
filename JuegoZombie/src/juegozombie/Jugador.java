/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 *@author Jhonny Picado
 * Clase que hereda de personaje y a su vez es padre de los jugadores
 */
public class Jugador extends Personaje{
    
    //Atributos de la clase
    protected int nivel;
    protected int experiencia;
    protected String tipo;
    
    //protected int posicion;
    protected boolean usoItem;
    protected boolean usoMover;
    protected boolean usoAtaque;
    
    
    //Constructor
    public Jugador(){        
        super();
        this.nivel=1;
        this.experiencia=0;
        this.vida=(int)(Math.random()*50+20); 
        this.defensa=0; 
        this.usoItem=false;
        this.usoAtaque=false;
        this.usoMover=false;
    }
    
    //Metodo utilizado para subir el nivel del jugador
    public void SubirNivel(){
        this.nivel+=1;
    }
    

    //Metodo utilizado para usar un item
    public void UsarItem(int index){
        
        Items item=articulos.get(index);
        
        //Valida que tipo de item es para modificar las stats del personaje
        if ("Consumible".equals(item.getTipo()))
            setVida(item.getPoder());
        
        else if ("Medalla".equals(item.getTipo()))
            setExperiencia(item.getPoder());
        
        else if ("Escudo".equals(item.getTipo()))
            setDefensa(item.getPoder());
        
        else
            setAtaque(item.getPoder());
        this.articulos.remove(index);
    }
    
    //Getters y setter setters de los artibutos de los jugadores
    public void setExperiencia(int valor){
        this.experiencia+=valor;
    }
    
    public String getEspecialidad(){
        return this.tipo;
    }
    
    public int getNivel(){
        return this.nivel;
    }
    
    public int getExperiencia(){
        return this.experiencia;
    }
    
    //Los metodos siguientes son para saber si ya realizaron esta acciones para llevar el flujo de los turnos del juego
    public boolean getUsoItem(){
        return this.usoItem;
    }
    
    public void setUsoItem(boolean condicion){
        this.usoItem=condicion;
    }
    
    public boolean getUsoMover(){
        return this.usoMover;
    }
    
    public void setUsoMover(boolean condicion){
        this.usoMover=condicion;
    }
    
    
    public boolean getUsoAtaque(){
        return this.usoAtaque;
    }
    
    public void setUsoAtaque(boolean condicion){
        this.usoAtaque=condicion;
    }
    
    public boolean AccionesJugador(){   
        return (this.usoItem==true && this.usoAtaque==true && this.usoMover==true);
    }
}
