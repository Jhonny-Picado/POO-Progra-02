/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import static Interfaz.Mapa.vista;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhonny Picado
 */
public class Tanjiro extends Jugador{
    
    //Constructor del jugador Tanjiro
    public Tanjiro(){
        super();
        this.nombre="Tanjiro";
        this.tipo = "Espadachín";
        this.habilidad1=true;
        iniciarItems();     //Metodo que le añade algunos items en su inicializacion
    }
    
    
    //Metodo de la primera habilidad de este jugador, le da más capacidad de items si ya posee la habilidad
    public void MasCapacidad(Items item){
        if (this.habilidad1){
            this.articulos.add(item);
        }
        else
            JOptionPane.showMessageDialog(vista, "Habilidad aun no desbloqueada");
    }  
    
    
    //Metodo de la segunda habilidad de este jugador, le duplica el incremento de la vida si ya posee la habilidad
    public int DuplicarCuracion(int numero){
        
        if (this.habilidad2){
            return numero*2;
        }
        return numero;
    }  
        
    //Para la tercera habilidad de este personaje no es necesario hacer un metodo

    //Método que retorna los nombres de habilidades del personaje
    public String[] Nombrehabs(){
        String[] nombres= new String[3];
        
        if (this.habilidad1)
            nombres[0]="Aumentar Inventario";
        if (this.habilidad2)
            nombres [1]="Duplicar Curación";
        if (this.habilidad3)
            nombres[2]="Ocultarse en obstáculos";
        return nombres;
    }
}
