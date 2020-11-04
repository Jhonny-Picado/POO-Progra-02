/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegozombie;

/**
 *
 * @author Jhonny Picado
 */
public class JuegoZombie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        
        Saitama sai = new Saitama();
        System.out.println(sai.nombre);
        sai.IncrementarVida(55);
        System.out.println(sai.vida + "\n"+ "\n");
        
        
        Tanjiro tan = new Tanjiro();
        System.out.println(tan.nombre);
        tan.IncrementarVida(27);
        System.out.println(tan.vida + "\n"+ "\n");
        
        
        Sogeking sog = new Sogeking();
        System.out.println(sog.nombre);
        sog.IncrementarVida(1);
        System.out.println(sog.vida + "\n"+ "\n");
       
        
        int golpe= sai.Atacar(15);
        sog.RecibirDano(golpe);
        System.out.println(sog.vida + "\n"+ "\n");
        
        
        Consumible con = new Consumible();
        
        System.out.println(con.nombre + con.getTipo()+con.poder+"\n"+"\n");
        
        Consumible cejas = new Consumible();
        
        System.out.println(cejas.nombre + cejas.getTipo()+cejas.poder+"\n"+"\n");
        
        sog.AgregarItem(con);
        
        System.out.println(sog.articulos.get(0).poder + "   "+ sog.articulos.get(0).nombre);
        
        sai.AgregarItem(cejas);
        System.out.println("\n"+"\n"+sai.articulos.get(0).poder + "   "+ sai.articulos.get(0).nombre);
        
    }
}
