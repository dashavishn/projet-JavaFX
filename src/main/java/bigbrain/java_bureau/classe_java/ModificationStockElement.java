package bigbrain.java_bureau.classe_java;

public class ModificationStockElement {

        private String code;
        private String nom;
        private double quantiteStockmodifiee;
        private String uniteMesure;

        private double prixAchat;

        private double prixVente;


        public ModificationStockElement(String code, String nom, double quantiteStockmodifiee, String uniteMesure, double prixAchat, double prixVente) {
            this.code = code;
            this.nom = nom;
            this.quantiteStockmodifiee = quantiteStockmodifiee;
            this.uniteMesure = uniteMesure;
            this.prixAchat = prixAchat;
            this.prixVente = prixVente;
        }



        //va retourner le code de l'élément
        public String getCode() {
            return code;
        }
    //retourne quantité actuelle du produit

    public double getQuantiteStockmodifiee() {
        return quantiteStockmodifiee;
    }

        //retourne le nom d'un élément
        public String getNom() {
            return nom;
        }

        //retourne l'unité de mesure de l'élément

        public String getUniteMesure () {
            return uniteMesure;
        }

        //met à jour l'unité de l'élément


        public double getPrixAchat() {
            return prixAchat;
        }

        public double getPrixVente() {
            return prixVente;
        }

    }
