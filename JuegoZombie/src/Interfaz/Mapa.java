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
import java.util.Arrays;
import java.util.List;
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
    public static int []temporal= new int [2]; 
    public static int []position= new int [2]; 
    public static int []base={0,7};
    public static List<Enemigo>  enemigos = new ArrayList<>();
     
    //Constructor por defecto
    Mapa(){    
    }  
    
    //Constructor que llama a la GUI e inicia algunas label, así como que muestra el tablero
    Mapa(GUI interfaz){
    this.vista = interfaz;      //Vista es la variable para referirse a la GUI
    NombrePersonaje();
    Tablero();  
    CargarItems(tanjiro, frameTanjiro.tablaTan);        //Llama a los metodos que se encargan de inicializar los items de los tres jugadores
    CargarItems(sogeking, frameSogeking.tablaSog);
    CargarItems(saitama, frameSaitama.tablaSai);
    ColocarEnemigos(5);
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
        vista.defensetan.setText(Integer.toString(tanjiro.getDefensa()));
        vista.ataquetan.setText(Integer.toString(tanjiro.getAtaque()));
        
        vista.vidaS.setText(Integer.toString(sogeking.getVida()));
        vista.nivelS.setText(Integer.toString(sogeking.getNivel()));
        vista.experienciaS.setText(Integer.toString(sogeking.getExperiencia()));
        vista.defenseSog.setText(Integer.toString(sogeking.getDefensa()));
        vista.ataqueSog.setText(Integer.toString(sogeking.getAtaque()));
        
        vista.vidaSa.setText(Integer.toString(saitama.getVida()));
        vista.nivelSa.setText(Integer.toString(saitama.getNivel()));
        vista.experienciaSa.setText(Integer.toString(saitama.getExperiencia()));
        vista.defenseSai.setText(Integer.toString(saitama.getDefensa()));
        vista.ataqueSai.setText(Integer.toString(saitama.getAtaque()));
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
        AsignarObstaculos();
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
    
    
    
    
    
    //Metodo que cambia el color de fondo de los botones en el tablero para indicar que estos son obstaculos 
    public static void AsignarObstaculos(){
        int randf, randc;
        
        for(int i=0; i<18; i++){
            randf= (int)(Math.random()*14+0);
            randc=(int) (Math.random()*9+4);
                if (Casillas[randf][randc].getBackground()==Color.blue)
                Casillas[randf][randc].setBackground(Color.orange);
        }
    }
    
    public void ColocarEnemigos(int numero){

        Enemigo ene;
        for (int i=0; i<numero; i++){
            int filar= (int)(Math.random()*14+0);
            int colr=(int)(Math.random()*8+6);

            if (Casillas[filar][colr].getBackground()==Color.BLUE){
                int random= (int)(Math.random()*2+0);

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
            ene.setPosicion(filar,colr);
            this.enemigos.add(ene);
            Casillas[filar][colr].setBackground(Color.darkGray);
            }  
        }
    }
    
    
    public void MoverEnemigo(){
       
        for (Enemigo enemigo : enemigos) {
            
            temporal=enemigo.getPosicion();
            
            if (temporal[1]!=0 && Casillas[temporal[0]][temporal[1]-1].getBackground() == Color.blue){
                Casillas[temporal[0]][temporal[1]-1].setBackground(Color.darkGray);
                Casillas[temporal[0]][temporal[1]].setBackground(Color.blue);
                enemigo.setPosicion(temporal[0], temporal[1]-1); 
            }
            else if(temporal[0]!=0 && Casillas[temporal[0]-1][temporal[1]].getBackground() == Color.blue){
                Casillas[temporal[0]-1][temporal[1]].setBackground(Color.darkGray);
                Casillas[temporal[0]][temporal[1]].setBackground(Color.blue);
                enemigo.setPosicion(temporal[0]-1, temporal[1]); 
            }
            else if(temporal[0]!=14 && Casillas[temporal[0]+1][temporal[1]].getBackground() == Color.blue){
                Casillas[temporal[0]+1][temporal[1]].setBackground(Color.darkGray);
                Casillas[temporal[0]][temporal[1]].setBackground(Color.blue);
                enemigo.setPosicion(temporal[0]+1, temporal[1]); 
            }
        }
    }
    
    
   
    //Metodo que realiza el movimiento de los jugadores, mueve colores y direcciones de los jugadores
    public void MoverJugadores(Jugador jugador, Color color){
        if (!jugador.getUsoMover()){
            temporal=jugador.getPosicion();
            if (ValidarMovida(jugador)){
                Casillas [temporal[0]][temporal[1]].setBackground(Color.blue);
                Casillas [position[0]][position[1]].setBackground(color);
                jugador.setPosicion(position[0], position[1]);
                jugador.setUsoMover(true);
                FlujoJuego();
            }
        }
        else
            JOptionPane.showMessageDialog(vista, "El personaje ya utilizo mover en esta ronda");
    }
    
    //Metodo que valida si la posicion a moverse es valida, solo deja mover horizontal y verticalmente y una sola casilla
    public boolean ValidarMovida(Jugador jugador){
        
        JButton aux=Casillas[position[0]][position[1]];
        if (aux.getBackground()!= Color.blue){  
            
            if (aux.getBackground()==Color.orange && "Tanjiro".equals(jugador.getNombre()) && jugador.getHabilidad3())
                return true;
            else
                return false;
        }
        else if("Saitama".equals(jugador.getNombre()) && jugador.getHabilidad1())   //Primera habilidad de Saitama moverse libre
            return true;
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
    
    
    //Metodo que carga los items iniciales de los tres jugadores
    public void CargarItems(Jugador jugador, DefaultTableModel tabla){
        for (int i=0; i<jugador.getItems().size(); i++){
            Items products =(Items) jugador.getItems().get(i);
            AñadirRow(products, tabla);
        }
    }
    
/*
    public void AgregarItem(Jugador jugador, DefaultTableModel tabla){
        Consumible con = new Consumible();
        if (jugador.getItems().size()<8){    
            jugador.AgregarItem(con);
            AñadirRow(con, tabla);
           }
        else if("Tanjiro".equals(jugador.getNombre()))
            tanjiro. MasCapacidad(con);
        else{
            JOptionPane.showMessageDialog(vista, "El personaje no tiene la habilidad de poseer más items");
        }
    }
   Agrega items y de una usa la habilidad 1 de tanjiro*/
    
    
    
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
    //Recibe de entrada el nombre del jugador, el indice al item y la tabla de items de dicho jugador
    public void UtilizarItem(String jugador, int index,DefaultTableModel tabla){
        
        if ("tanjiro".equals(jugador)&& !tanjiro.getUsoItem())
            UtilizarItemAux(tanjiro, index, tabla);
        
        else if ("sogeking".equals(jugador) && !sogeking.getUsoItem())
            UtilizarItemAux(sogeking, index, tabla);

        else if ("saitama".equals(jugador) && !saitama.getUsoItem())
            UtilizarItemAux(saitama, index, tabla);
        
        else
            JOptionPane.showMessageDialog(vista, "El personaje ya utilizo algún item en esta ronda");   
    }
    
    
    //Metodo que se encarga de utilizar un item, valida si el jugador que lo solicita ya uso la opcion
    public void UtilizarItemAux(Jugador jugador, int index, DefaultTableModel tabla){
        if (!jugador.getUsoItem()){
            jugador.UsarItem(jugador,index);
            jugador.setUsoItem(true);
            tabla.removeRow(index);
            MostrarStats();
            FlujoJuego();
        }
        else
            JOptionPane.showMessageDialog(vista, "El personaje ya utilizo algún item en esta ronda"); 
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
                MostrarVentanaItems(tanjiro);
                break;
            case "Items Sogeking":
                MostrarVentanaItems(sogeking);
                break;
            case "Items Saitama":
                MostrarVentanaItems(saitama);
                break;
            case "Atacar Tanjiro":
                Atacar(tanjiro);
                break;
            case "Atacar Sogeking":
                Atacar(sogeking);
                break;          
            case "Atacar Saitama":
                Atacar(tanjiro);
                break;
            case "Pasar Turno":
                ReglasEnemigos();
                break;
            default:
                position=getCoordenadas((JButton) ae.getSource());
                break;
        }
    }

    
    
    public void FlujoJuego(){
        
        boolean estadoT=tanjiro.getUsoItem()&& tanjiro.getUsoMover();
        boolean estadoSo=sogeking.getUsoItem()&& sogeking.getUsoMover();
        boolean estadoSa=saitama.getUsoItem()&& saitama.getUsoMover();
        
        if (estadoT && estadoSo && estadoSa)
            ReglasEnemigos();
    }

    
    public void ReglasEnemigos(){
        MoverEnemigo();
        tanjiro.ResetearUsos();
        saitama.ResetearUsos();
        sogeking.ResetearUsos();
    }

    
    public void Atacar(Jugador jugador){
        Enemigo ene = new Enemigo();
        Enemigo aux= new Enemigo();
        if(Casillas[position[0]][position[1]].getBackground()== Color.darkGray){
           aux=ene.RetornaEnemigo(enemigos, position);
           System.out.println(jugador.getAtaque());
           aux.RecibirDano(jugador.getAtaque());
           if (aux.Muerto()){
               Casillas[position[0]][position[1]].setBackground(Color.blue);
               enemigos.remove(aux);
               JOptionPane.showMessageDialog(vista, "El personaje ha eliminado un enemigo"); 
           }
           else
               JOptionPane.showMessageDialog(vista, "Aun le queda vida al enemigo: le queda"+aux.getVida());
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
        controladorGUI.vista.setExtendedState(JFrame.MAXIMIZED_BOTH);   //Maximiza la ventana al iniciarla
    }
}