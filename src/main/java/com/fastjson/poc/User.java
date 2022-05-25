package com.fastjson.poc;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	  @JSONField
	  private String name;
	  @JSONField
	  private Integer age;
	  
	  public String getName()
	  {
	    return this.name;
	  }
	  
	  public void setName(String name)
	  {
	    this.name = name;
	  }
	  
	  public Integer getAge()
	  {
	    return this.age;
	  }
	  
	  public void setAge(Integer age)
	  {
	    this.age = age;
	  }
}
