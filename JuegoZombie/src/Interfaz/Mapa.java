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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
/**
 * Clase que llama la interfaz y tiene los metodos para que las clases se relacionen
 * @author Jhonny Picado Vega
 */
    public final class Mapa implements ActionListener{
   
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
    public static int []position= new int [2]; 
    public static List<Enemigo>  enemigos = new ArrayList<>();
    public static List<SpawningPoints> spwanings = new ArrayList<>();
    
    //Constructor por defecto
    public Mapa(){    
    }  
    
    //Constructor que llama a la GUI e inicia algunas label, así como que muestra el tablero
    Mapa(GUI interfaz){
        this.vista = interfaz;      //Vista es la variable para referirse a la GUI
        Tablero();  
        LoadItems();
        SpawningPoints(4);
        MostrarStats();
    }
    
    
    //Metodo que llama al metodo que muestra los stats de los jugadores
    public static void MostrarStats(){
        Controlador stats=new Controlador();
        stats.MostrarStats(vista, tanjiro, sogeking, saitama);
    }
    
    
    //Metodo que genera la matriz de botones del programa
    public static void Tablero() {
   
        for(int i=0; i < Casillas.length; i++) {
            
            for(int j=0; j < Casillas[i].length; j++) {
                JButton btnNuevo = new JButton();
                Casillas[i][j]=btnNuevo;
                Casillas[i][j].setActionCommand(Integer.toString(i)+Integer.toString(j));
                Casillas[i][j].setBackground(Color.red);
                Casillas[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                
                if (i==7 && j ==0){
                    Casillas[i][j].setText("BASE");
                    Casillas[i][j].setBackground(Color.yellow);
                }
                ActionListener myButtonListener = new Mapa();
                Casillas[i][j].addActionListener(myButtonListener);
                vista.PanelTablero.add(Casillas[i][j]);
            }
        }
        Controlador con= new Controlador();
        con.darPosicionesIniciales(Casillas, tanjiro, saitama, sogeking);
        con.AsignarObstaculos(Casillas);
        vista.PanelTablero.setLayout(new GridLayout(15, 15));
    }
    
    
    //Metodo que retorna las coordenadas en la matriz del botton presionado
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
    
    
    //Metodo que pone las "Tumbas" donde saldrán los enemigos del juego
    public void SpawningPoints(int numero){
        
        int rand, filar, colr;
        int i=0;
        
        while(i<numero){
            rand= ThreadLocalRandom.current().nextInt(0, 1);
            
            if (rand==0){
                
                filar= (int)(Math.random()*14);
                colr=ThreadLocalRandom.current().nextInt(9, 14);
                int [] po={filar,colr};
                
                if (Casillas[filar][colr].getBackground()==Color.red && Casillas[filar][colr-1].getBackground()==Color.red){
                    Casillas[filar][colr].setBackground(Color.WHITE);
                    SpawningPoints e= new SpawningPoints(po);
                    spwanings.add(e);
                    ColocarEnemigos();
                }
            }
            i++;
        }
    }
    
    
    //Metodo que coloca nuevos enemigos en el tablero
    public void ColocarEnemigos(){

        Enemigo ene;

        for (int i=0; i<spwanings.size(); i++){
            int []tmp=spwanings.get(i).getPosicion();
            if (Casillas[tmp[0]][tmp[1]-1].getBackground()==Color.red){
                int random= (int)(Math.random()*2);

                switch (random) {
                    case 0:
                        ene=new Garou();
                        break;
                    case 1:
                        ene=new Thanos();
                        break;
                    default:
                        ene=new AllForOne();
                        break;
                    }
                ene.setPosicion(tmp[0],tmp[1]-1);
                this.enemigos.add(ene);
                Casillas[tmp[0]][tmp[1]-1].setBackground(Color.green);
            }
        }
    } 
      
    
    //Método encargado de cargar las ventanas inciales de los items de todos los jugadores
    //Funciona con el boton respectivo
    public void MostrarVentanaItems(Jugador jugador){        
        if (jugador==tanjiro) 
            frameTanjiro.setVisible(true);
        else if(jugador==sogeking)
            frameSogeking.setVisible(true);
        else
            frameSaitama.setVisible(true);
    } 
    
   
    //Método utilizado para utilizar los items de cada jugador
    //Recibe de entrada el nombre del jugador, el indice al item y la tabla de items de dicho jugador
    public void UtilizarItem(String jugador, int index,DefaultTableModel tabla){
        AccionesJugador j= new AccionesJugador();
        if ("tanjiro".equals(jugador)&& !tanjiro.getUsoItem())
            j.UtilizarItemAux(tanjiro, index, tabla);
        
        else if ("sogeking".equals(jugador) && !sogeking.getUsoItem())
            j.UtilizarItemAux(sogeking, index, tabla);

        else if ("saitama".equals(jugador) && !saitama.getUsoItem())
            j.UtilizarItemAux(saitama, index, tabla);
        else
            JOptionPane.showMessageDialog(vista, "El personaje ya utilizo algún item en esta ronda");   
    }
   
    
    //Método que se encarga de cargar los items al inicio de la partida
    public void LoadItems(){
        AccionesJugador a= new AccionesJugador();
        a.CargarItems(tanjiro, frameTanjiro.tablaTan);        //Llama a los metodos que se encargan de inicializar los items de los tres jugadores
        a.CargarItems(sogeking, frameSogeking.tablaSog);
        a.CargarItems(saitama, frameSaitama.tablaSai);
    }
    
    
    //Metodo que detecta las acciones que el usuario realiza con la interfaz
    //Redirecciona a los metodo correspondientes, segun cada boton
    @Override
    public void actionPerformed(ActionEvent ae) {
        AccionesJugador a=new AccionesJugador();
        
        switch (ae.getActionCommand()) {
            case "Mover Saitama":
                a.MoverJugadores(saitama, Color.gray, Casillas, position);
                break;
            case "Mover Sogeking":
                a.MoverJugadores(sogeking, Color.blue, Casillas, position);
                break;
            case "Mover Tanjiro":
                a.MoverJugadores(tanjiro, Color.cyan, Casillas, position);
                break;
            case "Items Tanjiro":
                MostrarVentanaItems(tanjiro);
                break;
            case "Items Sogeking":
                MostrarVentanaItems(sogeking);
                break;
            case "Items Saitama":
                MostrarVentanaItems(saitama);
                break;
            case "Atacar Tanjiro":
                a.AtacarAux(tanjiro, Casillas, position,enemigos,frameTanjiro.tablaTan);
                break;
            case "Atacar Sogeking":
                a.AtacarAux(sogeking, Casillas, position,enemigos,frameSogeking.tablaSog);
                break;          
            case "Atacar Saitama":
                a.AtacarAux(saitama,Casillas, position,enemigos,frameSaitama.tablaSai);
                break;
            case "Pasar Turno":
                LlamarAccionesZombie();
                break;
            case "Habilidades de Jugadores":
                Controlador habs=new Controlador();
                habs.MostrarHabilidades(tanjiro, sogeking, saitama);
                break;
            default:
                position=getCoordenadas((JButton) ae.getSource());
                break;
        }
    }
    
    
    //Función que valida si todos los jugadores hicieron sus tres acciones para darle el turno a la PC
    public void FlujoJuego(){
        
        boolean estadoT=tanjiro.getUsoItem()&& tanjiro.getUsoMover() && tanjiro.getUsoAtaque();
        boolean estadoSo=sogeking.getUsoItem()&& sogeking.getUsoMover() && sogeking.getUsoAtaque();
        boolean estadoSa=saitama.getUsoItem()&& saitama.getUsoMover() && saitama.getUsoAtaque();
        
        if (estadoT && estadoSo && estadoSa){
            LlamarAccionesZombie();
        }
    }
    
    
    //Método que llama las acciones del zombie
    public void LlamarAccionesZombie(){
        AccionesZombi act = new AccionesZombi();
        act.ReglasEnemigos(tanjiro, saitama, sogeking, enemigos, Casillas);
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
        controladorGUI.vista.setExtendedState(JFrame.MAXIMIZED_BOTH);   //Maximiza la ventana al iniciarla
    }
}