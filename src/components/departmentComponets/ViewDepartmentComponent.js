import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { default as DepartmentService } from '../../services/DepartmentService';

function ViewDepartmentComponent (){

    const [department, setDepartment] = useState([]);
    const params = useParams()
    const id = params.id;



    useEffect(() => {
        DepartmentService.getDepartmentById(id).then( res => {
            setDepartment(res.data);
        })
    },[]);

        return (
            <div>
                {console.log(department)}
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Department Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Department Name: { department.departmentName}</label>
                           
                        </div>
                        <div className = "row">
                            <label> Active Status :  {department.active ? 'Active' : 'Deactive'} </label>
                            <div> { }</div>
                        </div>
                        
                    </div>

                </div>
            </div>
        )
    }

export default ViewDepartmentComponent;