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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhonny Picado 
 */
public class AccionesJugador {
    
    //Constructor de la clase
    public AccionesJugador(){    
    }
    
    
    //Metodo que realiza el movimiento de los jugadores, mueve colores y direcciones de los jugadores
    public void MoverJugadores(Jugador jugador, Color color,JButton[][]Casillas, int[]position){
        int []temporal;
        
        if (jugador.Muerto()){  //Valida si el jugador esta muerto
            JOptionPane.showMessageDialog(null, "El personaje ha muerto :(");
        }
        //Válida sino ha usado el movimiento
        else if (!jugador.getUsoMover()){
            
            temporal=jugador.getPosicion(); //Agarra la posicion del jugador
            
            if (ValidarMovida(jugador,temporal,Casillas,position)){ //Verifica si la movida da por el usuario es correcta
                
                Casillas [temporal[0]][temporal[1]].setBackground(Color.red);  //Si lo fue, pinta el tablero
                Casillas [position[0]][position[1]].setBackground(color);       //Y pinta el jugador en la posicion indicada
                jugador.setPosicion(position[0], position[1]);                  //Le doy la nueva posicion al jugador
                jugador.setUsoMover(true);
                jugador.setExperiencia(3);
                
                //Muestro las stats y valido el flujo del juego
                Mapa m=new Mapa();
                m.MostrarStats();
                m.FlujoJuego();
            }
        }
        else
            JOptionPane.showMessageDialog(null, "El personaje ya utilizo mover en esta ronda");
    }
    

    //Metodo que valida si la posicion a moverse es valida, solo deja mover horizontal y verticalmente y una sola casilla
    public boolean ValidarMovida(Jugador jugador, int[]temporal, JButton[][]Casillas, int []position){
        
        JButton aux=Casillas[position[0]][position[1]];    //Agarro el boton de la matriz en la posicion indicada
        
        if (aux.getBackground()!= Color.red){     //Verifico que sea una casilla desocupada
            if (aux.getBackground()==Color.black && "Tanjiro".equals(jugador.getNombre()) && jugador.getHabilidad3()) //Aca usa la habilidad3 de tanjiro
                return true;
            else
                return false;
        }
        
        else if("Saitama".equals(jugador.getNombre()) && jugador.getHabilidad1())   //Primera habilidad de Saitama moverse libre
            return true;
        
        else 
            return ValidarMovimiento(aux, temporal,Casillas);   //Sino hay habilidades, tons valida el movimiento por casilla
    }
    
    
    
    //metodo de validar el movimiento indicado
    public boolean ValidarMovimiento(JButton aux, int [] temporal,JButton[][]Casillas){
        
        
        //Valida arriba, abajo, y los lados, retorna tru en caso de encontrara una igualdad
        if (temporal[1]!=0 && Casillas[temporal[0]][temporal[1]-1]==aux){
            return true;
        }
        else if (temporal[1]!=14 && Casillas[temporal[0]][temporal[1]+1]==aux){
            return true;
        }
        else if (temporal[0]!=0 && Casillas[temporal[0]-1][temporal[1]]==aux){
            return true;
        }
        else if (temporal[0]!=14 && Casillas[temporal[0]+1][temporal[1]]==aux){
            return true;
        }
        else
            return false;
    }
    

    //Metodo que carga los items iniciales de los tres jugadores
    public void CargarItems(Jugador jugador, DefaultTableModel tabla){
        for (int i=0; i<jugador.getItems().size(); i++){
            Items products =(Items) jugador.getItems().get(i);
            AñadirRow(products, tabla);
        }
    }
    
    
    //Metodo utilizado para crea Row's y apartir de allí crear el objeto que se añade a la jtable
    //Recibe un item y la tabla del jugador en turno
    public void AñadirRow(Items products, DefaultTableModel tabla){
    
        Object [] objeto= new Object[3];      
        objeto[0]=products.getNombre();
        objeto[1]=products.getPoder();
        objeto[2]=products.getTipo();
        tabla.addRow(objeto);
    }
    
    
    //Método encargado de agregar items al jugador, además modifica la tabla del mismo
    public void AgregarItem(Jugador jugador, DefaultTableModel tabla, Items item, int pos, List<Enemigo> enemigos){
        
        Tanjiro tanjiro= new Tanjiro();
        enemigos.remove(pos);               //Elimino el en esa posicion
        
        if (jugador.getItems().size()<8){    //Valido que no tenga mas de 7 items
            jugador.AgregarItem(item);
            AñadirRow(item, tabla);
        }
        
        else if("Tanjiro".equals(jugador.getNombre())){//Si se trata de tanjiro, este tiene inventario infinito
            tanjiro=(Tanjiro)(jugador);
            tanjiro.MasCapacidad(item);
        }
        
        else
            JOptionPane.showMessageDialog(null, "El personaje no tiene la habilidad de poseer más items");
    }
       
    
    //Metodo que se encarga de utilizar un item, valida si el jugador que lo solicita ya uso la opcion
    public void UtilizarItemAux(Jugador jugador, int index, DefaultTableModel tabla){
        
        if (jugador.Muerto()){ //Si el personaje esta muerto, tons envia un msj
            JOptionPane.showMessageDialog(null, "El personaje ha muerto no puede usar Items:(");
        }
        
        //Sino utiliza el item, llama a un metodo de clase Personaje
        else{
            jugador.UsarItem(jugador,index);
            jugador.setUsoItem(true);
            tabla.removeRow(index);
            Mapa m= new Mapa();
            m.MostrarStats();
            m.FlujoJuego();
        }
    }
    
    
    //Metodo de ataque auxiliar para los zombies
    public void AtacarAux(Jugador jugador, JButton[][]Casillas, int []position, List<Enemigo> enemigos,DefaultTableModel tabla){
        
        JButton boton=Casillas[position[0]][position[1]];
        Mapa a= new Mapa();
        int [] temporal=jugador.getPosicion();
        
        if (jugador.Muerto()){
            JOptionPane.showMessageDialog(null, "El personaje ha muerto :(");
        }
        
        else if (!jugador.getUsoAtaque()){
            if(jugador.getArmaActual()==null){  //Valida si tiene arma
                JOptionPane.showMessageDialog(null, "El Personaje no tiene Arma");
            }
            
            else if ("Arma Grande".equals(jugador.getArmaActual().getTipo())){ //Revisa si es un arma grande, suma mas exp y no tiene limite de movimiento
                Atacar(jugador, Casillas, position, enemigos,tabla);
                jugador.setExperiencia(15);
                a.MostrarStats();
            }
            else if (ValidarMovimiento(boton, temporal, Casillas)){ //Arma pequeña valida si puede atacar, suma exp
                Atacar(jugador, Casillas, position, enemigos,tabla);
                jugador.setExperiencia(8);
                a.MostrarStats();
                }
            }
        else
            JOptionPane.showMessageDialog(null, "El Personaje ya uso atacar");   
        }


    //Método ataca a los enemigos
    public void Atacar(Jugador jugador, JButton[][]Casillas, int []position,List<Enemigo>enemigos,DefaultTableModel tabla){
        
        int pos;
       
        if(Casillas[position[0]][position[1]].getBackground()== Color.green){    //Valida si la posicion tocada esta un enemigo
            
            //Activa el uso de ataque, retorna el enemigo y le aplica daño al enemigo
            pos=enemigos.get(0).RetornaEnemigo(enemigos, position);
            jugador.setUsoAtaque(true);
            enemigos.get(pos).RecibirDano(jugador.getAtaque());
            ValidarMuerte(enemigos, Casillas, jugador, pos, tabla, position);
            jugador.setPosAtaque(position);
        }   
    }
    
    
    //Método que valida si el enemigo murio despues del ataque
    public void ValidarMuerte(List<Enemigo>enemigos, JButton[][]Casillas, Jugador jugador, int pos, DefaultTableModel tabla, int []position){
        
        AccionesJugador a= new AccionesJugador();
        
        if (enemigos.get(pos).Muerto()){ //Hace la validacion
            
            Casillas[position[0]][position[1]].setBackground(Color.red);       //Pinta de vacio la casilla
            JOptionPane.showMessageDialog(null, "El personaje ha eliminado un enemigo"); //Envia un msj de aviso
      
            if (!enemigos.get(pos).getItems().isEmpty()){   //Pregunta si la lista de items del enemigo esta vacia, sino le pregunta al usuario
                
                int opcion = JOptionPane.showConfirmDialog (null,"El enemigo dejo un item. ¡Desea adquirirlo?", "Item" ,JOptionPane.YES_NO_OPTION);
                
                if (opcion==JOptionPane.YES_OPTION)
                    a.AgregarItem(jugador, tabla, enemigos.get(pos).getItems().get(0), pos,enemigos);
                else
                    enemigos.remove(pos);
            }
            else enemigos.remove(pos);
            }
        
        else
            JOptionPane.showMessageDialog(null, "Aún le queda vida al enemigo: le queda  "+enemigos.get(pos).getVida());
    }
}
