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
    public static int []temporal= new int [2]; 
    public static int []position= new int [2]; 
    public static int []base={0,7};
    public static List<Enemigo>  enemigos = new ArrayList<>();
    public static List<SpawningPoints> spwanings = new ArrayList<>();
    
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
    SpawningPoints(4);
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
            randf= (int)(Math.random()*14);
            randc=ThreadLocalRandom.current().nextInt(5, 11);
                if (Casillas[randf][randc].getBackground()==Color.blue)
                Casillas[randf][randc].setBackground(Color.orange);
        }
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
                
                if (Casillas[filar][colr].getBackground()==Color.BLUE && Casillas[filar][colr-1].getBackground()==Color.BLUE){
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
            if (Casillas[tmp[0]][tmp[1]-1].getBackground()==Color.blue){
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
                Casillas[tmp[0]][tmp[1]-1].setBackground(Color.darkGray);
            }
        }
    }
    
    
    //Metodo que mueve el enemigo
    public void MoverEnemigo(){
        for (Enemigo enemigo : enemigos) {
            
            temporal=enemigo.getPosicion();
            if (enemigo.getUsoAtaque()){
                continue;
            }
            else if(temporal[0]==7 && temporal[1]-1==0){
                JOptionPane.showMessageDialog(vista, "Has Perdido!!");  
                System.exit(0);
                break;
            }
            else if(temporal[1]==0 && temporal[0]<7) {
                if(Casillas[temporal[0]+1][temporal[1]].getBackground()==Color.blue){
                Casillas[temporal[0]+1][temporal[1]].setBackground(Color.darkGray);
                Casillas[temporal[0]][temporal[1]].setBackground(Color.blue);
                enemigo.setPosicion(temporal[0]+1, temporal[1]);}                
                else if (temporal[0]+1==7){
                    JOptionPane.showMessageDialog(vista, "Has Perdido!!");
                    System.exit(0);
                    break;
                }
            }
            else if(temporal[1]==0 && temporal[0]>7){
                if (Casillas[temporal[0]-1][temporal[1]].getBackground()==Color.blue){
                Casillas[temporal[0]-1][temporal[1]].setBackground(Color.darkGray);
                Casillas[temporal[0]][temporal[1]].setBackground(Color.blue);
                enemigo.setPosicion(temporal[0]-1, temporal[1]);}
                else if (temporal[0]-1==7){
                    JOptionPane.showMessageDialog(vista, "Has Perdido!!");  
                    System.exit(0);
                    break;
                }
            }
            else if (temporal[1]!=0 && Casillas[temporal[0]][temporal[1]-1].getBackground() == Color.blue){
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
                jugador.setExperiencia(3);
                MostrarStats();
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
        
        else 
            return ValidarMovimiento(aux);
        
    }
    
    
    public boolean ValidarMovimiento(JButton aux){
        
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
    

    public void AgregarItem(Jugador jugador, DefaultTableModel tabla, Items item, int pos){
        enemigos.remove(pos);
        if (jugador.getItems().size()<8){    
            jugador.AgregarItem(item);
            AñadirRow(item, tabla);
           }
        else if("Tanjiro".equals(jugador.getNombre()))
            tanjiro. MasCapacidad(item);
        else{
            JOptionPane.showMessageDialog(vista, "El personaje no tiene la habilidad de poseer más items");
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
                AtacarAux(tanjiro);
                break;
            case "Atacar Sogeking":
                AtacarAux(sogeking);
                break;          
            case "Atacar Saitama":
                AtacarAux(saitama);
                break;
            case "Pasar Turno":
                ReglasEnemigos();
                break;
            case "Habilidades de Jugadores":
                MostrarHabilidades();
                break;
            default:
                position=getCoordenadas((JButton) ae.getSource());
                break;
        }
    }

    
    public static void MostrarHabilidades(){
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
    
    //Función que valida si todos los jugadores hicieron sus tres acciones para darle el turno a la PC
    public void FlujoJuego(){
        
        boolean estadoT=tanjiro.getUsoItem()&& tanjiro.getUsoMover() && tanjiro.getUsoAtaque();
        boolean estadoSo=sogeking.getUsoItem()&& sogeking.getUsoMover() && sogeking.getUsoAtaque();
        boolean estadoSa=saitama.getUsoItem()&& saitama.getUsoMover() && saitama.getUsoAtaque();
        
        if (estadoT && estadoSo && estadoSa)
            ReglasEnemigos();
    }

    
    public void ReglasEnemigos(){
        AtaqueZombiAux();
        MoverEnemigo();
        
        
        
        //Reiniciar usos de acciones
        tanjiro.ResetearUsos();
        saitama.ResetearUsos();
        sogeking.ResetearUsos();
        for(Enemigo e: enemigos){
            e.ResetearUsos();}
            
        JOptionPane.showMessageDialog(vista, "Aparecen más enemigos");
        SpawningPoints(2);
    }

    public void AtacarAux(Jugador jugador){
        JButton boton=Casillas[position[0]][position[1]];
        temporal=jugador.getPosicion();
        
        if (!jugador.getUsoAtaque()){
        
            if ("Arma Grande".equals(jugador.getArmaActual().getTipo())){
                Atacar(jugador);
                jugador.setExperiencia(15);
                MostrarStats();
            }
            else if (ValidarMovimiento(boton)){ 
                Atacar(jugador);
                jugador.setExperiencia(8);
                MostrarStats();
                }
            }
        else
            JOptionPane.showMessageDialog(vista, "El Personaje ya uso atacar");   
    }
    
    
    //Esta vara hay que acomodarla si sirve pero hay que acomodarla lo de los items
    public void Atacar(Jugador jugador){
        int pos;
        if(Casillas[position[0]][position[1]].getBackground()== Color.darkGray){
            pos=enemigos.get(0).RetornaEnemigo(enemigos, position);
            jugador.setUsoAtaque(true);
            enemigos.get(pos).RecibirDano(jugador.getAtaque());
            if (enemigos.get(pos).Muerto()){
               Casillas[position[0]][position[1]].setBackground(Color.blue);
               JOptionPane.showMessageDialog(vista, "El personaje ha eliminado un enemigo");
               if (!enemigos.get(pos).getItems().isEmpty()){
                    int opcion = JOptionPane.showConfirmDialog (null,"El enemigo dejo un item. ¡Desea adquirirlo?", "Item" ,JOptionPane.YES_NO_OPTION);
                    if (opcion==JOptionPane.YES_OPTION){
                       if("Tanjiro".equals(jugador.getNombre()))
                            AgregarItem(jugador, frameTanjiro.tablaTan, enemigos.get(pos).getItems().get(0), pos);
                        else if ("Sogeking".equals(jugador.getNombre()))
                            AgregarItem(jugador, frameSogeking.tablaSog, enemigos.get(pos).getItems().get(0),pos);
                        else 
                            AgregarItem(jugador, frameSaitama.tablaSai, enemigos.get(pos).getItems().get(0),pos);
                        }
                    else
                       enemigos.remove(pos);
               }
               else enemigos.remove(pos);
            }
            else
                JOptionPane.showMessageDialog(vista, "Aun le queda vida al enemigo: le queda  "+enemigos.get(pos).getVida());
        }   
    }   
    
    
    public void AtaqueZombiAux(){
        
        VerificarAtaqueZombi(tanjiro);
        
        VerificarAtaqueZombi(sogeking);

        VerificarAtaqueZombi(saitama);
    }
    
    
    public void Borrar(Jugador jugador){
        MostrarStats();
        if (jugador.Muerto()){
            int [] posicion=jugador.getPosicion();
            Casillas[posicion[0]][posicion[1]].setBackground(Color.BLUE);
            JOptionPane.showMessageDialog(vista, "El jugador"+jugador.getNombre()+"ha muerto :(");
        }
        
    }
    
    
    
    //Meter esta funcion en la clase enemigo
    public void VerificarAtaqueZombi(Jugador jugador){
        if ((jugador.Muerto())){
        }
        else{
            int [] iJugador= jugador.getPosicion();
            for(int i=0; i<enemigos.size(); i++){
            
                int []iEnemigo=enemigos.get(i).getPosicion();
            
                if (iEnemigo[0]==iJugador[0] && iEnemigo[1]-1 == iJugador[1])
                    AtaqueZombi(jugador, i);
                else if(iEnemigo[0]==iJugador[0] && iEnemigo[1]+1 == iJugador[1])
                    AtaqueZombi(jugador,i);
                else if(iEnemigo[0]+1==iJugador[0] && iEnemigo[1] == iJugador[1])
                    AtaqueZombi(jugador,i);
                else if(iEnemigo[0]-1==iJugador[0] && iEnemigo[1] == iJugador[1])
                     AtaqueZombi(jugador,i);
            }
        }
    }
         
    
    
    public void AtaqueZombi(Jugador jugador, int i){
        if (!jugador.Muerto()){
            JOptionPane.showMessageDialog(vista, "Un Zombie tipo: "+enemigos.get(i).getNombre()+" ataca al jugador: "+jugador.getNombre());
            jugador.RecibirDano(enemigos.get(i).getAtaque());
            enemigos.get(i).setUsoAtaque(true);
            Borrar(jugador);
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