package model;

public class ProdutoSolido extends Produto{
    private double valorCalculado;
    
    public ProdutoSolido(int id, String nome, float medida){
        super(id, nome, medida);
    }
     @Override
    public double getPreco() {
      return valorCalculado;
    }

    @Override
    public void setPreco(double valorOriginal){
        valorCalculado = valorOriginal + (valorOriginal * 0.30);
    }

    @Override
    public String getTipo() {
        return "solido";
    }
    
}
