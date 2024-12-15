package model;

public abstract class Produto {
    private int id;
    private String nome;
    private float medida;
    
    public Produto(int id, String nome, float medida){
        this.id = id;
        this.nome = nome;
        this.medida = medida;
    }
    public abstract double getPreco();
    public abstract void setPreco(double valorOriginal);
    public abstract String getTipo();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getMedida() {
        return medida;
    }
            
    
}
