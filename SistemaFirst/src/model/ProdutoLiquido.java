package model;

public class ProdutoLiquido extends Produto{
    private double valorCalculado;
    
    public ProdutoLiquido(int id, String nome, float medida){
        super(id, nome, medida);
    }
    @Override
    public double getPreco() {
      return valorCalculado;
    }

    @Override
    public void setPreco(double valorOriginal){
        valorCalculado = valorOriginal + (valorOriginal * 0.10);
    }
    
    @Override
    public String getTipo() {
      return "liquido";
    }
    
}
