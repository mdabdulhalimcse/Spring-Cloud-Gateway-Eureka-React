import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DepartmentService from "../../services/DepartmentService";

export default function UpdateDepartmentComponent(){

    const [departmentName, setDepartmentName] = useState('');
    const [activeStatus, setActiveStatus] = useState(false);

    const history = useNavigate();
    const params = useParams();
    const idDepartment = params.id;
    useEffect(() => {
        DepartmentService.getDepartmentById(idDepartment)
        .then(response => {
            console.log(response.data);
            setDepartmentName(response.data.departmentName);
            setActiveStatus(response.data.active);
        
        });
    },[idDepartment])

    const submitHandler = (e) => {
        e.preventDefault();
        let department = {
            id:idDepartment,
            departmentName:departmentName,
            active:activeStatus};
        console.log(activeStatus);
        console.log(departmentName);
        DepartmentService.updateDepartment(department).then(data => {
            console.log(data)
            history(`/department/`)
         })
         .catch(error => {
            console.log(error.response.data.error)
         })
    }

    const cancelHandler = () =>{
        history(`/department`);
    }



    return(
        <div>
            <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3"></div>
            <form onSubmit={submitHandler}>
                                <div className = "form-group">
                                    <label> Department Name: </label>
                                    <input placeholder="Department Name" name="name" required type="text"  className="form-control" onChange={(e) => setDepartmentName(e.target.value)} value={departmentName} />
                                </div>

                                <div className = "form-group">
                                    <label> Active Status :  </label>
                                    <input type="checkbox" name="checkbox" onChange={(e) => setActiveStatus(e.target.checked) } DefaultChecked={activeStatus}  />
                                </div>

                                <button className="btn btn-success" type="submit" value="Submit" >Save</button>
                                <button className="btn btn-danger" onClick={cancelHandler} >Cancel</button>
                            </form>
                            </div>
                            </div>
                          
        </div>
    )
}