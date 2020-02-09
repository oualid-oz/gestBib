
package bean;

public class emprunt {
    public emprunt(int idL, String titre, String cin){
        setIdL(idL);
        setTitre(titre);
        setCin(cin);
    }
    private int id,idL;
    private String titre,cin;
    public int getIdL() { return idL; }
    public void setIdL(int idL) { this.idL = idL; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
}
