import { useState } from "react";
import { useNavigate } from "react-router-dom";
import DepartmentService from "../../services/DepartmentService";

export default function CreateDepartmentComponent(){

    const [departmentName, setDepartmentName] = useState('');
    const [activeStatus, setActiveStatus] = useState(true);
    const history = useNavigate();

    const submitHandler = (e) => {
        e.preventDefault();
        let department = {departmentName:departmentName,
            active:activeStatus};
        console.log(department);
       
        DepartmentService.createDepartment(department).then(res => {
            history(`/department`);
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
                                    <input type="checkbox" name="checkbox" onClick={(e) => setActiveStatus(e.target.checked) } defaultChecked={activeStatus}  />
                                </div>

                                <button className="btn btn-success" type="submit" value="Submit" >Save</button>
                                <button className="btn btn-danger" onClick={cancelHandler} >Cancel</button>
                            </form>
                            </div>
                            </div>
                          
        </div>
    )
}