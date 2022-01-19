
import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import CreateDepartmentComponent from './components/departmentComponets/CreateDepartmentComponent';
import ListDepartmentComponent from './components/departmentComponets/ListDepartmentComponent';
import UpdateDepartmentComponent from './components/departmentComponets/UpdateDepartmentComponent';
import ViewDepartmentComponent from './components/departmentComponets/ViewDepartmentComponent';
import CreateEmployeeComponent from './components/employeeComponets/CreateEmployeeComponent';
import ListEmployeeComponent from './components/employeeComponets/ListEmployeeComponent';
import UpdateEmployeeComponent from './components/employeeComponets/UpdateEmployeeComponent';
import ViewEmployeeComponent from './components/employeeComponets/ViewEmployeeComponent';
import HeaderComponent from './components/HeaderComponent';
import Welcome from './components/WelcomeComponet';


function App() {
  return (
    <div className="App">
<HeaderComponent />
<Routes>
  <Route path="/" element={<Navigate to="/dashboard" />} />
  <Route path="/dashboard" element={<Welcome/>} />
  <Route path="/department" element={<ListDepartmentComponent/>} />
  <Route path="/department/create/" element={<CreateDepartmentComponent />} />
  <Route path="/department/edit/:id" element={<UpdateDepartmentComponent />} />
  <Route path="/department/view/:id" element={<ViewDepartmentComponent />} />
  <Route path="/employee" element={<ListEmployeeComponent />} />
  <Route path="/employee/create/" element={<CreateEmployeeComponent />} />
  <Route path="/employee/view/:id" element={<ViewEmployeeComponent />} />
  <Route path="/employee/edit/:id" element={<UpdateEmployeeComponent />} />
</Routes>

    </div>
  );
}

export default React.memo(App);
