/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 * @author Jhonny Picado
 * Clase del jugador Sogeking
 */
public class Sogeking extends Jugador{
    
    //Constructor de la clase
    public Sogeking(){
        super();
        this.nombre="Sogeking";
        this.tipo ="Dios";
        this.habilidad1=true;
        iniciarItems(); //Inicializa algunos items al instanciar el objeto
        ArmaInicial();
    }  
    
    
    //Metodo de la primera habilidad de este jugador
    //Esta se brinda apenas se inicia el juego y le da dos armas de largo alcance a este jugador
    public void ArmaInicial(){
        for(int i=0; i<2; i++){
            LargoAlcance arma= new LargoAlcance();
            this.articulos.add(arma);
        }
    }
    
    
    //Metodo de la segunda habilidad de este jugador, le duplica el incremento de ataque si ya posee la habilidad
    public int DuplicarAtaqueCorto(Jugador jugador,int numero){
        if (jugador.habilidad2){
            numero*=2;
        }
        return numero;
    }      

    //Metodo que retorna el nombre de las habilidades del jugador
    public String[] Nombrehabs(){
        String[] nombres= new String[3];
        
        if (this.habilidad1)
            nombres[0]="Arma Grande inicial";
        if (this.habilidad2)
            nombres [1]="Duplicar Arma-Corta";
        if (this.habilidad3)
            nombres[2]="Revivir";
        return nombres;
    }
}