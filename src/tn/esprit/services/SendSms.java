/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author pc
 */
public class SendSms {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "ACdeddd4cace14e03d372eb1df2a7ea42d";
    public static final String AUTH_TOKEN = "571ef37d11bd378a853b7e76a314c665";

    public void sendSms (int ticket,String num) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),
                new com.twilio.type.PhoneNumber("+12764444912"),
               "Félicitation votre reservation à été effecuter avec succées et votre numero de ticket est : "+ticket
)
            .create();

        System.out.println(message.getSid());
    }
}

