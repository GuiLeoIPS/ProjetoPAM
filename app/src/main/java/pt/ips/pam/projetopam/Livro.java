package pt.ips.pam.projetopam;

public class Livro {

    private int idLivro;
    private String nomeLivro;
    private String descricao;
    private int numeroPaginas;
    private int stock;
    private float preco;
    private int idEditora;

    public Livro(int idLivro, String nomeLivro, String descricao, int numeroPaginas, int stock, float preco, int idEditora) {
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.descricao = descricao;
        this.numeroPaginas = numeroPaginas;
        this.stock = stock;
        this.preco = preco;
        this.idEditora = idEditora;
    }

    //Gets & Sets
    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public String getNomeLivro() { return nomeLivro; }
    public void setNomeLivro(String nomeLivro) { this.nomeLivro = nomeLivro; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(int numeroPaginas) { this.numeroPaginas = numeroPaginas; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public float getPreco() {return preco; }
    public void setPreco(float preco) {this.preco = preco; }

    public int getIdEditora() { return idEditora; }
    public void setIdEditora(int idEditora) { this.idEditora = idEditora; }
}
