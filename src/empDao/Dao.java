package empDao;

import POJO.Employee;


public interface Dao {
    public void empCreate(Employee employee) throws Exception;

    public void empUpdate(Employee employee) throws Exception;

    public Employee empRetrieve(Long Id) throws Exception;

    public void empDelete(int empId) throws Exception;
}
