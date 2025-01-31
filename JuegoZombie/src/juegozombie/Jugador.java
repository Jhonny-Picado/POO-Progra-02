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
    protected boolean habilidad1;
    protected boolean habilidad2;
    protected boolean habilidad3;
    protected Arma armaActual;
    
    //Constructor de la clase
    public Jugador(){        
        super();
        this.nivel=1;
        this.experiencia=0;
        this.vida=100; 
        this.defensa=0; 
        this.usoItem=false;
        this.habilidad2=false;
        this.habilidad3=false;
        this.ataque=0;
    }
    
    //Metodo utilizado para subir el nivel del jugador, cada 30 de exp se sube de nivel
    public void SubirNivel(){
        if (experiencia>=30){
            this.nivel+=1;
            this.experiencia-=experiencia;
        }
        
        if ((nivel == 2 || nivel== 5 || nivel ==7)){
            switch(nivel){
                case 2:
                    setHabilidad1();
                    break;
                case 5:
                    setHabilidad2();
                    break;
                case 7:
                    setHabilidad3();
                    break;
            }   
        } 
    }
    
    
    //Metodo utilizado para usar un item
    public void UsarItem(Jugador jugador, int index){
        
        Items item=articulos.get(index);
        
        //Valida que tipo de item es para modificar las stats del personaje
        if ("Consumible".equals(item.getTipo()))
            setVida(jugador,item.getPoder());
        
        else if ("Medalla".equals(item.getTipo()))
            setExperiencia(item.getPoder());
        
        else if ("Escudo".equals(item.getTipo()))
            setDefensa(item.getPoder());
        
        else{
            setAtaque(jugador,item);
            this.armaActual=(Arma)item;
        }    
        this.articulos.remove(index);
    }
    
    //Getters y setter setters de los artibutos de los jugadores, valida de una una habilidad del jugador saitama
    public void setExperiencia(int valor){
       
        if ("Saitama".equals(this.getNombre())){
            Saitama sait=(Saitama)this;
            experiencia+=sait.DuplicarExperiencia(valor);
        }
        else
            this.experiencia+=valor;
        
        this.SubirNivel();
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
    
    public boolean AccionesJugador(){   
        return (this.usoItem==true && this.usoAtaque==true && this.usoMover==true);
    }
    
    //Metodo que modifican o muestran las habilidades de los jugadores(true or false)
    public void setHabilidad1(){
        if (!habilidad1){
            this.habilidad1=true;
            this.experiencia-=experiencia;
        }
    }
    
    public void setHabilidad2(){
        if (!habilidad2){
            this.habilidad2=true;
            this.experiencia-=experiencia;
        }
    }
    
    public void setHabilidad3(){
        if (!habilidad3){
            this.habilidad3=true;
            this.experiencia-=experiencia;
        }
    }
    
    public boolean getHabilidad1(){
        return this.habilidad1;
    }
    
    public boolean getHabilidad3(){
        return this.habilidad3;
    }

    //Alamcena la arma que esta en uso
    public Arma getArmaActual(){
        return this.armaActual;
    }
    
    //Este atributo es para ver la posicion de ataque que hubo
    public void setPosAtaque(int[] pos){
        this.posAtaque=pos;
    }
    
    public int[] getPosAtaque(){
        return this.posAtaque;
    }
    
    public void setMuerto(boolean condicion){
        this.muerto=condicion;
    }
}
