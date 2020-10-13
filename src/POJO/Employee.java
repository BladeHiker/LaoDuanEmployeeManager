package POJO;


public class Employee {

  private long empId;
  private String empName;
  private long empDepartment;
  private long empAge;
  private long empSex;
  private java.sql.Date empBirth;


  public long getEmpId() {
    return empId;
  }

  public void setEmpId(long empId) {
    this.empId = empId;
  }


  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }


  public long getEmpDepartment() {
    return empDepartment;
  }

  public void setEmpDepartment(long empDepartment) {
    this.empDepartment = empDepartment;
  }


  public long getEmpAge() {
    return empAge;
  }

  public void setEmpAge(long empAge) {
    this.empAge = empAge;
  }


  public long getEmpSex() {
    return empSex;
  }

  public void setEmpSex(long empSex) {
    this.empSex = empSex;
  }


  public java.sql.Date getEmpBirth() {
    return empBirth;
  }

  public void setEmpBirth(java.sql.Date empBirth) {
    this.empBirth = empBirth;
  }

}
