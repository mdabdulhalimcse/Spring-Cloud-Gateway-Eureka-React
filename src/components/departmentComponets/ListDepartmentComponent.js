/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import DepartmentService from '../../services/DepartmentService';


export default function ListDepartmentComponent(){
    const [department, setDepartment] = useState([]);
    const [activeStatus, setActiveStatus] = useState(true);
    const [name,setName] = useState({})
    const history = useNavigate();
    
    useEffect(() => {
       DepartmentService.getDepartment()
        .then(response => {
            setDepartment(response.data);
        });
    },[department]);

    const createDepartment = () => {
        history(`/department/create/`)
    }
    
    const editDepartment = (id) => {
        history(`/department/edit/${id}`);
    }

    const deleteDepartment = (id) => {
        DepartmentService.deleteDepartment(id).then( res => {
            console.log(res.data.message);
        });
    }

    const viewDepartment = (id) => {
        history(`/department/view/${id}`);
    }


    return(
        
        <div className="container">
        <h2 className="text-center">Department List</h2>
        <div className = "row">
           <button className="btn btn-primary" onClick={createDepartment}> Add Department</button>
        </div>
        <br></br>
        <div className = "row">
               <table className = "table table-striped table-bordered">

                   <thead>
                       <tr>
                           <th> Department Name</th>
                           <th> Active Status</th>
                           <th> Actions</th>
                       </tr>
                   </thead>
                   <tbody>
                       {
                          department.map(
                               dept => 
                               <tr key = {dept.id}>
                                    <td> { dept.departmentName} </td>   
                                    <td> {dept.active ? 'Active' : 'Deactive'}</td>
                                    <td>
                                        
                                        <button onClick={ () =>editDepartment(dept.id)} className="btn btn-info">Update </button>

                                        <button style={{marginLeft: "10px"}} onClick={ () => deleteDepartment(dept.id)} className="btn btn-danger">Delete </button>

                                        <button style={{marginLeft: "10px"}} onClick={ () => viewDepartment(dept.id)} className="btn btn-info">View </button>
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