
package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AllureUtil {

    // Zips the contents of allure-results folder into a zip file
    public static String zipAllureResults(String sourceFolder, String zipFilePath) throws IOException {
        File folder = new File(sourceFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IOException("Allure results folder does not exist: " + sourceFolder);
        }

        FileOutputStream fos = new FileOutputStream(zipFilePath);
        ZipOutputStream zos = new ZipOutputStream(fos);

        zipFile(folder, folder.getName(), zos);

        zos.close();
        fos.close();
        return zipFilePath;
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {
        if (fileToZip.isHidden()) return;

        if (fileToZip.isDirectory()) {
            if (!fileName.endsWith("/")) fileName += "/";
            zos.putNextEntry(new ZipEntry(fileName));
            zos.closeEntry();

            File[] children = fileToZip.listFiles();
            if (children != null) {
                for (File child : children) {
                    zipFile(child, fileName + child.getName(), zos);
                }
            }
            return;
        }

        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        fis.close();
    }

    // Sends an email with the specified attachment file
    public static void sendEmailWithAttachment(
            String host, String port,
            final String user, final String password,
            String toAddress,
            String subject,
            String message,
            String attachFilePath) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    //    props.put("mail.smtp.starttls.required", "true");        
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 🔥 TLS fix
       props.put("mail.smtp.ssl.trust", host);


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(user));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        MimeBodyPart attachPart = new MimeBodyPart();
        try {
            attachPart.attachFile(new File(attachFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MessagingException("Failed to attach file: " + attachFilePath);
        }
       

      Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachPart);

        msg.setContent(multipart);

       Transport.send(msg);
     ;

        System.out.println("Report Email Sent Successfully!");
    }
}
