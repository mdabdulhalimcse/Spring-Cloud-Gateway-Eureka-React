import moment from "moment";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useNavigate, useParams } from "react-router-dom";
import DepartmentService from "../../services/DepartmentService";
import EmployeeService from "../../services/EmployeeService";

export default function UpdateEmployeeComponent() {
  const [name, setName] = useState("");
  const [code, setCode] = useState("");
  const [mobile, setMobile] = useState("");
  const [gender, setGender] = useState("");
  const [dob, setDateBirth] = useState(new Date());
  const [departmentId,setDepartmentId] = useState('');
  const [ department,setDepartment] = useState([]);

  const history = useNavigate();
  const params = useParams()
  const employeeId = params.id;

  useEffect(() => {
    DepartmentService.getDepartment()
    .then(response => setDepartment(response.data));
},[]);

useEffect(() => {
    EmployeeService.getEmployeeById(employeeId)
    .then(response => {
        setName(response.data.name);
        setCode(response.data.code);
        setGender(response.data.gender);
        setDateBirth(response.data.dob);
        setMobile(response.data.mobile);
        setDepartmentId(response.data.departmentId);
    });
},[employeeId]);

  const submitHandler = (e) => {
    e.preventDefault();   
    var newDate= moment(dob).format('YYYY-MM-DD');
    if(code.length !== 4){
      alert("Code must be 4 character");
      return false;
    }
    
    let employee = {
      id:employeeId,
      name: name,
      code: code,
      dob: newDate,
      gender: gender,
      mobile: mobile,
      departmentId:departmentId,
    };

    EmployeeService.updateEmployee(employee)
      .then((data) => {
        alert(data.data.message);
        history(`/employee`);
      })
      .catch((error) => {
        console.log(error.response.data.error);
      });
  };

  const options = [
    {
      label: "FEMALE",
      value: "FEMALE",
    },
    {
      label: "MALE",
      value: "MALE",
    },
    {
      label: "OTHERS",
      value: "OTHERS",
    },
  ];

  const dateHandler = (e) =>{
    var newDate= moment(e).format('YYYY-MM-DD')
    setDateBirth(newDate);
  }

  const cancelHanler= () =>{
      history(`/employee/`)
  }

  const departmentChangeHandle = (e) =>{
    setDepartmentId(e.target.value);
  }

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3"></div>
          <form onSubmit={submitHandler}>
            <div className="form-group">
              <label> Employee Name: </label>
              <input
                placeholder="enter employee name"
                name="name"
                required
                type="text"
                className="form-control"
                onChange={(e) => setName(e.target.value)}
                value={name}
              />
            </div>

            <div className="form-group">
              <label> Code: </label>
              <input
                placeholder="enter code "
                name="name"
                required
                type="text"
                className="form-control"
                onChange={(e) => setCode(e.target.value)}
                value={code}
              />
            </div>

            <div className="form-group">
              <label> Mobile: </label>
              <input
                placeholder="enter mobile number "
                name="mobile"
                required
                type="text"
                className="form-control"
                onChange={(e) => setMobile(e.target.value)}
                value={mobile}
              />
            </div>

            <div className="form-group">
              <label> Date of Birth: </label>
              <DatePicker
                value={dob}
                onChange={dateHandler}
              />
            </div> <br/>
           
            <div>
            <label> Gender: </label>
            <select value={gender} onChange={(e) => setGender(e.target.value)}>
              {options.map((option,index) => (
                <option value={option.value} key={index} >{option.label}</option>
              ))}
            </select>
            </div> <br/>

            <div>
            <label> Department: </label>
            <select value={departmentId} onChange={departmentChangeHandle}>
              {department.map((dept,index) => (
                <option value={dept.id} key={index} >{dept.departmentName}</option>
              ))}
            </select>
            </div> <br/>

            <button className="btn btn-success" type="submit" value="Submit">
              Update
            </button>
            <button className="btn btn-danger" onClick={cancelHanler} >Cancel</button>
          </form>
        </div>
      </div>
    </div>
  );
}
