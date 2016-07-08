package com.seri.notification;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seri.common.CommonTypes;
import com.seri.security.Role;
import com.seri.service.notification.Notification;
import com.seri.service.notification.NotificationService;
import com.seri.service.notification.RoleType;
import com.seri.web.model.User;
import com.seri.web.model.UserRoles;
import com.seri.web.utils.CalendarUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestNotification {
	
	@Autowired
	NotificationService notificationService;
	

	@Test
	@Ignore
	public void createEntityNotification(){
		notificationService.createNotification(sampleNotification());
		Notification notification = sampleNotification();
		notification.setSchoolId(9);
		notificationService.createNotification(notification);
		notification = sampleNotification();
		notification.setSchoolId(9);
		notification.setStandardId(2);
		notificationService.createNotification(notification);
		
	}
	@Test
//	@Ignore
	public void testGetUserNotification() {
		List<Notification> notificatioList = notificationService.getNotificationForUser(sampleUser());
		assertEquals(3, notificatioList.size());
	}
	
	
	@Ignore
	public Notification sampleNotification(){
		Notification notification = new Notification();
		notification.setNotificationType(CommonTypes.HOME_WORK);
		notification.setGroupType(RoleType.ROLE_TEACHER);
		notification.setDueDate(CalendarUtil.addDays(2));
		notification.setCreatedBy(3);
		notification.setCreatedDate(CalendarUtil.getDate());
		return notification;
	}
	@Ignore
	public User sampleUser(){
		Role role = new Role();
		role.setRoleName(RoleType.ROLE_TEACHER);
		UserRoles userRoles= new UserRoles();
		userRoles.setRole(role);
		userRoles.setDefault(true);
		User user = new User();
		user.setId(3);
		user.setUserRoles(Arrays.asList(userRoles));
		user.setSchool(9);
		user.setStandard("1,2");
		return user;
	}

}
