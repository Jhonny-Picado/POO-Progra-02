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
 * @author Usuario
 */
public class Tanjiro extends Jugador{
    
    public Tanjiro(){
        super();
        this.nombre="Tanjiro";
        this.tipo = "Espadachín";
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
            numero*=2;
        }
        return numero;
    }  
}
