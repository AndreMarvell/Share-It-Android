package com.andremarvell.shareit.classe.user;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marvell on 13/12/14.
 */
public class User {
    /**
     *  identifiant de l'utilisateur
     */
    private int id;

    /**
     *  nom de l'utilisateur
     */
    private String nom;

    /**
     * prenom
     */
    private String prenom;

    /**
     * adresse mail du user
     */
    private String email;

    /**
     * adresse du user
     */
    private Adresse adresse;

    /**
     * adresse du user en format text
     */
    private String adresseText;

    /**
     * Identifiant Facebook du user
     */
    private String facebookId;

    /**
     * Token du user
     */
    private String token;

    /**
     * Nom d'utilisateur
     */
    private String username;

    /**
     * Role de l'utilisateur (Admin,  User)
     */
    private int role = 1;

    /**
     * boolean pour savoir si le user est bloqué ou banni
     */
    private boolean locked = false;

    /**
     * Date d'inscription du user
     */
    private String dateInscription;

    /**
     * photo de l'utilisateur
     */
    private String photo = "unknow.jpg";

    /**
     * Code pin à 4 chiffre de l'utilisateur
     */
    private int pin;




    /**
     * Date d'anniversaire de l'utilisateur
     */
    private String birthday;

    /**
     * Credit de l'utilisateur
     */
    private float credits = 0;

    public User() {
    }

    public User(JSONObject json) {
        try {
            id = json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            nom = (json.getString("nom")!=null)?json.getString("nom"):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            prenom = (json.getString("prenom")!=null)?json.getString("prenom"):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            email = (json.getString("email")!=null)?json.getString("email"):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            adresse = (json.getJSONObject("adresse")!=null)?(new Adresse(json.getJSONObject("adresse"))):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            facebookId = (json.getString("facebookId")!=null)?json.getString("facebookId"):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            token = (json.getString("token")!=null)?json.getString("token"):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            username = (json.getString("username")!=null)?json.getString("username"):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            role = json.getInt("role");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            locked = json.getBoolean("locked");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            photo = (json.getString("photo")!=null)?json.getString("photo"):null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getAdresseText() {
        return adresseText;
    }

    public void setAdresseText(String adresseText) {
        this.adresseText = adresseText;
    }

    public JSONObject getUserJson(){
        JSONObject json = new JSONObject();

        try {
            json.put("nom", (nom!=null)?nom:"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("prenom", (prenom!=null)?prenom:"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("adresse", (adresseText!=null)?adresseText:"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("facebookId", (facebookId!=null)?facebookId:"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("pin",pin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("photo", photo);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json;
    }

}
