import axios from 'axios';

const DEPARMENT_API_BASE_URL = "http://localhost:8080/department/";

class DepartmentService {

    getDepartment(){
        return axios.get(DEPARMENT_API_BASE_URL);
    }

    createDepartment(department){
        return axios.post(DEPARMENT_API_BASE_URL, department);
    }

    getDepartmentById(departmentId){
        return axios.get(DEPARMENT_API_BASE_URL+  departmentId);
    }

    updateDepartment(department){
        return axios.put(DEPARMENT_API_BASE_URL , department);
    }

    deleteDepartment(employeeId){
        return axios.delete(DEPARMENT_API_BASE_URL + employeeId);
    }
}

export default new DepartmentService()