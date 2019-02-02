package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.policy.upgrade.UserLevelUpgradePolicy;
import lombok.Setter;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceTarget")
public class UserServiceImpl implements UserService {

    @Autowired
    @Setter
    protected UserDao userDao;

    @Autowired
    @Setter
    protected ApplicationContext applicationContext;

    @Autowired
    @Setter
    protected MailSender mailSender;

    @Override
    public void add(User user) {
        if( user.getLevel() == null ) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    public void upgradeLevels() throws Exception {
        List<User> users = userDao.getAll();

        for (User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        }
    }

    boolean canUpgradeLevel(User user) {

        boolean canUpgradeLevel = false;

        UserLevelUpgradePolicy policy = applicationContext.getBean(
                CaseUtils.toCamelCase(user.getLevel().name(), false) + "UpgradePolicy",
                UserLevelUpgradePolicy.class
        );

        if( policy != null ) {
            canUpgradeLevel = policy.canUpgradeLevel(user);
        }

        return canUpgradeLevel;
    }

    protected void upgradeLevel(User user) {
        user.setLevel(user.getLevel().nextLevel());
        userDao.update(user);
        sendUpgradeEmail(user);
    }

    private void sendUpgradeEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());

        mailSender.send(mailMessage);
    }
}