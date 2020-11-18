/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import Interfaz.GUI;
import Interfaz.Habilidades;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;

/**
 *
 * @author Jhonny Picado
 */
public class Controlador {
    
    //Constructor de la clase
    public Controlador(){
        
    }
    
    
    //Metodo utilizado para mostar las stats de los jugadores
    public void MostrarStats(GUI vista, Tanjiro tanjiro, Sogeking sogeking, Saitama saitama){
        
        vista.nJugador1.setText(tanjiro.getNombre());
        vista.especialidadT.setText(tanjiro.getEspecialidad());
        vista.vidaT.setText(Integer.toString(tanjiro.getVida()));
        vista.nivelT.setText(Integer.toString(tanjiro.getNivel()));
        vista.experienciaT.setText(Integer.toString(tanjiro.getExperiencia()));
        vista.defensetan.setText(Integer.toString(tanjiro.getDefensa()));
        vista.ataquetan.setText(Integer.toString(tanjiro.getAtaque()));
       
        vista.nJugador2.setText(sogeking.getNombre());
        vista.especialidadS.setText(sogeking.getEspecialidad());
        vista.vidaS.setText(Integer.toString(sogeking.getVida()));
        vista.nivelS.setText(Integer.toString(sogeking.getNivel()));
        vista.experienciaS.setText(Integer.toString(sogeking.getExperiencia()));
        vista.defenseSog.setText(Integer.toString(sogeking.getDefensa()));
        vista.ataqueSog.setText(Integer.toString(sogeking.getAtaque()));
        
        vista.nJugador3.setText(saitama.getNombre());
        vista.especialidadSa.setText(saitama.getEspecialidad());
        vista.vidaSa.setText(Integer.toString(saitama.getVida()));
        vista.nivelSa.setText(Integer.toString(saitama.getNivel()));
        vista.experienciaSa.setText(Integer.toString(saitama.getExperiencia()));
        vista.defenseSai.setText(Integer.toString(saitama.getDefensa()));
        vista.ataqueSai.setText(Integer.toString(saitama.getAtaque()));
    }
    
    
    //Método que actualiza y muestra la ventana donde aparecen los nombres de las habilidades de cada jugador
    public void MostrarHabilidades(Tanjiro tanjiro, Sogeking sogeking, Saitama saitama){
        Habilidades ventana=new Habilidades();
        
        String[] habsT= tanjiro.Nombrehabs();
        String[] habsSo= sogeking.Nombrehabs();
        String[] habsSa= saitama.Nombrehabs();
        
        
        ventana.habilidadT1.setText(habsT[0]);
        ventana.habilidadT2.setText(habsT[1]);
        ventana.habilidadT3.setText(habsT[2]);
        
        ventana.habilidadSo1.setText(habsSo[0]);
        ventana.habilidadSo2.setText(habsSo[1]);
        ventana.habilidadSo3.setText(habsSo[2]);
       
        ventana.habilidadSa1.setText(habsSa[0]);
        ventana.habilidadSa2.setText(habsSa[1]);
        ventana.habilidadSa3.setText(habsSa[2]);        
                
        ventana.setVisible(true);
    }
    
    
    //Metodo que da las posiciones iniciales a los jugadores
    public void darPosicionesIniciales(JButton[][]Casillas, Tanjiro tanjiro, Saitama saitama, Sogeking sogeking){
        
        Casillas[6][0].setBackground(Color.cyan);
        tanjiro.setPosicion(6,0);

        Casillas[7][1].setBackground(Color.blue);
        sogeking.setPosicion(7,1);
        
        Casillas[8][0].setBackground(Color.gray);
        saitama.setPosicion(8,0);
    }
    
    
    //Metodo que cambia el color de fondo de los botones en el tablero para indicar que estos son obstaculos 
    public void AsignarObstaculos(JButton[][]Casillas){
        int randf, randc;
        
        for(int i=0; i<18; i++){
            randf= (int)(Math.random()*14);
            randc=ThreadLocalRandom.current().nextInt(5, 11);
                if (Casillas[randf][randc].getBackground()==Color.red)
                Casillas[randf][randc].setBackground(Color.black);
        }
    }
}
