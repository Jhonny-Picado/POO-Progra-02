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
public class LargoAlcance extends Arma{
    
//Listas para dar valores a algunos atributos
    private final String nombresA[]={"Cañon","Bazuka","Granada","Franco","Molotov","Escopeta","Dirigible","Ak-47","Bomba","Pistola"};
    
    //Constructor de la clase, instacia una arma de largo alcance
    public LargoAlcance(){
        super();
        this.nombre= nombresA[(int) (Math.random()*9+0)];
        this.poder= (int)(Math.random()*40+10); 
        this.ruido= (int)(Math.random()*8+5); 
        this.tipo="Arma Grande";
    }
}
