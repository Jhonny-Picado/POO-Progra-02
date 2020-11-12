/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

//Librerias necesarias para el funcionamiento del progama
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import juegozombie.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
/**
 * Clase que llama la interfaz y tiene los metodos para que las clases se relacionen
 * @author Jhonny Picado Vega
 */
    public class Mapa implements ActionListener{
   
    //Atibutos a utilizar, se implementan con static para no instanciar objetos, 
    //dado a que en este programa la dependencia no es tan relevante
    public static JButton[][]Casillas = new JButton[15][15];
    public static Tanjiro tanjiro = new Tanjiro();
    public static Sogeking  sogeking = new Sogeking();
    public static Saitama saitama = new Saitama();
    public static GUI vista;
    public static ItemsT frameTanjiro = new ItemsT();
    public static ItemsSog frameSogeking = new ItemsSog();
    public static ItemsSai frameSaitama = new ItemsSai();
    public static int []temporal= new int [2]; 
    public static int []position= new int [2]; 
    public static int []base={0,7};
   
    //Constructor por defecto
    Mapa(){    
    }  
    
    //Constructor que llama a la gui e inicia algunas label, así como que muestra el tablero
    Mapa(GUI interfaz){
  
    this.vista = interfaz;      //Vista es la variable para referirse a la GUI
    NombrePersonaje();
    Tablero();  
    MostrarStats();
    }


    //Metodo que muestra el nombre de los jugadores
    public static void NombrePersonaje(){
        vista.nJugador1.setText(tanjiro.getNombre());
        vista.especialidadT.setText(tanjiro.getEspecialidad());
        
        vista.nJugador2.setText(sogeking.getNombre());
        vista.especialidadS.setText(sogeking.getEspecialidad());
        
        vista.nJugador3.setText(saitama.getNombre());
        vista.especialidadSa.setText(saitama.getEspecialidad());
    } 
    
    
    //Metodo utilizado para mostar las stats de los jugadores
    public static void MostrarStats(){
        
        vista.vidaT.setText(Integer.toString(tanjiro.getVida()));
        vista.nivelT.setText(Integer.toString(tanjiro.getNivel()));
        vista.experienciaT.setText(Integer.toString(tanjiro.getExperiencia()));
        
        vista.vidaS.setText(Integer.toString(sogeking.getVida()));
        vista.nivelS.setText(Integer.toString(sogeking.getNivel()));
        vista.experienciaS.setText(Integer.toString(sogeking.getExperiencia()));
        
        vista.vidaSa.setText(Integer.toString(saitama.getVida()));
        vista.nivelSa.setText(Integer.toString(saitama.getNivel()));
        vista.experienciaSa.setText(Integer.toString(saitama.getExperiencia()));
    }
    
    
    //Metodo que genera la matriz de botones del programa
    public static void Tablero() {
   
        for(int i=0; i < Casillas.length; i++) {
            
            for(int j=0; j < Casillas[i].length; j++) {
                JButton btnNuevo = new JButton();
                Casillas[i][j]=btnNuevo;
                Casillas[i][j].setActionCommand(Integer.toString(i)+Integer.toString(j));
                Casillas[i][j].setBackground(Color.blue);
                Casillas[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                
                if (i==7 && j ==0){
                    Casillas[i][j].setText("Base");
                    Casillas[i][j].setBackground(Color.green);
                }
                ActionListener myButtonListener = new Mapa();
                Casillas[i][j].addActionListener(myButtonListener);
                vista.PanelTablero.add(Casillas[i][j]);
            }
        }
        darPosicionesIniciales();
        vista.PanelTablero.setLayout(new GridLayout(15, 15));
    }
  
    
    //Metodo que da las posiciones iniciales a los jugadores
    public static void darPosicionesIniciales(){
        
        Casillas[6][0].setBackground(Color.yellow);
        tanjiro.setPosicion(6,0);

        Casillas[7][1].setBackground(Color.red);
        sogeking.setPosicion(7,1);
        
        Casillas[8][0].setBackground(Color.gray);
        saitama.setPosicion(8,0);
    }
    
    
    //Metodo que retorna las coordenadas en la matriz del bottonevaluada
    public int[] getCoordenadas(JButton casilla) {
        int [] coordenadas = new int[2];
        for (int i=0; i < this.Casillas.length; i++) {
            for (int j=0; j < this.Casillas.length; j++) {
                if (this.Casillas[i][j] == casilla) {
                    coordenadas[0] = i;
                    coordenadas[1] = j;
                }
            }
        }
        return coordenadas;
    }
    
   
    //Metodo que realiza el movimiento de los jugadores, mueve colores y direcciones de los jugadores
    public void MoverJugadores(Jugador jugador, Color color){

        temporal=jugador.getPosicion();
        if (ValidarMovida()){
            Casillas [temporal[0]][temporal[1]].setBackground(Color.blue);
            Casillas [position[0]][position[1]].setBackground(color);
            jugador.setPosicion(position[0], position[1]);
        }
    }
    
    
    //Metodo que valida si la posicion a moverse es valida, solo deja mover horizontal y verticalmente y una sola casilla
    public boolean ValidarMovida(){
        
        JButton aux=Casillas[position[0]][position[1]];
        if (aux.getBackground()!= Color.blue)
            return false;
        else if (temporal[1]!=0 && Casillas[temporal[0]][temporal[1]-1]==aux){
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
    
    
    //Método encargado de cargar las ventanas inciales de los items de todos los jugadores
    //Funciona con el boton respectivo
    public void MostrarVentanaItems(Jugador jugador, DefaultTableModel tabla){
        for (int i=0; i<jugador.getItems().size(); i++){
            Items products =(Items) jugador.getItems().get(i);
            AñadirRow(products, tabla);
        }
        
        if (jugador==tanjiro) 
            frameTanjiro.setVisible(true);
        else if(jugador==sogeking)
            frameSogeking.setVisible(true);
        else
            frameSaitama.setVisible(true);
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
  
   
    //Método utilizado para utilizar los items de cada jugador
    //Recibe de entrada el nombre del juagdor, el indice al item y la tabla de items de dicho jugador
    public void UtilizarItem(String jugador, int index,DefaultTableModel tabla){
        
        switch (jugador) {
            case "tanjiro":
                tanjiro.UsarItem(index);
                break;
            case "sogeking":
                sogeking.UsarItem(index);
                break;
            default:
                saitama.UsarItem(index);
                break;
        }
        tabla.removeRow(index);
        MostrarStats();   
    }
    
    //Metodo que detecta las acciones que el usuario realiza con la interfaz
    //Redirecciona a los metodo correspondientes, segun cada boton
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Mover Saitama":
                MoverJugadores(saitama, Color.gray);
                break;
            case "Mover Sogeking":
                MoverJugadores(sogeking, Color.red);
                break;
            case "Mover Tanjiro":
                MoverJugadores(tanjiro,Color.yellow);
                break;
            case "Items Tanjiro":
                MostrarVentanaItems(tanjiro, frameTanjiro.tablaTan);
                break;
            case "Items Sogeking":
                MostrarVentanaItems(sogeking, frameSogeking.tablaSog);
                break;
            case "Items Saitama":
                MostrarVentanaItems(saitama, frameSaitama.tablaSai);
                break;
            default:
                position=getCoordenadas((JButton) ae.getSource());
                break;
        }
    }
    
    
    //Metodo main llama a la interfaz
    public static void main(String[] args) {
    
        //Inicializa la variable vista con el constructor por defecto de la interfaz
        vista = new GUI();
        
        //Pasa esta variable al controlador de esta clase para que se inicialice con la GUI
        Mapa controladorGUI = new Mapa(vista);
        
        //Hace visible la interfaz
        controladorGUI.vista.setVisible(true);
        controladorGUI.vista.setLocationRelativeTo(null);
    }
}