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
public class SpawningPoints {
    int[] posicion;
    
    public SpawningPoints(int[]pos) {
        this.posicion=pos;
    }
    
    public int [] getPosicion(){
        return this.posicion;
    }
}
