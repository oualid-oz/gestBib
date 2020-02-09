
package bean;

public class livre {
    public livre(String titre, int edition, String date, int stock){
        setTitre(titre);
        setEdition(edition);
        setDate(date);
        setStock(stock);
    }
    private String titre,date;
    private int edition,stock;
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getEdition() { return edition; }
    public void setEdition(int edition) { this.edition = edition; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
