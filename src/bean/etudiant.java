
package bean;

public class etudiant {
    public etudiant(String nom, String prenom, String filiere, String cin){
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setFiliere(filiere);
        this.setCin(cin);
    }
    private String nom,prenom,filiere,cin;
    public String getNom(){ return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
}