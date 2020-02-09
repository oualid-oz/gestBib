
package bean;

public class remettre {
    public remettre(int id, String titre, String cin){
        setId(id);
        setTitre(titre);
        setCin(cin);
    }
    private int id;
    private String titre,cin;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
}
