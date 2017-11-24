/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

public class CategoriasPOJO {
    private int id_Categoria=0;
    private String categoria="" ;

    public int getId_Categoria() {
        return id_Categoria;
    }

    /**
     * @param id_Categoria the id_Categoria to set
     */
    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
