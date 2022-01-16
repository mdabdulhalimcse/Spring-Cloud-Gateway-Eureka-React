/* eslint-disable no-unused-vars */
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../../services/EmployeeService";

export default function ListEmployeeComponent(){
    const [employee, setEmployee] = useState([]);

    const history = useNavigate();
    useEffect(() => {
        axios.get('http://localhost:8080/employee/')
        .then(response => setEmployee(response.data));
    },[]);
    
    const createEmployee = () => {
        history(`/employee/create/`)
    }
    
    const editEmployee= (id) => {
        history(`/employee/edit/${id}`);
    }

    const deleteEmployee = (id) => {
        EmployeeService.deleteEmployee(id);
    }

    const viewEmployee = (id) => {
        history(`/employee/view/${id}`);
    }


    return(
        
        <div className="container">
        <h2 className="text-center">Employee List</h2>
        <div className = "row">
           <button className="btn btn-primary" onClick={createEmployee}> Add Employee</button>
        </div>
        <br></br>
        <div className = "row">
               <table className = "table table-striped table-bordered" >

                   <thead>
                       <tr>
                           <th> Name</th>
                           <th> Code </th>
                           <th> Gender </th>
                           <th> Mobile </th>
                           <th> Date of birth </th>
                           <th> Actions</th>
                       </tr>
                   </thead>
                   <tbody >
                       {
                          employee.map(
                               emp => 
                               <tr key = {emp.id}>
                                    <td> { emp.name} </td>   
                                    <td> { emp.code} </td>   
                                    <td> {emp.gender}</td>
                                    <td> {emp.mobile}</td>
                                    <td> {emp.dob}</td>
                                    <td >
                                        
                                        <button style={{marginLeft: "10px"}} onClick={ () =>editEmployee(emp.id)} className="btn btn-info">Update </button>

                                        <button style={{marginLeft: "10px"}} onClick={ () => deleteEmployee(emp.id)} className="btn btn-danger">Delete </button>

                                        <button style={{marginLeft: "10px"}} onClick={ () => viewEmployee(emp.id)} className="btn btn-info">View </button>

                                    </td>
                               </tr>
                           )
                       }
                   </tbody>
               </table>

        </div>

   </div>
    )
}