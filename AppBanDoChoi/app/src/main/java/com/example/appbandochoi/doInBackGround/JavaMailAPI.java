package com.example.appbandochoi.doInBackGround;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void,Void,Void> {
    private Context mContext;
    private Session mSession;

    private String mEmail;
    private String mSubject;
    private String body;

    public JavaMailAPI(Context mContext, String mEmail, String mSubject, String body) {
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.body=body;
    }
    @Override
    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        mSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
                    }
                });
        try {
            MimeMessage mm = new MimeMessage(mSession);
            mm.setFrom(new InternetAddress(Utils.EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
            mm.setSubject(mSubject);
            String mMessage=body;
            mm.setText(mMessage);
            Transport.send(mm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}