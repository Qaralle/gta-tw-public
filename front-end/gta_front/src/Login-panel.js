
import React, { useState, useEffect, useContext, useRef} from 'react';
import ReactDOM from 'react-dom';
import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import "primereact/resources/themes/lara-light-indigo/theme.css"
import "primereact/resources/primereact.min.css"

import Input_data_panel from './Input-data-panel';

import tstore from './storage/token_storage';
import AccountContext from './storage/account_context';
import Creditionals_message from './Creditionals-message.js'


function Login_panel() {
	const credRef = useRef(Creditionals_message);

	const { switchToSignUp } = useContext(AccountContext);
	const { switchToMain } = useContext(AccountContext);

	let [uName,setUName] = useState('');
	let [uPass,setUPass] = useState('');


	function handleSubmit() {

		const data = {
			username: uName,
			password: uPass,
		}

		fetch('/api/auth/login', {
				method: 'POST',
				body: JSON.stringify(data),
				headers: {
	                'Content-Type': 'application/json;charset=utf-8'
	            },
			})
			.then(response => response.text().then(text => {
				let data = JSON.parse(text)
				if (response.ok) {
					tstore.dispatch({type: "NEW_TOKEN", token: data.token, rtoken: data.refresh_token});
					switchToMain();
				}else {
					credRef.current.show(data.description);
				}
			}))

  	}

  	function dateCheckSet() {
  		if(uName == '' || uPass == '') {
  			return 1;
  		}
  	}


	return(
		<div className="Page-form-panel" id="login-page-form-panel">
			<form>
				<div id="form-panel-header">
					<Input_data_panel hstr="User Name" typeT="text" callBack={(e)=> {setUName(e.target.value)}}/>
					<Creditionals_message ref={credRef} duration={200} repeat_count={2}/>
					<Input_data_panel hstr="Password" typeT="password" callBack={(e)=> {setUPass(e.target.value)}}/>
				</div>
				<div className="Login" id="form-panel-hero">
					<button className="Button" disabled={dateCheckSet()} type="button" onClick={handleSubmit}>
						Sign In 
					</button>
					<a onClick={switchToSignUp}>Sign Up</a>
				</div>
			</form>
		</div>
		);
}

export default Login_panel;