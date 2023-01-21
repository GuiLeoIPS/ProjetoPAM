package pt.ips.pam.projetopam;

public class Autor {

    private int idAutor;
    private String nomeAutor;

    public Autor(int idAutor, String nomeAutor) {
        this.idAutor = idAutor;
        this.nomeAutor = nomeAutor;
    }

    public int getIdAutor() { return idAutor; }
    public void setIdAutor(int idAutor) { this.idAutor = idAutor; }

    public String getNomeAutor() { return nomeAutor; }
    public void setNomeAutor(String nomeAutor) { this.nomeAutor = nomeAutor; }
}
