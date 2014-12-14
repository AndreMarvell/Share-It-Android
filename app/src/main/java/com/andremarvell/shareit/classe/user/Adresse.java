package com.andremarvell.shareit.classe.user;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marvell on 13/12/14.
 */
public class Adresse {

    private String id;

    private String numero;

    private String rue;

    private String adresseComplete;

    private String postal;

    private String ville;

    private String region;

    private String departement;

    private String pays = "France";

    private String longitude;

    private String latitude;

    public Adresse(JSONObject json) {
        try {
            id = (json.getString("id")!=null)?json.getString("id"):null;
            numero = (json.getString("numero")!=null)?json.getString("numero"):null;
            rue = (json.getString("rue")!=null)?json.getString("rue"):null;
            adresseComplete = (json.getString("adresseComplete")!=null)?json.getString("adresseComplete"):null;
            postal = (json.getString("postal")!=null)?json.getString("postal"):null;
            ville = (json.getString("ville")!=null)?json.getString("ville"):null;
            region = (json.getString("region")!=null)?json.getString("region"):null;
            departement = (json.getString("departement")!=null)?json.getString("departement"):null;
            pays = (json.getString("pays")!=null)?json.getString("pays"):null;
            latitude = (json.getString("latitude")!=null)?json.getString("latitude"):null;
            longitude = (json.getString("longitude")!=null)?json.getString("longitude"):null;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getAdresseComplete() {
        return adresseComplete;
    }

    public void setAdresseComplete(String adresseComplete) {
        this.adresseComplete = adresseComplete;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
