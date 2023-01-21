package pt.ips.pam.projetopam;

public class Conta {

    private int idConta;
    private String nomeConta;
    private String emailConta;
    private String senha;
    private String telemovel;
    private boolean administrador;

    public Conta(int idConta, String nomeConta, String emailConta, String senha, String telemovel, boolean administrador) {
        this.idConta = idConta;
        this.nomeConta = nomeConta;
        this.emailConta = emailConta;
        this.senha = senha;
        this.telemovel = telemovel;
        this.administrador = administrador;
    }

    public int getIdConta() { return idConta; }
    public void setIdConta(int idConta) { this.idConta = idConta; }

    public String getNomeConta() { return nomeConta; }
    public void setNomeConta(String nomeConta) { this.nomeConta = nomeConta; }

    public String getEmailConta() { return emailConta; }
    public void setEmailConta(String emailConta) { this.emailConta = emailConta; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTelemovel() { return telemovel; }
    public void setTelemovel(String telemovel) { this.telemovel = telemovel; }

    public boolean isAdministrador() { return administrador; }
    public void setAdministrador(boolean administrador) { this.administrador = administrador; }
}
