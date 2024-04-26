package in.ashokit.utils;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to, String Subject, String body, File f) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg,true);
			helper.setTo("rahultambe780@gmail.com");
			helper.setSubject("Subject line");
			helper.setText("<h1>Mail body here.......... </h1>", true);
			helper.addAttachment("plan-details", f);
			mailSender.send(msg);
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
