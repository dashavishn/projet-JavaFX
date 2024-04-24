package bigbrain.java_bureau.classe_java;

public class ModificationStockElement {
    public class Element {
        private String code;
        private String nom;
        private double quantiteStockmodifiee;
        private String uniteMesure;

        private double prixAchat;

        private double prixVente;


        public Element(String code, String nom, double quantiteStockmodifiee, String uniteMesure, double prixAchat, double prixVente) {
            this.code = code;
            this.nom = nom;
            this.quantiteStockmodifiee = quantiteStockmodifiee;
            this.uniteMesure = uniteMesure;
            this.prixAchat = prixAchat;
            this.prixVente = prixVente;
        }


        //retourne quantité actuelle du produit

        public double getQuantiteStock() {
            return quantiteStockmodifiee;

        }

        //va retourner le code de l'élément
        public String getCode() {
            return code;
        }


        //retourne le nom d'un élément
        public String getNom() {
            return nom;
        }
        // va mettre à jour le nom d'un nouvel élément

        //retourne l'unité de mesure de l'élément
        public String getUniteMesure() {
            return uniteMesure;
        }

        //met à jour l'unité de l'élément
        public void setUniteMesure(String uniteMesure) {
            this.uniteMesure = uniteMesure;
        }

        public double getPrixAchat() {
            return prixAchat;
        }

        public double getPrixVente() {
            return prixVente;
        }


    }
}