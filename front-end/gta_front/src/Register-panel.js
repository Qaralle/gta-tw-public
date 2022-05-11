import React, { useState, useEffect, useContext, useRef} from 'react';
import ReactDom from 'react-dom';

import Input_data_panel from "./Input-data-panel";
import AccountContext from './storage/account_context';
import tstore from './storage/token_storage';
import {Messages} from "primereact/messages";
import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import "primereact/resources/themes/lara-light-indigo/theme.css"
import "primereact/resources/primereact.min.css"

import Creditionals_message from './Creditionals-message.js'


let MessagesInstance
function Register_panel(props) {
	const credRef = useRef(Creditionals_message);

	const { switchToSignIn } = useContext(AccountContext);
	const { switchToMain } = useContext(AccountContext);

	let [uName,setUName] = useState('');
	let [uPass,setUPass] = useState('');
	let [name,setName] = useState('');
	let [uSName,setUSName] = useState('');
	let [uGang,setUGang] = useState(1);
	let [uAge,setUAge] = useState(0);
	let [isnCorr,setIsnCorr] = useState(false);

	let [gangsOptions,setGangOps] = useState([]);

	let gangs = props.gangs;

	useEffect(() => {
		setGangOps(()=>{
			let vv = [];
			for(var i = 0; i < gangs.length; i++) {
				vv.push(<option value={gangs[i].name}>{gangs[i].name}</option>);
			}
			return [vv]; 
		});
	}, gangs);

	function setGangPar(value){
		setUGang(()=>{
			for(var i = 0; i < gangs.length; i++) {
				if(gangs[i].name == value) {
					return parseInt(gangs[i].id, 10);
				}
			}
		});
	}

	function handleSubmit() {

    	const data = {
			username: uName,
			password: uPass,
			name: name,
			lastname: uSName,
			gang:  uGang,
			age: parseInt(uAge,10)
    	}


		fetch('/api/auth/register', {
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
  		if(uName == '' || uPass == '' || name == '' || uAge == '' || (uGang.name == '' && uGang.id == '')) {
  			return 1;
  		}
  	}

	return (
		<div className="Page-form-panel" id="register-page-form-panel">
			<Messages className="p-messages"	icon ref={(el) => MessagesInstance = el}/>
			<form>
				<div id="form-panel-header">
					<Input_data_panel hstr="User Name" typeT="text" callBack={(e)=> {setUName(e.target.value)}}/>
					<Creditionals_message ref={credRef} duration={200} repeat_count={2}/>
					<Input_data_panel hstr="Password" typeT="password" callBack={(e)=> {setUPass(e.target.value)}}/>
					<Input_data_panel hstr="Name" typeT="text" callBack={(e)=> {setName(e.target.value)}}/>
					<Input_data_panel hstr="Second Name" typeT="text" callBack={(e)=> {setUSName(e.target.value)}}/>
				</div>
				<div className="Register" id="form-panel-hero">
					<div>
						<select id="select" defaultValue="" onChange={(e)=>{setGangPar(e.target.value)}}>
							{gangsOptions}
						</select>
					</div>
					<div id="form-panel-hero-data">
						<lable>Age</lable>
						<input type="text" id="birthday" onChange={(e)=>{setUAge(e.target.value)}}/>
						{isnCorr && <a>*incorrect age value</a>}
					</div>
				</div>
				<div id="form-panel-footer">
					<button className="Button" disabled={dateCheckSet()} type="button" onClick={handleSubmit}>
						Sign Up
					</button>
					<a onClick={switchToSignIn}>Sign In</a>
				</div>
			</form>
		</div>
		);
}

export default Register_panel;