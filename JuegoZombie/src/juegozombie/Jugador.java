/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import static Interfaz.Mapa.vista;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *@author Jhonny Picado
 * Clase que hereda de personaje y a su vez es padre de los jugadores
 */
public class Jugador extends Personaje{
    
    //Atributos de la clase
    protected int nivel;
    protected int experiencia;
    protected String tipo;
    protected boolean usoItem;
    protected boolean usoMover;
    protected boolean usoAtaque;
    protected boolean habilidad1;
    protected boolean habilidad2;
    protected boolean habilidad3;
    
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
        this.habilidad1=false;
        this.habilidad2=false;
        this.habilidad3=true;
        this.ataque=0;
    }
    
    //Metodo utilizado para subir el nivel del jugador
    public void SubirNivel(){
        this.nivel+=1;
    }
    

    //Metodo utilizado para usar un item
    public void UsarItem(Jugador jugador, int index){
        
        Items item=articulos.get(index);
        
        //Valida que tipo de item es para modificar las stats del personaje
        if ("Consumible".equals(item.getTipo()))
            setVida(jugador,item.getPoder());
        
        else if ("Medalla".equals(item.getTipo()))
            setExperiencia(jugador,item.getPoder());
        
        else if ("Escudo".equals(item.getTipo()))
            setDefensa(item.getPoder());
        
        else
            setAtaque(jugador,item);
        this.articulos.remove(index);
    }
    
    //Getters y setter setters de los artibutos de los jugadores
    public void setExperiencia(Jugador jugador, int valor){
        Saitama sait= new Saitama();
        if ("Saitama".equals(jugador.getNombre()))
            valor=sait.DuplicarExperiencia(valor);
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
    
    public void setHabilidad1(){
        this.habilidad1=true;
    }
    
    public boolean getHabilidad1(){
        return this.habilidad1;
    }
    public boolean getHabilidad3(){
        return this.habilidad3;
    }

/*
    public void AgregarItem(Jugador jugador, DefaultTableModel tabla){
        Consumible con = new Consumible();
        Tanjiro user=new Tanjiro();
        
        if (jugador.getItems().size()<8){    
            jugador.AgregarItem(con);
            AñadirRow(con, tabla);
           }
        else if("Tanjiro".equals(jugador.getNombre()))
            user=(Tanjiro)(jugador);
            user.MasCapacidad(con);
        }
        else{
            JOptionPane.showMessageDialog(vista, "El personaje no tiene la habilidad de poseer más items");
        }
    }*/
    
    
    public void ResetearUsos(){
        this.usoItem=false;
        this.usoAtaque=false;
        this.usoMover=false;
    }

}
