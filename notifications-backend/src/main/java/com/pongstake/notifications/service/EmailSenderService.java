package com.pongstake.notifications.service;


import com.pongstake.notifications.model.Order;
import com.pongstake.notifications.model.Prediction;
import com.pongstake.notifications.repository.PredictionRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailSenderService {

    private static final Logger log = LoggerFactory.getLogger(EmailSenderService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PredictionRepository predictionRepository;


    @Value("${FROM_EMAIL}")
    String fromEmail;

    @Async
    public void sendEmail(String to, String subject, long id) throws MessagingException {
        log.info("sendEmail method called");
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);

        String content = String.format(
                """
                <h1><b>Hello! here are your predictions</b></h1><br/>
                %s
                """, id);
        message.setContent(content, "text/html; charset=utf-8");

        mailSender.send(message);
    }


    public synchronized void sendPredictionsToEmailSentFalse() throws MessagingException {
        log.info("sendPredictionsToEmailSentFalse called");
        List<Prediction> foundEmails = predictionRepository.findAllByEmailSentFalse();
        log.info("found: {}", foundEmails.size());

        if (foundEmails.isEmpty()){
            log.info("Didn't find any emails");
            return;
        }

        for (Prediction p : foundEmails){
            p.setEmailSent(true);
            predictionRepository.saveAndFlush(p);

            final String to = p.getOrder().getEmail();
            final long id = p.getOrder().getId();

            sendEmail(to, "Prediction bundle", id);
        }

        log.info("sended all emails");
    }


    @Scheduled(fixedDelay = 2000)
    public void checkEmails() throws MessagingException {
        log.info("CHECKING EMAILS");
        sendPredictionsToEmailSentFalse();
    }

}
