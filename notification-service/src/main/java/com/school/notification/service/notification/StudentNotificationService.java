package com.school.notification.service.notification;

import com.school.amqp.dto.student.StudentNotificationDTO;
import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import com.school.notification.domain.SmsLog;
import com.school.notification.repository.SmsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.school.amqp.dto.enumartion.NotificationType.SMS;
import static com.school.amqp.dto.enumartion.StudentNotificationType.END_TERM;
import static com.school.amqp.dto.enumartion.StudentNotificationType.PAYMENT;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentNotificationService {

    @Value("${notification.language}")
    private String language;

    private final SmsLogRepository smsLogRepository;
    private final ResourceBundleMessageSource messageSource;

    private static final String SMS_NOTIF_MESSAGE_CODE = "student.end-term.sms.regexp";
    private static final String SMS_PAYMENT_NOTIF_MESSAGE_CODE = "student.payment.sms.regexp";

    public void send(StudentPaymentNotificationDTO notificationDTO) {
        log.debug("enter send(), studentNotificationDTO: {}", notificationDTO);

        if (notificationDTO.getType() == SMS) {
            log.debug("consume student notification with Type: {}", notificationDTO.getType());
            sendSms(notificationDTO);
        }

        log.debug("exit send");
    }

    private void sendSms(StudentPaymentNotificationDTO notificationDTO) {
        log.debug("enter sendSms(), notificationDTO: {}", notificationDTO);

        if (notificationDTO.getStudentNotificationType() == END_TERM) {
            log.debug("Notification type is end-term");
            sendEndTermSms(notificationDTO);
        }
        else if (notificationDTO.getStudentNotificationType() == PAYMENT) {
            log.debug("Notification type is payment");
            sendPaymentSms(notificationDTO);
        }
    }

    private void sendPaymentSms(StudentPaymentNotificationDTO notificationDTO) {
        String[] params = new String[] {notificationDTO.getFirstName(), notificationDTO.getLastName(), notificationDTO.getInvoiceCode()};
        String text = messageSource.getMessage(SMS_PAYMENT_NOTIF_MESSAGE_CODE, params, new Locale(language));
        String phoneNumber = notificationDTO.getPhoneNumber();

        saveSmsLog(text, phoneNumber);
    }

    private void sendEndTermSms(StudentNotificationDTO notificationDTO) {
        String[] params = new String[] {notificationDTO.getFirstName(), notificationDTO.getLastName(), notificationDTO.getPointSum().toString()};
        String text = messageSource.getMessage(SMS_NOTIF_MESSAGE_CODE, params, new Locale(language));
        String phoneNumber = notificationDTO.getPhoneNumber();

        saveSmsLog(text, phoneNumber);
    }

    private void saveSmsLog(String text, String phoneNumber) {
        SmsLog smsLog = new SmsLog();
        smsLog.setNumber(phoneNumber);
        smsLog.setText(text);
        smsLogRepository.save(smsLog);
        log.info("Send sms: {}, to: {}, successfully", text, phoneNumber);
    }
}
