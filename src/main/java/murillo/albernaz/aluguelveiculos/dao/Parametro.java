/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.dao;

/**
 *
 * @author murillo
 */
public class Parametro {
    private String nomeParametro;
    private String valor;

    public Parametro(String nomeParametro, String valor) {
        this.nomeParametro = nomeParametro;
        this.valor = valor;
    }
    
    

    public String getNomeParametro() {
        return nomeParametro;
    }

    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
