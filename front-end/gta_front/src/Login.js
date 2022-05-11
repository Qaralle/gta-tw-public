import React from 'react';
import ReactDOM from 'react-dom';
import Login_panel from './Login-panel';


import './Start-page.css'



function Login() {
	return(
			<div className="Start-page" id="login-page">
				<Login_panel />
			</div>
		);
}

export default Login;