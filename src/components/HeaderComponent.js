import { Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";

export default function Header(){
    return (
        <div>
           <header>
               <Navbar className="navbar navbar-dark bg-dark mb-4">
                    <nav >
                    <div><a href="/" className="navbar-brand">IBCS-PRIMAX   Assessment</a></div>
                    </nav>

                    <nav className="navbar navbar-dark bg-dark ">
                    <div><Link to="/department"   style={{textDecoration: "none"}}>Department</Link></div>
                    </nav>

                    <div className="p-2">
                    <nav className="navbar navbar-dark bg-dark">
                    <div ><Link to="/employee"  style={{textDecoration: "none"}}>Employee</Link></div>
                    </nav>
                    </div>
                    
                    </Navbar>
                    </header>
        </div>
    );
}