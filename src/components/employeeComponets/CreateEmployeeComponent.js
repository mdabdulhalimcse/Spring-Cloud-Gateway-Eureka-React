import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useNavigate } from "react-router-dom";
import DepartmentService from '../../services/DepartmentService';
import EmployeeService from "../../services/EmployeeService";

export default function CreateEmployeeComponent() {
  const [name, setName] = useState("");
  const [code, setCode] = useState("");
  const [mobile, setMobile] = useState("");
  const [gender, setGender] = useState("");
  const [startDate, setStartDate] = useState(new Date());
  const [departmentId,setDepartmentId] = useState('');
  const [ department,setDepartment] = useState([]);
  const history = useNavigate();

  useEffect(() => {
    DepartmentService.getDepartment()
    .then(response => setDepartment(response.data));
},[]);

  const submitHandler = (e) => {
    e.preventDefault();
    let employee = {
      name: name,
      code: code,
      dob: startDate,
      gender: gender,
      mobile: mobile,
      department:{
          id:departmentId,
      }
    };

    EmployeeService.createEmployee(employee)
      .then((data) => {
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

  const cancelHanler= () =>{
    history(`/employee/`)
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
              <DatePicker placeholderText="select your date of birth"
                selected={startDate}
                onChange={(date) => setStartDate(date)}
              />
            </div>
           
            <div>
            <label> Gender: </label>
            <select value={gender} onChange={(e) => setGender(e.target.value)}>
              {options.map((option,index) => (
                <option value={option.value} key={index} >{option.label}</option>
              ))}
            </select>
            </div>

            <div>
            <label> Department: </label>
            <select value={departmentId} onChange={(e) => setDepartmentId(e.target.value)}>
              {department.map((dept,index) => (
                <option value={dept.id} key={index} >{dept.departmentName}</option>
              ))}
            </select>
            </div>

            <br/>
            <button className="btn btn-success" type="submit" value="Submit">
              Save
            </button>
            <button className="btn btn-danger" onClick={cancelHanler} >Cancel</button>
          </form>
        </div>
      </div>
    </div>
  );
}
