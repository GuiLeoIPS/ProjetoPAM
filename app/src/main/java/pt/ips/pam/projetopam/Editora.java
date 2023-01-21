package pt.ips.pam.projetopam;

public class Editora {

    private int idEditora;
    private String nomeEditora;
    private String sigla;
    private String morada;

    public Editora(int idEditora, String nomeEditora, String sigla, String morada) {
        this.idEditora = idEditora;
        this.nomeEditora = nomeEditora;
        this.sigla = sigla;
        this.morada = morada;
    }

    public int getIdEditora() { return idEditora; }
    public void setIdEditora(int idEditora) { this.idEditora = idEditora; }

    public String getNomeEditora() { return nomeEditora; }
    public void setNomeEditora(String nomeEditora) { this.nomeEditora = nomeEditora; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getMorada() { return morada; }
    public void setMorada(String morada) { this.morada = morada; }
}
