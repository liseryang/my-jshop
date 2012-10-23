package cn.myshop.platform.entitys;
 

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import cn.myshop.platform.common.util.CustomDateSerializer;


/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser  {
    // Fields    

     private String userId;
     private String userName;
     private String password;
     private String realName;
     private String status;
     private String email;
     @DateTimeFormat(pattern="yyyy-MM-dd") 
     private Date invalDate;
     private Date lastLogin;
     private Date unlockTime;
     private Integer maxLoginCount;
     private Integer errLoginCount;
     private String ipAddress;
     private String orgId;
     private String deptId;
     private String lang;
     private String field1;
     private String field2;
     private String field3;

     private  SysUser sysUser;
     private Map<Object,Object> map;
     
    public Map<Object, Object> getMap() {
		return map;
	}


	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}


	public SysUser() {
    }
    

    public SysUser getSysUser() {
		return sysUser;
	}


	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getInvalDate() {
        return this.invalDate;
    }
    
    public void setInvalDate(Date invalDate) {
        this.invalDate = invalDate;
    }

    @JsonSerialize(using=CustomDateSerializer.class)
    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getUnlockTime() {
        return this.unlockTime;
    }
    
    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    public Integer getMaxLoginCount() {
        return this.maxLoginCount;
    }
    
    public void setMaxLoginCount(Integer maxLoginCount) {
        this.maxLoginCount = maxLoginCount;
    }

    public Integer getErrLoginCount() {
        return this.errLoginCount;
    }
    
    public void setErrLoginCount(Integer errLoginCount) {
        this.errLoginCount = errLoginCount;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLang() {
        return this.lang;
    }
    
    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getField1() {
        return this.field1;
    }
    
    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return this.field2;
    }
    
    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return this.field3;
    }
    
    public void setField3(String field3) {
        this.field3 = field3;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
   



public static void main(String[] a) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
	SysUser user=new SysUser();
	Class clas=user.getClass();//.forName("cn.myshop.platform.entitys.SysUser");
	Method method=clas.getDeclaredMethod("setUserName",new Class[] { String.class });
	method.invoke(user, new String[]{"测试"});
	System.out.print(user.getUserName());
}




}