import React, { useState, useEffect} from 'react';
import ReactDom from 'react-dom';

import Register_panel from './Register-panel';


import "./Start-page.css"

function Register() {

	let [gangList,setGangList] = useState([{id: 0, color: '', name: ''}]);

	useEffect(()=> {
		fetch('/api/app/gangs')
			.then((response)=>{
				return response.json();
			})
			.then((response)=>{
				setGangList(response.data.gangs);
			});
	}, []);

	return(
		<div className="Start-page" id="register-page">
			<Register_panel gangs={gangList}/>
		</div>
		);
}

export default Register;