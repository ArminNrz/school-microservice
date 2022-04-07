package com.school.notification.service.notification;

import com.school.amqp.dto.student.StudentNotificationDTO;
import com.school.notification.domain.SmsLog;
import com.school.notification.repository.SmsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.school.amqp.dto.enumartion.NotificationType.SMS;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentNotificationService {

    @Value("${notification.language}")
    private String language;

    private final SmsLogRepository smsLogRepository;
    private final ResourceBundleMessageSource messageSource;

    private static final String SMS_NOTIF_MESSAGE_CODE = "student.end-term.sms.regexp";

    public void send(StudentNotificationDTO notificationDTO) {
        log.debug("enter send(), studentNotificationDTO: {}", notificationDTO);

        if (notificationDTO.getType() == SMS) {
            log.debug("consume student notification with Type: {}", notificationDTO.getType());
            sendSms(notificationDTO);
        }

        log.debug("exit send");
    }

    private void sendSms(StudentNotificationDTO notificationDTO) {
        String[] params = new String[] {notificationDTO.getFirstName(), notificationDTO.getLastName(), notificationDTO.getPointSum().toString()};
        String text = messageSource.getMessage(SMS_NOTIF_MESSAGE_CODE, params, new Locale(language));
        String phoneNumber = notificationDTO.getPhoneNumber();

        SmsLog smsLog = new SmsLog();
        smsLog.setNumber(phoneNumber);
        smsLog.setText(text);
        smsLogRepository.save(smsLog);

        log.info("Send sms: {}, to: {}, successfully", text, phoneNumber);
    }
}
