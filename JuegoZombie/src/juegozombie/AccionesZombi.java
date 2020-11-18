/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

import Interfaz.Mapa;
import java.awt.Color;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * 
 * @author Jhonny Picado
 */
public class AccionesZombi {
    
    //Constructor de la Clase Acciones Zombi
    public AccionesZombi(){
        
    }
    
    
    //Método que lleva las reglas de los enemigos, primero intenta ataque, sino ve, sino escucha y si no se cumple ninguna se mueve hacia la base 
    public void ReglasEnemigos(Tanjiro tanjiro, Saitama saitama, Sogeking sogeking, List<Enemigo> enemigos, JButton[][]Casillas){
        AtaqueZombiAux(tanjiro, sogeking, saitama, enemigos, Casillas);
        AuxVision(tanjiro, sogeking, saitama, enemigos, Casillas);
        EscuchaAux(tanjiro, sogeking, saitama, enemigos, Casillas);
        MoverEnemigo(enemigos, Casillas);        
        

        //Reiniciar usos de acciones, del jugador y de los enemigos
        tanjiro.ResetearUsos();
        saitama.ResetearUsos();
        sogeking.ResetearUsos();
        for(Enemigo e: enemigos){
            e.ResetearUsos();}
        
        JOptionPane.showMessageDialog(null, "Aparecen más enemigos");
        Mapa m= new Mapa();
        m.SpawningPoints(2);        //Intenta poner más Spawning Points
    }

    
    
    //Metodo que le dice a los zombies que ataquen a determinado jugador
    public void AtaqueZombiAux(Tanjiro tanjiro, Sogeking sogeking, Saitama saitama, List<Enemigo> enemigos, JButton[][]Casillas){
        VerificarAtaqueZombi(tanjiro, enemigos, Casillas);
        VerificarAtaqueZombi(sogeking,enemigos,Casillas);
        VerificarAtaqueZombi(saitama, enemigos, Casillas);
    }
    
    
    //Metodo que verifica si los enemigos pueden atacar, solo ataquen si esta a una casilla de él
    public void VerificarAtaqueZombi(Jugador jugador, List<Enemigo> enemigos,JButton[][]Casillas){
        
        //Válida que el jugador no este muerto
        if (!jugador.Muerto()){
            
            int [] iJugador= jugador.getPosicion();     //Toma su posicion
            
            for(int i=0; i<enemigos.size(); i++){   //Recorro los enemigos y comparo su posiciones proximas con la posicion del jugador
            
                int []iEnemigo=enemigos.get(i).getPosicion();   //Pido la posicion del enemigo
                
                //Válido a los lados superiores y laterales si se encuentra para que lo ataque
                if (iEnemigo[0]==iJugador[0] && iEnemigo[1]-1 == iJugador[1])
                    AtaqueZombi(jugador,enemigos.get(i), Casillas);
                
                else if(iEnemigo[0]==iJugador[0] && iEnemigo[1]+1 == iJugador[1])
                    AtaqueZombi(jugador,enemigos.get(i), Casillas);
                
                else if(iEnemigo[0]+1==iJugador[0] && iEnemigo[1] == iJugador[1])
                    AtaqueZombi(jugador,enemigos.get(i), Casillas);
                
                else if(iEnemigo[0]-1==iJugador[0] && iEnemigo[1] == iJugador[1])
                    AtaqueZombi(jugador,enemigos.get(i), Casillas);
            }
        }
    }
    
    
    //Método que ataca al jugador, despues de la validación
    public void AtaqueZombi(Jugador jugador, Enemigo enemigo, JButton[][]Casillas){
        
            JOptionPane.showMessageDialog(null, "Un Zombie tipo "+enemigo.getNombre()+" ataca a "+jugador.getNombre()); //Envia msj de info
            jugador.RecibirDano(enemigo.getAtaque());   //Le bajo la vida al jugador, conforme el ataque de enemigo
            enemigo.setUsoAtaque(true);         //Le indico que el enemigo uso el ataque
            Borrar(jugador, Casillas);          //Llama a un metodo que pregunta si el ataque lo mato

    }
    
    
    //Método que borra al jugador del tablero si el ataque lo mato
    public void Borrar(Jugador jugador, JButton[][]Casillas){
        
        //Muestra las stats
        Mapa m= new Mapa();
        m.MostrarStats();
        
        //Verifico si es sogeking y si tiene la tercera habilidad, si es si tons lo revive
        if (jugador.Muerto() && "Sogeking".equals(jugador.getNombre()) && jugador.getHabilidad3()){
            JOptionPane.showMessageDialog(null, "Han asesinado al jugador Sogeking, pero con la habilidad 3 este revive");
            jugador.setVida(jugador, 100);
            jugador.setMuerto(false);
            m.MostrarStats();
        }
        else if (jugador.Muerto()){     //Aca si esta muerto lo desaparece y envia msj
            int [] posicion=jugador.getPosicion();
            Casillas[posicion[0]][posicion[1]].setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "El jugador"+jugador.getNombre()+"ha muerto :(");
        }  
    }
    
    
    //Método que llama a que lo enemigos utilicen la vision
    public void AuxVision(Tanjiro tanjiro, Sogeking sogeking, Saitama saitama,List<Enemigo> enemigos, JButton[][] Casillas){
        Vision(tanjiro, enemigos, Casillas);
        Vision(sogeking, enemigos, Casillas);
        Vision(saitama, enemigos, Casillas);
    }
    
    
    //Metodo que recorre los enemigos y determina si uno tiene algun enemigo a la vista
    public void Vision(Jugador jugador, List<Enemigo> enemigos, JButton[][]Casillas){
        
        if (!jugador.Muerto()){//Valida que no este muerto
            int [] iJugador= jugador.getPosicion(); //Guarda la posicion del jugador
            
            for(int i=0; i<enemigos.size(); i++){
                
                int j=2;            //Inicio j en 2, para empiece a observar a una distancia de dos casillas
                int []iEnemigo=enemigos.get(i).getPosicion();
                int range=enemigos.get(i).getVisison();     //Le doy el rango de vision
                
                while(j<=range){    //Recorro las casillas en el rango de vision de cada enemigo
                    if (enemigos.get(i).getUsoAtaque()) {   //Si ya ataco que no haga nada
                        j=5;
                    } 
                    
                    else if (iEnemigo[0]==iJugador[0] && iEnemigo[1]-j == iJugador[1]){ //Valido las casillas de la izquierda del enemigo
                        enemigos.get(i).setUsoVision(true);
                        if (Casillas[iEnemigo[0]][iEnemigo[1]-1].getBackground()==Color.red){//Si esta vacia, me muevo
                            int [] npos={iEnemigo[0],iEnemigo[1]-1};
                            TerminarVision(Casillas, iEnemigo, npos, enemigos.get(i));
                        }
                        j=5;
                    }
                
                    else if(iEnemigo[0]==iJugador[0] && iEnemigo[1]+j == iJugador[1]){ //Valido las casillas de la derecha del enemigo
                        enemigos.get(i).setUsoVision(true);
                        if (Casillas[iEnemigo[0]][iEnemigo[1]+1].getBackground()==Color.red){//Si esta vacia, me muevo
                            int [] npos={iEnemigo[0],iEnemigo[1]+1};
                            TerminarVision(Casillas, iEnemigo, npos, enemigos.get(i));
                        }
                        j=5;
                    }
                    
                    else if(iEnemigo[0]+j==iJugador[0] && iEnemigo[1] == iJugador[1]){  //Valido las casillas de abajo del enemigo
                        enemigos.get(i).setUsoVision(true);
                        if (Casillas[iEnemigo[0]+1][iEnemigo[1]].getBackground()==Color.red){ //Si esta vacia, me muevo
                            int [] npos={iEnemigo[0]+1,iEnemigo[1]};
                            TerminarVision(Casillas, iEnemigo, npos, enemigos.get(i));
                        }
                        j=5;
                    }
                
                    else if(iEnemigo[0]-j==iJugador[0] && iEnemigo[1] == iJugador[1]){ //Valido las casillas de arriba del enemigo
                        enemigos.get(i).setUsoVision(true);
                        if (Casillas[iEnemigo[0]-1][iEnemigo[1]].getBackground()==Color.red){//Si esta vacia, me muevo
                            int [] npos={iEnemigo[0]-1,iEnemigo[1]};
                            TerminarVision(Casillas, iEnemigo, npos, enemigos.get(i));
                        }
                        j=5;
                    }
                j++;
                }
            }
        }    
    } 
  
    
    //Metodo que mueve los colores del tablero y le da la nueva posicion al enemigo, además activa el uso
    public void TerminarVision(JButton[][]Casillas, int[] pos, int []nuevapos, Enemigo enemigo){
        Casillas[pos[0]][pos[1]].setBackground(Color.red);       
        Casillas[nuevapos[0]][nuevapos[1]].setBackground(Color.green);
        enemigo.setPosicion(nuevapos[0], nuevapos[1]);
    }
    
   
    //Método que llama a que lo enemigos utilicen la escucha
    public void EscuchaAux(Tanjiro tanjiro, Sogeking sogeking, Saitama saitama, List<Enemigo> enemigos, JButton[][] Casillas){
        Escucha(tanjiro,enemigos,Casillas);
        Escucha(sogeking,enemigos,Casillas);
        Escucha(saitama,enemigos,Casillas);
    }
    
    
    //Metodo que recorre los enemigos para ver la escucha de cada uno
    public void Escucha(Jugador jugador, List<Enemigo> enemigos, JButton[][] Casillas){
        
        int []a=jugador.getPosAtaque(); //Guardo la posicion de ataque del jugador
        
        if(a!=null && a[0] != 20){ //Verifico que haya habido un ataque y que sea una posicion de verdad
            
            for (int i=0; i<enemigos.size(); i++){      //Recorre los enemigos
                
                int j=1;            //Inicio la j en 1, para que escuche a partir de una casilla hacia algun lado
                int []posE=enemigos.get(i).getPosicion();
                int range=enemigos.get(i).getEscucha();
                
                while(j<=range) { //Recorro todo el rango de escuch de cada enemigo
                
                    if (enemigos.get(i).getUsoAtaque() || enemigos.get(i).getUsoVision()){}     //Verifico que no haya usado otra accion
                    
                    else if(posE[0]==a[0] && posE[1]-j ==a[1]){ //Verifica si se encuentra cerca de un ataque, hacia la izquierda
                        enemigos.get(i).setUsoEscucha();
                        if (Casillas[posE[0]][posE[1]-1].getBackground()==Color.red){ //Si la casilla esta vacia, me muevo
                            int [] npos={posE[0],posE[1]-1};
                            TerminarEscucha(Casillas, posE, npos, enemigos.get(i));
                        }
                        j=10;
                    }
                    
                    else if(posE[0]==a[0] && posE[1]+j==a[1]){//Verifica si se encuentra cerca de un ataque, hacia la derecha
                        enemigos.get(i).setUsoEscucha();
                        if (Casillas[posE[0]][posE[1]+1].getBackground()==Color.red){//Si la casilla esta vacia, me muevo
                            int [] npos={posE[0],posE[1]+1};
                            TerminarEscucha(Casillas, posE, npos, enemigos.get(i));
                        }
                        j=10;
                    }
                    
                    else if(posE[0]-j==a[0] && posE[1] ==a[1]){//Verifica si se encuentra cerca de un ataque, hacia arriba
                        
                        enemigos.get(i).setUsoEscucha();
                        if (Casillas[posE[0]-1][posE[1]].getBackground()==Color.red){//Si la casilla esta vacia, me muevo
                            int [] npos={posE[0]-1,posE[1]};
                            TerminarEscucha(Casillas, posE, npos, enemigos.get(i));
                        }
                        j=10;
                    }
                    
                    else if(posE[0]+j==a[0] && posE[1] ==a[1]){ //Verifica si se encuentra cerca de un ataque, hacia abajo
                        enemigos.get(i).setUsoEscucha();
                        if (Casillas[posE[0]+1][posE[1]].getBackground()==Color.red){//Si la casilla esta vacia, me muevo
                            int [] npos={posE[0]+1,posE[1]};
                            TerminarEscucha(Casillas, posE, npos, enemigos.get(i));
                        } 
                        j=10;
                    }
                    j++;
                }
            } 
        }
    }        
    

    //Metodo que mueve los colores del tablero y le da la nueva posicion al enemigo, además activa el uso, en la escucha
    public void TerminarEscucha(JButton[][]Casillas, int[] pos, int []nuevapos, Enemigo enemigo){
        Casillas[pos[0]][pos[1]].setBackground(Color.red);       
        Casillas[nuevapos[0]][nuevapos[1]].setBackground(Color.green);
        enemigo.setPosicion(nuevapos[0], nuevapos[1]);
    }
    
    
    //Metodo que mueve el enemigo hacia la base
    public void MoverEnemigo(List<Enemigo>enemigos, JButton[][]Casillas){
        
        int []temporal;
        
        for (Enemigo enemigo : enemigos) {  //Recorre la lista de enemigos
            
            temporal=enemigo.getPosicion(); //Le da la posicion de cada enemigo
            
            if(!ValidarDerrota(temporal, Casillas, enemigo)){ //Valida que no haya  habido derrota, ni algun movimiento
                
                //Valida movimiento hacia la izquierda
                if (temporal[1]!=0 && Casillas[temporal[0]][temporal[1]-1].getBackground() == Color.red){
                    Casillas[temporal[0]][temporal[1]-1].setBackground(Color.green);
                    Casillas[temporal[0]][temporal[1]].setBackground(Color.red);
                    enemigo.setPosicion(temporal[0], temporal[1]-1); 
                }
                
                //Válida movimiento hacia arriba
                else if(temporal[0]!=0 && Casillas[temporal[0]-1][temporal[1]].getBackground() == Color.red){
                    Casillas[temporal[0]-1][temporal[1]].setBackground(Color.green);
                    Casillas[temporal[0]][temporal[1]].setBackground(Color.red);
                    enemigo.setPosicion(temporal[0]-1, temporal[1]); 
                }
                
                //Valida movimiento hacia abajo
                else if(temporal[0]!=14 && Casillas[temporal[0]+1][temporal[1]].getBackground() == Color.red){
                    Casillas[temporal[0]+1][temporal[1]].setBackground(Color.green);
                    Casillas[temporal[0]][temporal[1]].setBackground(Color.red);
                    enemigo.setPosicion(temporal[0]+1, temporal[1]);        
                }
            }
        }
    }
    
    
    //Metodo que valida si hubo derrota, o algun movimiento no regular
    public boolean ValidarDerrota(int []temporal, JButton[][]Casillas, Enemigo enemigo){
                    
        if (enemigo.getUsoAtaque() || enemigo.getUsoVision()|| enemigo.getUsoEscucha())    //Valida que no haya realizado ninguna accion
            return true;
        
        else if(temporal[0]==7 && temporal[1]-1==0){    //Valida que la siguiente casilla no sea la base
            JOptionPane.showMessageDialog(null, "Has Perdido!!");  
            System.exit(0);
        }
           
        else if(temporal[1]==0 && temporal[0]<7) {  //Valida si esta en la primera columna y fila menor a 7
            
            if(Casillas[temporal[0]+1][temporal[1]].getBackground()==Color.red){ //Lo mueve hacia arriba
                Casillas[temporal[0]+1][temporal[1]].setBackground(Color.green);
                Casillas[temporal[0]][temporal[1]].setBackground(Color.red);
                enemigo.setPosicion(temporal[0]+1, temporal[1]);
                return true;
            }
            
            else if (temporal[0]+1==7){ //Si la proxima movida para arriba es la base, envia msj
                JOptionPane.showMessageDialog(null, "Has Perdido!!");
                System.exit(0);
            }
        }

        else if(temporal[1]==0 && temporal[0]>7){//Valida si esta en la primera columna y fila mayor a 7
            
            if (Casillas[temporal[0]-1][temporal[1]].getBackground()==Color.red){
                Casillas[temporal[0]-1][temporal[1]].setBackground(Color.green);
                Casillas[temporal[0]][temporal[1]].setBackground(Color.red);
                enemigo.setPosicion(temporal[0]-1, temporal[1]);
                return true;
            }
            else if (temporal[0]-1==7){ //Si la proxima movida para abajo es la base, envia msj
                    JOptionPane.showMessageDialog(null, "Has Perdido!!");  
                    System.exit(0);
            }
        }
        return false;
    }    
}