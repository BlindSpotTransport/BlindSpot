/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.api;

import tn.esprit.entity.RepReclamation;

/**
 *
 * @author sbs
 */
public class sendSMS {
 public static final String ACCOUNT_SID = System.getenv("AC1aa7313ff104db655abbbaf27f791bf5");
    public static final String AUTH_TOKEN = System.getenv("3bc52d170b010589671860195888a53f");
    

    public static void sendSMS(RepReclamation r) {
      
//        Twilio.init("AC1aa7313ff104db655abbbaf27f791bf5","3bc52d170b010589671860195888a53f");
//        Message message = Message.creator(new PhoneNumber("+21694085257"),
//                new PhoneNumber("+13235535487"),
//                "reclamation ajouté: description: "+r.getDescription()+" nom: "+r.getNom()+" prenom: "+r.getPrenom()).create();
//
//
//        System.out.println(message.getSid());
    }    
}
