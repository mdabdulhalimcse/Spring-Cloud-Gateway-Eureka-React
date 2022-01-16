import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { default as EmployeeService } from '../../services/EmployeeService';

function ViewEmployeeComponent (){

    const [employee, setEmployee] = useState({department:''});
    const params = useParams()
    const id = params.id;



    useEffect(() => {
        EmployeeService.getEmployeeById(id).then( res => {
            setEmployee(res.data);
        })
    },[]);

        return (
            <div>
                {console.log(employee)}
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Employee Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label>  Name: { employee.name }</label>
                        </div>
                        <div className = "row">
                            <label>  Code: { employee.code }</label>
                        </div>
                        <div className = "row">
                            <label>  Date of Birth: { employee.dob }</label>
                        </div>
                        <div className = "row">
                            <label>  Mobile: { employee.mobile }</label>
                        </div>
                        <div className = "row">
                            <label>Department: { employee.department.departmentName}</label>
                        </div>
                        <div className = "row">
                            <label>Active Status: { employee.department.active ? "Active" : "Deactive"}</label>
                        </div>
                        </div>
                    </div>
                </div>
        )
    }

export default ViewEmployeeComponent;