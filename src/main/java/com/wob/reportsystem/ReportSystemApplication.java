package com.wob.reportsystem;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ReportSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportSystemApplication.class, args);
    }

    static UserManager propertiesUserManager() {
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("/Users/miki/Desktop/report-system/src/main/resources/users.properties"));
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        UserManager userManager = userManagerFactory.createUserManager();
        return userManager;
    }

    @Bean
    ApplicationRunner ftpService() {
        return args -> {
            FtpServerFactory serverFactory = new FtpServerFactory();
            ListenerFactory factory = new ListenerFactory();
            UserManager userManager = propertiesUserManager();
            initUsers(userManager);
            serverFactory.setUserManager(userManager);
            serverFactory.addListener("default", factory.createListener());
            FtpServer server = serverFactory.createServer();
            server.start();
        };
    }

    private static void initUsers(UserManager userManager) throws Exception {
        File root = new File("/Users/miki/Desktop/root/");
        for (String user : Set.of("miki")) {
            BaseUser baseUser = new BaseUser();
            baseUser.setEnabled(true);
            baseUser.setName(user);
            baseUser.setPassword("password");
            baseUser.setHomeDirectory(root.getAbsolutePath());
            baseUser.setAuthorities(List.of(new WritePermission("/")));
            userManager.save(baseUser);
        }
    }
}
